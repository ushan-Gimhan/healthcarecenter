package com.service.Project.HealthCare.bo.custom.Impl;

import com.service.Project.HealthCare.bo.custom.AdminBO;
import com.service.Project.HealthCare.dao.DAOFactory;
import com.service.Project.HealthCare.dao.custom.AdminDAO;
import com.service.Project.HealthCare.dao.custom.Impl.AdminDAOImpl;
import com.service.Project.HealthCare.dto.AdminDTO;
import com.service.Project.HealthCare.entity.Admin;

import java.util.List;

public class AdminBOImpl implements AdminBO {
    AdminDAO adminDAO = (AdminDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.Admin);
    @Override
    public List<AdminDTO> findAll() {
        return List.of();
    }

    @Override
    public AdminDTO find(String id) {
        return null;
    }

    @Override
    public boolean save(AdminDTO entity) {
        return adminDAO.save(new Admin(entity.getId(),entity.getName(),entity.getPhone(),entity.getPassword(),entity.getEmail()));
    }

    @Override
    public boolean update(AdminDTO entity) {
        return adminDAO.update(new Admin(entity.getId(),entity.getName(),entity.getPhone(),entity.getPassword(),entity.getEmail()));
    }

    @Override
    public boolean delete(String id) {
        return adminDAO.delete(id);
    }

    @Override
    public String genareateID() {
        return adminDAO.genarateId();
    }
}
