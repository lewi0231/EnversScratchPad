package lewis.enverstesting;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Tuple;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.hibernate.envers.DefaultRevisionEntity;
import org.hibernate.envers.RevisionType;
import org.hibernate.envers.query.AuditEntity;
import org.hibernate.envers.query.AuditQuery;
import org.hibernate.envers.query.criteria.AuditCriterion;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.history.Revision;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Instant;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ManagerTest {
    @Autowired
    private Manager manager;

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Test
    void newEnrolment() {
        manager.newEnrolment("John", 20, true, "macbook", "apple");
        manager.newEnrolment("Fred", 33, true, "macbook", "apple");
        manager.newEnrolment("Graham", 44, false, "dodge", "lenovo");
        manager.newEnrolment("Joseph", 55, false, "7060", "dell");

        manager.setStudentIsSmart("John", false);
        manager.setStudentIsSmart("Joseph", true);

        manager.setStudentIsEnrolled("Fred", false);
        manager.setStudentIsEnrolled("Graham", false);
    }

    @Test
    void query(){
        try (EntityManager entityManager = entityManagerFactory.createEntityManager()){
            entityManager.getTransaction().begin();

            AuditReader reader = AuditReaderFactory.get(entityManager);

            Instant start = Instant.parse("2021-01-01T00:00:00.00Z");
            Instant end = Instant.now();


            AuditQuery query = reader.createQuery()
                    .forRevisionsOfEntity(Student.class, false, true)
                    .addOrder(AuditEntity.revisionNumber().asc())
                    .add(AuditEntity.relatedId("student").eq(AuditEntity.id()));

            List<Object[]> revisions = query.getResultList();

            System.out.println("revision information\n");
            for (Object[] revision : revisions) {
                Enrolment enrolment = (Enrolment) revision[0];
                DefaultRevisionEntity revisionEntity = (DefaultRevisionEntity) revision[1];
                RevisionType revisionType = (RevisionType) revision[2];

                // Optionally, get the associated enrollment for the same revision
                Student student = reader.find(Student.class, enrolment.getStudent().getStudentId(), revisionEntity.getId());

                // You can now use the student, revisionEntity and revisionType
                System.out.println("enrolment: " + enrolment);
                System.out.println("Revision: " + revisionEntity.getId());
                System.out.println("Revision Type: " + revisionType);
                System.out.println("Student: " + student);
            }


        }

    }

    @Test
    void joinAndSaveToCSV() {
        try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
            String sql = "SELECT r.revision, COALESCE(s.student_id, e.student_student_id) as student_id, s.student_name, s.is_smart, e.enrolment_id, e.enrolment_date, COALESCE(e.revtype, s.revtype) as revType, r.timestamp " +
                        "FROM enrolment_audit e " +
                        "FULL OUTER JOIN student_audit s ON s.student_id = e.student_student_id AND s.rev = e.rev " +
                        "LEFT JOIN revisions r ON COALESCE(e.rev, s.rev) = r.revision " +
                        "ORDER BY COALESCE(e.rev, s.rev);";

            List<Object[]> result = entityManager.createNativeQuery(sql).getResultList();

            try (BufferedWriter writer = new BufferedWriter(new FileWriter("output.csv"))){
                writer.write("revision,id,name,is_smart,enrol_id,enrol_date,rev_type,timestamp\n");
                for (Object[] row : result) {
                    int rev = (int) row[0];
                    int studentId = (int) row[1];
                    String studentName = (String) row[2];
                    Boolean isSmart = (Boolean) row[3];
                    int enrolmentId = row[4] == null ? -1 : (int) row[4];
                    Instant enrolmentDate = row[5] == null ? null : (Instant) row[5];
                    short revType = (short) row[6];
                    Instant revtstmp = Instant.ofEpochMilli((Long) row[7]);

                    writer.write(rev + "," + studentId
                            + "," + studentName + "," + isSmart + ","
                            + enrolmentId + "," + enrolmentDate + ","
                            + revType + "," + revtstmp);
                    writer.newLine();

                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }



}