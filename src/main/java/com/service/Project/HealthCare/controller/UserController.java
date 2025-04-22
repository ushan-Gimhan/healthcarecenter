package com.service.Project.HealthCare.controller;

import com.service.Project.HealthCare.bo.BOFactory;
import com.service.Project.HealthCare.bo.custom.UserBO;
import com.service.Project.HealthCare.dto.UserDTO;
import com.service.Project.HealthCare.dto.TM.UserTM;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import org.mindrot.jbcrypt.BCrypt;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class UserController implements Initializable {

    UserBO userBO;

    {
        try {
            userBO = (UserBO) BOFactory.getInstance().getBOType(BOFactory.BOType.admin);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    private Button GenarateReport;

    @FXML
    private TableView<UserTM> adminTable;

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnReset;

    @FXML
    private Button btnUpdate;

    @FXML
    private TableColumn<UserTM, String> colAdminId;

    @FXML
    private TableColumn<UserTM,String> colEmail;

    @FXML
    private TableColumn<UserTM,String> colMobileNumber;

    @FXML
    private TableColumn<UserTM,String> colName;

    @FXML
    private TableColumn<UserTM,String> passwordColum;

    @FXML
    public TableColumn<UserTM,String> coluserName;

    @FXML
    private Button sendMail;

    @FXML
    private Label txtAdminId;

    @FXML
    private TextField txtUserName;

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
    private ChoiceBox <String>roleSelector;

    @FXML
    public TableColumn<UserTM,String> colRole;


    @FXML
    void addAdmin(ActionEvent event) {
        String adminId=txtAdminId.getText();
        String email=txtEmail.getText();
        String mobileNumber=txtMobileNumber.getText();
        String name=txtName.getText();
        String userName=txtUserName.getText();
        String password=txtPassword.getText();
        String passwordHidden=txtPasswordHidden.getText();
        String role = roleSelector.getValue();

        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());

        if(password.equals(passwordHidden)){
            UserDTO userDTO = new UserDTO();
            userDTO.setEmail(email);
            userDTO.setPassword(hashedPassword);
            userDTO.setName(name);
            userDTO.setUserNAme(userName);
            userDTO.setId(adminId);
            userDTO.setPhone(mobileNumber);
            userDTO.setRole(role);

            boolean saved= userBO.save(userDTO);

            if(saved){
                new Alert(Alert.AlertType.INFORMATION,"User Added Successfully").show();
                refreshPage();
            }else {
                new Alert(Alert.AlertType.ERROR,"User Not Added Successfully").show();
            }
        }else{
            new Alert(Alert.AlertType.ERROR,"Input your Password Correctly!!").show();
        }
    }

    @FXML
    void clicked(MouseEvent event) {
        UserTM userTM = adminTable.getSelectionModel().getSelectedItem();
        if(userTM !=null){
            txtAdminId.setText(userTM.getId());
            txtEmail.setText(userTM.getEmail());
            txtMobileNumber.setText(userTM.getPhone());
            txtName.setText(userTM.getName());
            txtUserName.setText(userTM.getUserName());
            txtPassword.setText(userTM.getPassword());
            txtPasswordHidden.setText(userTM.getPassword());
            roleSelector.setValue(userTM.getRole());
        }
        btnAdd.setDisable(true);
    }

    @FXML
    void deleteAdmin(ActionEvent event) {
        UserTM userTM = adminTable.getSelectionModel().getSelectedItem();
        if(userTM !=null){
            boolean b= userBO.delete(userTM.getId());
            if (b){
                new Alert(Alert.AlertType.INFORMATION,"User Deleted Successfully").show();
                refreshPage();
            }else {
                new Alert(Alert.AlertType.ERROR,"User Not Deleted Successfully").show();
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
    void updateAdmin(ActionEvent event) throws IOException {
        String adminId=txtAdminId.getText();
        String email=txtEmail.getText();
        String mobileNumber=txtMobileNumber.getText();
        String name=txtName.getText();
        String userName= txtUserName.getText();
        String password=txtPassword.getText();
        String passwordHidden=txtPasswordHidden.getText();
        String role = roleSelector.getValue();

        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());

        if(password.equals(passwordHidden)){
            UserDTO userDTO = new UserDTO();
            userDTO.setEmail(email);
            userDTO.setPassword(hashedPassword);
            userDTO.setName(name);
            userDTO.setUserNAme(userName);
            userDTO.setId(adminId);
            userDTO.setPhone(mobileNumber);
            userDTO.setRole(role);

            boolean updated= userBO.update(userDTO);

            if(updated){
                new Alert(Alert.AlertType.INFORMATION,"User Updated Successfully").show();
                refreshPage();
            }else {
                new Alert(Alert.AlertType.ERROR,"Error!! Can't Update User").show();
            }
        }else{
            new Alert(Alert.AlertType.ERROR,"Input your Password Correctly!!").show();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        roleSelector.setItems(FXCollections.observableArrayList("Admin", "Receptionist"));
        colAdminId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colMobileNumber.setCellValueFactory(new PropertyValueFactory<>("phone"));
        passwordColum.setCellValueFactory(new PropertyValueFactory<>("password"));
        coluserName.setCellValueFactory(new PropertyValueFactory<>("userName"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colRole.setCellValueFactory(new PropertyValueFactory<>("role"));

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
        txtUserName.setText("");
        txtPassword.setText("");
        txtPasswordHidden.setText("");
        roleSelector.setValue("");
    }
    public void genarateId(){
        if (txtAdminId != null) {
            txtAdminId.setText(userBO.genareateID());
        } else {
            System.out.println("txtAdminId is null");
        }
    }
    public void loadTableData(){
        List<UserDTO> allAdmin = userBO.findAll();
        ObservableList<UserTM> userTMS = FXCollections.observableArrayList();

        for(UserDTO userDTO : allAdmin){
            UserTM userTM = new UserTM(
                    userDTO.getId(),
                    userDTO.getName(),
                    userDTO.getEmail(),
                    userDTO.getPhone(),
                    userDTO.getUserNAme(),
                    userDTO.getPassword(),
                    userDTO.getRole()
            );
            userTMS.add(userTM);
        }
        adminTable.setItems(userTMS);
    }
}
