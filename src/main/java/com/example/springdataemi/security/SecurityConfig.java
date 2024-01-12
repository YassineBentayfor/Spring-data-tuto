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

import static org.springframework.security.config.Customizer.withDefaults;

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
                .roles("TEACHER", "ADERENT")
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
        return http
                .csrf(csrf -> csrf.disable())
                .authorizeRequests(auth -> {
                    auth.requestMatchers("/").permitAll();

                    auth.requestMatchers("/students/**").hasRole("STUDENT");

                    auth.requestMatchers("/teachers/**").hasRole("ADMIN");
                    auth.requestMatchers("/courses/**").hasAnyRole("TEACHER", "ADMIN");
                })
                .httpBasic(withDefaults())
                .build();
    }




    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
