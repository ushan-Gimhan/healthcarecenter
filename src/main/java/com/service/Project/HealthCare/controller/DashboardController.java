package com.service.Project.HealthCare.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

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
    private
    AnchorPane contentPane;

    @FXML
    private AnchorPane mainPane;

    @FXML
    void clickedAdmin(ActionEvent event) throws IOException {
        navigation("/View/admin.fxml");
    }

    @FXML
    void clickedAppointments(ActionEvent event) throws IOException {
        navigation("/View/registration.fxml");
    }

    @FXML
    void clickedDashboard(ActionEvent event) throws IOException {
        Parent load = FXMLLoader.load(getClass().getResource("/View/AdminDashboard.fxml"));

        Image image = new Image(getClass().getResource("/images/healthicon2.png").toExternalForm());
        Stage stage= new Stage();
        Scene scene = new Scene(load);
        stage.setScene(scene);
        stage.setTitle("SERENITY");
        stage.getIcons().add(image);

        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        currentStage.close();
        stage.show();
    }

    @FXML
    void clickedLogOut(ActionEvent event) throws IOException {
        Parent load = FXMLLoader.load(getClass().getResource("/View/LoagingView.fxml"));

        Image image = new Image(getClass().getResource("/images/healthicon2.png").toExternalForm());
        Stage stage= new Stage();
        Scene scene = new Scene(load);
        stage.setScene(scene);
        stage.setTitle("SERENITY");
        stage.getIcons().add(image);

        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        currentStage.close();
        stage.show();
    }

    @FXML
    void clickedPatients(ActionEvent event) throws IOException {
        navigation("/View/patient.fxml");
    }

    @FXML
    void clickedPayment(ActionEvent event) throws IOException {
        navigation("/View/payment.fxml");
    }

    @FXML
    void clickedSessions(ActionEvent event) throws IOException {
        navigation("/View/sessionManage.fxml");
    }

    @FXML
    void clickedTherapists(ActionEvent event) throws IOException {
        navigation("/View/therapist.fxml");
    }

    @FXML
    void clickedTheraryPrograms(ActionEvent event) throws IOException {
        navigation("/View/thropyPrograme.fxml");
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
