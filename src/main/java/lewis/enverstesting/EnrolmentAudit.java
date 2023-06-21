package lewis.enverstesting;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.Instant;

@Entity
@Table(name = "enrolment_aud")
public class EnrolmentAudit {

    @Id
    @Column(name = "enrolment_id")
    private Integer enrolmentId;

    @Column(name = "student_student_id")
    private Integer studentStudentId;

    @Column(name = "is_enrolled")
    private boolean isEnrolled;

    @Column(name = "enrolment_date")
    private Instant enrolmentDate;

    @Column(name = "rev")
    private Integer rev;

    @Column(name = "revtype")
    private Integer revType;

    public Integer getEnrolmentId() {
        return enrolmentId;
    }

    public void setEnrolmentId(Integer enrolmentId) {
        this.enrolmentId = enrolmentId;
    }

    public Integer getStudentStudentId() {
        return studentStudentId;
    }

    public void setStudentStudentId(Integer studentStudentId) {
        this.studentStudentId = studentStudentId;
    }

    public Integer getRev() {
        return rev;
    }

    public void setRev(Integer rev) {
        this.rev = rev;
    }

    public Integer getRevType() {
        return revType;
    }

    public void setRevType(Integer revType) {
        this.revType = revType;
    }

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
}