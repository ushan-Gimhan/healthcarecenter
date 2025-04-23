package com.service.Project.HealthCare.dto.TM;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.sql.Time;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SessionTM {
    private String sId;
    private int SessionCount;
    private Time time;
    private Date date;
    private String status;

    public SessionTM(String sId, int sessionCount, Time time, String status) {
        this.sId = sId;
        this.SessionCount = sessionCount;
        this.time = time;
        this.status = status;
    }
}
