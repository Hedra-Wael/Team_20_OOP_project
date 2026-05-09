package com.mycompany.oop_project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.io.IOException;




public class LoginController {
    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;
    @FXML private Label errorLabel;
    private Stage stage;
    private Scene scene;
    private Parent root;



    @FXML
    public void switchToRoomBrowser(ActionEvent event) throws IOException {
        String username = usernameField.getText();
        String password = passwordField.getText();


        if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
            errorLabel.setText("Username and Password cannot be empty");
            errorLabel.setTextFill(Color.RED);
            return;
        }


        for (Guest guest : HotelDatabase.guests) {
            if (guest.getUsername().equals(username) && guest.getPassword().equals(password)) {
                switchScene(event, "/com/mycompany/oop_project/RoomBrowser.fxml");
                return;
            }
        }


        for (Staff staff : HotelDatabase.staffMembers) {
            if (staff.getUsername().equals(username) && staff.getPassword().equals(password)) {
                App.setRoot("ReservationManagement");
                return;
            }
        }


        errorLabel.setText("Wrong Username or Password");
        errorLabel.setTextFill(Color.RED);
    }
    private void switchScene(ActionEvent event, String fxmlPath) throws IOException {
        root = FXMLLoader.load(getClass().getResource(fxmlPath));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

   @FXML
    public void switchToRegister(ActionEvent event) throws IOException {

        App.setRoot("Register");
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
