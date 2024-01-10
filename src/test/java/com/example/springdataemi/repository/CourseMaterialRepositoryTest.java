package com.example.springdataemi.repository;

import com.example.springdataemi.entity.Course;
import com.example.springdataemi.entity.CourseMaterial;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class CourseMaterialRepositoryTest {
    @Autowired
    CourseMaterialRepository courseMaterialRepository;
    @Autowired
    CourseRepository courseRepository;



    @Test
    public void SaveCourseMaterial() {
        Course course = new Course(".net", 6);
        CourseMaterial courseMaterial = new CourseMaterial("www.google.com", course);
        courseRepository.save(course);
        courseMaterialRepository.save(courseMaterial);
    }

    @Test
    public void printAllCourseMaterials() {
        List<CourseMaterial> courseMaterials =
                courseMaterialRepository.findAll();

        System.out.println("courseMaterials = " + courseMaterials);

    }

}