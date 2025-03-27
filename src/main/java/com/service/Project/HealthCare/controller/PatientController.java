package com.service.Project.HealthCare.controller;

import com.service.Project.HealthCare.bo.BOFactory;
import com.service.Project.HealthCare.bo.custom.PatientBO;
import com.service.Project.HealthCare.dto.PatientDTO;
import com.service.Project.HealthCare.dto.TM.PatientTM;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PatientController implements Initializable {
    PatientBO patientBO = (PatientBO) BOFactory.getInstance().getBOType(BOFactory.BOType.Patient);

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnReset;

    @FXML
    private Button btnUpdate;

    @FXML
    private ComboBox<String> cmbGender;

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

    public PatientController() throws IOException {
    }

    @FXML
    void addPatient(ActionEvent event) {
        String patientId = txtPatientId.getText();
        String name = txtName.getText();
        String email = txtEmail.getText();
        String mobileNumber = txtMobileNumber.getText();
        String age = txtAge.getText();
        String gender = cmbGender.getSelectionModel().getSelectedItem().toString();
        String NIC = txtnic.getText();

        txtName.setStyle(txtName.getStyle() + "-fx-text-fill: blue;");
        txtnic.setStyle(txtnic.getStyle() + "-fx-text-fill: blue;");
        txtEmail.setStyle(txtEmail.getStyle() + "-fx-text-fill: blue;");
        txtMobileNumber.setStyle(txtMobileNumber.getStyle() + "-fx-text-fill: blue;");

        String namePattern = "^[A-Za-z ]+$";
        String nicPattern = "^\\d{9}[VX]$";
        String emailPattern = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
        String phonePattern = "^(\\d+)||((\\d+\\.)(\\d){2})$";
        String agePattern = "^\\d{1,3}$";

        boolean isValidName= name.matches(namePattern);
        boolean isValidNic = NIC.matches(nicPattern);
        boolean isValidEmail = email.matches(emailPattern);
        boolean isValidMobileNumber = mobileNumber.matches(mobileNumber);
        boolean isValidAge = age.matches(agePattern);

//        if (isValidAge && isValidEmail && isValidMobileNumber && isValidName) {
//            PatientDTO patientDTO = new PatientDTO(patientId,name,email,mobileNumber,age,gender);
//
//        }



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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cmbGender.setItems(FXCollections.observableArrayList("Male", "Female","Other"));

        txtPatientId.setText(patientBO.generateId());
    }

}
