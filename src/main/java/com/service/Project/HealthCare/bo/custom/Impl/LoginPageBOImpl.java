package com.service.Project.HealthCare.bo.custom.Impl;

import com.service.Project.HealthCare.bo.custom.LoginPageBO;
import com.service.Project.HealthCare.dao.DAOFactory;
import com.service.Project.HealthCare.dao.custom.UserDAO;
import com.service.Project.HealthCare.entity.User;

import java.io.IOException;

public class LoginPageBOImpl implements LoginPageBO {
    UserDAO userDAO = (UserDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.Admin);
    User user;

    public LoginPageBOImpl() throws IOException {
    }

    @Override
    public User Adminlogin(String userName) {
        user = userDAO.findByuserNAme(userName);
        return user;
    }

}
