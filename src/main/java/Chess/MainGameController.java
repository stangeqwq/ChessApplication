package Chess;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.IOException;

public class MainGameController {
    @FXML
    private TextField inputMove;
    @FXML
    private TextArea Board;

    private String move;

    @FXML
    private void initialize() {
        ChessGame game1 = new ChessGame("Alice", "Bob");
        Board.clear();
        Board.appendText(game1.getBoard());
    }

    @FXML
    private void handleMoveInput() {
        move = inputMove.getText();
    }
}
