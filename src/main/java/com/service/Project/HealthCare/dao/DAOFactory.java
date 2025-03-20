package com.service.Project.HealthCare.dao;

import com.service.Project.HealthCare.bo.SuperBO;
import com.service.Project.HealthCare.dao.custom.AdminDAO;
import com.service.Project.HealthCare.dao.custom.Impl.AdminDAOImpl;
import com.service.Project.HealthCare.dao.custom.Impl.ReceptionistDAOImpl;

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
        Admin,Receptionist,
    }

    public SuperDAO getDAO(DAOType daoType) {
        switch (daoType) {
            case Admin:
                return new AdminDAOImpl();
            case Receptionist:
                return new ReceptionistDAOImpl();
                default:
                    return null;
        }
    }
}
