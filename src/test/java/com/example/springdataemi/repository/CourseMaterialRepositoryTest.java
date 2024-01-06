package com.example.springdataemi.repository;

import com.example.springdataemi.entity.Course;
import com.example.springdataemi.entity.CourseMaterial;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;


@SpringBootTest
class CourseMaterialRepositoryTest {
    @Autowired
    private CourseMaterialRepository CourseMaterialrepository;
    @Autowired
    private CourseRepository courseRepository;

    @Test
    public void SaveCourseMaterial() {
        Course course =
                Course.builder()
                        .title(".net")
                        .credit(6)
                        .build();

        CourseMaterial courseMaterial =
                CourseMaterial.builder()
                        .url("www.google.com")
                        .course(course)
                        .build();

        courseRepository.save(course);
        CourseMaterialrepository.save(courseMaterial);
    }

    @Test
    public void printAllCourseMaterials() {
        List<CourseMaterial> courseMaterials =
                CourseMaterialrepository.findAll();

        System.out.println("courseMaterials = " + courseMaterials);

    }
}