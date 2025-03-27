package com.service.Project.HealthCare.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity(name = "Therapy_Program")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Programs implements SuperEntity {
    @Id
    private String tId;
    private String pName;

    @OneToMany(mappedBy = "programs", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Registration> registrationList;

    @OneToOne(mappedBy = "programs", cascade = CascadeType.ALL)
    private Payement payement;

}
