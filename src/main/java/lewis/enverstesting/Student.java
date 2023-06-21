package lewis.enverstesting;

import jakarta.persistence.*;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;

import java.util.ArrayList;
import java.util.List;

@Entity
@Audited
@AuditTable("student_audit")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long studentId;

    private String studentName;

    private boolean isSmart;

    @NotAudited
    private int age;

    @OneToMany(mappedBy = "student")
    private List<Laptop> laptops = new ArrayList<>();

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public boolean isSmart() {
        return isSmart;
    }

    public void setSmart(boolean smart) {
        isSmart = smart;
    }

    public List<Laptop> getLaptop() {
        return laptops;
    }

    public void addLaptop(Laptop laptop) {
        this.laptops.add(laptop);
    }

    public int getAge() {
    	return age;
    }

    public void setAge(int age) {
    	this.age = age;
    }

    public long getStudentId() {
        return studentId;
    }

    public void setStudentId(long studentId) {
        this.studentId = studentId;
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentId=" + studentId +
                ", studentName='" + studentName + '\'' +
                ", isSmart=" + isSmart +
                ", age=" + age +
                ", laptop=" + laptops +
                '}';
    }
}
