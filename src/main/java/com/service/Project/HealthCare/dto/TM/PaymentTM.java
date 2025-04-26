package com.service.Project.HealthCare.dto.TM;

import com.service.Project.HealthCare.entity.Patient;
import javafx.scene.Parent;
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
public class PaymentTM {
    private String payId;
    private String payMethod;
    private Double amount;
    private Date date;
    private Double availabalAmout;
    private Patient patient;
    private String patientId;

    public PaymentTM(String payId, String payMethod, Double amount, LocalDate date, Double availabalAmout, String id) {
        this.payId = payId;
        this.payMethod = payMethod;
        this.amount = amount;
        this.date= Date.valueOf(date);
        this.availabalAmout=availabalAmout;
        this.patientId=id;
    }
}
