package com.service.Project.HealthCare.bo.custom;

import com.service.Project.HealthCare.bo.SuperBO;
import com.service.Project.HealthCare.dto.PatientDTO;
import com.service.Project.HealthCare.entity.Patient;

import java.io.IOException;
import java.util.List;

public interface PatientBO extends SuperBO {
    public List<PatientDTO> findAll();
    public PatientDTO find(String id);
    public boolean save(PatientDTO patient);
    public boolean update(PatientDTO patient);
    public boolean delete(String id);
    public String generateId();
    public List<String> getAllPatientIds();

    public Patient getPationByID(String id);
    public Patient getPationByName(String name);

    List<Patient> getPationByNic(String nic);
    List<Patient> getPationBYMobile(String mobile);
}
