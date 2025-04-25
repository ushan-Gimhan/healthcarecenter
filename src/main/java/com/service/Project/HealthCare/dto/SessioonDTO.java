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


    public SessioonDTO(String sId, int sessionCount, Time time, Date date, String status) {
        this.sId = sId;
        this.SessionCount = sessionCount;
        this.date = Date.valueOf(date.toLocalDate()).toLocalDate();
        this.time = Time.valueOf(time.toLocalTime());
        this.status = status;
    }
}
