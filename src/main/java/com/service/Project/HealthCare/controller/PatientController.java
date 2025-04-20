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
        int age = Integer.parseInt(txtAge.getText());
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

        boolean isValidName= name.matches(namePattern);
        boolean isValidNic = NIC.matches(nicPattern);
        boolean isValidEmail = email.matches(emailPattern);
        boolean isValidMobileNumber = mobileNumber.matches(phonePattern);


        if (isValidEmail && isValidMobileNumber && isValidName) {
            PatientDTO patientDTO = new PatientDTO(patientId,name,email,NIC,mobileNumber,gender,age);

            boolean saved = patientBO.save(patientDTO);

            if(saved){
                new Alert(Alert.AlertType.INFORMATION,"Patient saved!!");
                refreshPage();
            }else {
                new Alert(Alert.AlertType.ERROR,"Patient not saved!!");
            }
        }

    }

    @FXML
    void clicked(MouseEvent event) {
        PatientTM patientTM=patientTable.getSelectionModel().getSelectedItem();
        if(patientTM!=null){
            txtName.setText(patientTM.getName());
            txtEmail.setText(patientTM.getEmail());
            txtMobileNumber.setText(patientTM.getMobile());
            txtAge.setText(String.valueOf(patientTM.getAge()));
            txtnic.setText(patientTM.getNIC());
            cmbGender.getSelectionModel().select(patientTM.getGender());
        }
    }

    @FXML
    void deletePatient(ActionEvent event) {
        String patientId = txtPatientId.getText();

        patientBO.delete(patientId);

        refreshPage();
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
        int age = Integer.parseInt(txtAge.getText());
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

        boolean isValidName= name.matches(namePattern);
        boolean isValidNic = NIC.matches(nicPattern);
        boolean isValidEmail = email.matches(emailPattern);
        boolean isValidMobileNumber = mobileNumber.matches(phonePattern);

        if (isValidEmail && isValidNic && isValidName && isValidMobileNumber){
            PatientDTO patientDTO = new PatientDTO(patientId,name,email,NIC,mobileNumber,gender,age);

            boolean updated = patientBO.update(patientDTO);
            if(updated){
                new Alert(Alert.AlertType.INFORMATION,"Patient updated!!");
                refreshPage();
            }else {
                new Alert(Alert.AlertType.ERROR,"Patient not updated!!");
            }
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
