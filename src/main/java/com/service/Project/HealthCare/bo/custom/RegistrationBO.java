package com.service.Project.HealthCare.bo.custom;

import com.service.Project.HealthCare.bo.SuperBO;
import com.service.Project.HealthCare.dto.PatientDTO;
import com.service.Project.HealthCare.dto.RegitrationDTO;
import com.service.Project.HealthCare.entity.Programs;
import com.service.Project.HealthCare.entity.Registration;

import java.util.List;

public interface RegistrationBO extends SuperBO {
    public List<RegitrationDTO> findAll();
    public PatientDTO find(String id);
    public boolean save(RegitrationDTO patient);
    public boolean update(RegitrationDTO patient);
    public boolean delete(String id);
    public String generateId();

    public Registration getRegistrationByPatientId(String id);
}
