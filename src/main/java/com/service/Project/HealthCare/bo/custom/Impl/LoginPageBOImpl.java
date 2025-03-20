package com.service.Project.HealthCare.bo.custom.Impl;

import com.service.Project.HealthCare.bo.custom.LoginPageBO;
import com.service.Project.HealthCare.dao.DAOFactory;
import com.service.Project.HealthCare.dao.custom.AdminDAO;
import com.service.Project.HealthCare.dao.custom.ReceptionistDAO;
import com.service.Project.HealthCare.entity.Admin;
import com.service.Project.HealthCare.entity.Receptionist;

public class LoginPageBOImpl implements LoginPageBO {
    AdminDAO adminDAO = (AdminDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.Admin);
    ReceptionistDAO receptionistDAO = (ReceptionistDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.Receptionist);
    Admin admin;
    Receptionist receptionist;

    @Override
    public Admin Adminlogin(String userName) {
        admin=adminDAO.findByuserNAme(userName);
        return admin;
    }

    @Override
    public Receptionist Receptionlogin(String userName) {
        receptionist = receptionistDAO.findByuserNAme(userName);
        return receptionist;
    }
}
