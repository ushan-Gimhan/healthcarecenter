package com.service.Project.HealthCare.bo.custom;

import com.service.Project.HealthCare.bo.SuperBO;
import com.service.Project.HealthCare.dto.PatientDTO;
import com.service.Project.HealthCare.dto.TheropistDTO;
import com.service.Project.HealthCare.entity.Theropist;

import java.util.List;

public interface TheropistBO extends SuperBO {
    public List<TheropistDTO> findAll();
    public TheropistDTO find(String id);
    public boolean save(TheropistDTO theropistDTO);
    public boolean update(TheropistDTO theropistDTO);
    public boolean delete(String id);
    public String generateId();
}
