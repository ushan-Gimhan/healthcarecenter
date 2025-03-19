package com.service.Project.HealthCare.bo.custom;

import com.service.Project.HealthCare.bo.SuperBO;
import com.service.Project.HealthCare.entity.Admin;
import com.service.Project.HealthCare.entity.Receptionist;

public interface LoginPageBO extends SuperBO {
    public Admin Adminlogin(String userName);
    public Receptionist Receptionlogin(String userName);
}
