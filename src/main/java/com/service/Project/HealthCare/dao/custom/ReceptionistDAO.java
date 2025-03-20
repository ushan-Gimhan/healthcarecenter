package com.service.Project.HealthCare.dao.custom;

import com.service.Project.HealthCare.dao.CrudDAO;
import com.service.Project.HealthCare.entity.Receptionist;

public interface ReceptionistDAO extends CrudDAO<Receptionist,String> {
    public Receptionist findByuserNAme(String useName);
}