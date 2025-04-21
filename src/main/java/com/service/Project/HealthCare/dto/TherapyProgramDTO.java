package com.service.Project.HealthCare.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TherapyProgramDTO {
    private String tId;
    private String pName;
    private String duration;
    private Double price;
}
