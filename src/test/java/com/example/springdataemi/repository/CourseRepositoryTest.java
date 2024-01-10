package com.example.springdataemi.repository;

import com.example.springdataemi.entity.Course;
import com.example.springdataemi.entity.Student;
import com.example.springdataemi.entity.Teacher;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class CourseRepositoryTest {
    @Autowired
    CourseRepository courseRepository;


    @Test
    public void printCourses() {
        List<Course> courses = courseRepository.findAll();
        System.out.println("courses = " + courses);
    }

    @Test
    public void saveCourseWithTeacher() {
        Teacher teacher = new Teacher("Yassine","Bentayfor");
        Course course = new Course("Python", 6, teacher);


        courseRepository.save(course);
    }


}