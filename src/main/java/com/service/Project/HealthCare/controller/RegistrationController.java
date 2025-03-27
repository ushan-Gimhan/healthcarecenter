package com.service.Project.HealthCare.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class RegistrationController {

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnReset;

    @FXML
    private Button btnUpdate;

    @FXML
    private ComboBox<?> cmbPatient;

    @FXML
    private ComboBox<?> cmbProgram;

    @FXML
    private TableColumn<?, ?> colDate;

    @FXML
    private TableColumn<?, ?> colPatient;

    @FXML
    private TableColumn<?, ?> colPayment;

    @FXML
    private TableColumn<?, ?> colProgram;

    @FXML
    private TableColumn<?, ?> colRegId;

    @FXML
    private DatePicker datePicker;

    @FXML
    private TableView<?> registrationTable;

    @FXML
    private TextField txtPayment;

    @FXML
    private Label txtRegId;

    @FXML
    void addRegistration(ActionEvent event) {

    }

    @FXML
    void clicked(MouseEvent event) {

    }

    @FXML
    void deleteRegistration(ActionEvent event) {

    }

    @FXML
    void resetFields(ActionEvent event) {

    }

    @FXML
    void updateRegistration(ActionEvent event) {

    }

}
