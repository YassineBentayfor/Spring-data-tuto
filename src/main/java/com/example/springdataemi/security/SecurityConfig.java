package com.example.springdataemi.security;

import jakarta.servlet.DispatcherType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        PasswordEncoder passwordEncoder = passwordEncoder();

        String encodedTeacherPassword = passwordEncoder.encode("teacher1pass");
        String encodedStudentPassword = passwordEncoder.encode("student1pass");
        String encodedAdminPassword = passwordEncoder.encode("adminpass");

        UserDetails teacher = User.withUsername("teacher")
                .password(encodedTeacherPassword)
                .roles("TEACHER")
                .authorities("TEACHER")
                .build();
        UserDetails student = User.withUsername("student")
                .password(encodedStudentPassword)
                .roles("STUDENT")
                .build();
        UserDetails admin = User.withUsername("admin")
                .password(encodedAdminPassword)
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(teacher, student, admin);
    }



    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http.
                authorizeRequests(auth -> auth
                        .dispatcherTypeMatchers(DispatcherType.FORWARD, DispatcherType.ERROR).permitAll()
                        .requestMatchers("/students/**").permitAll()
                        .requestMatchers("/teachers/**").hasAnyRole("ADMIN", "TEACHER")
                        .requestMatchers("/courses").hasAnyRole("TEACHER", "ADMIN", "STUDENT")
                        .anyRequest().denyAll()
                );
        return http.build();
    }




    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
