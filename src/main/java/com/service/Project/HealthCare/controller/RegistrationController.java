package com.service.Project.HealthCare.controller;

import com.service.Project.HealthCare.bo.BOFactory;
import com.service.Project.HealthCare.bo.custom.RegistrationBO;
import com.service.Project.HealthCare.dto.RegitrationDTO;
import com.service.Project.HealthCare.dto.TM.RegistrationTM;
import com.service.Project.HealthCare.entity.Registration;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.time.LocalDate;

public class RegistrationController {

    RegistrationBO registrationBO;

    {
        try {
            registrationBO = (RegistrationBO) BOFactory.getInstance().getBOType(BOFactory.BOType.registration);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnReset;

    @FXML
    private Button btnUpdate;

    @FXML
    private ComboBox<String> cmbPatient;

    @FXML
    private ComboBox<String> cmbProgram;

    @FXML
    private TableColumn<RegistrationTM,String> colDate;

    @FXML
    private TableColumn<RegistrationTM,String> colPatient;

    @FXML
    private TableColumn<RegistrationTM,String> colPayment;

    @FXML
    private TableColumn<RegistrationTM,String> colProgram;

    @FXML
    private TableColumn<RegistrationTM,String> colRegId;

    @FXML
    private DatePicker datePicker;

    @FXML
    private TableView<RegistrationTM> registrationTable;

    @FXML
    private TextField txtPayment;

    @FXML
    private Label txtRegId;

    @FXML
    void addRegistration(ActionEvent event) {
        String id = txtRegId.getText();
        String payment = txtPayment.getText();
        String patientValue = cmbPatient.getValue();
        String program = cmbProgram.getValue();

        txtRegId.setStyle("-fx-text-fill: black;");
        txtPayment.setStyle("-fx-text-fill: black;");

        String idPattern = "^[A-Za-z0-9]{4,10}$";
        String paymentPattern = "^\\d+(\\.\\d{1,2})?$";

        if (id.isEmpty() || payment.isEmpty() || patientValue == null || program == null) {
            new Alert(Alert.AlertType.ERROR, "All fields must be filled.").show();
            return;
        }

        if (!id.matches(idPattern)) {
            txtRegId.setStyle("-fx-text-fill: red;");
            new Alert(Alert.AlertType.ERROR, "Invalid Registration ID. Use 4-10 letters or numbers.").show();
            return;
        }

        if (!payment.matches(paymentPattern)) {
            txtPayment.setStyle("-fx-text-fill: red;");
            new Alert(Alert.AlertType.ERROR, "Invalid Payment Amount. Use valid numbers (e.g., 100.50)").show();
            return;
        }

        RegitrationDTO regitrationDTO = new RegitrationDTO(id, payment, patientValue, program);
        boolean saved = registrationBO.save(regitrationDTO);

        if (saved) {
            new Alert(Alert.AlertType.INFORMATION, "Registration Completed!!").show();
            refreshPage();
        } else {
            new Alert(Alert.AlertType.ERROR, "Can't Complete Registration!!").show();
        }

    }

    @FXML
    void clicked(MouseEvent event) {
        RegistrationTM selected = registrationTable.getSelectionModel().getSelectedItem();
        if (selected != null) {
            txtRegId.setText(selected.getRegId());
            txtPayment.setText(String.valueOf(selected.getPayment()));
            datePicker.setValue(LocalDate.parse((CharSequence) selected.getDate())); // assumes date is saved in "yyyy-MM-dd" format
        }
    }

    @FXML
    void deleteRegistration(ActionEvent event) {
        String id = txtRegId.getText();

        if (id.isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Please select a registration to delete.").show();
            return;
        }

        boolean deleted = registrationBO.delete(id);

        if (deleted) {
            new Alert(Alert.AlertType.INFORMATION, "Registration Deleted Successfully!").show();
            refreshPage();
        } else {
            new Alert(Alert.AlertType.ERROR, "Failed to Delete Registration!").show();
        }
    }

    @FXML
    void resetFields(ActionEvent event) {
        refreshPage();
        registrationBO.generateId();
    }

    @FXML
    void updateRegistration(ActionEvent event) {
        String id = txtRegId.getText();
        String payment = txtPayment.getText();
        String patientValue = cmbPatient.getValue();
        String program = cmbProgram.getValue();

        txtRegId.setStyle("-fx-text-fill: black;");
        txtPayment.setStyle("-fx-text-fill: black;");


        String idPattern = "^[A-Za-z0-9]{4,10}$"; // 4-10 alphanumeric characters
        String paymentPattern = "^\\d+(\\.\\d{1,2})?$"; // Valid decimal number

        if (id.isEmpty() || payment.isEmpty() || patientValue == null || program == null) {
            new Alert(Alert.AlertType.ERROR, "All fields must be filled.").show();
            return;
        }

        if (!id.matches(idPattern)) {
            txtRegId.setStyle("-fx-text-fill: red;");
            new Alert(Alert.AlertType.ERROR, "Invalid Registration ID. Use 4-10 letters or numbers.").show();
            return;
        }

        if (!payment.matches(paymentPattern)) {
            txtPayment.setStyle("-fx-text-fill: red;");
            new Alert(Alert.AlertType.ERROR, "Invalid Payment Amount. Use valid numbers (e.g., 100.50)").show();
            return;
        }

        RegitrationDTO regitrationDTO = new RegitrationDTO(id, payment, patientValue, program);
        boolean updated = registrationBO.update(regitrationDTO);

        if (updated) {
            new Alert(Alert.AlertType.INFORMATION, "Registration Updated Successfully!").show();
            refreshPage();
        } else {
            new Alert(Alert.AlertType.ERROR, "Failed to Update Registration!").show();
        }
    }

    public void refreshPage() {
        txtRegId.setText("");
        txtPayment.setText("");
        cmbPatient.setValue(null);
        cmbProgram.setValue(null);
        datePicker.setValue(null);

        txtRegId.setStyle("-fx-text-fill: black;");
        txtPayment.setStyle("-fx-text-fill: black;");
    }

}
