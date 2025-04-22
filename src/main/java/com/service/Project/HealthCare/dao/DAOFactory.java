package com.service.Project.HealthCare.dao;

import com.service.Project.HealthCare.dao.custom.Impl.*;

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
        Admin,Patient, Therapist , program,session , Registration
    }

    public SuperDAO getDAO(DAOType daoType) throws IOException {
        switch (daoType) {
            case Admin:
                return new UserDAOImpl();
                case Patient:
                    return new PatientDAOImpl();
                    case Therapist:
                        return new TheropistDAOImpl();
                        case program:
                            return new TherapyProgramDAOImpl();
                            case session:
                                return new SessionDAOImpl();
            case Registration:
                return new RegistrationDAOImpl();
                default:
                    return null;
        }
    }
}
