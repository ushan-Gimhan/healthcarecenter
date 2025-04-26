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
public class PaymentDTO {
    private String payId;
    private String payMethod;
    private Double amount;
    private LocalDate date;
    private Double availabalAmout;
    private Patient patient;
    private Programs program;
    private String patientId;


    public PaymentDTO(String payId, String payMethod, Double amount, LocalDate localDate, Double availabalAmout) {
        this.payId = payId;
        this.payMethod = payMethod;
        this.amount = amount;
        this.date = localDate;
        this.availabalAmout = availabalAmout;
    }

    public PaymentDTO(String id, String method, double amount, LocalDate date, Double avalableAmount, Patient patient, Programs programs) {
        this.payId = id;
        this.payMethod = method;
        this.amount = amount;
        this.date = date;
        this.patient = patient;
        this.program = programs;
        this.availabalAmout = avalableAmount;
    }

}
