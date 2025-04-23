package com.service.Project.HealthCare.dao.custom;

import com.service.Project.HealthCare.dao.CrudDAO;
import com.service.Project.HealthCare.entity.Patient;
import com.service.Project.HealthCare.entity.Programs;

import java.util.List;

public interface TherapyProgramDAO extends CrudDAO<Programs,String> {
    public List<String> getAllPrograms();
    public Programs getProgramByName(String name);
}
