package com.service.Project.HealthCare.controller;

import com.service.Project.HealthCare.bo.BOFactory;
import com.service.Project.HealthCare.bo.custom.PatientBO;
import com.service.Project.HealthCare.bo.custom.PaymentBO;
import com.service.Project.HealthCare.dto.PaymentDTO;
import com.service.Project.HealthCare.dto.TM.PaymentTM;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

public class PaymentController implements Initializable {
    PaymentBO paymentBO;
    PatientBO pationBO;

    {
        try {
            paymentBO = (PaymentBO) BOFactory.getInstance().getBOType(BOFactory.BOType.payment);
            pationBO = (PatientBO) BOFactory.getInstance().getBOType(BOFactory.BOType.Patient);

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
        String patient = cmbPatient.getValue();
        String method = cmbPaymentMethod.getValue();
        String amountText = txtAmount.getText();
        LocalDate date = datePicker.getValue();

        if (patient == null || patient.isEmpty()) {
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

        String paymentId = java.util.UUID.randomUUID().toString();

        boolean isSaved = paymentBO.save(new PaymentDTO(paymentId, patient, method, amount, date));

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
        String patient = cmbPatient.getValue();
        String method = cmbPaymentMethod.getValue();
        String amountText = txtAmount.getText();
        LocalDate date = datePicker.getValue();

        if (patient == null || patient.isEmpty()) {
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

        String paymentId = java.util.UUID.randomUUID().toString();

        boolean isSaved = paymentBO.update(new PaymentDTO(paymentId, patient, method, amount, date));

        if (isSaved) {
            new Alert(Alert.AlertType.INFORMATION, "Payment Successful!").showAndWait();
            refreshPage();
        } else {
            new Alert(Alert.AlertType.ERROR, "Failed to make payment.").showAndWait();
        }


    }


    public void searchPatient(ActionEvent event) {
    }

    public void clearFilter(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cmbPaymentMethod.setItems(FXCollections.observableArrayList("Cash", "Card","Mobile Transfer"));
    }

    public void ClickedPation(ActionEvent event) {
        List<String> patientIds = pationBO.getAllPatientIds();
        cmbPatient.getItems().setAll(patientIds);
    }
    public void refreshPage() {
        txtPayment.clear();
        txtAmount.clear();
        txtAvailablePayment.clear();
        txtAllPayment.clear();
        txtSearch.clear();

        cmbPatient.getSelectionModel().clearSelection();
        cmbPaymentMethod.getSelectionModel().clearSelection();

        datePicker.setValue(null);
        lblStatus.setText("");
        txtPaymentId.setText("AUTO");

    }
}
