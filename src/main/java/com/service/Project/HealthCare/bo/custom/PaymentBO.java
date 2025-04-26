package com.service.Project.HealthCare.bo.custom;

import com.service.Project.HealthCare.bo.SuperBO;
import com.service.Project.HealthCare.dto.PaymentDTO;
import com.service.Project.HealthCare.dto.SessioonDTO;

import java.util.List;

public interface PaymentBO extends SuperBO {
    public List<PaymentDTO> findAll();
    public SessioonDTO find(String id);
    public boolean save(PaymentDTO payment);
    public boolean update(PaymentDTO payment);
    public boolean delete(String id);
    public String generateId();

    Double getAllPaymentByPatientId(String patient);
}
