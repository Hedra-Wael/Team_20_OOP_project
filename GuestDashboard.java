package com.mycompany.oop_project;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;

public class GuestDashboardController {
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML private Label welcomeLabel;
    @FXML private Label balanceLabel;

    @FXML private TableView<Reservation> reservationsTable;
    @FXML private TableColumn<Reservation, Integer> colResId;
    @FXML private TableColumn<Reservation, String> colRoom;
    @FXML private TableColumn<Reservation, LocalDate> colCheckIn;
    @FXML private TableColumn<Reservation, ReservationStatus> colStatus;

    @FXML
    public void initialize() {
        if (App.currentGuest != null) {
            welcomeLabel.setText("Welcome, " + App.currentGuest.getUsername() + "!");
            balanceLabel.setText("Balance: " + App.currentGuest.getBalance() + " EGP");


            colResId.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getReservationId()));
            colRoom.setCellValueFactory(cellData -> new SimpleStringProperty("Room " + cellData.getValue().getRoom().getRoomNumber()));
            colCheckIn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getCheckInDate()));
            colStatus.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getStatus()));


            ObservableList<Reservation> myReservations = FXCollections.observableArrayList();
            for (Reservation r : HotelDatabase.reservations) {
                if (r.getGuest() != null && r.getGuest().getUsername().equals(App.currentGuest.getUsername())) {
                    myReservations.add(r);
                }
            }

            reservationsTable.setItems(myReservations);
        }
    }



    public void goToRoomBrowser(ActionEvent event) throws IOException {
        switchScene(event, "/com/mycompany/oop_project/RoomBrowser.fxml");
    }

    @FXML
    public void handleLogout(ActionEvent event) throws IOException {
        App.currentGuest = null;
        switchScene(event, "/com/mycompany/oop_project/Login.fxml");
    }

    private void switchScene(ActionEvent event, String fxmlPath) throws IOException {
        root = FXMLLoader.load(getClass().getResource(fxmlPath));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}





}
