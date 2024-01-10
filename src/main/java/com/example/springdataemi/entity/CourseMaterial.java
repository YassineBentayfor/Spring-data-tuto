package com.example.springdataemi.entity;

import jakarta.persistence.*;

import java.util.Objects;


@Entity
public class CourseMaterial {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long courseMaterialId;
    private String url;

    @OneToOne
    @JoinColumn(
            name = "course_id",
            referencedColumnName = "courseId"
    )
    private Course course;


    public CourseMaterial(String url, Course course) {
        this.url = url;
        this.course = course;
    }

    public CourseMaterial() {
    }




    public Long getCourseMaterialId() {
        return courseMaterialId;
    }

    public void setCourseMaterialId(Long courseMaterialId) {
        this.courseMaterialId = courseMaterialId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CourseMaterial that = (CourseMaterial) o;
        return Objects.equals(courseMaterialId, that.courseMaterialId) && Objects.equals(url, that.url) && Objects.equals(course, that.course);
    }

    @Override
    public int hashCode() {
        return Objects.hash(courseMaterialId, url, course);
    }

    @Override
    public String toString() {
        return "CourseMaterial{" +
                "courseMaterialId=" + courseMaterialId +
                ", url='" + url + '\'' +
                '}';
    }
}
