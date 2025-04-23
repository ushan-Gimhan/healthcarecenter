package com.service.Project.HealthCare.controller;

import com.service.Project.HealthCare.bo.BOFactory;
import com.service.Project.HealthCare.bo.custom.SessionBO;
import com.service.Project.HealthCare.bo.custom.TherapyProgramBO;
import com.service.Project.HealthCare.dto.PatientDTO;
import com.service.Project.HealthCare.dto.SessioonDTO;
import com.service.Project.HealthCare.dto.TM.PatientTM;
import com.service.Project.HealthCare.dto.TM.SessionTM;
import com.service.Project.HealthCare.entity.TherapySession;
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
import java.sql.Time;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

public class SessionController implements Initializable {

    public TableColumn colTherapist;
    public TableColumn colTherapyProgram;
    public Label lblSessionCount;
    SessionBO sessionBO;

    {
        try {
            sessionBO = (SessionBO) BOFactory.getInstance().getBOType(BOFactory.BOType.Session);
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
        String id = lblSessionId.getId();
        String countText = txtSessionCount.getText();
        String timeText = txtSessionTime.getText();
        LocalDate dateValue = dpSessionDate.getValue();
        String status = cmbStatus.getValue();

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
        String date = dateValue.toString();
        String time = Time.valueOf(timeText + ":00").toString();

        boolean saved = sessionBO.save(new SessioonDTO(id, count, date, time, status));

        if (saved) {
            new Alert(Alert.AlertType.INFORMATION, "Session added!").show();
            refreshPage();
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
            txtSessionCount.setText(String.valueOf(selectedItem.getSessionCount()));
            cmbStatus.setValue(selectedItem.getStatus());
            lblSessionId.setText(selectedItem.getSId());
        }
    }

    @FXML
    void updateSession(ActionEvent event) {
        String id = lblSessionId.getId();
        String countText = txtSessionCount.getText();
        String timeText = txtSessionTime.getText();
        LocalDate dateValue = dpSessionDate.getValue();
        String status = cmbStatus.getValue(); // ✅ get selected value

        String countPattern = "\\d+";
        String timePattern = "^([01]\\d|2[0-3]):[0-5]\\d$";

        if (!countText.matches(countPattern)) {
            new Alert(Alert.AlertType.ERROR, "Invalid session count. Only numbers allowed.").show();
            return;
        }

        if (!timeText.matches(timePattern)) {
            new Alert(Alert.AlertType.ERROR, "Invalid time format. Use HH:mm (e.g., 09:30)").show();
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
        String date = dateValue.toString();
        String time = Time.valueOf(timeText + ":00").toString(); // convert to full "HH:mm:ss"

        boolean saved = sessionBO.save(new SessioonDTO(id, count, date, time, status));

        if (saved) {
            new Alert(Alert.AlertType.INFORMATION, "Session Updated!").show();
            refreshPage();
        } else {
            new Alert(Alert.AlertType.ERROR, "Session not Updated!").show();
        }
    }

    public void refreshPage() {
        sessionBO.generateId();

        txtSessionCount.setText("");
        txtSessionTime.setText("");
        cmbStatus.getItems().clear();
        dpSessionDate.setValue(null);

    }
    public void loadTableData(){
        List<SessioonDTO> all = sessionBO.findAll();
        ObservableList<SessionTM> data = FXCollections.observableArrayList();

        for(SessioonDTO s : all){
            SessionTM sessionTM = new SessionTM(s.getSId(),s.getSessionCount(),s.getTime(),s.getStatus());
            data.add(sessionTM);
        }
        sessionTable.setItems(data);

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cmbStatus.setItems(FXCollections.observableArrayList("Available","Unavailable"));
        colSessionId.setCellValueFactory(new PropertyValueFactory<>("sId"));
//        colSessionCount.setCellValueFactory(new PropertyValueFactory<>("SessionCount"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("time"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        colTime.setCellValueFactory(new PropertyValueFactory<>("time"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("Date"));

        lblSessionId.setText(sessionBO.generateId());
        loadTableData();

    }

    public void selectTherapist(ActionEvent event) {

    }

    public void selectPatient(ActionEvent event) {

    }
}
