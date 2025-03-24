package com.service.Project.HealthCare.dao.custom.Impl;

import com.service.Project.HealthCare.dao.custom.PatientDAO;
import com.service.Project.HealthCare.entity.Patient;

import java.util.List;

public class PatientDAOImpl implements PatientDAO {
    @Override
    public List<Patient> findAll() {
        return List.of();
    }

    @Override
    public Patient find(String id) {
        return null;
    }

    @Override
    public boolean save(Patient entity) {
        return false;
    }

    @Override
    public boolean update(Patient entity) {
        return false;
    }

    @Override
    public boolean delete(String id) {
        return false;
    }

    @Override
    public String genarateId() {
        return "";
    }
}
