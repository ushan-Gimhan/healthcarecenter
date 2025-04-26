package com.service.Project.HealthCare.controller;

import com.service.Project.HealthCare.bo.BOFactory;
import com.service.Project.HealthCare.bo.custom.TheropistBO;
import com.service.Project.HealthCare.dto.TM.PatientTM;
import com.service.Project.HealthCare.dto.TM.TeropistTM;
import com.service.Project.HealthCare.dto.TM.TherapyProgramTM;
import com.service.Project.HealthCare.dto.TherapyProgramDTO;
import com.service.Project.HealthCare.dto.TheropistDTO;
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

public class TherapistController implements Initializable {

    @FXML
    private TableColumn <TeropistTM, String>colGender;
    @FXML
    private TableColumn <TeropistTM, String>colStatus;
    @FXML
    private ComboBox<String> cmbGender;
    @FXML
    private ComboBox<String>cmbStatus;
    @FXML
    private ComboBox<String> cmbSpecialization;
    @FXML
    private TextField txtAge;
    @FXML
    private Button btnAdd;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnReset;

    @FXML
    private Button btnUpdate;

    @FXML
    private TableColumn<TeropistTM, String> colEmail;

    @FXML
    private TableColumn <TeropistTM, String>colAge;

    @FXML
    private TableColumn<TeropistTM, String> colExperience;

    @FXML
    private TableColumn<TeropistTM, String> colMobileNumber;

    @FXML
    private TableColumn<TeropistTM, String> colName;

    @FXML
    private TableColumn<TeropistTM, String> colSpecialization;

    @FXML
    private TableColumn<TeropistTM, String> colTherapistId;

    @FXML
    private TableView<TeropistTM> therapistTable;

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

    TheropistBO theropistBO;

