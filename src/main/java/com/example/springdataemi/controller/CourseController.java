package com.example.springdataemi.controller;

import com.example.springdataemi.entity.Course;
import com.example.springdataemi.entity.Teacher;
import com.example.springdataemi.service.CourseService;
import com.example.springdataemi.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/courses")
public class CourseController {

    private final CourseService courseService;


    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping
    public List<Course> getAllCourses() {
        return courseService.getAllCourses();
    }

    @GetMapping("/{id}")
    public Course getCourseById(@PathVariable("id") Long courseId) {
        return courseService.getCourseById(courseId);
    }

    @PostMapping
    public Course createCourse(@RequestBody Course newCourse) {
        return courseService.createCourse(newCourse);
    }

    @PutMapping("/{id}")
    public Course updateCourse(@PathVariable("id") Long courseId, @RequestBody Course updatedCourse) {
        return courseService.updateCourse(courseId, updatedCourse);
    }



    @PatchMapping("/{id}")
    public Course partiallyUpdateCourse(@PathVariable("id") Long courseId, @RequestBody Map<String, Object> updates) {
        return courseService.partiallyUpdateCourse(courseId, updates);
    }

    @DeleteMapping("/{id}")
    public void deleteCourse(@PathVariable("id") Long courseId) {
        courseService.deleteCourse(courseId);
    }
}
