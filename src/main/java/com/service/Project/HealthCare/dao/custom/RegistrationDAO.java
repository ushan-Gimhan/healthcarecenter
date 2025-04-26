package com.service.Project.HealthCare.dao.custom;

import com.service.Project.HealthCare.dao.CrudDAO;
import com.service.Project.HealthCare.entity.Programs;
import com.service.Project.HealthCare.entity.Registration;

public interface RegistrationDAO extends CrudDAO<Registration,String> {
    Registration getPegistrationById(String id);
}
