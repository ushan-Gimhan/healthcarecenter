package com.service.Project.HealthCare.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.*;

import java.sql.Date;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Payement implements SuperEntity{
    @Id
    private String payId;
    private String payMethod;
    private Double amount;
    private Date date;
    private Double availabalAmout;


    @ManyToOne
    private Patient patient;

    @OneToOne
    private Programs programs;

    public Payement(String payId, String payMethod, Double amount, Date date, Double availabalAmout) {
        this.payId = payId;
        this.payMethod = payMethod;
        this.amount = amount;
        this.date = date;
        this.availabalAmout = availabalAmout;
    }

    public Payement(String payId, String payMethod, Double amount, LocalDate date, Double availabalAmout) {
        this.payId = payId;
        this.payMethod = payMethod;
        this.amount = amount;
        this.date = Date.valueOf(date);
        this.availabalAmout = availabalAmout;
    }
}
