package com.service.Project.HealthCare.bo.custom;

import com.service.Project.HealthCare.bo.SuperBO;
import com.service.Project.HealthCare.dto.AdminDTO;

import java.util.List;

public interface AdminBO extends SuperBO {
    public List<AdminDTO> findAll();
    public AdminDTO find(String id);
    public boolean save(AdminDTO entity);
    public boolean update(AdminDTO entity);
    public boolean delete(String id);
    public String genareateID();
}
