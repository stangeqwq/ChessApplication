package Chess;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import java.io.IOException;

public class MainGameController {
    @FXML
    private TextField inputMove;
    @FXML
    private TextArea Board;
    @FXML
    private Button OkButton;

    private String move;

    private ChessGame game1;

    @FXML
    void initialize() {
        game1 = new ChessGame("Alice", "Bob");
        Board.clear();
        Board.appendText(game1.getBoard());
    }

    @FXML
    private void handleInputMove() {
        try {
            move = inputMove.getText();
            game1.move(move);
            Board.clear();
            Board.appendText(game1.getBoard());
        } catch (Exception event) {
            System.out.println(event);

        }
    }
}
