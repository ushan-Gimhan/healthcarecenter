package com.service.Project.HealthCare.bo;

import com.service.Project.HealthCare.bo.custom.Impl.AdminBOImpl;
import com.service.Project.HealthCare.bo.custom.Impl.LoginPageBOImpl;
import com.service.Project.HealthCare.bo.custom.Impl.PatientBOImpl;

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
        loginPage,admin,Patient
    }

    public SuperBO getBOType(BOType type) {
        switch (type) {
            case loginPage:
                return new LoginPageBOImpl();
                case admin:
                    return new AdminBOImpl();
            case Patient:
                return new PatientBOImpl();
                default:
                    return null;
        }
    }
}
