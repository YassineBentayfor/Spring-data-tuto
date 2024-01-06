package com.example.springdataemi.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(
        name = "student_db",
        uniqueConstraints = @UniqueConstraint(
                name = "emailUnique",
                columnNames = "email_adress"
        )
)
public class Student {
    @SequenceGenerator(
            name = "student_sequence",
            sequenceName = "student_sequence",
            allocationSize = 1

    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "student_sequence"
    )
    @Id
    private Long studentId;
    private String firstName;
    private String lastName;
    @Column(name = "email_adress")
    private String emailId;

    @Embedded
    private Guardian guardian;
    @Transient
    private int age;


}
