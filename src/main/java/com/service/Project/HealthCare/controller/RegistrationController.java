package com.service.Project.HealthCare.controller;

import com.service.Project.HealthCare.bo.BOFactory;
import com.service.Project.HealthCare.bo.custom.PatientBO;
import com.service.Project.HealthCare.bo.custom.RegistrationBO;
import com.service.Project.HealthCare.bo.custom.TherapyProgramBO;
import com.service.Project.HealthCare.dto.RegitrationDTO;
import com.service.Project.HealthCare.dto.TM.RegistrationTM;
import com.service.Project.HealthCare.entity.Patient;
import com.service.Project.HealthCare.entity.Programs;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class RegistrationController implements Initializable {

    RegistrationBO registrationBO;
    PatientBO pationBO;
    TherapyProgramBO programBO;

    {
        try {
            registrationBO = (RegistrationBO) BOFactory.getInstance().getBOType(BOFactory.BOType.registration);
            pationBO = (PatientBO) BOFactory.getInstance().getBOType(BOFactory.BOType.Patient);
            programBO = (TherapyProgramBO) BOFactory.getInstance().getBOType(BOFactory.BOType.Program);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    private Label lblSelectedPatient;

    @FXML
    private Label lblSelectedProgram;

    @FXML
    public Label lblProgramPrice;

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
        LocalDate date = datePicker.getValue();
        String patientValue = cmbPatient.getValue();
        String program = cmbProgram.getValue();

        Patient patient = pationBO.getPationByID(patientValue);
        Programs programs = programBO.getProgramByName(program);

        txtRegId.setStyle("-fx-text-fill: black;");
        txtPayment.setStyle("-fx-text-fill: black;");

        String idPattern = "^[A-Za-z0-9]{4,10}$";
        String paymentPattern = "^\\d+(\\.\\d{1,2})?$";

        if (id.isEmpty() || payment.isEmpty() ||date == null|| patientValue == null || program == null) {
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

        RegitrationDTO regitrationDTO = new RegitrationDTO(id, payment,date, patient, programs);
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
            datePicker.setValue(selected.getDate().toLocalDate());
            cmbProgram.setValue(selected.getProgram_Id());
            cmbPatient.setValue(selected.getPatient_Id());
            txtPayment.setText(selected.getPayment().toString());
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
        LocalDate date = datePicker.getValue();
        String patientValue = cmbPatient.getValue();
        String program = cmbProgram.getValue();

        Programs programs = programBO.getProgramByName(program);
        Patient patient=pationBO.getPationByID(patientValue);


        txtRegId.setStyle("-fx-text-fill: black;");
        txtPayment.setStyle("-fx-text-fill: black;");


        String idPattern = "^[A-Za-z0-9]{4,10}$"; // 4-10 alphanumeric characters
        String paymentPattern = "^\\d+(\\.\\d{1,2})?$"; // Valid decimal number

        if (id.isEmpty() || payment.isEmpty() || date==null || patientValue == null || program == null) {
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

        RegitrationDTO regitrationDTO = new RegitrationDTO(id, payment,date, patient, programs);
        boolean updated = registrationBO.update(regitrationDTO);

        if (updated) {
            new Alert(Alert.AlertType.INFORMATION, "Registration Updated Successfully!").show();
            refreshPage();
        } else {
            new Alert(Alert.AlertType.ERROR, "Failed to Update Registration!").show();
        }
    }

    public void refreshPage() {
        txtPayment.setText("");
        cmbPatient.setValue(null);
        cmbProgram.setValue(null);
        datePicker.setValue(null);
        lblSelectedPatient.setText("");
        lblSelectedProgram.setText("");

        txtRegId.setStyle("-fx-text-fill: black;");
        txtPayment.setStyle("-fx-text-fill: black;");
        registrationBO.generateId();
        loadTableData();
        txtRegId.setText(registrationBO.generateId());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colRegId.setCellValueFactory(new PropertyValueFactory<>("regId"));
        colPatient.setCellValueFactory(new PropertyValueFactory<>("patient_Id"));
        colProgram.setCellValueFactory(new PropertyValueFactory<>("program_Id"));
        colPayment.setCellValueFactory(new PropertyValueFactory<>("payment"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));

        loadPatients();
        loadPrograms();
        genarateID();

        loadTableData();
    }
    public void loadPatients(){
        List<String> patientIds = pationBO.getAllPatientIds();
        cmbPatient.getItems().setAll(patientIds);
    }
    public void loadPrograms(){
        List<String> programs = programBO.getAllPrograms();
        cmbProgram.getItems().setAll(programs);
    }
    public void loadTableData(){
        List<RegitrationDTO> all= registrationBO.findAll();
        ObservableList<RegistrationTM> data = FXCollections.observableArrayList();

        for(RegitrationDTO regitrationDTO : all ){
            RegistrationTM registrationTM = new RegistrationTM(
                    regitrationDTO.getRegId(),
                    regitrationDTO.getPayment(),
                    regitrationDTO.getDate(),
                    regitrationDTO.getPatienId(),
                    regitrationDTO.getProgId());
            data.add(registrationTM);
        }
        registrationTable.setItems(data);
    }

    public void onPatientSelected(ActionEvent event) {
        String patientValue = cmbPatient.getValue();

        Patient patient=pationBO.getPationByID(patientValue);
        if (patient != null &&patient.getName() != null) {
            lblSelectedPatient.setText(patient.getName());
        } else {
            lblSelectedPatient.setText(""); // or any default message you'd like
        }
    }

    public void onProgramSelected(ActionEvent event) {
        String program = cmbProgram.getValue();

        Programs programs = programBO.getProgramByName(program);
        System.out.println(programs);

        if (programs != null && programs.getPrice() != null) {
            lblSelectedProgram.setText(programs.getPrice().toString());
        } else {
            lblSelectedProgram.setText("0.00"); // or any default message you'd like
        }
    }

    public void genarateID(){
        txtRegId.setText(registrationBO.generateId());
    }
}
