package com.service.Project.HealthCare.bo.custom.Impl;

import com.service.Project.HealthCare.bo.custom.RegistrationBO;
import com.service.Project.HealthCare.dao.DAOFactory;
import com.service.Project.HealthCare.dao.custom.PatientDAO;
import com.service.Project.HealthCare.dao.custom.RegistrationDAO;
import com.service.Project.HealthCare.dao.custom.TherapyProgramDAO;
import com.service.Project.HealthCare.dto.PatientDTO;
import com.service.Project.HealthCare.dto.RegitrationDTO;
import com.service.Project.HealthCare.entity.Patient;
import com.service.Project.HealthCare.entity.Registration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RegistrationBOImpl implements RegistrationBO {
   RegistrationDAO dao;
   PatientDAO pdao;
   TherapyProgramDAO therapyProgramDAO;

    {
        try {
            dao = (RegistrationDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.Registration);
            pdao = (PatientDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.Patient);
            therapyProgramDAO= (TherapyProgramDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.program);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<RegitrationDTO> findAll() {
        List<Registration> all =  dao.findAll();
        List<RegitrationDTO> regitrationDTOS = new ArrayList<>();
        for (Registration registration : all) {
            regitrationDTOS.add(new RegitrationDTO(registration.getRegId(),registration.getPayment(),registration.getDate()));
        }
        return regitrationDTOS;

    }

    @Override
    public PatientDTO find(String id) {
        return null;
    }

    @Override
    public boolean save(RegitrationDTO patient) {
        return dao.save(new Registration(patient.getRegId(), patient.getPayment(), patient.getDate()));
    }

    @Override
    public boolean update(RegitrationDTO patient) {
        return  dao.update(new Registration(patient.getRegId(), patient.getPayment(), patient.getDate()));
    }

    @Override
    public boolean delete(String id) {
        return  dao.delete(id);
    }

    @Override
    public String generateId() {
        return  dao.generateId();
    }
}
