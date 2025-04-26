package com.service.Project.HealthCare.controller;

import com.service.Project.HealthCare.bo.BOFactory;
import com.service.Project.HealthCare.bo.custom.PatientBO;
import com.service.Project.HealthCare.dto.PatientDTO;
import com.service.Project.HealthCare.dto.TM.PatientTM;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static com.service.Project.HealthCare.controller.PaymentController.serchMobile;
import static com.service.Project.HealthCare.controller.PaymentController.serchNic;

public class Serch implements Initializable {

    PatientBO patientBO;

    {
        try {
            patientBO = (PatientBO) BOFactory.getInstance().getBOType(BOFactory.BOType.Patient);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    private TableColumn<PatientTM, String> colAge;

    @FXML
    private TableColumn<PatientTM, String> colEmail;

    @FXML
    private TableColumn<PatientTM, String> colGender;

    @FXML
    private TableColumn<PatientTM, String> colMobileNumber;

    @FXML
    private TableColumn<PatientTM, String> colName;

    @FXML
    private TableColumn<PatientTM, String> colPatientId;

    @FXML
    private TableView<PatientTM> patientTable;

    @FXML
    void clicked(MouseEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colAge.setCellValueFactory(new PropertyValueFactory<>("age"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colGender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        colMobileNumber.setCellValueFactory(new PropertyValueFactory<>("mobile"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colPatientId.setCellValueFactory(new PropertyValueFactory<>("id"));

        loadTableData();
    }
    private void loadTableData() {
        List<PatientDTO> all = patientBO.findAll();
        ObservableList<PatientTM>  alls= FXCollections.observableArrayList();
        for (PatientDTO patientDTO : all) {
            if (patientDTO.getNIC().equals(serchNic)) {
                PatientTM patientTM = new PatientTM(patientDTO.getId(),patientDTO.getName(),patientDTO.getMobile(),patientDTO.getNIC(),patientDTO.getEmail(),patientDTO.getGender(),patientDTO.getAge());
                alls.add(patientTM);
            }else if(patientDTO.getMobile().equals(serchMobile)){
                PatientTM patientTM = new PatientTM(patientDTO.getId(),patientDTO.getName(),patientDTO.getMobile(),patientDTO.getNIC(),patientDTO.getEmail(),patientDTO.getGender(),patientDTO.getAge());
                alls.add(patientTM);
            }
        }
        patientTable.setItems(alls);
    }
}
