package com.example.springdataemi.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;




import java.util.List;
import java.util.Objects;

@Entity
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long courseId;
    private String title;
    private Integer credit;


    @OneToOne(mappedBy = "course")
    @JsonIgnore
    private CourseMaterial courseMaterial;



    @ManyToOne
    @JoinColumn(
            name = "teacher_id",
            referencedColumnName = "teacherId"
    )
    @JsonIgnore
    private Teacher teacher;





    @ManyToMany
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


    public Course() {
    }

    public Course(String title, Integer credit) {
        this.title = title;
        this.credit = credit;
    }

    public Course(String title, Integer credit, CourseMaterial courseMaterial) {
        this.title = title;
        this.credit = credit;
        this.courseMaterial = courseMaterial;
    }

    public Course(String title, Integer credit, CourseMaterial courseMaterial, Teacher teacher, List<Student> students) {
        this.title = title;
        this.credit = credit;
        this.courseMaterial = courseMaterial;
        this.teacher = teacher;
        this.students = students;
    }

    public Course(String title, Integer credit, Teacher teacher) {
        this.title = title;
        this.credit = credit;
        this.teacher = teacher;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getCredit() {
        return credit;
    }

    public void setCredit(Integer credit) {
        this.credit = credit;
    }

    public CourseMaterial getCourseMaterial() {
        return courseMaterial;
    }

    public void setCourseMaterial(CourseMaterial courseMaterial) {
        this.courseMaterial = courseMaterial;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return Objects.equals(courseId, course.courseId) && Objects.equals(title, course.title) && Objects.equals(credit, course.credit) && Objects.equals(courseMaterial, course.courseMaterial) && Objects.equals(teacher, course.teacher) && Objects.equals(students, course.students);
    }

    @Override
    public int hashCode() {
        return Objects.hash(courseId, title, credit, courseMaterial, teacher, students);
    }

    @Override
    public String toString() {
        return "Course{" +
                "courseId=" + courseId +
                ", title='" + title + '\'' +
                ", credit=" + credit +
                ", courseMaterial=" + courseMaterial +
                //", teacher=" + teacher +
                //", students=" + students +
                '}';
    }
}