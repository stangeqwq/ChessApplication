package Chess;

import java.util.ArrayList;
import java.util.List;

public class ChessGame {
    private Player player1;
    private Player player2;
    private List<List<String>> chessBoard = new ArrayList<List<String>>();
    public ChessGame(String player1, String player2) {
        this.player1 = new Player(player1, true);
        this.player2 = new Player(player2, false, this.player1);
    }
    public Player getWhitePlayer() {
        if (player1.getColorIsWhite()) {
            return player1;
        } else {
            return player2;
        }
    }
    public Player getBlackPlayer() {
        if (player2.getColorIsWhite()) {
            return player1;
        } else {
            return player2;
        }
    }
}
