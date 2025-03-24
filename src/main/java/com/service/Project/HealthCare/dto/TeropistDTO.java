package com.service.Project.HealthCare.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TeropistDTO {
    private String id;
    private String name;
    private int age;
    private String gender;
    private String email;
    private String phone;
    private String specialization;
    private String experience;
}
