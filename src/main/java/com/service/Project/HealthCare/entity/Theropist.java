package com.service.Project.HealthCare.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Theropist implements SuperEntity {
    @Id
    private String id;
    private String name;
    private int age;
    private String gender;
    private String email;
    private String phone;
    private String specialization;
    private String experience;

}
