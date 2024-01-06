package com.example.springdataemi.service;

import com.example.springdataemi.entity.Course;
import com.example.springdataemi.entity.CourseMaterial;
import com.example.springdataemi.repository.CourseMaterialRepository;
import com.example.springdataemi.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseMaterialService {

    private final CourseMaterialRepository courseMaterialRepository;
    private final CourseRepository courseRepository;

    @Autowired
    public CourseMaterialService(CourseMaterialRepository courseMaterialRepository, CourseRepository courseRepository) {
        this.courseMaterialRepository = courseMaterialRepository;
        this.courseRepository = courseRepository;
    }

    public List<CourseMaterial> getAllCourseMaterials() {
        return courseMaterialRepository.findAll();
    }

    public CourseMaterial getCourseMaterialById(Long courseMaterialId) {
        return courseMaterialRepository.findById(courseMaterialId)
                .orElseThrow(() -> new IllegalArgumentException("Course Material not found"));
    }

    public CourseMaterial createCourseMaterial(CourseMaterial newCourseMaterial) {
        return courseMaterialRepository.save(newCourseMaterial);
    }

    public CourseMaterial updateCourseMaterial(Long courseMaterialId, CourseMaterial updatedCourseMaterial) {
        if (courseMaterialRepository.existsById(courseMaterialId)) {
            updatedCourseMaterial.setCourseMaterialId(courseMaterialId);
            return courseMaterialRepository.save(updatedCourseMaterial);
        }
        throw new IllegalArgumentException("Course Material not found");
    }

    public void deleteCourseMaterial(Long courseMaterialId) {
        if (courseMaterialRepository.existsById(courseMaterialId)) {
            courseMaterialRepository.deleteById(courseMaterialId);
        } else {
            throw new IllegalArgumentException("Course Material not found");
        }
    }

    public CourseMaterial assignCourseToCourseMaterial(Long courseMaterialId, Long courseId) {
        Optional<CourseMaterial> optionalCourseMaterial = courseMaterialRepository.findById(courseMaterialId);
        Optional<Course> optionalCourse = courseRepository.findById(courseId);

        if (optionalCourseMaterial.isPresent() && optionalCourse.isPresent()) {
            CourseMaterial courseMaterial = optionalCourseMaterial.get();
            Course course = optionalCourse.get();

            courseMaterial.setCourse(course);
            return courseMaterialRepository.save(courseMaterial);
        } else {
            throw new IllegalArgumentException("Course or Course Material not found");
        }
    }
}
