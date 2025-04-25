package com.service.Project.HealthCare.dto;

import com.service.Project.HealthCare.entity.Patient;
import com.service.Project.HealthCare.entity.Programs;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegitrationDTO {
    private String regId;
    private Double payment;
    private Date date;
    private Patient patient;
    private Programs program;
    private String patienId;
    private String progId;

    public RegitrationDTO(String id, String payment, LocalDate date, Patient patient, Programs programs) {
        this.regId = id;
        this.payment = Double.parseDouble(payment);
        this.patient= patient;
        this.program = programs;
        this.date= Date.valueOf(date);
    }

    public RegitrationDTO(String regId, Double payment, Date date, String patientId, String programId) {
        this.regId = regId;
        this.payment = payment;
        this.date = date;
        this.patienId = patientId;
        this.progId = programId;
    }
}