    {
        try {
            theropistBO = (TheropistBO) BOFactory.getInstance().getBOType(BOFactory.BOType.Therapist);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void addTherapist(ActionEvent event) {
        String tId = txtTherapistId.getText();
        String name = txtName.getText();
        String email = txtEmail.getText();
        String mobileNumber = txtMobileNumber.getText();
        String specialization = cmbSpecialization.getValue();
        String experience = txtExperience.getText();
        String gender = cmbGender.getValue();
        String status = cmbStatus.getValue();
        int age = -1;

        try {
            age = Integer.parseInt(txtAge.getText());
        } catch (NumberFormatException e) {
            new Alert(Alert.AlertType.ERROR, "Invalid age. Please enter a valid number.").show();
            return;
        }

        txtName.setStyle("-fx-text-fill: black;");
        txtEmail.setStyle("-fx-text-fill: black;");
        txtMobileNumber.setStyle("-fx-text-fill: black;");
        txtExperience.setStyle("-fx-text-fill: black;");
        txtAge.setStyle("-fx-text-fill: black;");

        String namePattern = "^[A-Za-z ]+$";
        String emailPattern = "^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$";
        String phonePattern = "^(07\\d{8})$";
        String expPattern = "^\\d{1,2}(\\s?(years|Months|months|Years))?$";

        if (tId.isEmpty() || name.isEmpty() || email.isEmpty() || mobileNumber.isEmpty() || specialization == null || experience.isEmpty() || gender == null || status == null || age <= 0) {
            new Alert(Alert.AlertType.ERROR, "Please fill in all fields.").show();
            return;
        }

        boolean isValidName = name.matches(namePattern);
        boolean isValidEmail = email.matches(emailPattern);
        boolean isValidMobile = mobileNumber.matches(phonePattern);
        boolean isValidExp = experience.matches(expPattern);

        if (!isValidName) {
            txtName.setStyle("-fx-text-fill: red;");
            new Alert(Alert.AlertType.ERROR, "Invalid name. Only letters and spaces allowed.").show();
            return;
        }

        if (!isValidEmail) {
            txtEmail.setStyle("-fx-text-fill: red;");
            new Alert(Alert.AlertType.ERROR, "Invalid email format.").show();
            return;
        }

        if (!isValidMobile) {
            txtMobileNumber.setStyle("-fx-text-fill: red;");
            new Alert(Alert.AlertType.ERROR, "Invalid mobile number. Use 07XXXXXXXX format.").show();
            return;
        }

        if (!isValidExp) {
            txtExperience.setStyle("-fx-text-fill: red;");
            new Alert(Alert.AlertType.ERROR, "Invalid experience. Use numeric values like '5 years'.").show();
            return;
        }

        TheropistDTO therapistDTO = new TheropistDTO(tId, name, gender, email, mobileNumber, specialization, experience, age, status);

        boolean saved = theropistBO.save(therapistDTO);

        if (saved) {
            new Alert(Alert.AlertType.INFORMATION, "Therapist saved!!").show();
            refreshPage();
        } else {
            new Alert(Alert.AlertType.ERROR, "Therapist not saved!!").show();
        }

    }

    @FXML
    void clicked(MouseEvent event) {
        TeropistTM teropistTM = therapistTable.getSelectionModel().getSelectedItem();

        if (teropistTM != null) {
            txtEmail.setText(teropistTM.getEmail());
            txtExperience.setText(teropistTM.getExperience());
            txtName.setText(teropistTM.getName());
            txtTherapistId.setText(teropistTM.getId());
            txtEmail.setText(teropistTM.getEmail());
            txtMobileNumber.setText(teropistTM.getPhone());
            txtExperience.setText(teropistTM.getExperience());
            cmbSpecialization.setValue(teropistTM.getSpecialization());
            cmbStatus.setValue(teropistTM.getStatus());
            cmbGender.setValue(teropistTM.getGender());

        }
        btnAdd.setDisable(true);
    }

    @FXML
    void deleteTherapist(ActionEvent event) {
        boolean deleted=theropistBO.delete(txtTherapistId.getText());

        if(deleted){
            new Alert(Alert.AlertType.INFORMATION,"Therapist deleted!!");
            refreshPage();
        }else {
            new Alert(Alert.AlertType.ERROR,"Therapist not deleted!!");
        }
    }

    @FXML
    void resetFields(ActionEvent event) {
        btnAdd.setDisable(false);
        refreshPage();

    }

    @FXML
    void updateTherapist(ActionEvent event) {
        String tId = txtTherapistId.getText();
        String name = txtName.getText();
        String email = txtEmail.getText();
        String mobileNumber = txtMobileNumber.getText();
        String specialization = cmbSpecialization.getValue();
        String experience = txtExperience.getText();
        String gender = cmbGender.getValue();
        String status = cmbStatus.getValue();
        int age = -1;

        try {
            age = Integer.parseInt(txtAge.getText());
        } catch (NumberFormatException e) {
            new Alert(Alert.AlertType.ERROR, "Invalid age. Please enter a valid number.").show();
            return;
        }

        txtName.setStyle("-fx-text-fill: black;");
        txtEmail.setStyle("-fx-text-fill: black;");
        txtMobileNumber.setStyle("-fx-text-fill: black;");
        txtExperience.setStyle("-fx-text-fill: black;");
        txtAge.setStyle("-fx-text-fill: black;");

        String namePattern = "^[A-Za-z ]+$";
        String emailPattern = "^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$";
        String phonePattern = "^(07\\d{8})$";
        String expPattern = "^\\d{1,2}(\\s?(years|Months|months|Years))?$";

        if (tId.isEmpty() || name.isEmpty() || email.isEmpty() || mobileNumber.isEmpty() || specialization == null || experience.isEmpty() || gender == null || status == null || age <= 0) {
            new Alert(Alert.AlertType.ERROR, "Please fill in all fields.").show();
            return;
        }

        boolean isValidName = name.matches(namePattern);
        boolean isValidEmail = email.matches(emailPattern);
        boolean isValidMobile = mobileNumber.matches(phonePattern);
        boolean isValidExp = experience.matches(expPattern);

        if (!isValidName) {
            txtName.setStyle("-fx-text-fill: red;");
            new Alert(Alert.AlertType.ERROR, "Invalid name. Only letters and spaces allowed.").show();
            return;
        }

        if (!isValidEmail) {
            txtEmail.setStyle("-fx-text-fill: red;");
            new Alert(Alert.AlertType.ERROR, "Invalid email format.").show();
            return;
        }

        if (!isValidMobile) {
            txtMobileNumber.setStyle("-fx-text-fill: red;");
            new Alert(Alert.AlertType.ERROR, "Invalid mobile number. Use 07XXXXXXXX format.").show();
            return;
        }

        if (!isValidExp) {
            txtExperience.setStyle("-fx-text-fill: red;");
            new Alert(Alert.AlertType.ERROR, "Invalid experience. Use numeric values like '5 years'.").show();
            return;
        }

        TheropistDTO therapistDTO = new TheropistDTO(tId, name, gender, email, mobileNumber, specialization, experience, age, status);

        boolean saved = theropistBO.update(therapistDTO);

        if (saved) {
            new Alert(Alert.AlertType.INFORMATION, "Therapist updated!!").show();
            refreshPage();
        } else {
            new Alert(Alert.AlertType.ERROR, "Therapist not update!!").show();
        }

    }
    public void genarateID() {
        txtTherapistId.setText(theropistBO.generateId());
    }

    public void refreshPage() {
        genarateID();

        txtEmail.setText("");
        txtExperience.setText("");
        txtMobileNumber.setText("");
        txtName.setText("");
        txtAge.setText("");
        cmbSpecialization.setValue("");
        cmbStatus.setValue("");
        cmbGender.setValue("");
        loadTableData();

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        genarateID();
        loadTableData();

        colTherapistId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colExperience.setCellValueFactory(new PropertyValueFactory<>("experience"));
        colMobileNumber.setCellValueFactory(new PropertyValueFactory<>("phone"));
        colSpecialization.setCellValueFactory(new PropertyValueFactory<>("specialization"));
        colGender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        colAge.setCellValueFactory(new PropertyValueFactory<>("age"));

    }

    public void loadTableData(){
        List<TheropistDTO> allTheropist = theropistBO.findAll();
        ObservableList<TeropistTM> theropistTMS = FXCollections.observableArrayList();

        for (TheropistDTO theropistDTO : allTheropist) {
            TeropistTM theropistTM = new TeropistTM(
                    theropistDTO.getId(),
                    theropistDTO.getName(),
                    theropistDTO.getExperience(),
                    theropistDTO.getPhone(),
                    theropistDTO.getSpecialization(),
                    theropistDTO.getEmail(),
                    theropistDTO.getGender(),
                    theropistDTO.getStaus(),
                    theropistDTO.getAge()
            );
            theropistTMS.add(theropistTM);
        }
        therapistTable.setItems(theropistTMS);
    }
}
