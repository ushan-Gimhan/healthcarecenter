package com.service.Project.HealthCare.controller;

import com.service.Project.HealthCare.bo.BOFactory;
import com.service.Project.HealthCare.bo.custom.PatientBO;
import com.service.Project.HealthCare.bo.custom.PaymentBO;
import com.service.Project.HealthCare.bo.custom.RegistrationBO;
import com.service.Project.HealthCare.dto.TM.PaymentTM;
import com.service.Project.HealthCare.entity.Patient;
import com.service.Project.HealthCare.entity.Programs;
import com.service.Project.HealthCare.entity.Registration;
import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static com.service.Project.HealthCare.controller.PaymentController.paymentTM;

public class InvoicContoller implements Initializable {
    PaymentBO paymentBO;
    PatientBO patientBO;
    RegistrationBO registrationBO;

    {
        try {
            paymentBO = (PaymentBO) BOFactory.getInstance().getBOType(BOFactory.BOType.payment);
            patientBO = (PatientBO) BOFactory.getInstance().getBOType(BOFactory.BOType.Patient);
            registrationBO=(RegistrationBO) BOFactory.getInstance().getBOType(BOFactory.BOType.registration);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    private AnchorPane PrintingAnchor;

    @FXML
    private TextField amountPaidField;

    @FXML
    private Button closeButton;

    @FXML
    private TextField duePaymentField;

    @FXML
    private TextField patientIdField;

    @FXML
    private TextField patientNameField;

    @FXML
    private TextField paymentIdField;

    @FXML
    private TextField paymentTypeField;

    @FXML
    private Button printButton;

    @FXML
    private Label statusLabel;

    @FXML
    private TextField therapistIdField;

    @FXML
    private TextField therapistNameField;

    @FXML
    void closeBtnAction(ActionEvent event) {

    }

    @FXML
    void printBtnAction(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        paymentIdField.setText(paymentTM.getPayId());
        patientIdField.setText(paymentTM.getPatientId());
        Patient patient = patientBO.getPationByID(patientIdField.getText());
        patientNameField.setText(patient.getName());
        paymentTypeField.setText(paymentTM.getPayMethod());
        amountPaidField.setText(paymentTM.getAmount().toString());
        duePaymentField.setText(paymentTM.getAvailabalAmout().toString());
        Registration registration = registrationBO.getRegistrationByPatientId(patientIdField.getText());
        Programs programs = registration.getPrograms();
        therapistIdField.setText(programs.getTId());
        therapistNameField.setText(programs.getPName());


    }
}
