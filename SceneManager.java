public class SceneManager {
    private static Stage stage;

    public static void setStage(Stage primaryStage) {
        stage = primaryStage;
    }

    public static void switchScene(String fxmlFile) throws IOException {
        // Load FXML for the required screen (Login, Dashboard, etc.)
        Parent root = FXMLLoader.load(SceneManager.class.getResource(fxmlFile));
        stage.getScene().setRoot(root); // Smooth switch without opening new windows
    }
}
