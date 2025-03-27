package com.service.Project.HealthCare.bo.custom.Impl;

import com.service.Project.HealthCare.bo.custom.PatientBO;
import com.service.Project.HealthCare.dao.DAOFactory;
import com.service.Project.HealthCare.dao.custom.PatientDAO;
import com.service.Project.HealthCare.dto.PatientDTO;
import com.service.Project.HealthCare.entity.Patient;

import java.io.IOException;
import java.util.List;

public class PatientBOImpl implements PatientBO {
    PatientDAO patientDAO = (PatientDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.Patient);

    public PatientBOImpl() throws IOException {
    }

    @Override
    public List<PatientDTO> findAll() {
        return List.of();
    }

    @Override
    public PatientDTO find(String id) {
        return null;
    }

    @Override
    public boolean save(PatientDTO patient) {
        return patientDAO.save(new Patient(patient.getId(),patient.getName(),patient.getMobile(), patient.getEmail(),patient.getNIC(), patient.getGender(),patient.getAge()));
    }

    @Override
    public boolean update(PatientDTO patient) throws IOException {
        return patientDAO.update(new Patient(patient.getId(),patient.getName(),patient.getMobile(), patient.getEmail(),patient.getNIC(), patient.getGender(),patient.getAge()));
    }

    @Override
    public boolean delete(String id) {
        return patientDAO.delete(id);
    }

    @Override
    public String generateId() {
        return patientDAO.generateId();
    }
}
