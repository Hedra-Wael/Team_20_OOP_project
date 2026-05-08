package com.mycompany.oop_project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class invoiceController implements Initializable {

    @FXML private Label guestNameLabel;
    @FXML private Label totalAmountLabel;
    @FXML private Label statusLabel;
    @FXML private ChoiceBox<PaymentMethod> choiceBox;

    private Reservation currentReservation;
    private Invoice currentInvoice;
    private SceneController sceneController = new SceneController();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        choiceBox.getItems().addAll(PaymentMethod.values());
    }

    public void setupInvoice(Reservation reservation) {
        this.currentReservation = reservation;
        double pricePerNight = reservation.getRoom().getRoomType().getBasePrice();
        currentInvoice = new Invoice(HotelDatabase.invoices.size() + 5001, reservation, pricePerNight, 0.14);
        guestNameLabel.setText(reservation.getGuest().getUsername());
        totalAmountLabel.setText(String.format("%.2f EGP", currentInvoice.getTotalAmount()));
    }

    @FXML
    public void payNow(ActionEvent e) {
        PaymentMethod selectedMethod = choiceBox.getValue();
        if (selectedMethod == null) {
            statusLabel.setText("Please select a payment method!");
            return;
        }
        try {
            currentInvoice.processPayment(selectedMethod);
            statusLabel.setText("Success!");
            statusLabel.setTextFill(Color.GREEN);
            HotelDatabase.invoices.add(currentInvoice);
        } catch (InvalidPaymentException ex) {
            statusLabel.setText(ex.getMessage());
            statusLabel.setTextFill(Color.RED);
        }
    }

    @FXML
    public void goBack(ActionEvent event) throws IOException {
        sceneController.switchToDashboard(event);
    }
}
