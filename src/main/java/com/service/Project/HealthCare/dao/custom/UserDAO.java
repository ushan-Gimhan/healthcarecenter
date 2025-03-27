package com.service.Project.HealthCare.dao.custom;

import com.service.Project.HealthCare.dao.CrudDAO;
import com.service.Project.HealthCare.entity.User;

public interface UserDAO extends CrudDAO<User,String> {
    public User findByuserNAme(String useName);
}
