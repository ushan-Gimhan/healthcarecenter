package com.service.Project.HealthCare.bo.custom;

import com.service.Project.HealthCare.bo.SuperBO;
import com.service.Project.HealthCare.entity.User;

public interface LoginPageBO extends SuperBO {
    public User Adminlogin(String userName);
}
