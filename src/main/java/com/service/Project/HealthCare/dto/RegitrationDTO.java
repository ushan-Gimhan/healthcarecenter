package com.service.Project.HealthCare.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegitrationDTO {
    private String regId;
    private Double payment;
    private Date date;

    public RegitrationDTO(String id, String payment, String patientValue, String program) {
    }
}
