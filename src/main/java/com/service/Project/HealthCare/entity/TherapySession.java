package com.service.Project.HealthCare.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.sql.Time;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TherapySession implements SuperEntity{
    @Id
    private String sId;
    private int SessionCount;
    private Time time;
    private Date date;
    private String status;

    @ManyToOne
    private Patient patient;

    @ManyToOne
    private Theropist theropist;

    public TherapySession(String sId, int sessionCount, Time time, Date date, String status) {
    }

    public TherapySession(String id, int count, Time time, String status) {
    }
}
