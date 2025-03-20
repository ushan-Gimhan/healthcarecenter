package com.service.Project.HealthCare.dao.custom;

import com.service.Project.HealthCare.dao.CrudDAO;
import com.service.Project.HealthCare.entity.Admin;

public interface AdminDAO extends CrudDAO<Admin,String> {
    public Admin findByuserNAme(String useName);
}
