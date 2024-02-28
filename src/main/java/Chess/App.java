package Chess;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        // Load the FXML file
        Parent root = FXMLLoader.load(getClass().getResource("StartChess.fxml"));

        // Create a Scene with the loaded FXML content
        Scene scene = new Scene(root);

        // Set the scene on the primary stage
        primaryStage.setScene(scene);

        // Set the title of the window
        primaryStage.setTitle("Chess Application (Eric Joshua Stangeland)");

        // Show the window
        primaryStage.show();
    }

    public static void main(String[] args) {
        StartChessController startChessController = new StartChessController();
        MainGameController mainGameController = new MainGameController();

        launch(args);
    }
}
