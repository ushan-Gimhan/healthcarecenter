package com.service.Project.HealthCare.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "receptionist")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Receptionist implements SuperEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    private String name;
    private String address;
    private String phone;
    private String email;
    private String password;

    @ManyToOne
    private Admin admin;
}
