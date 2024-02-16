package Chess;

import java.util.ArrayList;
import java.util.List;

public class ChessGame {
    private Player player1;
    private Player player2;
    private List<List<String>> chessBoard = new ArrayList<List<String>>();
    private boolean finished = false;
    private boolean whiteTurn = true;
    private List<String> moves = new ArrayList<String>();
    
    public ChessGame(String player1, String player2) {
        this.player1 = new Player(player1, true);
        this.player2 = new Player(player2, false, this.player1);
    }
    public void move(String move) {
        if (whiteTurn) {
            player1.move(move);
            moves.add(move);
            whiteTurn = false;
        } else {
            player2.move(move);
            moves.add(move);
            whiteTurn = true;
        }
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
    public String toString() {
        return String.format("Players: %s (white), %s (black)", player1.getName(), player2.getName());
    }

    public static void main(String args[]) {
        ChessGame game1 = new ChessGame("Alice", "Bob");
        System.out.println(game1.toString());
    }
}
