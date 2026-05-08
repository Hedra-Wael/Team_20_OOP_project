package com.example.hotelreservation;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

public class LoginController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label errorLabel;

    @FXML
    public void handleLogin() {
        errorLabel.setText("");

        String username = usernameField.getText();
        String password = passwordField.getText();

        if (username == null  username.trim().isEmpty()  
            || password == null || password.trim().isEmpty()) {
            
            errorLabel.setText("Username and Password cannot be empty");
            errorLabel.setTextFill(Color.RED);
            return; 
        }

        for (Guest guest : HotelDatabase.guests) {
            if (guest.getUsername().equals(username) && guest.getPassword().equals(password)) {
                System.out.println("Guest Login Successful!");
                return; 
            }
        }

        for (Staff staff : HotelDatabase.staffMembers) {
            if (staff.getUsername().equals(username) && staff.getPassword().equals(password)) {
                System.out.println("Staff Login Successful! Role: " + staff.getRole());
                return; 
            }
        }

        errorLabel.setText("Wrong Username or Password");
        errorLabel.setTextFill(Color.RED);
    }
}
