package com.service.Project.HealthCare.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TherapySession implements SuperEntity{
    @Id
    private String sId;
    private int SessionCount;

    @ManyToOne
    private Patient patient;

    @ManyToOne
    private Theropist theropist;
}
