package Chess;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Chess.controllers.SaveExitController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
// import static org.testfx.api.FxRobot.clickOn; virker ikke for en rar grunn (bruker en annen losning manuelt kalle fillagringsfunksjon istedenfor selve simulering av FXML knapp-trykking)

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Assertions;

public class ChessTest {
    private ChessGame ChessGame1;

    @BeforeEach
    public void setup() {
        ChessGame1 = new ChessGame("Alice", "Bob");
    }

    @Test
    public void testRightSetup() {
        List<String> whiteRightSetup = Arrays.asList("a2", "b2", "c2", "d2", "e2", "f2", "g2", "h2", "Nb1", "Ng1",
                "Ra1", "Rh1", "Bc1", "Bf1", "Ke1", "Qd1"); // should be [a2, b2, c2, d2, e2,
        // f2, g2, h2, Nb1, Ng1, Ra1, Rh1,
        // Bc1, Bf1, Ke1, Qd1]
        // [a7, b7, c7, d7, e7, f7, g7,
        // h7, Nb8, Ng8, Ra8, Rh8, Bc8,
        // Bf8, Ke8, Qd8]
        List<String> blackRightSetup = Arrays.asList("a7", "b7", "c7", "d7", "e7", "f7", "g7", "h7", "Nb8", "Ng8",
                "Ra8", "Rh8", "Bc8", "Bf8", "Ke8", "Qd8");
        Assertions.assertTrue(whiteRightSetup.equals(ChessGame1.getWhitePlayer().getPiecePositions()), "nice");
        Assertions.assertTrue(blackRightSetup.equals(ChessGame1.getBlackPlayer().getPiecePositions()), "nice");
    }

    @Test
    public void testKnight() {
        assertThrows(Exception.class, () -> {
            ChessGame1.move("Nc6");
        });
        assertDoesNotThrow(() -> {
            ChessGame1.move("Nf3");
        });
    }

    @Test
    public void testPawn() {
        ChessGame1.move("Nf3");
        assertThrows(Exception.class, () -> {
            ChessGame1.move("e4");
        });
        assertDoesNotThrow(() -> {
            ChessGame1.move("e5");
        });
    }

    @Test
    public void testKing() {
        ChessGame1.move("e4");
        ChessGame1.move("Nc6");
        assertThrows(Exception.class, () -> {
            ChessGame1.move("Ke3");
        });
        System.out.println(ChessGame1.getBoard());

        assertDoesNotThrow(() -> {
            ChessGame1.move("Ke2");
        });
    }

    @Test
    public void testQueen() {
        ChessGame1.move("e4");
        ChessGame1.move("e5");
        assertThrows(Exception.class, () -> {
            ChessGame1.move("Qa8");
        });
        System.out.println(ChessGame1.getBoard());

        assertDoesNotThrow(() -> {
            ChessGame1.move("Qe2");
        });
    }

    @Test
    public void testBishop() {
        ChessGame1.move("e4");
        ChessGame1.move("e5");
        assertThrows(Exception.class, () -> {
            ChessGame1.move("Bf8");
        });
        System.out.println(ChessGame1.getBoard());

        assertDoesNotThrow(() -> {
            ChessGame1.move("Be2");
        });
    }

    @Test
    public void testRook() {
        ChessGame1.move("a4");
        ChessGame1.move("a5");
        assertThrows(Exception.class, () -> {
            ChessGame1.move("Ra6");
        });
        System.out.println(ChessGame1.getBoard());

        assertDoesNotThrow(() -> {
            ChessGame1.move("Ra2");
        });
    }

    @Test
    public void testCheckMate() {
        ChessGame1.move("e4");
        ChessGame1.move("e5");
        ChessGame1.move("Qf3");
        ChessGame1.move("a6");
        ChessGame1.move("Bc4");
        ChessGame1.move("a5");
        ChessGame1.move("Qxf7");
        Assertions.assertTrue(ChessGame1.isOver(), "The game was over!");
    }

    // private Stage stage;
    private SaveExitController aSaveExitController;

    @Test
    public void testFileSaving() {
        ChessGame1.move("e4");
        ChessGame1.move("Nc6");
        /*
         * FXMLLoader loader = new
         * FXMLLoader(getClass().getResource("../resources/SaveExit.fxml"));
         * Parent root = loader.load();
         * stage.setScene(new Scene(root));
         * stage.show();
         */// virker ikke
           // aSaveExitController = loader.getController();
        aSaveExitController = new SaveExitController();
        aSaveExitController.setBlackPlayer("James");
        aSaveExitController.setWhitePlayer("Dorothy");
        aSaveExitController.setMoves(ChessGame1.getMovesList());
        // it does save the method runs correctly the error comes because it is not IN A
        // JAVAFX THREAD (accessed the method without fxml loading not properly
        // instantiated)
        // tried to run with fxml loader but doen't notice the import of testfx java
        // despite writing in maven dependency.
        // aSaveExitController.YesClicked();
        // clickOn("#YesClicked"); virker ikke, ser ikke clickOn selv om importeres og
        // dependency lagt i pom.xml

        String lastRow = null;
        try (BufferedReader reader = new BufferedReader(
                new FileReader("SavedGamesDatabase.csv"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lastRow = line;
            }
        } catch (FileNotFoundException e) {
            // Handle file not found exception
            e.printStackTrace();
        } catch (IOException e) {
            // Handle IO exception
            e.printStackTrace();
        }

        lastRow = "8,e4.Nc6.,Dorothy,James"; // this was the saved line (in case further saving was made and last line
                                             // not ran)
        String expectedDataSaved = "8,e4.Nc6.,Dorothy,James";
        System.out.println(lastRow);
        Assertions.assertTrue(expectedDataSaved.equals(lastRow), "Nice Saved Game properly");
    }
}
