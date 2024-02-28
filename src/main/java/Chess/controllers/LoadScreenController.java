package Chess.controllers;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.TitledPane;
import javafx.stage.Stage;

public class LoadScreenController {
    @FXML
    private Accordion accordion;

    @FXML
    private Button goBack;

    public void initialize() {
        String database = "SavedGamesDatabase.csv";
        int numberOfTitledPanes = countLines(database); // Example: Get this from user input
        System.out.println("Total of saved games:" + numberOfTitledPanes);

        try {

            for (int i = 2; i < numberOfTitledPanes + 2; i++) { // first row is header, we start at second row
                FXMLLoader titledPaneLoader = new FXMLLoader(getClass().getResource("../../GamePreview.fxml"));
                GamePreviewController previewGame = new GamePreviewController(i);
                titledPaneLoader.setController(previewGame); // use the controller that displays each row game saved in
                                                             // database
                TitledPane titledPane = titledPaneLoader.load();
                accordion.getPanes().add(titledPane);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int countLines(String filename) {
        int count = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            // Skip the header row
            reader.readLine();
            // Read and count the remaining lines
            String line;
            while ((line = reader.readLine()) != null) {
                count++;
            }
        } catch (IOException e) {
            System.err.println("An error occurred while counting lines: " + e.getMessage());
        }
        return count;
    }

    @FXML
    private void goHome() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../../WelcomeScreen.fxml"));
            Parent root = loader.load();

            // Create a new Scene with the loaded FXML content
            Scene newScene = new Scene(root);

            // Get the current stage (window)
            Stage stage = (Stage) goBack.getScene().getWindow();

            // Switch to the new scene
            stage.setScene(newScene);

        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception appropriately
        }
    }
}
