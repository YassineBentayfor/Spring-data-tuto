package com.example.springdataemi.service;

import com.example.springdataemi.entity.Student;
import com.example.springdataemi.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.springdataemi.entity.Course;
import com.example.springdataemi.repository.CourseRepository;


import java.util.List;
import java.util.Optional;



@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository, CourseRepository courseRepository) {
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Student getStudentById(Long studentId) {
        return studentRepository.findById(studentId)
                .orElseThrow(() -> new IllegalArgumentException("Student not found"));
    }

    public Student createStudent(Student newStudent) {
        return studentRepository.save(newStudent);
    }

    public Student updateStudent(Long studentId, Student updatedStudent) {
        if (studentRepository.existsById(studentId)) {
            updatedStudent.setStudentId(studentId);
            return studentRepository.save(updatedStudent);
        }
        throw new IllegalArgumentException("Student not found");
    }

    public Student partiallyUpdateStudent(Long studentId, Student updatedStudent) {
        return updateStudent(studentId, updatedStudent);
    }

    public void deleteStudent(Long studentId) {
        if (studentRepository.existsById(studentId)) {
            studentRepository.deleteById(studentId);
        } else {
            throw new IllegalArgumentException("Student not found");
        }
    }

    public void assignStudentToCourse(Long studentId, Long courseId) {
        Optional<Student> optionalStudent = studentRepository.findById(studentId);
        Optional<Course> optionalCourse = courseRepository.findById(courseId);

        if (optionalStudent.isPresent() && optionalCourse.isPresent()) {
            Student student = optionalStudent.get();
            Course course = optionalCourse.get();

            List<Student> studentsInCourse = course.getStudents();
//           if (!studentsInCourse.contains(student)) {
                studentsInCourse.add(student);
                course.setStudents(studentsInCourse);
                courseRepository.save(course);
/*            } else {

              throw new IllegalStateException("Student is already assigned to this course");
            }*/

        } else {
            throw new IllegalArgumentException("Student or Course not found");
        }
    }

    public List<Student> findByNom(String nom) {
        return this.studentRepository.findByFirstName(nom);
    }
}