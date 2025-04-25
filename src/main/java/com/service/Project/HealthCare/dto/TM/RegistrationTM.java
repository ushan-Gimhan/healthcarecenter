package com.service.Project.HealthCare.dto.TM;

import com.service.Project.HealthCare.entity.Patient;
import com.service.Project.HealthCare.entity.Programs;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationTM {
    private String regId;
    private Double payment;
    private Date date;
    private Patient patient;
    private Programs program;
    private String patient_Id;
    private String program_Id;

    public RegistrationTM(String regId, Double payment, Date date, String id, String tId) {
        this.regId = regId;
        this.payment = payment;
        this.date = date;
        this.patient_Id = id;
        this.program_Id = tId;
    }
}
