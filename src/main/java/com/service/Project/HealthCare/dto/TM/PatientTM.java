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
    String id;
    String name;
    String mobile;
    String NIC;
    String email;
    String gender;
}
