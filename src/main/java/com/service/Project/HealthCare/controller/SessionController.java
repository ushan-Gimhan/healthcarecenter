package com.service.Project.HealthCare.controller;

import com.service.Project.HealthCare.bo.BOFactory;
import com.service.Project.HealthCare.bo.custom.PatientBO;
import com.service.Project.HealthCare.bo.custom.RegistrationBO;
import com.service.Project.HealthCare.bo.custom.SessionBO;
import com.service.Project.HealthCare.bo.custom.TheropistBO;
import com.service.Project.HealthCare.dto.SessioonDTO;
import com.service.Project.HealthCare.dto.TM.SessionTM;
import com.service.Project.HealthCare.dto.TheropistDTO;
import com.service.Project.HealthCare.entity.Patient;
import com.service.Project.HealthCare.entity.Programs;
import com.service.Project.HealthCare.entity.Registration;
import com.service.Project.HealthCare.entity.Theropist;
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
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class SessionController implements Initializable {

    @FXML
    private TableColumn<SessionTM,String> colPatient;
    @FXML
    private Label lblFilteredCount;
    @FXML
    public TextField txtSessions;
    @FXML
    public Label lblPatientName;
    @FXML
    public ComboBox<String> cmbPatientSelect;
    @FXML
    private TableColumn<SessionTM, String> colTherapist;
    @FXML
    private TableColumn<SessionTM, String> colTherapyProgram;
    @FXML
    public Label lblSessionCount;
    @FXML
    SessionBO sessionBO;
    @FXML
    PatientBO patientBO;
    @FXML
    TheropistBO theropist;
    RegistrationBO registrationBO;

    {
        try {
            sessionBO = (SessionBO) BOFactory.getInstance().getBOType(BOFactory.BOType.Session);
            patientBO = (PatientBO) BOFactory.getInstance().getBOType(BOFactory.BOType.Patient);
            theropist= (TheropistBO) BOFactory.getInstance().getBOType(BOFactory.BOType.Therapist);
            registrationBO= (RegistrationBO) BOFactory.getInstance().getBOType(BOFactory.BOType.registration);
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
    private ComboBox<String> cmbStatus;

    @FXML
    private TableColumn<SessionTM, String> colDate;

    @FXML
    private TableColumn<SessionTM, String> colSessionCount;

    @FXML
    private TableColumn<SessionTM, String> colSessionId;

    @FXML
    private TableColumn<SessionTM, String> colStatus;

    @FXML
    private TableColumn<SessionTM, String> colTime;

    @FXML
    private DatePicker dpSessionDate;

    @FXML
    private Label lblSessionId;

    @FXML
    private TableView<SessionTM> sessionTable;

    @FXML
    private TextField txtSessionCount;

    @FXML
    public ComboBox<String> cmbTherapistSelect;

    @FXML
    public Label lblTherapistName;

    @FXML
    public ComboBox<String> cmbTherapyProgram;

    @FXML
    private TextField txtSessionTime;

    @FXML
    void addSession(ActionEvent event) {
        String id = lblSessionId.getText();
        String countText = txtSessions.getText();
        String timeText = txtSessionTime.getText();
        LocalDate dateValue = dpSessionDate.getValue();
        String status = cmbStatus.getValue();
        String patientID = cmbPatientSelect.getValue();
        String therapist1 = cmbTherapistSelect.getValue();

        Patient patient= patientBO.getPationByID(patientID);
        Theropist theropist1 = theropist.getTheropistNameByID(therapist1);
        String theropist_id=null;

        if (patient == null) {
            System.out.println("Patient not found for ID: " + patientID);
            // handle the null case, maybe return or throw exception
        }else {
          theropist_id = theropist1.getId();
        }

        if (therapist1 == null) {
            System.out.println("Therapist not found for ID: " + therapist1);
            // handle the null case
        }


        String countPattern = "\\d+";                       // Only digits
        String timePattern = "^([01]\\d|2[0-3]):[0-5]\\d$"; // Valid 24-hour time (HH:mm)

        if (!countText.matches(countPattern)) {
            new Alert(Alert.AlertType.ERROR, "Invalid session count. Only numbers allowed.").show();
            return;
        }

        if (!timeText.matches(timePattern)) {
            new Alert(Alert.AlertType.ERROR, "Invalid time format. Use HH:mm (e.g., 14:30)").show();
            return;
        }

        if (dateValue == null) {
            new Alert(Alert.AlertType.ERROR, "Please select a date.").show();
            return;
        }

        if (status == null || status.trim().isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Please select a session status.").show();
            return;
        }

        int count = Integer.parseInt(countText);
        LocalDate date = dpSessionDate.getValue();
        Time time = Time.valueOf(Time.valueOf(timeText + ":00").toString());

        boolean saved = sessionBO.save(new SessioonDTO(id, count, time, Date.valueOf(date), status,patient,theropist1));

        if (saved) {
            new Alert(Alert.AlertType.INFORMATION, "Session added!").show();
            loadTableData();
        } else {
            new Alert(Alert.AlertType.ERROR, "Session not added!").show();
        }
    }

    @FXML
    void deleteSession(ActionEvent event) {
        boolean deleted = sessionBO.delete(lblSessionId.getId());

        if(deleted){
            new Alert(Alert.AlertType.INFORMATION,"Session deleted!!");
            refreshPage();
        }else {
            new Alert(Alert.AlertType.ERROR,"Session not deleted!!");
            refreshPage();
        }
    }

    @FXML
    void resetFields(ActionEvent event) {
        refreshPage();
    }

    @FXML
    void tableClicked(MouseEvent event) {
        SessionTM selectedItem = sessionTable.getSelectionModel().getSelectedItem();

        if (selectedItem !=null){
            txtSessionTime.setText(selectedItem.getTime().toString());
            txtSessions.setText(String.valueOf(selectedItem.getSessionCount()));
            cmbStatus.setValue(selectedItem.getStatus());
            lblSessionId.setText(selectedItem.getSId());
            cmbPatientSelect.setValue(selectedItem.getPatient_id());
            dpSessionDate.setValue(selectedItem.getDate());
            lblSessionId.setText(selectedItem.getSId());
            cmbTherapistSelect.setValue(selectedItem.getProgram_id());
        }
        btnAdd.setDisable(true);
    }

    @FXML
    void updateSession(ActionEvent event) {
        String id = lblSessionId.getText();
        String countText = txtSessions.getText();
        String timeText = txtSessionTime.getText();
        LocalDate dateValue = dpSessionDate.getValue();
        String status = cmbStatus.getValue();
        String pationID = cmbPatientSelect.getValue();
        String theropistId = cmbTherapistSelect.getValue();

        Patient patient= patientBO.getPationByID(pationID);
        Theropist theropist1 = theropist.getTheropistNameByID(theropistId);
        String theropist_id=null;

        if (patient == null) {
            System.out.println("Patient not found for ID: " + pationID);
        }else {
            theropist_id = theropist1.getId();
        }

        if (theropistId == null) {
            System.out.println("Therapist not found for ID: " + theropistId);
        }


        String countPattern = "\\d+";                       // Only digits
        String timePattern = "^([01]\\d|2[0-3]):[0-5]\\d$"; // Valid 24-hour time (HH:mm)

        if (!countText.matches(countPattern)) {
            new Alert(Alert.AlertType.ERROR, "Invalid session count. Only numbers allowed.").show();
            return;
        }

        if (!timeText.matches(timePattern)) {
            new Alert(Alert.AlertType.ERROR, "Invalid time format. Use HH:mm (e.g., 14:30)").show();
            return;
        }

        if (dateValue == null) {
            new Alert(Alert.AlertType.ERROR, "Please select a date.").show();
            return;
        }

        if (status == null || status.trim().isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Please select a session status.").show();
            return;
        }

        int count = Integer.parseInt(countText);
        LocalDate date = dpSessionDate.getValue();
        Time time = Time.valueOf(Time.valueOf(timeText + ":00").toString());

        boolean saved = sessionBO.update(new SessioonDTO(id, count, time, Date.valueOf(date), status,patient,theropist1));

        if (saved) {
            new Alert(Alert.AlertType.INFORMATION, "Session updated!").show();
            refreshPage();
        } else {
            new Alert(Alert.AlertType.ERROR, "Session not updated!").show();
            refreshPage();
        }
    }

    public void refreshPage() {

        txtSessions.clear();
        txtSessionTime.clear();
        dpSessionDate.setValue(null);
        cmbStatus.setValue(null);
        cmbPatientSelect.setValue(null);
        cmbTherapistSelect.setValue("");
        lblTherapistName.setText("");
        sessionBO.generateId();
        loadTableData();
        btnAdd.setDisable(false);

    }
    public void loadTableData(){
        List<SessioonDTO> all = sessionBO.findAll();
        ObservableList<SessionTM> data = FXCollections.observableArrayList();

        for(SessioonDTO s : all){
            SessionTM sessionTM = new SessionTM(s.getSId(),s.getSessionCount(),s.getTime(),s.getDate(),s.getStatus(),s.getPatient().getId(),s.getId().getId());
            data.add(sessionTM);
        }
        sessionTable.setItems(data);

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cmbStatus.setItems(FXCollections.observableArrayList("Available","Unavailable"));
        colSessionId.setCellValueFactory(new PropertyValueFactory<>("sId"));
        colSessionCount.setCellValueFactory(new PropertyValueFactory<>("SessionCount"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        colTime.setCellValueFactory(new PropertyValueFactory<>("time"));
        colTherapist.setCellValueFactory(new PropertyValueFactory<>("program_id"));
        colPatient.setCellValueFactory(new PropertyValueFactory<>("patient_id"));

        lblSessionId.setText(sessionBO.generateId());
        loadTableData();
        loadPatients();
        loadTheropists();

    }

    public void selectTherapist(ActionEvent event) {
        String t_id = cmbTherapistSelect.getValue();

        if (t_id == null || t_id.isEmpty()) {
            new Alert(Alert.AlertType.INFORMATION, "Please select a therapist ID.").show();
            return;
        }
        Theropist theropistNameByID = theropist.getTheropistNameByID(t_id);

        if (theropistNameByID == null) {
            new Alert(Alert.AlertType.INFORMATION, "Therapist not found for ID:" + t_id).show();
            return;
        }
        lblTherapistName.setText(theropistNameByID.getName());

    }

    public void selectPatient(ActionEvent event) {
        String patientValue = cmbPatientSelect.getValue();

        Patient patient= patientBO.getPationByID(patientValue);

        if (patient != null &&patient.getName() != null) {
            lblPatientName.setText(patient.getName());
            Registration registrationByPatientId = registrationBO.getRegistrationByPatientId(patientValue);
            if (registrationByPatientId != null) {
                Programs programs = registrationByPatientId.getPrograms();
                cmbTherapyProgram.setValue(programs.getPName());
            }else {
                cmbTherapyProgram.setValue(null);
                new Alert(Alert.AlertType.INFORMATION, "Patient not Register to Program").show();
            }
        } else {
            lblPatientName.setText("");
        }
    }

    public void searchSession(ActionEvent event) {
    }
    public void loadPatients(){
        List<String> patientIds = patientBO.getAllPatientIds();
        cmbPatientSelect.getItems().setAll(patientIds);
    }
    public void loadTheropists(){
        List<TheropistDTO> availableTherapistIds = theropist.findAll();
        List<String> allAvalableIds = new ArrayList<>();

        for (TheropistDTO t : availableTherapistIds){
            String status = t.getStaus();
            if (status.equals("Active")){
                allAvalableIds.add(t.getId());
            }
        }
        cmbTherapistSelect.getItems().setAll(allAvalableIds);
    }
}
