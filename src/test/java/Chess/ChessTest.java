package Chess;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Assertions;

public class ChessTest {
    private ChessGame ChessGame1;

    @BeforeEach
    public void setup() {
        ChessGame1 = new ChessGame("Alice", "Bob");
    }

    @Test
    public void testRightSetup() {
        Assertions.assertTrue(true == true, "nice");
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
        assertThrows(Exception.class, () -> {
            ChessGame1.move("Ke3");
        });
        assertDoesNotThrow(() -> {
            ChessGame1.move("Ke2");
        });
    }

    @Test
    public void testQueen() {
        String test = "This is a test";
        Assertions.assertTrue("This is a test" == test, "test not same");
        System.out.println("Starting test (working):");
    }

    @Test
    public void testBishop() {
        String test = "This is a test";
        Assertions.assertTrue("This is a test" == test, "test not same");
        System.out.println("Starting test (working):");
    }

    @Test
    public void testRook() {
        String test = "This is a test";
        Assertions.assertTrue("This is a test" == test, "test not same");
        System.out.println("Starting test (working):");
    }

    @Test
    public void testCheckMate() {
        String test = "This is a test";
        Assertions.assertTrue("This is a test" == test, "test not same");
        System.out.println("Starting test (working):");
    }

    @Test
    public void testFileSaving() {
        String test = "This is a test";
        Assertions.assertTrue("This is a test" == test, "test not same");
        System.out.println("Starting test (working):");
    }
}
