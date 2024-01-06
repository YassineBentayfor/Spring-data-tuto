package com.example.springdataemi.repository;

import com.example.springdataemi.entity.Guardian;
import com.example.springdataemi.entity.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;


@SpringBootTest
class StudentRepositoryTest {
    @Autowired
    StudentRepository studentRepository;

    @Test
    public void saveStudent(){
        Student student = Student.builder()
                .firstName("Yassine")
                .lastName("Bentayfor")
                .emailId("yassine@gmail.com")
                /*.guardian_name("Father")
                .guardian_email("father@gmail.com")
                .guardian_mobile("1234567890")*/
                .build();

        studentRepository.save(student);

    }

    @Test
    public void saveStudentWithGuardian(){
        Guardian guardian = Guardian.builder()
                .name("Father")
                .email("father@gmail.com")
                .mobile("1234567890")
                .build();
        Student student = Student.builder()
                .firstName("Mohamed")
                .lastName("Ayman")
                .emailId("school@gmail.com")
                .guardian(guardian)
                .build();

        studentRepository.save(student);

    }



    @Test
    public void printAllStudent() {
        List<Student> studentList =
                studentRepository.findAll();

        System.out.println("studentList = " + studentList);
    }

    @Test
    public void printStudentByFirstName() {

        List<Student> students =
                studentRepository.findByFirstName("yassine");

        System.out.println("students = " + students);
    }

    @Test
    public void printStudentByFirstNameContaining() {

        List<Student> students =
                studentRepository.findByFirstNameContaining("ya");

        System.out.println("students = " + students);
    }

    @Test
    public void printStudentBasedOnGuardianName(){
        List<Student> students =
                studentRepository.findByGuardianName("Father");
        System.out.println("students = " + students);
    }

    @Test
    public void printgetStudentByEmailAddress() {
        Student student =
                studentRepository.getStudentByEmailAddress(
                        "yassine@gmail.com"
                );

        System.out.println("student = " + student);
    }

    @Test
    public void printgetStudentFirstNameByEmailAddress() {
        String firstName =
                studentRepository.getStudentFirstNameByEmailAddress(
                        "yassine@gmail.com"
                );
        System.out.println("firstName = " + firstName);
    }

    @Test
    public void printgetStudentByEmailAddressNative(){
        Student student =
                studentRepository.getStudentByEmailAddressNative(
                        "yassine@gmail.com"
                );

        System.out.println("student = " + student);
    }

    @Test
    public void printgetStudentByEmailAddressNativeNamedParam() {
        Student student =
                studentRepository.getStudentByEmailAddressNativeNamedParam(
                        "yassine@gmail.com"
                );

        System.out.println("student = " + student);
    }

    @Test
    public void updateStudentNameByEmailIdTest() {
        studentRepository.updateStudentNameByEmailId(
                "Yassine Bentayfor",
                "Yassine@gmail.com");
    }

}