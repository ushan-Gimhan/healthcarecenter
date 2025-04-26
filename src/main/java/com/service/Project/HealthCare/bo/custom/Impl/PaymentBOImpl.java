package com.service.Project.HealthCare.bo.custom.Impl;

import com.service.Project.HealthCare.bo.custom.PaymentBO;
import com.service.Project.HealthCare.dao.DAOFactory;
import com.service.Project.HealthCare.dao.custom.PaymentDAO;
import com.service.Project.HealthCare.dto.PaymentDTO;
import com.service.Project.HealthCare.dto.SessioonDTO;
import com.service.Project.HealthCare.entity.Payement;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PaymentBOImpl implements PaymentBO {
    PaymentDAO paymentDAO;

    {
        try {
            paymentDAO = (PaymentDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.Payment);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<PaymentDTO> findAll() {
        List<Payement> allPayments = paymentDAO.findAll();
        List<PaymentDTO> paymentDTOs = new ArrayList<>();
        for (Payement payment : allPayments) {
            PaymentDTO paymentDTO = new PaymentDTO(payment.getPayId(),payment.getPayMethod(),payment.getAmount(), payment.getDate().toLocalDate(),payment.getAvailabalAmout());
            paymentDTOs.add(paymentDTO);
        }
        return paymentDTOs;
    }

    @Override
    public SessioonDTO find(String id) {
        return null;
    }

    @Override
    public boolean save(PaymentDTO payment) {
        return paymentDAO.save(new Payement(payment.getPayId(), payment.getPayMethod(), payment.getAmount(),payment.getDate(),payment.getAvailabalAmout(),payment.getPatient(),payment.getProgram()));
    }

    @Override
    public boolean update(PaymentDTO payment) {
        return paymentDAO.update(new Payement(payment.getPayId(), payment.getPayMethod(), payment.getAmount(),payment.getDate(),payment.getAvailabalAmout(),payment.getPatient(),payment.getProgram()));
    }

    @Override
    public boolean delete(String id) {
        return paymentDAO.delete(id);
    }

    @Override
    public String generateId() {
        return paymentDAO.generateId();
    }

    @Override
    public Double getAllPaymentByPatientId(String patient) {
        List<Payement> all = paymentDAO.findAll();
        List<Double> payments = new ArrayList<>();
        double total=0;
        for (Payement payment : all) {
            if (payment.getPayId().equals(patient)) {
                Double amount = payment.getAmount();
                total =+ amount;
            }
        }
        return total;
    }
}
