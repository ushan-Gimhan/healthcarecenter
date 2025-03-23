package com.service.Project.HealthCare.controller;

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
    private TableColumn<?, ?> colAge;

    @FXML
    private TableColumn<?, ?> colEmail;

    @FXML
    private TableColumn<?, ?> colGender;

    @FXML
    private TableColumn<?, ?> colMobileNumber;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colPatientId;

    @FXML
    private TableView<?> patientTable;

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
