package Chess;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class GamePreview {
    private int id = 0;
    @FXML
    private TextArea previewBoard;
    @FXML
    private TextArea previewMoves;
    @FXML
    private Label gamePreview;

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
            String[] moves = moves_String.split(".");
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

}
