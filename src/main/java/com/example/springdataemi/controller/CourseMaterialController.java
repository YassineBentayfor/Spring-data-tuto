package com.example.springdataemi.controller;

import com.example.springdataemi.entity.Course;
import com.example.springdataemi.entity.CourseMaterial;
import com.example.springdataemi.service.CourseMaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/course-materials")
public class CourseMaterialController {

    private final CourseMaterialService courseMaterialService;

    @Autowired
    public CourseMaterialController(CourseMaterialService courseMaterialService) {
        this.courseMaterialService = courseMaterialService;
    }

    @GetMapping
    public List<CourseMaterial> getAllCourseMaterials() {
        return courseMaterialService.getAllCourseMaterials();
    }

    @GetMapping("/{id}")
    public CourseMaterial getCourseMaterialById(@PathVariable("id") Long courseMaterialId) {
        return courseMaterialService.getCourseMaterialById(courseMaterialId);
    }

    @PostMapping
    public CourseMaterial createCourseMaterial(@RequestBody CourseMaterial newCourseMaterial) {
        return courseMaterialService.createCourseMaterial(newCourseMaterial);
    }

    @PutMapping("/{id}")
    public CourseMaterial updateCourseMaterial(
            @PathVariable("id") Long courseMaterialId,
            @RequestBody CourseMaterial updatedCourseMaterial) {
        return courseMaterialService.updateCourseMaterial(courseMaterialId, updatedCourseMaterial);
    }

    @DeleteMapping("/{id}")
    public void deleteCourseMaterial(@PathVariable("id") Long courseMaterialId) {
        courseMaterialService.deleteCourseMaterial(courseMaterialId);
    }

    @PostMapping("/{courseMaterialId}/courses/{courseId}")
    public CourseMaterial assignCourseToCourseMaterial(
            @PathVariable("courseMaterialId") Long courseMaterialId,
            @PathVariable("courseId") Long courseId) {
        return courseMaterialService.assignCourseToCourseMaterial(courseMaterialId, courseId);
    }


}
