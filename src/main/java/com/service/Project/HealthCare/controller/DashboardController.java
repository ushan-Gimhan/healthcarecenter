package com.service.Project.HealthCare.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static com.service.Project.HealthCare.controller.LoginPageController.ap;

public class DashboardController implements Initializable {

    @FXML
    private Button btnAdmin;

    @FXML
    private Button btnAppointments;

    @FXML
    private Button btnDashboard;

    @FXML
    private Button btnLogout;

    @FXML
    private Button btnPatients;

    @FXML
    private Button btnReseptionist;

    @FXML
    private Button btnSessions;

    @FXML
    private Button btnTherapists;

    @FXML
    private Button btnTheropyPrograms;

    @FXML
    private Button btnpayment;

    @FXML
    private AnchorPane contentPane;

    @FXML
    private AnchorPane mainPane;

    @FXML
    void clickedAdmin(ActionEvent event) throws IOException {
        navigation("/View/admin.fxml");
    }

    @FXML
    void clickedAppointments(ActionEvent event) {

    }

    @FXML
    void clickedDashboard(ActionEvent event) {

    }

    @FXML
    void clickedLogOut(ActionEvent event) {

    }

    @FXML
    void clickedPatients(ActionEvent event) throws IOException {
        navigation("/View/patient.fxml");
    }

    @FXML
    void clickedPayment(ActionEvent event) {

    }

    @FXML
    void clickedReceptionts(ActionEvent event) {

    }

    @FXML
    void clickedSessions(ActionEvent event) {

    }

    @FXML
    void clickedTherapists(ActionEvent event) throws IOException {
        navigation("/View/therapist.fxml");
    }

    @FXML
    void clickedTheraryPrograms(ActionEvent event) {

    }

    public void navigation(String path) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(path));
        AnchorPane newPane = loader.load();

        AnchorPane.setTopAnchor(newPane, 0.0);
        AnchorPane.setRightAnchor(newPane, 0.0);
        AnchorPane.setBottomAnchor(newPane, 0.0);
        AnchorPane.setLeftAnchor(newPane, 0.0);
        contentPane.getChildren().add(newPane);

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (ap.equals("Receptionist")){
            btnTheropyPrograms.setDisable(true);
            btnAdmin.setDisable(true);
        }

    }
}
