package Chess;

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
import java.io.IOException;

public class MainGameController {
    @FXML
    private TextField inputMove;
    @FXML
    private TextArea Board;
    @FXML
    private Button OkButton;
    @FXML
    private TextArea MoveList;

    private String move;

    private ChessGame game1;
    private String player1;
    private String player2; // default

    public void setPlayer1(String player1) {
        this.player1 = player1;
    }

    public void setPlayer2(String player2) {
        this.player2 = player2;
    }

    @FXML
    void initialize() {
        game1 = new ChessGame(player1, player2);
        Board.clear();
        Board.appendText(game1.getBoard());
        MoveList.clear();
        MoveList.appendText(game1.toString());
    }

    @FXML
    private void handleInputMove() {
        try {
            move = inputMove.getText();
            game1.move(move);
            Board.clear();
            Board.appendText(game1.getBoard());
            MoveList.clear();
            MoveList.appendText(game1.toString());
        } catch (Exception event) {
            System.out.println(event);

        }
    }

}
