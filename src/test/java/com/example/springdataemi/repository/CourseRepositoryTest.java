package com.example.springdataemi.repository;

import com.example.springdataemi.entity.Course;
import com.example.springdataemi.entity.Student;
import com.example.springdataemi.entity.Teacher;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;



@SpringBootTest
class CourseRepositoryTest {
    CourseRepository courseRepository;
    public CourseRepositoryTest(CourseRepository courseRepository) {
        this.courseRepository =courseRepository;
    }

    @Test
    public void printCourses() {
        List<Course> courses =
                courseRepository.findAll();

        System.out.println("courses = " + courses);
    }

    @Test
    public void saveCourseWithTeacher() {
        Teacher teacher = Teacher.builder()
                .firstName("Yassine")
                .lastName("Bentayfor")
                .build();

        Course course = Course
                .builder()
                .title("Python")
                .credit(6)
                .teacher(teacher)
                .build();

        courseRepository.save(course);
    }

    @Test
    public void saveCourseWithStudentAndTeacher() {

        Teacher teacher = Teacher.builder()
                .firstName("Lizze")
                .lastName("Morgan")
                .build();

        Student student = Student.builder()
                .firstName("Abhishek")
                .lastName("Singh")
                .emailId("abhishek@gmail.com")
                .build();

        Course course = Course
                .builder()
                .title("AI")
                .credit(12)
                .teacher(teacher)
                .build();

        course.addStudents(student);

        courseRepository.save(course);
    }

}