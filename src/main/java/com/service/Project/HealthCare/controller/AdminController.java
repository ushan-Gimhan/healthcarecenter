package com.service.Project.HealthCare.controller;

import com.service.Project.HealthCare.bo.BOFactory;
import com.service.Project.HealthCare.bo.custom.AdminBO;
import com.service.Project.HealthCare.dto.AdminDTO;
import com.service.Project.HealthCare.dto.TM.AdminTM;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import org.mindrot.jbcrypt.BCrypt;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class AdminController implements Initializable {

    AdminBO adminBO = (AdminBO) BOFactory.getInstance().getBOType(BOFactory.BOType.admin);

    @FXML
    private Button GenarateReport;

    @FXML
    private TableView<AdminTM> adminTable;

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnReset;

    @FXML
    private Button btnUpdate;

    @FXML
    private TableColumn<AdminTM, String> colAdminId;

    @FXML
    private TableColumn<AdminTM,String> colEmail;

    @FXML
    private TableColumn<AdminTM,String> colMobileNumber;

    @FXML
    private TableColumn<AdminTM,String> colName;

    @FXML
    private TableColumn<AdminTM,String> passwordColum;

    @FXML
    private Button sendMail;

    @FXML
    private Label txtAdminId;


    @FXML
    private TextField txtConfirmPasswordHidden;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtMobileNumber;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtPassword;

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

        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());

        if(password.equals(passwordHidden)){
            AdminDTO adminDTO= new AdminDTO();
            adminDTO.setEmail(email);
            adminDTO.setPassword(hashedPassword);
            adminDTO.setName(name);
            adminDTO.setId(adminId);
            adminDTO.setPhone(mobileNumber);

            boolean saved=adminBO.save(adminDTO);

            if(saved){
                new Alert(Alert.AlertType.INFORMATION,"Admin Added Successfully").show();
                refreshPage();
            }else {
                new Alert(Alert.AlertType.ERROR,"Admin Not Added Successfully").show();
            }
        }else{
            new Alert(Alert.AlertType.ERROR,"Input your Password Correctly!!").show();
        }
    }

    @FXML
    void clicked(MouseEvent event) {
        AdminTM adminTM = adminTable.getSelectionModel().getSelectedItem();
        if(adminTM!=null){
            txtAdminId.setText(adminTM.getId());
            txtEmail.setText(adminTM.getEmail());
            txtMobileNumber.setText(adminTM.getPhone());
            txtName.setText(adminTM.getName());
            txtPassword.setText(adminTM.getPassword());
            txtPasswordHidden.setText(adminTM.getPassword());
        }
        btnAdd.setDisable(true);
    }

    @FXML
    void deleteAdmin(ActionEvent event) {
        AdminTM adminTM = adminTable.getSelectionModel().getSelectedItem();
        if(adminTM!=null){
            boolean b=adminBO.delete(adminTM.getId());
            if (b){
                new Alert(Alert.AlertType.INFORMATION,"Admin Deleted Successfully").show();
                refreshPage();
            }else {
                new Alert(Alert.AlertType.ERROR,"Admin Not Deleted Successfully").show();
            }
        }else{
            new Alert(Alert.AlertType.ERROR,"Select admin for deleted!!").show();
        }
    }

    @FXML
    void resetFields(ActionEvent event) {
        refreshPage();
        loadTableData();
    }

    @FXML
    void updateAdmin(ActionEvent event) {
        String adminId=txtAdminId.getText();
        String email=txtEmail.getText();
        String mobileNumber=txtMobileNumber.getText();
        String name=txtName.getText();
        String password=txtPassword.getText();
        String passwordHidden=txtPasswordHidden.getText();

        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());

        if(password.equals(passwordHidden)){
            AdminDTO adminDTO= new AdminDTO();
            adminDTO.setEmail(email);
            adminDTO.setPassword(hashedPassword);
            adminDTO.setName(name);
            adminDTO.setId(adminId);
            adminDTO.setPhone(mobileNumber);

            boolean updated=adminBO.update(adminDTO);

            if(updated){
                new Alert(Alert.AlertType.INFORMATION,"Admin Updated Successfully").show();
                refreshPage();
            }else {
                new Alert(Alert.AlertType.ERROR,"Error!! Can't Update Admin").show();
            }
        }else{
            new Alert(Alert.AlertType.ERROR,"Input your Password Correctly!!").show();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colAdminId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colMobileNumber.setCellValueFactory(new PropertyValueFactory<>("phone"));
        passwordColum.setCellValueFactory(new PropertyValueFactory<>("password"));

        try{
            refreshPage();
            loadTableData();
        }catch(Exception e){
            e.printStackTrace();
        }

    }
    public void refreshPage(){
        genarateId();

        btnAdd.setDisable(false);

        txtEmail.setText("");
        txtMobileNumber.setText("");
        txtName.setText("");
        txtPassword.setText("");
        txtPasswordHidden.setText("");
    }
    public void genarateId(){
        txtAdminId.setText(adminBO.genareateID());
    }
    public void loadTableData(){
        List<AdminDTO> allAdmin = adminBO.findAll();
        ObservableList<AdminTM> adminTMS= FXCollections.observableArrayList();

        for(AdminDTO adminDTO : allAdmin){
            AdminTM adminTM = new AdminTM(
                    adminDTO.getId(),
                    adminDTO.getName(),
                    adminDTO.getEmail(),
                    adminDTO.getPhone(),
                    adminDTO.getPassword()
            );
            adminTMS.add(adminTM);
        }
        adminTable.setItems(adminTMS);
    }
}
