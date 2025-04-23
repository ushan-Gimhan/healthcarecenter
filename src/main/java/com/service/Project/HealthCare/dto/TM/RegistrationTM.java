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

    public RegistrationTM(String regId, Date date, Patient patient, Object o, Double payment, Programs program) {
    }
}
