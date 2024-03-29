package Chess.controllers;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class SaveExitController {
    private List<String> moveList;
    private String whitePlayer;
    private String blackPlayer;
    private int id = -1; // default previously non-saved game
    @FXML
    private Button YesButton;
    @FXML
    private Button NoButton;

    public SaveExitController() {
        // Constructor logic here (if needed)
    }

    public void setMoves(List<String> movesList) {
        this.moveList = movesList;
    }

    public void setWhitePlayer(String name) {
        this.whitePlayer = name;
    }

    public void setBlackPlayer(String name) {
        this.blackPlayer = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    @FXML
    public void YesClicked() {
        // load back to welcome screen writing to database
        // WRITE TO DATABASE
        if (id < 0) { // new game not a loaded game with an id
            String database = "./SavedGamesDatabase.csv";
            String moveString = "";
            int id = countLines(database);
            String DELIMETER = ",";
            for (String move : moveList) {
                moveString += move + "."; // separate moves with "." later to be decomposed in load game
            }

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(database, true))) {
                writer.write(id + DELIMETER + moveString + DELIMETER + whitePlayer + DELIMETER + blackPlayer);
                writer.newLine(); // Add a newline after each append
                System.out.println("Content has been appended to the database successfully.");
                System.out.println(id);
                System.out.println(moveString);
                System.out.println(whitePlayer);
                System.out.println(blackPlayer);
            } catch (IOException e) {
                System.err.println("An error occurred while appending to the database: " + e.getMessage());
            }
        } else {
            String database = "SavedGamesDatabase.csv"; // Replace with the path to your database file
            int lineToModify = id; // Example: Modify the 3rd line
            String DELIMETER = ",";
            String moveString = "";
            String temp_write = "temp_database.csv";
            for (String move : moveList) {
                moveString += move + "."; // separate moves with "." later to be decomposed in load game
            }
            String newContent = id + DELIMETER + moveString + DELIMETER + whitePlayer + DELIMETER + blackPlayer; // Example:
                                                                                                                 // New
                                                                                                                 // content
                                                                                                                 // for
                                                                                                                 // the
                                                                                                                 // line

            try (BufferedReader reader = new BufferedReader(new FileReader(database));
                    BufferedWriter writer = new BufferedWriter(new FileWriter(temp_write))) {

                String line;
                int currentLine = 0;
                while ((line = reader.readLine()) != null) {
                    currentLine++;
                    if (currentLine == lineToModify) {
                        // Modify the specific line
                        writer.write(newContent);
                    } else {
                        // Write the line as it is
                        writer.write(line);
                    }
                    writer.newLine(); // Write a newline character
                }
            } catch (IOException e) {
                e.printStackTrace();
                // Handle the exception
            }
            // Rename the temporary file to the original file
            File tempFile = new File(temp_write);
            File databaseFile = new File(database);
            if (tempFile.renameTo(databaseFile)) {
                System.out.println("Database modified successfully.");
            } else {
                System.err.println("Failed to rename temporary file.");
            }
        }

        // end
        try {
            // Load the FXML file of the new scene
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../../WelcomeScreen.fxml"));
            Parent root = loader.load();

            // Create a new Scene with the loaded FXML content
            Scene newScene = new Scene(root);

            // Get the current stage (window)
            Stage stage = (Stage) YesButton.getScene().getWindow();

            // Switch to the new scene
            stage.setScene(newScene);
        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception appropriately
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
    private void NoClicked() {
        // load back to welcome screen not writing to database
        try {

            // Load the FXML file of the new scene
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../../WelcomeScreen.fxml"));
            Parent root = loader.load();

            // Create a new Scene with the loaded FXML content
            Scene newScene = new Scene(root);

            // Get the current stage (window)
            Stage stage = (Stage) NoButton.getScene().getWindow();

            // Switch to the new scene
            stage.setScene(newScene);
        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception appropriately
        }

    }

}
