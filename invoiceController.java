package com.example.test;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.paint.Color;
public class invoiceController implements Initializable {

    @FXML 
    private Label guestNameLabel;
    @FXML 
    private Label totalAmountLabel;
    @FXML 
    private Label statusLabel;
    @FXML 
    private ChoiceBox<PaymentMethod> choiceBox;

    private Reservation currentReservation;
    private Invoice currentInvoice;

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

            statusLabel.setText("Success! New Balance: " + currentReservation.getGuest().getBalance());
            statusLabel.setTextFill(Color.GREEN);

            HotelDatabase.invoices.add(currentInvoice);

        } catch (InvalidPaymentException ex) {
            statusLabel.setText(ex.getMessage());
            statusLabel.setTextFill(Color.RED);
        }
    }
}
