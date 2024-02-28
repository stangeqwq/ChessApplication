package Chess.controllers;

import java.io.IOException;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class WelcomeScreenController {
    @FXML
    private Button New;
    @FXML
    private Button Load;
    @FXML
    private Button Exit;

    @FXML
    private void NewGame() {
        // Load the FXML file of the new scene
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../../StartChess.fxml"));
            Parent root = loader.load();

            // Create a new Scene with the loaded FXML content
            Scene newScene = new Scene(root);

            // Get the current stage (window)
            Stage stage = (Stage) New.getScene().getWindow();

            // Switch to the new scene
            stage.setScene(newScene);

        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception appropriately
        }

    }

    @FXML
    private void LoadGame() {
        // fetch from database and then load the state by setting each move
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../../LoadScreen.fxml"));
            Parent root = loader.load();

            // Create a new Scene with the loaded FXML content
            Scene newScene = new Scene(root);

            // Get the current stage (window)
            Stage stage = (Stage) New.getScene().getWindow();

            // Switch to the new scene
            stage.setScene(newScene);

        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception appropriately
        }
    }

    @FXML
    private void ExitApplication() {
        Platform.exit();
        System.exit(0);
    }
}
