package com.service.Project.HealthCare.dto;

import com.service.Project.HealthCare.entity.Patient;
import com.service.Project.HealthCare.entity.Theropist;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SessioonDTO {
    private String sId;
    private int SessionCount;
    private Time time;
    private LocalDate date;
    private String status;
    private Patient patient;
    private Theropist id;
    private String patient_Id;
    private String theropist_Id;


    public SessioonDTO(String id, int count, Time time, LocalDate date, String status, String patientID, String theropistId) {
        this.sId = id;
        this.SessionCount = count;
        this.time = time;
        this.date = date;
        this.status = status;
        this.patient_Id = patientID;
        this.theropist_Id = theropistId;
    }

    public SessioonDTO(String sId, int sessionCount, Time time, Date date, String status, Patient patient, Theropist theropist) {
        this.sId = sId;
        this.SessionCount = sessionCount;
        this.time = time;
        this.date = date.toLocalDate();
        this.status = status;
        this.patient = patient;
        this.id = theropist;
    }
}
