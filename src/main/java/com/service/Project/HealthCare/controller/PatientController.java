package com.service.Project.HealthCare.controller;

import com.service.Project.HealthCare.bo.BOFactory;
import com.service.Project.HealthCare.bo.custom.PatientBO;
import com.service.Project.HealthCare.dto.PatientDTO;
import com.service.Project.HealthCare.dto.TM.PatientTM;
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
import java.util.List;
import java.util.ResourceBundle;

public class PatientController implements Initializable {
    PatientBO patientBO;

    {
        try {
            patientBO = (PatientBO) BOFactory.getInstance().getBOType(BOFactory.BOType.Patient);
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

    @FXML
    void addPatient(ActionEvent event) {
        String patientId = txtPatientId.getText();
        String name = txtName.getText();
        String email = txtEmail.getText();
        String mobileNumber = txtMobileNumber.getText();
        String ageText = txtAge.getText();
        String gender = cmbGender.getValue();
        String nic = txtnic.getText();

        txtName.setStyle("-fx-text-fill: black;");
        txtnic.setStyle("-fx-text-fill: black;");
        txtEmail.setStyle("-fx-text-fill: black;");
        txtMobileNumber.setStyle("-fx-text-fill: black;");

        String namePattern = "^[A-Za-z ]+$";
        String nicPattern = "^\\d{9}[VXvx]$"; // NIC ending with V/X
        String emailPattern = "^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$";
        String phonePattern = "^(07\\d{8})$"; // SL format

        if (name.isEmpty() || email.isEmpty() || mobileNumber.isEmpty() || ageText.isEmpty() || gender == null || nic.isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Please fill in all fields.").show();
            return;
        }

        boolean isValidName = name.matches(namePattern);
        boolean isValidNic = nic.matches(nicPattern);
        boolean isValidEmail = email.matches(emailPattern);
        boolean isValidMobileNumber = mobileNumber.matches(phonePattern);

        if (!isValidName) {
            txtName.setStyle("-fx-text-fill: red;");
            new Alert(Alert.AlertType.ERROR, "Invalid name. Only letters and spaces allowed.").show();
            return;
        }

        if (!isValidNic) {
            txtnic.setStyle("-fx-text-fill: red;");
            new Alert(Alert.AlertType.ERROR, "Invalid NIC. Must be 9 digits followed by 'V' or 'X'.").show();
            return;
        }

        if (!isValidEmail) {
            txtEmail.setStyle("-fx-text-fill: red;");
            new Alert(Alert.AlertType.ERROR, "Invalid email address.").show();
            return;
        }

        if (!isValidMobileNumber) {
            txtMobileNumber.setStyle("-fx-text-fill: red;");
            new Alert(Alert.AlertType.ERROR, "Invalid phone number. Format should be 07XXXXXXXX.").show();
            return;
        }

        int age;
        try {
            age = Integer.parseInt(ageText);
        } catch (NumberFormatException e) {
            new Alert(Alert.AlertType.ERROR, "Invalid age. Must be a number.").show();
            return;
        }

        // Save operation
        PatientDTO patientDTO = new PatientDTO(patientId, name, email, nic, mobileNumber, gender, age);
        boolean saved = patientBO.save(patientDTO);

        if (saved) {
            new Alert(Alert.AlertType.INFORMATION, "Patient saved!!").show();
            refreshPage();
        } else {
            new Alert(Alert.AlertType.ERROR, "Patient not saved!!").show();
        }
    }

    @FXML
    void clicked(MouseEvent event) {
        PatientTM patientTM=patientTable.getSelectionModel().getSelectedItem();
        if(patientTM!=null){
            txtPatientId.setText(patientTM.getId());
            txtName.setText(patientTM.getName());
            txtnic.setText(patientTM.getNIC());
            txtEmail.setText(patientTM.getEmail());
            txtMobileNumber.setText(patientTM.getMobile());
            txtAge.setText(String.valueOf(patientTM.getAge()));
            cmbGender.getSelectionModel().select(patientTM.getGender());
        }
    }

    @FXML
    void deletePatient(ActionEvent event) {
        String patientId = txtPatientId.getText();

        boolean isDeleted = patientBO.delete(patientId);

        if (isDeleted) {
            new Alert(Alert.AlertType.INFORMATION, "Patient deleted successfully").showAndWait();
            refreshPage();
        } else {
            new Alert(Alert.AlertType.ERROR, "Failed to delete patient").showAndWait();
        }
    }

    @FXML
    void resetFields(ActionEvent event) {
       refreshPage();
    }

    @FXML
    void updatePatient(ActionEvent event) {
        String patientId = txtPatientId.getText();
        String name = txtName.getText();
        String email = txtEmail.getText();
        String mobileNumber = txtMobileNumber.getText();
        String ageText = txtAge.getText();
        String gender = cmbGender.getValue();
        String nic = txtnic.getText();

        txtName.setStyle("-fx-text-fill: black;");
        txtnic.setStyle("-fx-text-fill: black;");
        txtEmail.setStyle("-fx-text-fill: black;");
        txtMobileNumber.setStyle("-fx-text-fill: black;");

        String namePattern = "^[A-Za-z ]+$";
        String nicPattern = "^\\d{9}[VXvx]$"; // NIC ends with V or X (case-insensitive)
        String emailPattern = "^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$";
        String phonePattern = "^(07\\d{8})$"; // SL mobile numbers e.g. 0712345678

        if (name.isEmpty() || email.isEmpty() || mobileNumber.isEmpty() || nic.isEmpty() || ageText.isEmpty() || gender == null) {
            new Alert(Alert.AlertType.ERROR, "All fields must be filled.").show();
            return;
        }

        if (!name.matches(namePattern)) {
            txtName.setStyle("-fx-text-fill: red;");
            new Alert(Alert.AlertType.ERROR, "Invalid name. Only letters and spaces allowed.").show();
            return;
        }

        if (!nic.matches(nicPattern)) {
            txtnic.setStyle("-fx-text-fill: red;");
            new Alert(Alert.AlertType.ERROR, "Invalid NIC. Must be 9 digits followed by 'V' or 'X'.").show();
            return;
        }

        if (!email.matches(emailPattern)) {
            txtEmail.setStyle("-fx-text-fill: red;");
            new Alert(Alert.AlertType.ERROR, "Invalid email address.").show();
            return;
        }

        if (!mobileNumber.matches(phonePattern)) {
            txtMobileNumber.setStyle("-fx-text-fill: red;");
            new Alert(Alert.AlertType.ERROR, "Invalid phone number. Format: 07XXXXXXXX").show();
            return;
        }

        int age;
        try {
            age = Integer.parseInt(ageText);
        } catch (NumberFormatException e) {
            new Alert(Alert.AlertType.ERROR, "Invalid age. Must be a number.").show();
            return;
        }

        PatientDTO patientDTO = new PatientDTO(patientId, name, email, nic, mobileNumber, gender, age);

        boolean updated = patientBO.update(patientDTO);
        if (updated) {
            new Alert(Alert.AlertType.INFORMATION, "Patient updated!!").show();
            refreshPage();
        } else {
            new Alert(Alert.AlertType.ERROR, "Patient not updated!!").show();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cmbGender.setItems(FXCollections.observableArrayList("Male", "Female","Other"));
        colPatientId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colMobileNumber.setCellValueFactory(new PropertyValueFactory<>("mobile"));
        colAge.setCellValueFactory(new PropertyValueFactory<>("age"));
        colGender.setCellValueFactory(new PropertyValueFactory<>("gender"));

        genarateId();
        loadTableData();

    }

    public void genarateId(){
        txtPatientId.setText(patientBO.generateId());
    }
    public void refreshPage(){
        genarateId();
        loadTableData();

        txtName.setText("");
        txtEmail.setText("");
        txtMobileNumber.setText("");
        txtAge.setText("");
        txtnic.setText("");
        cmbGender.getSelectionModel().clearSelection();
    }
    public void loadTableData(){
        List<PatientDTO> all = patientBO.findAll();

        ObservableList<PatientTM> data = FXCollections.observableArrayList();

        for (PatientDTO patientDTO : all) {
            PatientTM patientTM = new PatientTM(patientDTO.getId(),patientDTO.getName(),patientDTO.getMobile(),patientDTO.getEmail(),patientDTO.getNIC(),patientDTO.getGender(),patientDTO.getAge());

            data.add(patientTM);
        }
        patientTable.setItems(data);
    }
}
