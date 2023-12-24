package com.example.springdataemi.repository;

import com.example.springdataemi.entity.CourseMaterial;
import com.example.springdataemi.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseMaterialRepository extends JpaRepository<CourseMaterial, Long> {
}
