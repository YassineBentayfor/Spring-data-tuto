package com.example.springdataemi.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        UserDetails teacher = User.withUsername("teacher")
                .password("teacher1pass")
                .roles("TEACHER")
                .build();
        UserDetails student = User.withUsername("student")
                .password("student1pass")
                .roles("STUDENT")
                .build();
        UserDetails admin = User.withUsername("admin")
                .password("adminpass")
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
                        auth.requestMatchers(HttpMethod.GET, "/teachers/**").hasRole("TEACHER");
                        auth.requestMatchers(HttpMethod.GET, "/students/**").hasRole("STUDENT");
                        auth.requestMatchers(HttpMethod.GET, "/course-materials/**").hasAnyRole("TEACHER", "STUDENT", "ADMIN");
                        auth.requestMatchers(HttpMethod.POST, "/students/**").hasRole("ADMIN");
                        auth.requestMatchers(HttpMethod.DELETE, "/students/**").hasRole("ADMIN");
                })
                .httpBasic(withDefaults())
                .build();
    }

}
