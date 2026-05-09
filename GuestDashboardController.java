package com.mycompany.oop_project;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import java.io.IOException;

public class GuestDashboardController {

    @FXML private Label welcomeLabel;
    @FXML private Label balanceLabel;
    @FXML private TableView<Reservation> reservationsTable;
    @FXML private TableColumn<Reservation, Integer> colResId;
    @FXML private TableColumn<Reservation, String> colRoom;
    @FXML private TableColumn<Reservation, String> colCheckIn;
    @FXML private TableColumn<Reservation, String> colStatus;

    @FXML
    public void initialize() {
        // Load the actual user's data!
        if (App.currentGuest != null) {
            welcomeLabel.setText("Welcome, " + App.currentGuest.getUsername() + "!");
            balanceLabel.setText("Balance: " + App.currentGuest.getBalance() + " EGP");

            // Setup the Table Columns
            colResId.setCellValueFactory(new PropertyValueFactory<>("reservationId"));
            colRoom.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getRoom().getRoomNumber())));
            colCheckIn.setCellValueFactory(new PropertyValueFactory<>("checkInDate"));
            colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));

            // Filter reservations for THIS guest only
            ObservableList<Reservation> myReservations = FXCollections.observableArrayList();
            for (Reservation r : HotelDatabase.reservations) {
                if (r.getGuest().getUsername().equals(App.currentGuest.getUsername())) {
                    myReservations.add(r);
                }
            }
            reservationsTable.setItems(myReservations);
        }
    }

    @FXML
    public void handleLogout(ActionEvent event) throws IOException {
        // Clear memory and go back to login
        App.currentGuest = null;
        App.setRoot("Login");
    }

    @FXML
    public void goToRoomBrowser(ActionEvent event) throws IOException {
        App.setRoot("RoomBrowser");
    }
    @FXML
    public void goToCheckout(ActionEvent event) throws IOException {
        // Grab the clicked reservation from the table
        Reservation selected = reservationsTable.getSelectionModel().getSelectedItem();
        
        // Safety Check: Did they actually click a row?
        if (selected == null) {
            System.out.println("ERROR: Please click on a reservation in the table first!");
            return;
        }
        
        // The Magic Lines! Save it to memory and switch screens
        App.currentReservationToPay = selected;
        App.setRoot("Invoice");
    }
}
