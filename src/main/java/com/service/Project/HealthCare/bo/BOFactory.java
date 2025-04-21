package com.service.Project.HealthCare.bo;

import com.service.Project.HealthCare.bo.custom.Impl.*;

import java.io.IOException;

public class BOFactory {
    private static BOFactory instance;
    private BOFactory() {

    }
    public static BOFactory getInstance() {
        if (instance == null) {
            instance = new BOFactory();
            return instance;
        }
        return instance;
    }
    public enum BOType {
        loginPage,admin,Patient, Therapist, Program
    }

    public SuperBO getBOType(BOType type) throws IOException {
        switch (type) {
            case loginPage:
                return new LoginPageBOImpl();
                case admin:
                    return new UserBOImpl();
                    case Patient:
                        return new PatientBOImpl();
                        case Therapist:
                            return new TheropistBOImpl();
                            case Program:
                                return new TherapyProgramBOImpl();
                default:
                    return null;
        }
    }
}
