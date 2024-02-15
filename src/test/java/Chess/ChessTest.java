package Chess;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

public class ChessTest {
    @Test
    public void testBasic() {
        String test = "This is a test";
        Assertions.assertTrue("This is a test" == test, "test not same");
        System.out.println("Starting test (working):");
    }
    
    @Test
    public void testRightSetup() {
        Assertions.assertTrue(true==true, "nice");
    }
}
