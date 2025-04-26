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

    public PaymentDTO(String paymentId, String patient, String method, Double amount, LocalDate date) {
        this.payId = paymentId;
        this.payMethod = patient;
        this.amount = amount;
        this.date = date;
    }

    public PaymentDTO(String payId, String payMethod, Double amount, LocalDate localDate, Double availabalAmout) {
        this.payId = payId;
        this.payMethod = payMethod;
        this.amount = amount;
        this.date = localDate;
        this.availabalAmout = availabalAmout;
    }
}
