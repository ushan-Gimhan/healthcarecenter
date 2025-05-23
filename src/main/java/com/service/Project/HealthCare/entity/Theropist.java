package com.service.Project.HealthCare.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

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
    private String status;

    @OneToMany(mappedBy = "theropist", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<TherapySession> therapySessions;

    public Theropist(String id, String name, int age, String gender, String email, String phone, String specialization, String experience, String staus) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.email = email;
        this.phone = phone;
        this.specialization = specialization;
        this.experience = experience;
        this.status = staus;
        this.age = age;
    }
}
