package com.service.Project.HealthCare.dao;

import com.service.Project.HealthCare.dao.custom.Impl.PatientDAOImpl;
import com.service.Project.HealthCare.dao.custom.Impl.UserDAOImpl;

import java.io.IOException;

public class DAOFactory {
    private static DAOFactory instance;
    private DAOFactory() {

    }
    public static DAOFactory getInstance() {
        if (instance == null) {
            instance = new DAOFactory();
            return instance;
        }
        return instance;
    }

    public enum DAOType{
        Admin,Patient
    }

    public SuperDAO getDAO(DAOType daoType) throws IOException {
        switch (daoType) {
            case Admin:
                return new UserDAOImpl();
                case Patient:
                    return new PatientDAOImpl();
                default:
                    return null;
        }
    }
}
