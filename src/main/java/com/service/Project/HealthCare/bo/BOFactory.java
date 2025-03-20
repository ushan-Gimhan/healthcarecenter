package com.service.Project.HealthCare.bo;

import com.service.Project.HealthCare.bo.custom.Impl.LoginPageBOImpl;

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
        loginPage,
    }

    public SuperBO getBOType(BOType type) {
        switch (type) {
            case loginPage:
                return new LoginPageBOImpl();
                default:
                    return null;
        }
    }
}
