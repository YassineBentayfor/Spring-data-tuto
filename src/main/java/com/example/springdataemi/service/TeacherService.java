package com.example.springdataemi.service;

import com.example.springdataemi.entity.Course;
import com.example.springdataemi.entity.Teacher;
import com.example.springdataemi.repository.TeacherRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TeacherService {

    private final TeacherRepository teacherRepository;

    @Autowired
    public TeacherService(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    public List<Teacher> getAllTeachers() {
        return teacherRepository.findAll();
    }

    public Teacher getTeacherById(Long teacherId) {
        return teacherRepository.findById(teacherId).get();
    }
    public Teacher getTeacherByIsd(Long teacherId) {
        Optional<Teacher> optionalTeacher = teacherRepository.findById(teacherId);
        return optionalTeacher.orElse(null);
    }

    public Teacher createTeacher(Teacher newTeacher) {
        return teacherRepository.save(newTeacher);
    }

    public Teacher updateTeacher(Long teacherId, Teacher updatedTeacher) {
        Teacher existingTeacher = getTeacherById(teacherId);
        if (existingTeacher != null) {
            existingTeacher.setFirstName(updatedTeacher.getFirstName());
            existingTeacher.setLastName(updatedTeacher.getLastName());
            return teacherRepository.save(existingTeacher);
        } else {
            throw new NoSuchElementException("Teacher not found with id: " + teacherId);
        }
    }

    public Teacher assignCourseToTeacher(Long teacherId, Course course) {
        Optional<Teacher> optionalTeacher = teacherRepository.findById(teacherId);

        if (optionalTeacher.isPresent()) {
            Teacher teacher = optionalTeacher.get();
            List<Course> teacherCourses = teacher.getCourses();
            if (teacherCourses == null) {
                teacherCourses = new ArrayList<>();
            }
            teacherCourses.add(course);
            course.setTeacher(teacher);
            teacher.setCourses(teacherCourses);
            return teacherRepository.save(teacher);
        } else {
            throw new EntityNotFoundException("Teacher not found with ID: " + teacherId);
        }
    }



    public void deleteTeacher(Long teacherId) {
        if (teacherRepository.existsById(teacherId)) {
            teacherRepository.deleteById(teacherId);
        } else {
            throw new NoSuchElementException("Teacher not found with id: " + teacherId);
        }
    }

}
