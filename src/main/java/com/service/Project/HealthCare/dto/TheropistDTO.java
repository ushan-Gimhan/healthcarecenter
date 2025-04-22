package com.service.Project.HealthCare.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TheropistDTO {
    private String id;
    private String name;
    private String gender;
    private String email;
    private String phone;
    private String specialization;
    private String experience;

    public TheropistDTO(String tId, String name, String email, String mobileNumber, String specialization, String experience) {
    }
}
