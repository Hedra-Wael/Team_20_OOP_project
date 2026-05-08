package com.mycompany.oop_project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import java.io.IOException;

public class GuestDashboardController {

    @FXML private Label welcomeLabel;
    @FXML private Label balanceLabel;
    @FXML private TableView<Reservation> reservationsTable;
    @FXML private TableColumn<Reservation, String> colResId;
    @FXML private TableColumn<Reservation, String> colRoom;
    @FXML private TableColumn<Reservation, String> colCheckIn;
    @FXML private TableColumn<Reservation, String> colStatus;

    private SceneController sceneController = new SceneController();

    @FXML
    public void initialize() {
        welcomeLabel.setText("Welcome, Guest!");
        balanceLabel.setText("Balance: 0.0 EGP");
    }

    @FXML
    public void handleLogout(ActionEvent event) throws IOException {
        sceneController.switchToLogin(event);
    }

    @FXML
    public void goToRoomBrowser(ActionEvent event) throws IOException {
        sceneController.switchToRoomBrowser(event);
    }
}
