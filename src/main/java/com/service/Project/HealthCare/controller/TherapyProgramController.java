package com.service.Project.HealthCare.controller;

import com.service.Project.HealthCare.bo.BOFactory;
import com.service.Project.HealthCare.bo.custom.TherapyProgramBO;
import com.service.Project.HealthCare.bo.custom.TheropistBO;
import com.service.Project.HealthCare.dto.TM.TherapyProgramTM;
import com.service.Project.HealthCare.dto.TM.UserTM;
import com.service.Project.HealthCare.dto.TherapyProgramDTO;
import com.service.Project.HealthCare.dto.UserDTO;
import com.service.Project.HealthCare.entity.Programs;
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

public class TherapyProgramController implements Initializable {
    TherapyProgramBO therapyProgramBO;

    {
        try {
            therapyProgramBO = (TherapyProgramBO) BOFactory.getInstance().getBOType(BOFactory.BOType.Program);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    private Button btnAddTherapy;

    @FXML
    private Button btnDeleteTherapy;

    @FXML
    private Button btnResetTherapy;

    @FXML
    private Button btnUpdateTherapy;

    @FXML
    private TableColumn<TherapyProgramTM, String> colDuration;

    @FXML
    private TableColumn<TherapyProgramTM, String> colPName;

    @FXML
    private TableColumn<TherapyProgramTM, String> colPrice;

    @FXML
    private TableColumn<TherapyProgramTM, String> colTId;

    @FXML
    private TableView<TherapyProgramTM> therapyTable;

    @FXML
    private TextField txtDuration;

    @FXML
    private TextField txtPrice;

    @FXML
    private TextField txtProgramName;

    @FXML
    private TextField txtTherapyId;

    @FXML
    void addTherapy(ActionEvent event) {

        String id = txtTherapyId.getText();
        String duration = txtDuration.getText();
        String programName = txtProgramName.getText();
        Double price = Double.valueOf(txtPrice.getText());

        boolean saved = therapyProgramBO.save(new TherapyProgramDTO(id,programName,duration,price));

        if(saved){
            new Alert(Alert.AlertType.INFORMATION,"Patient updated!!");
            refreshPage();
        }else {
            new Alert(Alert.AlertType.ERROR,"Patient not updated!!");
        }

    }

    @FXML
    void deleteTherapy(ActionEvent event) {
        boolean deleted=therapyProgramBO.delete(txtTherapyId.getText());

        if(deleted){
            new Alert(Alert.AlertType.INFORMATION,"Therapy Program deleted!!");
            refreshPage();
        }else {
            new Alert(Alert.AlertType.ERROR,"Therapy Program not deleted!!");
        }
    }

    @FXML
    void resetTherapyFields(ActionEvent event) {
        refreshPage();
    }

    @FXML
    void selectTherapy(MouseEvent event) {

    }

    @FXML
    void updateTherapy(ActionEvent event) {
        String id = txtTherapyId.getText();
        String duration = txtDuration.getText();
        String programName = txtProgramName.getText();
        Double price = Double.valueOf(txtPrice.getText());

        boolean updated = therapyProgramBO.save(new TherapyProgramDTO(id,programName,duration,price));

        if(updated){
            new Alert(Alert.AlertType.INFORMATION,"Therapy Program updated!!");
            refreshPage();
        }else {
            new Alert(Alert.AlertType.ERROR,"Therapy Program not updated!!");
        }
    }

    public void refreshPage() {
        genarateID();
        loadTableData();

        txtProgramName.setText("");
        txtDuration.setText("");
        txtPrice.setText("");
    }
    public void genarateID(){
        txtTherapyId.setText(therapyProgramBO.generateId());
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        colTId.setCellValueFactory(new PropertyValueFactory<TherapyProgramTM, String>("tId"));
        colPName.setCellValueFactory(new PropertyValueFactory<TherapyProgramTM,String>("pName"));
        colDuration.setCellValueFactory(new PropertyValueFactory<TherapyProgramTM,String>("duration"));
        colPrice.setCellValueFactory(new PropertyValueFactory<TherapyProgramTM,String>("price"));

        genarateID();
        loadTableData();

    }
    public void loadTableData(){
        List<TherapyProgramDTO> allProgram = therapyProgramBO.findAll();
        ObservableList<TherapyProgramTM> progamTMS = FXCollections.observableArrayList();

        for(TherapyProgramDTO progam : allProgram) {
            TherapyProgramTM therapyProgramTM = new TherapyProgramTM(
                    progam.getTId(),
                    progam.getPName(),
                    progam.getDuration(),
                    progam.getPrice()
            );
            progamTMS.add(therapyProgramTM);
        }
        therapyTable.setItems(progamTMS);
    }
}
