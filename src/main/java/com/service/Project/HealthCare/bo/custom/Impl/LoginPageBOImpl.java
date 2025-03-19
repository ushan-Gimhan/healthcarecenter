package com.service.Project.HealthCare.bo.custom.Impl;

import com.service.Project.HealthCare.bo.custom.LoginPageBO;
import com.service.Project.HealthCare.dao.custom.Impl.AdminDAOImpl;
import com.service.Project.HealthCare.dao.custom.Impl.ReceptionistDAOImpl;
import com.service.Project.HealthCare.entity.Admin;
import com.service.Project.HealthCare.entity.Receptionist;
import com.service.Project.HealthCare.entity.SuperEntity;

public class LoginPageBOImpl implements LoginPageBO {
    AdminDAOImpl adminDAO = new AdminDAOImpl();
    ReceptionistDAOImpl receptionistDAO = new ReceptionistDAOImpl();
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
