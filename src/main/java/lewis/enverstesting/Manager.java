package lewis.enverstesting;

import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class Manager {

    private final StudentRepository studentRepository;
    private final LaptopRepository laptopRepository;
    private final EnrolmentRepository enrolmentRepository;
    public Manager(EnrolmentRepository enrolmentRepository, StudentRepository studentRepository, LaptopRepository laptopRepository) {
        this.enrolmentRepository = enrolmentRepository;
        this.studentRepository = studentRepository;
        this.laptopRepository = laptopRepository;
    }


    public boolean newEnrolment(
            String name,
            int age,
            boolean isSmart,
            String model,
            String brand
    ){
        Student student = new Student();
        student.setStudentName(name);
        student.setAge(age);
        student.setSmart(isSmart);

        Laptop laptop = new Laptop();
        laptop.setModel(model);
        laptop.setBrand(brand);

        student.addLaptop(laptop);
        laptop.setStudent(student);

        Enrolment enrolment = new Enrolment();
        enrolment.setEnrolled(true);
        enrolment.setEnrolmentDate(Instant.now());
        enrolment.setStudent(student);

        studentRepository.save(student);
        laptopRepository.save(laptop);
        enrolmentRepository.save(enrolment);
        return true;
    }

    public void setStudentIsSmart(String bob, boolean b) {
        Student student = studentRepository.findByStudentName(bob);
        student.setSmart(b);
        studentRepository.save(student);
    }

    public void setStudentIsEnrolled(String bob, boolean b) {
        Student student = studentRepository.findByStudentName(bob);
        Enrolment enrolment = enrolmentRepository.findByStudent(student);
        enrolment.setEnrolled(b);
        enrolmentRepository.save(enrolment);
    }
}
