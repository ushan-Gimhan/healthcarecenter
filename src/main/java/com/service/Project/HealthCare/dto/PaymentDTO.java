package com.service.Project.HealthCare.dto;

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

    public PaymentDTO(String paymentId, String patient, String method, Double amount, LocalDate date) {
        this.payId = paymentId;
        this.payMethod = patient;
        this.amount = amount;
        this.date = date;
    }
}
