package com.service.Project.HealthCare.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class ForgotPasswordController {
    public AnchorPane mainPain;
    LoginPageController loginPageController = new LoginPageController();

    @FXML
    private Hyperlink backToLoginLink;

    @FXML
    private TextField emailField;

    @FXML
    private Label errorLabel;

    @FXML
    private Button resetButton;

    @FXML
    private Label statusLabel;

    @FXML
    void handleBackToLogin(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/LoadingView.fxml"));
        AnchorPane newPane = loader.load();

        AnchorPane.setTopAnchor(newPane, 0.0);
        AnchorPane.setRightAnchor(newPane, 0.0);
        AnchorPane.setBottomAnchor(newPane, 0.0);
        AnchorPane.setLeftAnchor(newPane, 0.0);
        mainPain.getChildren().add(newPane);
    }

    @FXML
    void handleResetPassword(ActionEvent event) {

    }

}
