package Chess;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Accordion;
import javafx.scene.control.TitledPane;

public class LoadScreen {
    @FXML
    private Accordion accordion;

    public void initialize() {
        String database = "SavedGamesDatabase.csv";
        int numberOfTitledPanes = countLines(database); // Example: Get this from user input
        System.out.println("Total of saved games:" + numberOfTitledPanes);

        try {

            for (int i = 2; i < numberOfTitledPanes + 2; i++) { // first row is header, we start at second row
                FXMLLoader titledPaneLoader = new FXMLLoader(getClass().getResource("GamePreview.fxml"));
                GamePreview previewGame = new GamePreview(i);
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
}
