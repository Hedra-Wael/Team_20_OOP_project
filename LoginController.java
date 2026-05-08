package com.mycompany.oop_project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import java.io.IOException;

public class LoginController {

    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;
    @FXML private Label errorLabel;

    private SceneController sceneController = new SceneController();

    @FXML
    public void handleLogin(ActionEvent event) throws IOException {
        errorLabel.setText("");
        String username = usernameField.getText();
        String password = passwordField.getText();

        if (username == null || username.trim().isEmpty() || password == null || password.trim().isEmpty()) {
            errorLabel.setText("Username and Password cannot be empty");
            errorLabel.setTextFill(Color.RED);
            return;
        }

        for (Guest guest : HotelDatabase.guests) {
            if (guest.getUsername().equals(username) && guest.getPassword().equals(password)) {
                sceneController.switchToDashboard(event);
                return;
            }
        }

        for (Staff staff : HotelDatabase.staffMembers) {
            if (staff.getUsername().equals(username) && staff.getPassword().equals(password)) {
                sceneController.switchToReservationManagement(event);
                return;
            }
        }

        errorLabel.setText("Wrong Username or Password");
        errorLabel.setTextFill(Color.RED);
    }
}
