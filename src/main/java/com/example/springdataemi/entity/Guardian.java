package com.example.springdataemi.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Embeddable


public class Guardian implements Serializable{
    private String name;
    private String email;
    private String mobile;
}
