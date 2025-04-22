package com.service.Project.HealthCare.bo.custom;

import com.service.Project.HealthCare.bo.SuperBO;
import com.service.Project.HealthCare.dto.PatientDTO;
import com.service.Project.HealthCare.dto.SessioonDTO;

import java.util.List;

public interface SessionBO extends SuperBO {
    public List<SessioonDTO> findAll();
    public SessioonDTO find(String id);
    public boolean save(SessioonDTO patient);
    public boolean update(SessioonDTO patient);
    public boolean delete(String id);
    public String generateId();
}
