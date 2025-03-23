package com.service.Project.HealthCare.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class TherapistController {

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnReset;

    @FXML
    private Button btnUpdate;

    @FXML
    private TableColumn<?, ?> colEmail;

    @FXML
    private TableColumn<?, ?> colExperience;

    @FXML
    private TableColumn<?, ?> colMobileNumber;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colSpecialization;

    @FXML
    private TableColumn<?, ?> colTherapistId;

    @FXML
    private TableView<?> therapistTable;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtExperience;

    @FXML
    private TextField txtMobileNumber;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtSpecialization;

    @FXML
    private Label txtTherapistId;

    @FXML
    void addTherapist(ActionEvent event) {

    }

    @FXML
    void clicked(MouseEvent event) {

    }

    @FXML
    void deleteTherapist(ActionEvent event) {

    }

    @FXML
    void resetFields(ActionEvent event) {

    }

    @FXML
    void updateTherapist(ActionEvent event) {

    }

}
