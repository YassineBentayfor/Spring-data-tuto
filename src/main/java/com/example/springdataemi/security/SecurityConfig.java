package com.example.springdataemi.security;

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
                .roles("TEACHER")
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
                .authorizeRequests(auth -> {
                    auth.requestMatchers("/").permitAll();

                    auth.requestMatchers(HttpMethod.GET, "/teachers/**").hasAnyRole("TEACHER", "ADMIN");
                    auth.requestMatchers(HttpMethod.DELETE, "/teachers/**").hasRole( "ADMIN");
                    auth.requestMatchers(HttpMethod.POST, "/teachers/**").hasRole( "ADMIN");
                    auth.requestMatchers(HttpMethod.PUT, "/teachers/**").hasRole( "ADMIN");

                    auth.requestMatchers(HttpMethod.GET, "/students/**").hasAnyRole("STUDENT", "ADMIN", "TEACHER");
                    auth.requestMatchers(HttpMethod.DELETE, "/students/**").hasRole( "ADMIN");
                    auth.requestMatchers(HttpMethod.POST, "/students/**").hasRole( "ADMIN");
                    auth.requestMatchers(HttpMethod.PUT, "/students/**").hasAnyRole( "ADMIN", "TEACHER");

                    auth.requestMatchers(HttpMethod.GET, "/course-materials/**").hasAnyRole("TEACHER", "STUDENT", "ADMIN");
                    auth.requestMatchers(HttpMethod.POST, "/course-materials/**").hasRole("TEACHER");
                    auth.requestMatchers(HttpMethod.DELETE, "/course-materials/**").hasRole("TEACHER");
                    auth.requestMatchers(HttpMethod.PUT, "/course-materials/**").hasRole("TEACHER");


                })
                .httpBasic(withDefaults())
                .build();
    }




    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
