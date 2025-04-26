package com.service.Project.HealthCare.controller;

import com.service.Project.HealthCare.bo.BOFactory;
import com.service.Project.HealthCare.bo.custom.PatientBO;
import com.service.Project.HealthCare.bo.custom.PaymentBO;
import com.service.Project.HealthCare.bo.custom.RegistrationBO;
import com.service.Project.HealthCare.dto.PatientDTO;
import com.service.Project.HealthCare.dto.PaymentDTO;
import com.service.Project.HealthCare.dto.TM.PatientTM;
import com.service.Project.HealthCare.dto.TM.PaymentTM;
import com.service.Project.HealthCare.entity.Patient;
import com.service.Project.HealthCare.entity.Programs;
import com.service.Project.HealthCare.entity.Registration;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class PaymentController implements Initializable {
    static String serchNic;
    static String serchMobile;
    public TextField txtSearchPatient;
    PaymentBO paymentBO;
    PatientBO pationBO;
    RegistrationBO registrationBO;

    {
        try {
            paymentBO = (PaymentBO) BOFactory.getInstance().getBOType(BOFactory.BOType.payment);
            pationBO = (PatientBO) BOFactory.getInstance().getBOType(BOFactory.BOType.Patient);
            registrationBO = (RegistrationBO) BOFactory.getInstance().getBOType(BOFactory.BOType.registration);

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
    private ComboBox<String> cmbPatient;

    @FXML
    private ComboBox<String> cmbPaymentMethod;

    @FXML
    private TableColumn<PaymentTM,String> colAmount;

    @FXML
    private TableColumn<PaymentTM,String> colDate;

    @FXML
    private TableColumn<PaymentTM,String> colPatient;

    @FXML
    private TableColumn<PaymentTM,String> colPaymentId;

    @FXML
    private TableColumn<PaymentTM,String> colPaymentMethod;

    @FXML
    private DatePicker datePicker;

    @FXML
    private Label lblStatus;

    @FXML
    private TableView<PaymentTM> paymentTable;

    @FXML
    private TextField txtAllPayment;

    @FXML
    private TextField txtAmount;

    @FXML
    private TextField txtAvailablePayment;

    @FXML
    private TextField txtPayment;

    @FXML
    private Label txtPaymentId;

    @FXML
    private TextField txtSearch;

    @FXML
    void addPayment(ActionEvent event) {
        String id = txtPaymentId.getText();
        String patientId = cmbPatient.getValue();
        String method = cmbPaymentMethod.getValue();
        String amountText = txtAmount.getText();
        Double avalableAmount = Double.valueOf(txtAvailablePayment.getText());
        LocalDate date = datePicker.getValue();
        Patient patient = pationBO.getPationByID(patientId);
        Programs programs=null;

        Registration registration =registrationBO.getRegistrationByPatientId(patientId);
        if (registration != null) {
            programs= registration.getPrograms();
        } else {
            new Alert(Alert.AlertType.WARNING, "Not program Found for Patient!!!").showAndWait();
        }



        if (patient == null) {
            new Alert(Alert.AlertType.WARNING, "Please select a patient.").showAndWait();
            return;
        }

        if (method == null || method.isEmpty()) {
            new Alert(Alert.AlertType.WARNING, "Please select a payment method.").showAndWait();
            return;
        }

        if (amountText == null || amountText.trim().isEmpty()) {
            new Alert(Alert.AlertType.WARNING, "Please enter the amount.").showAndWait();
            return;
        }

        double amount;
        try {
            amount = Double.parseDouble(amountText);
            if (amount <= 0) {
                new Alert(Alert.AlertType.WARNING, "Amount must be greater than zero.").showAndWait();
                return;
            }
        } catch (NumberFormatException e) {
            new Alert(Alert.AlertType.ERROR, "Invalid amount. Please enter a valid number.").showAndWait();
            return;
        }

        if (date == null) {
            new Alert(Alert.AlertType.WARNING, "Please select a date.").showAndWait();
            return;
        }

        boolean isSaved = paymentBO.save(new PaymentDTO(id, method, amount, date,avalableAmount,patient,programs));

        if (isSaved) {
            new Alert(Alert.AlertType.INFORMATION, "Payment Successful!").showAndWait();
            refreshPage();
        } else {
            new Alert(Alert.AlertType.ERROR, "Failed to make payment.").showAndWait();
        }


    }

    @FXML
    void clicked(MouseEvent event) {
        PaymentTM paymentTM = paymentTable.getSelectionModel().getSelectedItem();
        if (paymentTM!=null){
            txtPaymentId.setText(paymentTM.getPayId());
            txtPayment.setText(paymentTM.getPayMethod());
            txtAmount.setText(paymentTM.getAmount().toString());
            txtAllPayment.setText(paymentTM.getAmount().toString());
        }
    }

    @FXML
    void deletePayment(ActionEvent event) {
        boolean isdeleted = paymentBO.delete(txtPaymentId.getText());

        if (isdeleted) {
            new Alert(Alert.AlertType.INFORMATION, "Payment Deleted Successfully!").show();
            refreshPage();
        } else {
            new Alert(Alert.AlertType.ERROR, "Failed to delete Payment!").show();
        }
    }

    @FXML
    void resetFields(ActionEvent event) {
        refreshPage();
    }

    @FXML
    void updatePayment(ActionEvent event) {
        String id = txtPaymentId.getText();
        String patientId = cmbPatient.getValue();
        String method = cmbPaymentMethod.getValue();
        String amountText = txtAmount.getText();
        Double avalableAmount = Double.valueOf(txtAvailablePayment.getText());
        LocalDate date = datePicker.getValue();
        Patient patient = pationBO.getPationByID(patientId);
        Programs programs=null;

        Registration registration =registrationBO.getRegistrationByPatientId(patientId);
        if (registration != null) {
             programs= registration.getPrograms();
        } else {
            new Alert(Alert.AlertType.WARNING, "Not program Found for Patient!!!").showAndWait();
        }



        if (patient == null) {
            new Alert(Alert.AlertType.WARNING, "Please select a patient.").showAndWait();
            return;
        }

        if (method == null || method.isEmpty()) {
            new Alert(Alert.AlertType.WARNING, "Please select a payment method.").showAndWait();
            return;
        }

        if (amountText == null || amountText.trim().isEmpty()) {
            new Alert(Alert.AlertType.WARNING, "Please enter the amount.").showAndWait();
            return;
        }

        double amount;
        try {
            amount = Double.parseDouble(amountText);
            if (amount <= 0) {
                new Alert(Alert.AlertType.WARNING, "Amount must be greater than zero.").showAndWait();
                return;
            }
        } catch (NumberFormatException e) {
            new Alert(Alert.AlertType.ERROR, "Invalid amount. Please enter a valid number.").showAndWait();
            return;
        }

        if (date == null) {
            new Alert(Alert.AlertType.WARNING, "Please select a date.").showAndWait();
            return;
        }

        boolean isSaved = paymentBO.update(new PaymentDTO(id, method, amount, date,avalableAmount,patient,programs));

        if (isSaved) {
            new Alert(Alert.AlertType.INFORMATION, "Payment Successful!").showAndWait();
            refreshPage();
        } else {
            new Alert(Alert.AlertType.ERROR, "Failed to make payment.").showAndWait();
        }


    }


    public void searchPatient(ActionEvent event) throws IOException {
        serchNic = txtSearchPatient.getText();
        serchMobile=null;
        List<Patient> pationByNic= new ArrayList<>();
        List<Patient> pationBYMobile = new ArrayList<>();
        if (serchNic==null){
            serchMobile= txtSearch.getText();
            if  (serchMobile==null){
                new Alert(Alert.AlertType.ERROR, "Enter Nic or Mobile Number!!!").showAndWait();
            }
            else {
                pationBYMobile= pationBO.getPationBYMobile( serchMobile);
            }
        }else {
            pationByNic= pationBO.getPationByNic(serchNic);
        }

        if (pationByNic.size()==0 && pationBYMobile.size()==0){
            new Alert(Alert.AlertType.ERROR, "No patient found").showAndWait();
        }else {
            Parent load = FXMLLoader.load(getClass().getResource("/View/patientSerch.fxml"));
            Scene scene = new Scene(load);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Search Patient");
            stage.show();
        }


    }

    public void clearFilter(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        txtPaymentId.setText(paymentBO.generateId());
        cmbPaymentMethod.setItems(FXCollections.observableArrayList("Cash", "Card","Mobile Transfer"));
        colAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        colPatient.setCellValueFactory(new PropertyValueFactory<>("patient"));
        colPaymentId.setCellValueFactory(new PropertyValueFactory<>("payId"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colPaymentMethod.setCellValueFactory(new PropertyValueFactory<>("payMethod"));

        loadPatientsIds();
        loadTableData();
    }

    public void ClickedPation(ActionEvent event) {
        String patient = cmbPatient.getValue();
        Registration registration=registrationBO.getRegistrationByPatientId(patient);

        Double payemnt = registration.getPayment();
        if (payemnt==0){
            txtPayment.setText("");
        }
        Double allPaymentByPatientId = paymentBO.getAllPaymentByPatientId(patient);

        if(payemnt>allPaymentByPatientId){
            txtPayment.setText(String.valueOf(payemnt));
        }else {
            txtPayment.setText(String.valueOf(allPaymentByPatientId));
        }

        Programs programs = registration.getPrograms();
        Double fullpayment =programs.getPrice();
        txtAllPayment.setText(String.valueOf(programs.getPrice()));

        txtAvailablePayment.setText(String.valueOf(fullpayment-payemnt));
    }
    public void refreshPage() {
        txtPayment.clear();
        txtAmount.clear();
        txtAvailablePayment.clear();
        txtAllPayment.clear();

        cmbPatient.getSelectionModel().clearSelection();
        cmbPaymentMethod.getSelectionModel().clearSelection();

        datePicker.setValue(null);
        lblStatus.setText("");
        txtPaymentId.setText("AUTO");

    }
    public void loadPatientsIds(){
        List<String> patientIds = pationBO.getAllPatientIds();
        ObservableList<String> patientId= FXCollections.observableArrayList();
        for (String patientI : patientIds) {
            patientId.add(patientI);
        }
        cmbPatient.setItems((patientId));
    }
    public void loadTableData(){
        List<PaymentDTO> paymentDTOS = paymentBO.findAll();
        ObservableList<PaymentTM> patientTM = FXCollections.observableArrayList();

        for(PaymentDTO paymentDTO : paymentDTOS){
            PaymentTM paymentTM = new PaymentTM(paymentDTO.getPayId(),paymentDTO.getPayMethod(),paymentDTO.getAmount(),paymentDTO.getDate(),paymentDTO.getAvailabalAmout(),paymentDTO.getPatient());
            patientTM.add(paymentTM);
        }
        paymentTable.setItems(patientTM);
    }
}
