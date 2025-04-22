package com.service.Project.HealthCare.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.sql.Time;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SessioonDTO {
    private String sId;
    private int SessionCount;
    private Time time;
    private Date date;
    private String status;

    public SessioonDTO(String id, int count, String time, String status) {
    }
}
