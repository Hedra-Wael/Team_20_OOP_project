package com.mycompany.oop_project;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.ComboBox;

public class RoomBrowserController {

    @FXML private ListView<Room> roomListView;
    @FXML private ComboBox<String> roomTypeFilter;

    @FXML
    public void initialize() {
        // Direct population from the Milestone 1 static list 
        roomListView.getItems().setAll(HotelDatabase.rooms);
    }

    @FXML
    private void filterRooms() {
        String selectedType = roomTypeFilter.getValue();
        roomListView.getItems().clear();

        // Simple loop to check each room
        for (Room r : HotelDatabase.rooms) {
            if (selectedType == null || selectedType.equals("All") || r.getRoomType().getName().equalsIgnoreCase(selectedType)) {
                roomListView.getItems().add(r);
            }
        }
    }
}
