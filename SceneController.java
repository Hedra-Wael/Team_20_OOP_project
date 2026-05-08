package com.mycompany.oop_project;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class SceneController {

    private Stage stage;
    private Scene scene;
    private Parent root;

    public void switchToLogin(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/com/mycompany/oop_project/Login.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToDashboard(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/com/mycompany/oop_project/GuestDashboard.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToRoomBrowser(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/com/mycompany/oop_project/RoomBrowser.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToReservationManagement(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/com/mycompany/oop_project/ReservationManagement.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToInvoice(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/com/mycompany/oop_project/Invoice.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
