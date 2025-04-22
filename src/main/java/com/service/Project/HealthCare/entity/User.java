package com.service.Project.HealthCare.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "admin_table")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User implements SuperEntity{
    @Id
    private String id;

    private String name;
    private String password;
    private String email;
    private String phone;
    private String userName;
    private String role;

}
