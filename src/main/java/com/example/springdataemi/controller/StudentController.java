package com.example.springdataemi.controller;

import com.example.springdataemi.entity.Course;
import com.example.springdataemi.entity.Student;
import com.example.springdataemi.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    @GetMapping("/{id}")
    public Student getStudentById(@PathVariable("id") Long studentId) {
        return studentService.getStudentById(studentId);
    }

    @GetMapping("/search")
    public List<Student> student(@RequestParam String nom){
        return studentService.findByNom(nom);
    }

    @PostMapping
    public Student createStudent(@RequestBody Student newStudent) {
        return studentService.createStudent(newStudent);
    }

    @PutMapping("/{id}")
    public Student updateStudent(@PathVariable("id") Long studentId, @RequestBody Student updatedStudent) {
        return studentService.updateStudent(studentId, updatedStudent);
    }

    @PatchMapping("/{id}")
    public Student partiallyUpdateStudent(@PathVariable("id") Long studentId, @RequestBody Student updatedStudent) {
        return studentService.partiallyUpdateStudent(studentId, updatedStudent);
    }

    @DeleteMapping("/{id}")
    public void deleteStudent(@PathVariable("id") Long studentId) {
        studentService.deleteStudent(studentId);
    }

    @PostMapping("/{studentId}/courses/{courseId}")
    public void assignStudentToCourse(@PathVariable("studentId") Long studentId, @PathVariable("courseId") Long courseId) {
        studentService.assignStudentToCourse(studentId, courseId);
    }
}
