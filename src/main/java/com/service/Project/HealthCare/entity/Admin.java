package com.service.Project.HealthCare.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "admin_table")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Admin implements SuperEntity{
    @Id
    private String id;

    private String name;
    private String password;
    private String email;
    private String phone;
    private String userName;


    @OneToMany(mappedBy = "admin",cascade = CascadeType.ALL)
    private List<Receptionist> receptionists;

    public Admin(String id, String name, String phone, String password, String email,String userName) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.password = password;
        this.email = email;
        this.userName = userName;
    }
}
