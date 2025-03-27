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
public class Patient implements SuperEntity {
    @Id
    private String id;
    private String name;
    private String mobile;
    private String NIC;
    private String email;
    private String gender;
    private int age;

    @OneToMany(mappedBy = "patient" ,cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Registration> registrationList;

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL ,fetch = FetchType.LAZY)
    private List<TherapySession> therapySessions;

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Payement> payements;

}
