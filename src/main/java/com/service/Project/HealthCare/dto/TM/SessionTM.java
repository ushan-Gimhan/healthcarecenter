package com.service.Project.HealthCare.dto.TM;

import com.service.Project.HealthCare.entity.Patient;
import com.service.Project.HealthCare.entity.Theropist;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SessionTM {
    private String sId;
    private int SessionCount;
    private Time time;
    private LocalDate date;
    private String status;
    private Patient patient;
    private Theropist program;
    private String patient_id;
    private String program_id;


    public SessionTM(String sId, int sessionCount, Time time, LocalDate date, String status, String id, String id1) {
        this.sId = sId;
        this.SessionCount = sessionCount;
        this.time = time;
        this.date = date;
        this.status = status;
        this.patient_id = id;
        this.program_id = id1;
    }
}
