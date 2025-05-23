package com.service.Project.HealthCare.bo.custom;

import com.service.Project.HealthCare.bo.SuperBO;
import com.service.Project.HealthCare.dto.PatientDTO;
import com.service.Project.HealthCare.dto.TherapyProgramDTO;
import com.service.Project.HealthCare.entity.Programs;

import java.util.List;

public interface TherapyProgramBO extends SuperBO {
    public List<TherapyProgramDTO> findAll();
    public PatientDTO find(String id);
    public boolean save(TherapyProgramDTO program);
    public boolean update(TherapyProgramDTO program);
    public boolean delete(String id);
    public String generateId();
    public List<String> getAllPrograms();
    public Programs getProgramByName(String name);
}
