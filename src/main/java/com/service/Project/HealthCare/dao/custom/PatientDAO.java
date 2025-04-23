package com.service.Project.HealthCare.dao.custom;

import com.service.Project.HealthCare.dao.CrudDAO;
import com.service.Project.HealthCare.entity.Patient;

import java.util.List;

public interface PatientDAO extends CrudDAO<Patient,String> {
    public List<String> getAllPatientIds();
    public Patient getPatientById(String id);

}
