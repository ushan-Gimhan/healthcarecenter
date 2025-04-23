package com.service.Project.HealthCare.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class PaymentController {

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnReset;

    @FXML
    private Button btnUpdate;

    @FXML
    private ComboBox<?> cmbPatient;

    @FXML
    private ComboBox<?> cmbPaymentMethod;

    @FXML
    private TableColumn<?, ?> colAmount;

    @FXML
    private TableColumn<?, ?> colDate;

    @FXML
    private TableColumn<?, ?> colPatient;

    @FXML
    private TableColumn<?, ?> colPaymentId;

    @FXML
    private TableColumn<?, ?> colPaymentMethod;

    @FXML
    private DatePicker datePicker;

    @FXML
    private Label lblStatus;

    @FXML
    private TableView<?> paymentTable;

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

    }

    @FXML
    void clicked(MouseEvent event) {

    }

    @FXML
    void deletePayment(ActionEvent event) {

    }

    @FXML
    void resetFields(ActionEvent event) {

    }

    @FXML
    void updatePayment(ActionEvent event) {

    }

    public void searchPatient(ActionEvent event) {
    }

    public void clearFilter(ActionEvent event) {

    }
}
