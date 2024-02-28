package Chess;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class StartChessController {
    private String player1Name = "Alice";
    private String player2Name = "Bob"; // Alice and Bob are default player names
    @FXML
    private TextField inputPlayer1;

    @FXML
    private TextField inputPlayer2;
    @FXML
    private Button startGameInput;

    @FXML
    private void handleButtonClick() {
        try {

            // Get the text entered in the text fields
            player1Name = inputPlayer1.getText();
            player2Name = inputPlayer2.getText();

            // Load the FXML file of the new scene
            FXMLLoader loader = new FXMLLoader(getClass().getResource("MainGame.fxml"));
            Parent root = loader.load();
            MainGameController mainController = loader.getController();
            mainController.setPlayer1(player1Name);
            mainController.setPlayer2(player2Name);

            // Create a new Scene with the loaded FXML content
            Scene newScene = new Scene(root);

            // Get the current stage (window)
            Stage stage = (Stage) startGameInput.getScene().getWindow();

            // Switch to the new scene
            stage.setScene(newScene);
        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception appropriately
        }
    }
}
