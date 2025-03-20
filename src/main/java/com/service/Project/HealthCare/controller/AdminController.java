package com.service.Project.HealthCare.controller;

import com.service.Project.HealthCare.bo.BOFactory;
import com.service.Project.HealthCare.bo.custom.AdminBO;
import com.service.Project.HealthCare.dto.AdminDTO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class AdminController implements Initializable {
    AdminBO adminBO = (AdminBO) BOFactory.getInstance().getBOType(BOFactory.BOType.admin);

    @FXML
    private Button GenarateReport;

    @FXML
    private TableView<?> adminTable;

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnReset;

    @FXML
    private Button btnUpdate;

    @FXML
    private TableColumn<?, ?> colAdminId;

    @FXML
    private TableColumn<?, ?> colEmail;

    @FXML
    private TableColumn<?, ?> colMobileNumber;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> passwordColum;

    @FXML
    private Button sendMail;

    @FXML
    private Label txtAdminId;

    @FXML
    private PasswordField txtConfirmPassword;

    @FXML
    private TextField txtConfirmPasswordHidden;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtMobileNumber;

    @FXML
    private TextField txtName;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private TextField txtPasswordHidden;

    @FXML
    void addAdmin(ActionEvent event) {
        String adminId=txtAdminId.getText();
        String email=txtEmail.getText();
        String mobileNumber=txtMobileNumber.getText();
        String name=txtName.getText();
        String password=txtPassword.getText();
        String passwordHidden=txtPasswordHidden.getText();

        if(password.equals(passwordHidden)){
            new Alert(Alert.AlertType.INFORMATION,"Password Matches").show();
        }else{
            new Alert(Alert.AlertType.ERROR,"Input your Password Correctly!!").show();
        }
        AdminDTO adminDTO= new AdminDTO();
        adminDTO.setEmail(email);
        adminDTO.setPassword(password);
        adminDTO.setName(name);
        adminDTO.setId(adminId);
        adminDTO.setPhone(mobileNumber);

        boolean saved=adminBO.save(adminDTO);
        if(saved){
            new Alert(Alert.AlertType.INFORMATION,"Admin Added Successfully").show();
        }else {
            new Alert(Alert.AlertType.ERROR,"Admin Not Added Successfully").show();
        }
    }

    @FXML
    void clicked(MouseEvent event) {

    }

    @FXML
    void deleteAdmin(ActionEvent event) {

    }

    @FXML
    void resetFields(ActionEvent event) {

    }

    @FXML
    void updateAdmin(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
