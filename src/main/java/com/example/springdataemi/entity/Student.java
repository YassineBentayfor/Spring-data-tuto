package com.example.springdataemi.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;


@Entity
@Table(
        name = "student_db",
        uniqueConstraints = @UniqueConstraint(
                name = "emailUnique",
                columnNames = "email_adress"
        )
)
public class Student {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long studentId;
    private String firstName;
    private String lastName;
    @Column(name = "email_adress")
    private String emailId;

    @Embedded
    private Guardian guardian;
    @Transient
    private int age;




    public Student(String firstName, String lastName, String emailId, Guardian guardian, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailId = emailId;
        this.guardian = guardian;
        this.age = age;
    }

    public Student(String firstName, String lastName, String emailId, Guardian guardian) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailId = emailId;
        this.guardian = guardian;
    }

    public Student() {
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public void setGuardian(Guardian guardian) {
        this.guardian = guardian;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Long getStudentId() {
        return studentId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmailId() {
        return emailId;
    }

    public Guardian getGuardian() {
        return guardian;
    }

    public int getAge() {
        return age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return age == student.age && Objects.equals(studentId, student.studentId) && Objects.equals(firstName, student.firstName) && Objects.equals(lastName, student.lastName) && Objects.equals(emailId, student.emailId) && Objects.equals(guardian, student.guardian);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentId, firstName, lastName, emailId, guardian, age);
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentId=" + studentId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", emailId='" + emailId + '\'' +
                ", guardian=" + guardian +
                ", age=" + age +
                '}';
    }
}
