package com.mycompany.oop_project;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ReservationManagementController implements Initializable {

    @FXML private TableView<Reservation> reservationTable;
    @FXML private TableColumn<Reservation, Integer> idColumn;
    @FXML private TableColumn<Reservation, String> guestNameColumn;
    @FXML private TableColumn<Reservation, Integer> roomColumn;
    @FXML private TableColumn<Reservation, String> checkInColumn;
    @FXML private TableColumn<Reservation, String> checkOutColumn;
    @FXML private TableColumn<Reservation, String> statusColumn;
    @FXML private Button cancelButton;
    @FXML private Button viewDetailsButton;

    private SceneController sceneController = new SceneController();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("reservationId"));
        checkInColumn.setCellValueFactory(new PropertyValueFactory<>("checkInDate"));
        checkOutColumn.setCellValueFactory(new PropertyValueFactory<>("checkOutDate"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));

        guestNameColumn.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getGuest().getUsername()));

        roomColumn.setCellValueFactory(cellData ->
                new SimpleIntegerProperty(cellData.getValue().getRoom().getRoomNumber()).asObject());

        ObservableList<Reservation> reservationList = FXCollections.observableArrayList(HotelDatabase.reservations);
        reservationTable.setItems(reservationList);
    }

    @FXML
    void handleCancelReservation(ActionEvent event) {
        Reservation selected = reservationTable.getSelectionModel().getSelectedItem();
        if (selected != null) {
            selected.setStatus(ReservationStatus.CANCELLED);
            reservationTable.refresh();
        }
    }

    @FXML
    void handleAddNewReservation(ActionEvent event) throws IOException {
        sceneController.switchToRoomBrowser(event);
    }

    @FXML
    void handleLogout(ActionEvent event) throws IOException {
        sceneController.switchToLogin(event);
    }
}
