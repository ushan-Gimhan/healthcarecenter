package com.service.Project.HealthCare.dto.TM;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PatientTM {
    private String id;
    private String name;
    private String mobile;
    private String email;
    private String NIC;
    private String gender;
    private int age;
}
