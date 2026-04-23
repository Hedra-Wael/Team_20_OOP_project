package com.mycompany.oop_project;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.time.LocalDate;

public class HotelGUI extends Application {

    private Stage primaryStage;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        // Initialize the database with dummy data
        HotelDatabase.loaddata(); 
        showLoginScreen();
    }

    public void showLoginScreen() {
        VBox layout = new VBox(15);
        layout.setPadding(new Insets(40));
        layout.setAlignment(Pos.CENTER);
        layout.setStyle("-fx-background-color: #ecf0f1;");

        Label titleLabel = new Label("Grand Horizon Hotel");
        titleLabel.setStyle("-fx-font-size: 26px; -fx-font-weight: bold; -fx-text-fill: #2c3e50;");

        TextField usernameField = new TextField();
        usernameField.setPromptText("Username");
        usernameField.setMaxWidth(250);
        
        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Password");
        passwordField.setMaxWidth(250);

        Button loginBtn = new Button("Login");
        loginBtn.setStyle("-fx-background-color: #3498db; -fx-text-fill: white; -fx-font-weight: bold;");
        loginBtn.setMinWidth(100);
        
        Label statusLabel = new Label();

        loginBtn.setOnAction(e -> {
            String user = usernameField.getText();
            String pass = passwordField.getText();

            // Check Guest Login
            boolean found = false;
            for (Guest g : HotelDatabase.guests) {
                if (g.getUsername().equals(user) && g.getPassword().equals(pass)) {
                    showGuestDashboard(g);
                    found = true;
                    break;
                }
            }

            // Simple Logic for Admin/Staff Access
            if (!found && user.equals("admin") && pass.equals("admin")) {
                Admin adminStaff = new Admin("admin", "admin", LocalDate.of(1990, 1, 1), 40);
                showAdminDashboard(adminStaff);
                found = true;
            }

            if (!found) {
                statusLabel.setText("Invalid username or password!");
                statusLabel.setStyle("-fx-text-fill: red;");
            }
        });

        layout.getChildren().addAll(titleLabel, usernameField, passwordField, loginBtn, statusLabel);

        Scene scene = new Scene(layout, 450, 400);
        primaryStage.setTitle("Hotel Management System - Login");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void showAdminDashboard(Admin admin) {
        BorderPane root = new BorderPane();
        
        // Sidebar Navigation
        VBox sidebar = new VBox(15);
        sidebar.setPadding(new Insets(20));
        sidebar.setStyle("-fx-background-color: #2c3e50;");
        sidebar.setPrefWidth(200);

        Label lblUser = new Label("Staff: " + admin.getUsername());
        lblUser.setStyle("-fx-text-fill: white; -fx-font-weight: bold;");

        Button btnRooms = new Button("View Rooms");
        Button btnReservations = new Button("View Reservations");
        Button btnLogout = new Button("Logout");
        
        // Style buttons
        String btnStyle = "-fx-background-color: #34495e; -fx-text-fill: white; -fx-alignment: CENTER-LEFT;";
        btnRooms.setStyle(btnStyle); btnRooms.setMaxWidth(Double.MAX_VALUE);
        btnReservations.setStyle(btnStyle); btnReservations.setMaxWidth(Double.MAX_VALUE);
        btnLogout.setStyle("-fx-background-color: #e74c3c; -fx-text-fill: white;");
        
        btnLogout.setOnAction(e -> showLoginScreen());

        sidebar.getChildren().addAll(lblUser, new Separator(), btnRooms, btnReservations, btnLogout);

        // Content Area: ListView for Rooms
        ListView<String> contentList = new ListView<>();
        btnRooms.setOnAction(e -> {
            contentList.getItems().clear();
            for (Room r : HotelDatabase.rooms) {
                String status = r.isAvailable() ? "AVAILABLE" : "OCCUPIED";
                contentList.getItems().add("Room " + r.getRoomNumber() + " [" + r.getRoomType().getName() + "] - " + status);
            }
        });
