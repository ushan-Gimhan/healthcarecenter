package com.service.Project.HealthCare.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PatientDTO {
    private String id;
    private String name;
    private String mobile;
    private String NIC;
    private String email;
    private String gender;
    private int age;

}
