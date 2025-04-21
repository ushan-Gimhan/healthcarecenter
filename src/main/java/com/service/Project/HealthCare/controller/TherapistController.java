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
        String email = txtEmail.getText();
        String experience = txtExperience.getText();
        String mobileNumber = txtMobileNumber.getText();
        String name = txtName.getText();
        String specialization = txtSpecialization.getText();
        String gender = txtAge.getText();

        boolean saved=theropistBO.save(new TheropistDTO(tId,name,gender,email,mobileNumber,specialization,experience));

        if(saved){
            new Alert(Alert.AlertType.INFORMATION,"Patient saved!!");
            refreshPage();
        }else {
            new Alert(Alert.AlertType.ERROR,"Patient not saved!!");
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
            txtSpecialization.setText(teropistTM.getSpecialization());
            txtEmail.setText(teropistTM.getEmail());
        }
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
        refreshPage();

    }

    @FXML
    void updateTherapist(ActionEvent event) {
        String tId = txtTherapistId.getText();
        String email = txtEmail.getText();
        String experience = txtExperience.getText();
        String mobileNumber = txtMobileNumber.getText();
        String name = txtName.getText();
        String specialization = txtSpecialization.getText();
        String gender = txtAge.getText();

        boolean saved=theropistBO.save(new TheropistDTO(tId,name,gender,email,mobileNumber,specialization,experience));

        if(saved){
            new Alert(Alert.AlertType.INFORMATION,"Patient saved!!");
            refreshPage();
        }else {
            new Alert(Alert.AlertType.ERROR,"Patient not saved!!");
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
        txtSpecialization.setText("");
        txtAge.setText("");

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        genarateID();
        loadTableData();

        colTherapistId.setCellValueFactory(new PropertyValueFactory<>("therapistId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colExperience.setCellValueFactory(new PropertyValueFactory<>("experience"));
        colMobileNumber.setCellValueFactory(new PropertyValueFactory<>("mobileNumber"));
        colSpecialization.setCellValueFactory(new PropertyValueFactory<>("specialization"));
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
                    theropistDTO.getGender()
            );
            theropistTMS.add(theropistTM);
        }
        therapistTable.setItems(theropistTMS);
    }
}
