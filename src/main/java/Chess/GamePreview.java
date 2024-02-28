package Chess;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

public class GamePreview {
    private int id = 0;
    @FXML
    private TextArea previewBoard;
    @FXML
    private TextArea previewMoves;

    public GamePreview(int id) {
        this.id = id;
    }

    @FXML
    private void initialize() {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            int currentRow = 0;

            while ((line = reader.readLine()) != null) {
                currentRow++;
                if (currentRow == targetRow) {
                    System.out.println("Row " + targetRow + ": " + line);
                    // Process the data from the desired row
                    break; // Exit the loop after reading the target row
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
