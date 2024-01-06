package com.example.springdataemi.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long courseId;
    private String title;
    private Integer credit;


    @OneToOne(
            mappedBy = "course"
    )
    @JsonIgnore
    private CourseMaterial courseMaterial;



    @ManyToOne
    @JoinColumn(
            name = "teacher_id",
            referencedColumnName = "teacherId"
    )
    @JsonBackReference
    //@JsonIgnore
    private Teacher teacher;





    @ManyToMany//(cascade = CascadeType.ALL)
    @JoinTable(
            name = "studen_course_map",
            joinColumns = @JoinColumn(
                    name = "course_id",
                    referencedColumnName = "courseId"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "student_id",
                    referencedColumnName = "studentId"
            )
    )
    private List<Student> students;





 /*   @ElementCollection
    private List<String> yassine;
*/

    public void addStudents(Student student){
        if(students == null) students = new ArrayList<>();
        students.add(student);
    }


}