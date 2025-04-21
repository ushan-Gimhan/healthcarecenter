package com.service.Project.HealthCare.bo.custom.Impl;

import com.service.Project.HealthCare.bo.custom.TherapyProgramBO;
import com.service.Project.HealthCare.config.FactoryConfiguration;
import com.service.Project.HealthCare.dao.DAOFactory;
import com.service.Project.HealthCare.dao.custom.Impl.TherapyProgramDAOImpl;
import com.service.Project.HealthCare.dao.custom.TherapyProgramDAO;
import com.service.Project.HealthCare.dto.PatientDTO;
import com.service.Project.HealthCare.dto.TherapyProgramDTO;
import com.service.Project.HealthCare.entity.Patient;
import com.service.Project.HealthCare.entity.Programs;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TherapyProgramBOImpl implements TherapyProgramBO {
    TherapyProgramDAO therapyProgramDAO;

    {
        try {
            therapyProgramDAO = (TherapyProgramDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.program);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<TherapyProgramDTO> findAll() {
        List<Programs> all = therapyProgramDAO.findAll();
        List<TherapyProgramDTO> programDTOs = new ArrayList<TherapyProgramDTO>();
        for (Programs programs : all) {
            TherapyProgramDTO therapyProgramDTO = new TherapyProgramDTO(programs.getTId(),programs.getPName(),programs.getDuration(), programs.getPrice());
            programDTOs.add(therapyProgramDTO);
        }
        return programDTOs;
    }

    @Override
    public PatientDTO find(String id) {
        return null;
    }

    @Override
    public boolean save(TherapyProgramDTO program) {
        return therapyProgramDAO.save(new Programs(program.getTId(), program.getPName(), program.getDuration(), program.getPrice()));
    }

    @Override
    public boolean update(TherapyProgramDTO program) {
        return therapyProgramDAO.update(new Programs(program.getTId(), program.getPName(), program.getDuration(), program.getPrice()));
    }

    @Override
    public boolean delete(String id) {
        return therapyProgramDAO.delete(id);
    }

    @Override
    public String generateId() {
        return therapyProgramDAO.generateId();
    }
}
