package com.example.springdataemi.service;

import com.example.springdataemi.entity.Course;

import com.example.springdataemi.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class CourseService {
    private final CourseRepository courseRepository;


    @Autowired
    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }



    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public Course getCourseById(Long courseId) {
        Optional<Course> optionalCourse = courseRepository.findById(courseId);
        return optionalCourse.orElse(null);
    }



    public Course createCourse(Course newCourse) {
        return courseRepository.save(newCourse);
    }

    public Course updateCourse(Long courseId, Course updatedCourse) {
        //ICI la methode save ecrase l'enregistrement et ne fait pas un update
        // Je n'ai pas trouvé le saveOrUpdate dans Jpa
        updatedCourse.setCourseId(courseId);
        return courseRepository.save(updatedCourse);
    }

    public Course partiallyUpdateCourse(Long courseId, Map<String, Object> updates) {
        Course existingCourse = getCourseById(courseId);
        if (updates.containsKey("title")) {
            existingCourse.setTitle((String) updates.get("title"));
        }
        if (updates.containsKey("credit")) {
            existingCourse.setCredit((Integer) updates.get("credit"));
        }
        return courseRepository.save(existingCourse);
    }

    public void deleteCourse(Long courseId) {
        courseRepository.deleteById(courseId);
    }


}
