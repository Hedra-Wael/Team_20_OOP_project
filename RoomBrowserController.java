package com.mycompany.oop_project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.ComboBox;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import java.io.IOException;
import java.time.LocalDate;

public class RoomBrowserController {

    @FXML private ListView<Room> roomListView;
    @FXML private ComboBox<String> roomTypeFilter;
    @FXML private CheckBox availableOnlyCheckbox;
    @FXML private Label statusLabel; // THE NEW STATUS LABEL!

    @FXML
    public void initialize() {
        roomTypeFilter.getItems().addAll("All", "Single", "Double", "Suite");
        roomTypeFilter.setValue("All"); 
        roomListView.getItems().setAll(HotelDatabase.rooms);
    }

    @FXML
    private void filterRooms() {
        statusLabel.setText(""); // Clear any old messages
        String selectedType = roomTypeFilter.getValue();
        boolean mustBeAvailable = availableOnlyCheckbox.isSelected();
        roomListView.getItems().clear();

        for (Room r : HotelDatabase.rooms) {
            boolean typeMatches = selectedType == null || selectedType.equals("All") || r.getRoomType().getName().equalsIgnoreCase(selectedType);
            boolean availMatches = !mustBeAvailable || r.isAvailable();

            if (typeMatches && availMatches) {
                roomListView.getItems().add(r);
            }
        }
    }

    @FXML
    public void goBack(ActionEvent event) throws IOException {
        App.setRoot("GuestDashboard");
    }
    
    @FXML
    private void handleBooking(ActionEvent event) throws IOException {
        // Safety Net 1: Is a user logged in?
        if (App.currentGuest == null) {
            statusLabel.setText("System Error: No guest logged in!");
            statusLabel.setTextFill(Color.RED);
            return;
        }

        // Safety Net 2: Did they actually click a room in the list?
        Room selectedRoom = roomListView.getSelectionModel().getSelectedItem();
        if (selectedRoom == null) {
            statusLabel.setText("Please click on a room in the list first!");
            statusLabel.setTextFill(Color.RED);
            return;
        }

        // Safety Net 3: Is the room already taken?
        if (!selectedRoom.isAvailable()) {
            statusLabel.setText("Sorry, that room is already occupied!");
            statusLabel.setTextFill(Color.RED);
            return;
        }

        // ALL GOOD! PROCESS THE BOOKING!
        selectedRoom.setAvailable(false);
        double price = selectedRoom.getRoomType().getBasePrice();
        App.currentGuest.setBalance(App.currentGuest.getBalance() - price);
        
        Reservation newRes = new Reservation(HotelDatabase.reservations.size() + 1001, App.currentGuest, selectedRoom, LocalDate.now(), LocalDate.now().plusDays(1));
        HotelDatabase.reservations.add(newRes);
        
        // Go back to dashboard to see the new booking
        App.setRoot("GuestDashboard");
    }
}
