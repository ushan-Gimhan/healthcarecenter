package com.service.Project.HealthCare.controller;

import com.service.Project.HealthCare.dto.TM.PatientTM;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class PatientController {

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnReset;

    @FXML
    private Button btnUpdate;

    @FXML
    private ComboBox<?> cmbGender;

    @FXML
    private TableColumn<String, PatientTM> colAge;

    @FXML
    private TableColumn<String, PatientTM> colEmail;

    @FXML
    private TableColumn<String, PatientTM> colGender;

    @FXML
    private TableColumn<String, PatientTM> colMobileNumber;

    @FXML
    private TableColumn<String, PatientTM> colName;

    @FXML
    private TableColumn<String, PatientTM> colPatientId;

    @FXML
    private TableView<PatientTM> patientTable;

    @FXML
    private TextField txtAge;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtMobileNumber;

    @FXML
    private TextField txtName;

    @FXML
    private Label txtPatientId;

    @FXML
    private TextField txtnic;

    @FXML
    void addPatient(ActionEvent event) {
        String patientId = txtPatientId.getText();
        String name = txtName.getText();
        String email = txtEmail.getText();
        String mobileNumber = txtMobileNumber.getText();
        String age = txtAge.getText();
        String gender = cmbGender.getSelectionModel().getSelectedItem().toString();

    }

    @FXML
    void clicked(MouseEvent event) {

    }

    @FXML
    void deletePatient(ActionEvent event) {

    }

    @FXML
    void resetFields(ActionEvent event) {

    }

    @FXML
    void updatePatient(ActionEvent event) {

    }

}
