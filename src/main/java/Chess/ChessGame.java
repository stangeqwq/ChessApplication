package Chess;

import java.util.ArrayList;
import java.util.List;

public class ChessGame {
    private Player player1;
    private Player player2;
    private List<List<String>> chessBoard = new ArrayList<List<String>>();
    public ChessGame(String player1, String player2) {
        this.player1 = new Player(true);
        this.player2 = new Player(false);
    }
}
