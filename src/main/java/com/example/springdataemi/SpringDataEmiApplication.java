package com.example.springdataemi;

import com.example.springdataemi.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringDataEmiApplication {
    /*@Autowired
    ApplicationContext applicationContext;*/


    public static void main(String[] args) {
        SpringApplication.run(SpringDataEmiApplication.class, args);
    }

    @Autowired
    StudentRepository studentRepository;


    @Bean
    CommandLineRunner coucou() {
        return args -> {
 /*           Student_ST student = Student_ST.builder()
                    .firstName("Yassine")
                    .lastName("Bentayfor")
                    .emailId("yassine@gmail.com")
                    .guardian_name("Father")
                    .guardian_email("father@gmail.com")
                    .guardian_mobile("1234567890")
                    .build();*/
            System.out.println("GOOD");




        };

    }
}