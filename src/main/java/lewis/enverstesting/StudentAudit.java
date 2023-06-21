package lewis.enverstesting;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "student_aud")
public class StudentAudit {

    @Id
    @Column(name = "student_id")
    private Integer studentId;

    @Column(name = "rev")
    private Integer rev;

    @Column(name = "revtype")
    private Integer revType;

    @Column(name = "student_name")
    private String studentName;

    @Column(name = "is_smart")
    private boolean isSmart;

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
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

    public String getStudentName() {
        return studentName;
    }

    public boolean isSmart() {
        return isSmart;
    }

    public void setSmart(boolean smart) {
        isSmart = smart;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }
}