package Chess;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TitledPane;
import javafx.stage.Stage;

public class GamePreview {
    private int id = 1;
    @FXML
    private TextArea previewBoard;
    @FXML
    private TextArea previewMoves;
    @FXML
    private TitledPane gamePreview;
    @FXML
    private Button loadButton;

    private String database = "SavedGamesDatabase.csv";
    private String moves_String;
    private String whitePlayer;
    private String blackPlayer;

    public GamePreview(int id) {
        this.id = id;
    }

    @FXML
    private void initialize() {
        // get the data at the id/targetRow (used when checking the load game screen)
        // update that pane to show the saved game
        try (BufferedReader reader = new BufferedReader(new FileReader(database))) {
            String line;
            int currentRow = 0;
            int targetRow = id;

            while ((line = reader.readLine()) != null) {
                currentRow++;
                if (currentRow == targetRow) {
                    System.out.println("Row " + targetRow + ": " + line);
                    // Process the data from the desired row
                    String[] columns = line.split(",");
                    moves_String = columns[1];
                    whitePlayer = columns[2];
                    blackPlayer = columns[3];
                    break; // Exit the loop after reading the target row
                }
            }

            ChessGame thegame = new ChessGame(whitePlayer, blackPlayer);
            List<String> moves = Arrays.asList(moves_String.split("\\.")); // specify not all characters but strictly
                                                                           // the dot

            for (String move : moves) {
                thegame.move(move);
            }
            gamePreview.setText("#" + id + " " + whitePlayer + " vs. " + blackPlayer);
            previewBoard.clear();
            previewBoard.appendText(thegame.getBoard());
            previewMoves.clear();
            previewMoves.appendText(thegame.toString());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void LoadGame() {
        try {
            MainGameController mainController = new MainGameController();

            // Set player names before loading
            mainController.setPlayer1(whitePlayer);
            mainController.setPlayer2(blackPlayer);
            mainController.setId(id); // specify that this is a saved game

            // Load the FXML file of the new scene
            FXMLLoader loader = new FXMLLoader(getClass().getResource("MainGame.fxml"));
            loader.setController(mainController); // use the controller which have set the names with the inputs on this
                                                  // scene
            Parent root = loader.load();

            // Create a new Scene with the loaded FXML content
            Scene newScene = new Scene(root);

            // Get the current stage (window)
            Stage stage = (Stage) loadButton.getScene().getWindow();

            // Switch to the new scene
            stage.setScene(newScene);
        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception appropriately
        }
    }

}
