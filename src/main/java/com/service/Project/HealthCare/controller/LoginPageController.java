package com.service.Project.HealthCare.controller;

import com.service.Project.HealthCare.bo.custom.Impl.LoginPageBOImpl;
import com.service.Project.HealthCare.entity.Admin;
import com.service.Project.HealthCare.entity.Receptionist;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginPageController {
    public ImageView showPassword;
    LoginPageBOImpl loginPageBO = new LoginPageBOImpl();

    @FXML
    private Label errorLabel;

    @FXML
    private Hyperlink forgotPasswordLink;

    @FXML
    private Button loginButton;

    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField passwordVisibleField;

    @FXML
    private Hyperlink signupLink;

    @FXML
    private Button togglePasswordVisibility;

    @FXML
    private TextField usernameField;

    @FXML
    private ImageView visibilityIcon;

    private boolean isPasswordVisible = false;

    private final Image eyeOpen = new Image(getClass().getResourceAsStream("/images/LoginFile/showPaaword.png")); // Visible icon
    private final Image eyeClosed = new Image(getClass().getResourceAsStream("/images/LoginFile/visible.png")); // Hidden icon


    @FXML
    void handleForgotPassword(ActionEvent event) throws IOException {
        Parent load = FXMLLoader.load(getClass().getResource("/View/fogotPassword.fxml"));
        Stage stage = new Stage();
        Image image1 = new Image(getClass().getResource("/images/healthicon2.png").toExternalForm());

        Scene scene = new Scene(load);
        stage.setScene(scene);
        stage.setTitle("SERENITY");
//        stage.getIcons().add(image);
        stage.show();

    }

    @FXML
    void handleLogin(ActionEvent event) {
        Admin admin = new Admin();
        Receptionist receptionist = new Receptionist();

        String username = usernameField.getText();
        String password = passwordField.getText();

        usernameField.setStyle(usernameField.getStyle() + ";-fx-border-color: #7367F0;");
        passwordField.setStyle(passwordField.getStyle() + ";-fx-border-color: #7367F0;");

        String namePattern = "^[A-Za-z ]+$";
        boolean isValidName = username.matches(namePattern);

        if (!isValidName) {
            usernameField.setStyle(usernameField.getStyle() + ";-fx-border-color: red;");
        }

        if(username.equals("")){
            new Alert(Alert.AlertType.INFORMATION, "Enter you user Name").show();
            return;
        }else if(password.equals("")){
            new Alert(Alert.AlertType.INFORMATION, "Enter you Password").show();
            return;

        }

        admin = loginPageBO.Adminlogin(username);
        receptionist= loginPageBO.Receptionlogin(username);

        if(admin==null){
            if(receptionist==null){
                new Alert(Alert.AlertType.INFORMATION, "Invalid User Name!").show();
                usernameField.setStyle(usernameField.getStyle() + ";-fx-border-color: red;");
                return;
            }
        }

        if(admin != null){
            if(admin.getPassword().equals(password)){
                new Alert(Alert.AlertType.INFORMATION, "Login Successful As Admid").show();
            }else {
                passwordField.setStyle(passwordField.getStyle() + ";-fx-border-color: red;");
                new Alert(Alert.AlertType.INFORMATION, "Invalid Password").show();
            }
        }else if(receptionist != null){
            if(receptionist.getPassword().equals(password)){
                new Alert(Alert.AlertType.INFORMATION, "Login Successful Receptionist").show();
            }else{
                passwordField.setStyle(passwordField.getStyle() + ";-fx-border-color: red;");
                new Alert(Alert.AlertType.INFORMATION, "Invalid Password").show();
            }
        }

    }

    @FXML
    void handleSignup(ActionEvent event) {

    }
    @FXML
    public void clickedToHidePassword(MouseEvent mouseEvent) {
        if (isPasswordVisible) {
            passwordField.setText(passwordVisibleField.getText());
            passwordField.setVisible(true);
            passwordVisibleField.setVisible(false);
            visibilityIcon.setImage(eyeClosed);
        } else {
            passwordVisibleField.setText(passwordField.getText());
            passwordVisibleField.setVisible(true);
            passwordField.setVisible(false);
            visibilityIcon.setImage(eyeOpen);
        }
        isPasswordVisible = !isPasswordVisible;
    }
}
