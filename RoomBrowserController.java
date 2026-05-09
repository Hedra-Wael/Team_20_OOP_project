package com.mycompany.oop_project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.ComboBox;
import javafx.scene.control.CheckBox;
import javafx.stage.Stage;
import java.io.IOException;
import java.time.LocalDate;

public class RoomBrowserController {
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML private ListView<Room> roomListView;
    @FXML private ComboBox<String> roomTypeFilter;
    @FXML private CheckBox availableOnlyCheckbox;

    @FXML
    public void initialize() {
        roomTypeFilter.getItems().addAll("All", "Single", "Double", "Suite");
        roomTypeFilter.setValue("All");
        roomListView.getItems().setAll(HotelDatabase.rooms);
    }

    @FXML
    private void filterRooms() {
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
        root = FXMLLoader.load(getClass().getResource("/com/mycompany/oop_project/GuestDashboard.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void handleBooking(ActionEvent event) throws IOException {
        if (App.currentGuest == null) {
            return;
        }

        Room selectedRoom = roomListView.getSelectionModel().getSelectedItem();

        if (selectedRoom != null && selectedRoom.isAvailable()) {
            selectedRoom.setAvailable(false);

            double price = selectedRoom.getRoomType().getBasePrice();
            App.currentGuest.setBalance(App.currentGuest.getBalance() - price);

            Reservation newRes = new Reservation(
                    HotelDatabase.reservations.size() + 1001,
                    App.currentGuest,
                    selectedRoom,
                    LocalDate.now(),
                    LocalDate.now().plusDays(1)
            );
            HotelDatabase.reservations.add(newRes);

            HotelDatabase.reservations.add(newRes);
            App.setRoot("GuestDashboard");
        }
    }

}
