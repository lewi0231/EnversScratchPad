package lewis.enverstesting;

import jakarta.persistence.*;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;

import java.time.Instant;

@Entity
@Audited
@AuditTable("enrolment_audit")
public class Enrolment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int enrolmentId;
    private boolean isEnrolled;

    private Instant enrolmentDate;

    @OneToOne
    private Student student;

    public boolean isEnrolled() {
        return isEnrolled;
    }

    public void setEnrolled(boolean enrolled) {
        isEnrolled = enrolled;
    }

    public Instant getEnrolmentDate() {
        return enrolmentDate;
    }

    public void setEnrolmentDate(Instant enrolmentDate) {
        this.enrolmentDate = enrolmentDate;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    @Override
    public String toString() {
        return "Enrolment{" +
                "enrolmentId=" + enrolmentId +
                ", isEnrolled=" + isEnrolled +
                ", enrolmentDate=" + enrolmentDate +
                '}';
    }
}
