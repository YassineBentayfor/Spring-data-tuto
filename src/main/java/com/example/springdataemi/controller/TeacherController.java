package com.example.springdataemi.controller;

import com.example.springdataemi.entity.Course;
import com.example.springdataemi.entity.Teacher;
import com.example.springdataemi.service.CourseService;
import com.example.springdataemi.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/teachers")
public class TeacherController {

    private final TeacherService teacherService;
    private final CourseService courseService;

    @Autowired
    public TeacherController(TeacherService teacherService, CourseService courseService) {
        this.teacherService = teacherService;
        this.courseService = courseService;
    }

    @GetMapping
    //@RequestMapping(method = RequestMethod.GET)
    public List<Teacher> getAllTeachers() {
        return teacherService.getAllTeachers();
    }

    @GetMapping("/{id}")
    //@RequestMapping(path = "/{id}",method = RequestMethod.GET)
    public Teacher getTeacherById(@PathVariable("id") Long teacherId) {
        return teacherService.getTeacherById(teacherId);
    }

    @PostMapping
    public Teacher createTeacher(@RequestBody Teacher newTeacher) {
        return teacherService.createTeacher(newTeacher);
    }

   /* @PostMapping("/{teacherId}/courses")
    public Teacher assignCourseToTeacher(@PathVariable("teacherId") Long teacherId, @RequestBody Course newCourse) {
        Course savedCourse = courseService.createCourse(newCourse);
        return teacherService.assignCourseToTeacher(teacherId, savedCourse);
    }*/

    @PostMapping("/{teacherId}/courses/{courseId}")
    public ResponseEntity<String> assignCourseToTeacher(
            @PathVariable("teacherId") Long teacherId,
            @PathVariable("courseId") Long courseId) {

        Teacher teacher = teacherService.getTeacherById(teacherId);
        Course course = courseService.getCourseById(courseId);

        if (teacher != null && course != null) {
            teacherService.assignCourseToTeacher(teacherId, course);
            return ResponseEntity.ok("Course assigned to teacher successfully");
        } else {
            return ResponseEntity.notFound().build();
        }
    }




    @PutMapping("/{id}")
    public Teacher updateTeacher(@PathVariable("id") Long teacherId, @RequestBody Teacher updatedTeacher) {
        return teacherService.updateTeacher(teacherId, updatedTeacher);
    }


    @DeleteMapping("/{id}")
    public void deleteTeacher(@PathVariable("id") Long teacherId) {
        teacherService.deleteTeacher(teacherId);
    }
}
