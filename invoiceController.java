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

public class InvoiceController implements Initializable {

    @FXML private Label guestNameLabel;
    @FXML private Label totalAmountLabel;
    @FXML private Label statusLabel;
    @FXML private ChoiceBox<PaymentMethod> choiceBox;

    private Reservation currentReservation;
    private Invoice currentInvoice;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // 1. Populate the dropdown with CASH, CREDIT_CARD, etc.
        choiceBox.getItems().addAll(PaymentMethod.values());
        
        // 2. Automatically load the reservation data if it exists in memory!
        if (App.currentReservationToPay != null) {
            setupInvoice(App.currentReservationToPay);
        } else {
            guestNameLabel.setText("Error: No reservation found");
        }
    }

    public void setupInvoice(Reservation reservation) {
        this.currentReservation = reservation;
        double pricePerNight = reservation.getRoom().getRoomType().getBasePrice();
        
        // Creates the invoice object with a 14% tax rate (0.14)
        currentInvoice = new Invoice(HotelDatabase.invoices.size() + 5001, reservation, pricePerNight, 0.14);
        
        guestNameLabel.setText(reservation.getGuest().getUsername());
        totalAmountLabel.setText(String.format("%.2f EGP", currentInvoice.getTotalAmount()));
    }

    @FXML
    public void payNow(ActionEvent e) {
        PaymentMethod selectedMethod = choiceBox.getValue();
        
        if (selectedMethod == null) {
            statusLabel.setText("Please select a payment method!");
            statusLabel.setTextFill(Color.RED);
            return;
        }
        
        try {
            // Process the payment
            currentInvoice.processPayment(selectedMethod);
            
            // Mark the reservation as completed/paid!
            currentReservation.setStatus(ReservationStatus.COMPLETED); 
            
            statusLabel.setText("Payment Successful!");
            statusLabel.setTextFill(Color.GREEN);
            HotelDatabase.invoices.add(currentInvoice);
            
        } catch (InvalidPaymentException ex) {
            statusLabel.setText(ex.getMessage());
            statusLabel.setTextFill(Color.RED);
        }
    }

    @FXML
    public void goBack(ActionEvent event) throws IOException {
        // Clear the memory and return to the Dashboard
        App.currentReservationToPay = null;
        App.setRoot("GuestDashboard");
    }
}
