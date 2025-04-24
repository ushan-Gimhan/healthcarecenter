package com.service.Project.HealthCare.bo.custom.Impl;

import com.service.Project.HealthCare.bo.custom.PatientBO;
import com.service.Project.HealthCare.dao.DAOFactory;
import com.service.Project.HealthCare.dao.custom.PatientDAO;
import com.service.Project.HealthCare.dto.PatientDTO;
import com.service.Project.HealthCare.entity.Patient;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PatientBOImpl implements PatientBO {
    PatientDAO patientDAO;

    {
        try {
            patientDAO = (PatientDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.Patient);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<PatientDTO> findAll() {
        List<Patient> all = patientDAO.findAll();
        List<PatientDTO> patientDTOs = new ArrayList<PatientDTO>();
        for (Patient patient : all) {
            PatientDTO patientDTO = new PatientDTO(patient.getId(),patient.getName(),patient.getMobile(),patient.getNIC(),patient.getEmail(),patient.getGender(),patient.getAge());
           patientDTOs.add(patientDTO);
        }
        return patientDTOs;
    }

    @Override
    public PatientDTO find(String id) {
        return null;
    }

    @Override
    public boolean save(PatientDTO patient) {
        return patientDAO.save(new Patient(patient.getId(),patient.getName(),patient.getMobile(), patient.getNIC(),patient.getEmail(), patient.getGender(),patient.getAge()));
    }

    @Override
    public boolean update(PatientDTO patient){
        return patientDAO.update(new Patient(patient.getId(),patient.getName(),patient.getMobile(), patient.getNIC(),patient.getEmail(), patient.getGender(),patient.getAge()));
    }

    @Override
    public boolean delete(String id) {
        return patientDAO.delete(id);
    }

    @Override
    public String generateId() {
        return patientDAO.generateId();
    }

    @Override
    public List<String> getAllPatientIds() {
        return patientDAO.getAllPatientIds();
    }

    @Override
    public Patient getPationByID(String id) {
        return patientDAO.getPatientById(id);
    }

    @Override
    public Patient getPationByName(String name) {
        return patientDAO.getPationByName(name);
    }
}
