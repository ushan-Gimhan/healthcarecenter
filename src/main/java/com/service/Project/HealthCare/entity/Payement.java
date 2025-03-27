package com.service.Project.HealthCare.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.*;

import java.sql.Date;

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

    @ManyToOne
    private Patient patient;

    @OneToOne
    private Programs programs;
}
