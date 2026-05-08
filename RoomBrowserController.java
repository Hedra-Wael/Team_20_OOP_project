package com.mycompany.oop_project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.ComboBox;
import java.io.IOException;

public class RoomBrowserController {

    @FXML private ListView<Room> roomListView;
    @FXML private ComboBox<String> roomTypeFilter;

    private SceneController sceneController = new SceneController();

    @FXML
    public void initialize() {
        roomListView.getItems().setAll(HotelDatabase.rooms);
    }

    @FXML
    private void filterRooms() {
        String selectedType = roomTypeFilter.getValue();
        roomListView.getItems().clear();

        for (Room r : HotelDatabase.rooms) {
            if (selectedType == null || selectedType.equals("All") || r.getRoomType().getName().equalsIgnoreCase(selectedType)) {
                roomListView.getItems().add(r);
            }
        }
    }

    @FXML
    public void goBack(ActionEvent event) throws IOException {
        sceneController.switchToDashboard(event);
    }
}
