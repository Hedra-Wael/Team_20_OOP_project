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
        java.net.URL cssURL = App.class.getResource("/com/mycompany/oop_project/styles.css");
        if (cssURL != null) {
            scene.getStylesheets().add(cssURL.toExternalForm());
        } else {
            System.out.println("CSS FILE MISSING! Java cannot find styles.css");
        }
        stage.setTitle("Hotel Reservation System - Team 20");
        stage.setScene(scene);
        stage.show();
    }
    
    // ==========================================
    //       THE SCENE SWITCHER ENGINE
    // ==========================================
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
}
