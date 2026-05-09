package com.mycompany.oop_project;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class App extends Application {

    // This static scene variable is the secret to making the Scene Switcher work
    private static Scene scene;
    public static Guest currentGuest;

    @Override
    public void start(Stage stage) throws IOException {
        // 1. Initialize the backend data first!
        HotelDatabase.loaddata(); 
        
        // 2. Set the starting screen to the Login UI
        scene = new Scene(loadFXML("Login"), 800, 600); 
        
        stage.setTitle("Hotel Reservation System - Group 4");
        stage.setScene(scene);
        stage.show();
    }

    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }
}    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        if (password == null || password.isEmpty()) {
            throw new IllegalArgumentException("Invalid password");
        }
        if (password.length() < 6) {
            throw new IllegalArgumentException("Password must be at least 6 characters");
        }
        this.password = password;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        if (dateOfBirth == null) {
            throw new IllegalArgumentException("Invalid date of birth");
        }
        this.dateOfBirth = dateOfBirth;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        if (balance < 0) {
            throw new IllegalArgumentException("Balance cannot be negative");
        }
        this.balance = balance;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        if (address == null || address.trim().isEmpty()) {
            throw new IllegalArgumentException("Invalid address");
        }
        this.address = address;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        if (gender == null) {
            throw new IllegalArgumentException("Invalid gender");
        }
        this.gender = gender;
    }

    public List<String> getRoomPreferences() {
        return roomPreferences;
    }

    public void addRoomPreference(String preference) {
        if (preference == null || preference.trim().isEmpty()) {
            throw new IllegalArgumentException("Invalid room preference");
        }
        roomPreferences.add(preference);
    }

    public void register(String username, String password) {
        setUsername(username);
        setPassword(password);
        System.out.println("Registration successful!");
    }

    public boolean login(String username, String password) {
        if (this.username.equals(username) && this.password.equals(password)) {
            System.out.println("Login successful!");
            return true;
        } else {
            System.out.println("Invalid username or password");
            return false;
        }
    }
}
