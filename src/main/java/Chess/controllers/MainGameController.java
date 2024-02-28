package Chess.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import javafx.event.ActionEvent;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import Chess.ChessGame;

public class MainGameController {
    @FXML
    private TextField inputMove;
    @FXML
    private TextArea Board;
    @FXML
    private Button OkButton;
    @FXML
    private TextArea MoveList;
    @FXML
    private Button SaveExit;

    private String move;

    private ChessGame thegame;
    private String player1;
    private String player2; // default
    private int id = -1; // default new game
    private String moves_String;

    public void setPlayer1(String player1) {
        this.player1 = player1;
    }

    public void setPlayer2(String player2) {
        this.player2 = player2;
    }

    public void setId(int id) {
        this.id = id;
    }

    @FXML
    void initialize() {
        if (id < 0) {
            thegame = new ChessGame(player1, player2);
            Board.clear();
            Board.appendText(thegame.getBoard());
            MoveList.clear();
            MoveList.appendText(thegame.toString());
        } else { // saved game, load it
            String database = "SavedGamesDatabase.csv";
            try (BufferedReader reader = new BufferedReader(new FileReader(database))) { // read from database the saved
                                                                                         // game
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
                        player1 = columns[2];
                        player2 = columns[3];
                        break; // Exit the loop after reading the target row
                    }
                }

                thegame = new ChessGame(player1, player2);
                List<String> moves = Arrays.asList(moves_String.split("\\.")); // specify not all characters but
                                                                               // strictly
                                                                               // the dot

                for (String move : moves) {
                    thegame.move(move);
                }
                Board.clear();
                Board.appendText(thegame.getBoard());
                MoveList.clear();
                MoveList.appendText(thegame.toString());

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    private void handleInputMove() {
        try {
            move = inputMove.getText();
            thegame.move(move);
            Board.clear();
            Board.appendText(thegame.getBoard());
            MoveList.clear();
            MoveList.appendText(thegame.toString());
        } catch (Exception event) {
            System.out.println(event);

        }
    }

    @FXML
    private void SaveExitClicked() {
        // Load the FXML file of the new scene
        try {
            SaveExitController ExitController = new SaveExitController();

            ExitController.setMoves(thegame.getMovesList());
            ExitController.setWhitePlayer(player1);
            ExitController.setBlackPlayer(player2);
            ExitController.setId(id);

            FXMLLoader loader = new FXMLLoader(getClass().getResource("../../SaveExit.fxml"));
            loader.setController(ExitController);
            Parent root = loader.load();

            // Create a new Scene with the loaded FXML content
            Scene newScene = new Scene(root);

            // Get the current stage (window)
            Stage stage = (Stage) SaveExit.getScene().getWindow();

            // Switch to the new scene
            stage.setScene(newScene);

        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception appropriately
        }

    }

}
