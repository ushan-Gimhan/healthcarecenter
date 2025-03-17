package com.service.Project.HealthCare.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "admin_table")
@Getter
@Setter
public class Admin implements SuperEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private String password;
    private String email;

    @OneToMany(mappedBy = "admin",cascade = CascadeType.ALL)
    private List<Receptionist> receptionists;

}
