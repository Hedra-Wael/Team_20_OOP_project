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
        HotelDatabase.loaddata(); // Initialize your dummy data
        showLoginScreen();
    }

    public void showLoginScreen() {
        VBox layout = new VBox(15);
        layout.setPadding(new Insets(40));
        layout.setAlignment(Pos.CENTER);
        layout.setStyle("-fx-background-color: #f4f4f4;");

        Label titleLabel = new Label("Hotel Management System");
        titleLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

        TextField usernameField = new TextField();
        usernameField.setPromptText("Username");
        
        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Password");

        Button loginBtn = new Button("Login");
        loginBtn.setMinWidth(100);
        
        Label statusLabel = new Label();

        loginBtn.setOnAction(e -> {
            String user = usernameField.getText();
            String pass = passwordField.getText();

            // Simple Logic: Check against your HotelDatabase
            boolean found = false;
            for (Guest g : HotelDatabase.guests) {
                if (g.getUsername().equals(user) && g.getPassword().equals(pass)) {
                    showGuestDashboard(g);
                    found = true;
                    break;
                }
            }

            // For demo: Let "admin" / "admin" in as Admin
            if (!found && user.equals("admin") && pass.equals("admin")) {
                Admin adminStaff = new Admin("admin", "admin", LocalDate.of(1990, 1, 1), 40);
                showAdminDashboard(adminStaff);
                found = true;
            }

            if (!found) statusLabel.setText("Invalid Credentials!");
        });

        layout.getChildren().addAll(titleLabel, usernameField, passwordField, loginBtn, statusLabel);

        Scene scene = new Scene(layout, 400, 350);
        primaryStage.setTitle("Hotel Login");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void showAdminDashboard(Admin admin) {
        BorderPane root = new BorderPane();
        VBox sidebar = new VBox(10);
        sidebar.setPadding(new Insets(10));
        sidebar.setStyle("-fx-background-color: #333;");

        Label welcome = new Label("Admin: " + admin.getUsername());
        welcome.setStyle("-fx-text-fill: white;");

        Button btnRooms = new Button("Manage Rooms");
        Button btnReservations = new Button("All Reservations");
        Button btnLogout = new Button("Logout");
        
        btnRooms.setMaxWidth(Double.MAX_VALUE);
        btnReservations.setMaxWidth(Double.MAX_VALUE);
        btnLogout.setOnAction(e -> showLoginScreen());

        sidebar.getChildren().addAll(welcome, new Separator(), btnRooms, btnReservations, btnLogout);

        // Center Content: Room List
        ListView<String> roomListView = new ListView<>();
        for (Room r : HotelDatabase.rooms) {
            roomListView.getItems().add("Room " + r.getRoomNumber() + " - " + (r.isAvailable() ? "Available" : "Occupied"));
        }

        root.setLeft(sidebar);
        root.setCenter(roomListView);

        Scene scene = new Scene(root, 700, 500);
        primaryStage.setScene(scene);
    }

    private void showGuestDashboard(Guest guest) {
        VBox layout = new VBox(20);
        layout.setPadding(new Insets(20));
        layout.setAlignment(Pos.TOP_LEFT);

        Label welcome = new Label("Welcome back, " + guest.getUsername());
        welcome.setStyle("-fx-font-size: 20px;");

        Label balLabel = new Label("Current Balance: $" + guest.getBalance());
        
        Button btnBook = new Button("Book a Room");
        Button btnLogout = new Button("Logout");
        btnLogout.setOnAction(e -> showLoginScreen());

        layout.getChildren().addAll(welcome, balLabel, btnBook, btnLogout);

        Scene scene = new Scene(layout, 600, 400);
        primaryStage.setScene(scene);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
