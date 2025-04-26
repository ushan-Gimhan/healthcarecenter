package com.service.Project.HealthCare.dao.custom;

import com.service.Project.HealthCare.dao.CrudDAO;
import com.service.Project.HealthCare.entity.Payement;

import java.util.List;

public interface PaymentDAO extends CrudDAO<Payement,String> {
    List<Payement> findByPatientId(String patient);
}
