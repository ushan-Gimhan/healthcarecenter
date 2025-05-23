package com.service.Project.HealthCare.controller;

import com.service.Project.HealthCare.Exceptions.LoginException;
import com.service.Project.HealthCare.bo.BOFactory;
import com.service.Project.HealthCare.bo.custom.LoginPageBO;
import com.service.Project.HealthCare.entity.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import org.mindrot.jbcrypt.BCrypt;

import java.io.IOException;

public class LoginPageController {
    static String ap;
    public AnchorPane mainPane;
    LoginPageBO loginPageBO = (LoginPageBO) BOFactory.getInstance().getBOType(BOFactory.BOType.loginPage);

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

    public LoginPageController() throws IOException {
    }


    @FXML
    void handleForgotPassword(ActionEvent event) throws IOException {
        navigate("/View/fogotPassword.fxml");
    }

    @FXML
    void handleLogin(ActionEvent event) throws IOException {
        String username = usernameField.getText();
        String password = passwordField.getText();

        usernameField.setStyle(usernameField.getStyle() + ";-fx-border-color: #7367F0;");
        passwordField.setStyle(passwordField.getStyle() + ";-fx-border-color: #7367F0;");

        if (username.equals("")) {
            new Alert(Alert.AlertType.INFORMATION, "Enter you user Name").show();
            return;
        } else if (password.equals("")) {
            new Alert(Alert.AlertType.INFORMATION, "Enter you Password").show();
            return;
        }

        User user = loginPageBO.Adminlogin(username);

        if (user == null) {
            new Alert(Alert.AlertType.INFORMATION, "Invalid User Name!").show();
            usernameField.setStyle(usernameField.getStyle() + ";-fx-border-color: red;");
        }else{
          if (BCrypt.checkpw(password, user.getPassword())){
                ap=user.getRole();
                navigate("/View/AdminDashboard.fxml");
            } else {
                passwordField.setStyle(passwordField.getStyle() + ";-fx-border-color: red;");
              new Alert(Alert.AlertType.INFORMATION, "Enter you Password").show();
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
    public void navigate(String path) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(path));
        AnchorPane newPane = loader.load();

        AnchorPane.setTopAnchor(newPane, 0.0);
        AnchorPane.setRightAnchor(newPane, 0.0);
        AnchorPane.setBottomAnchor(newPane, 0.0);
        AnchorPane.setLeftAnchor(newPane, 0.0);
        mainPane.getChildren().add(newPane);

    }
}

