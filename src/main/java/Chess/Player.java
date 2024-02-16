package Chess;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private String name;
    private Player opponent;

    private boolean isWhite = true;
    private List<ChessPiece> pieces = new ArrayList<ChessPiece>();
    private Character[] validColumns = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h'};
    private Character[] validRows = {'1', '2', '3', '4', '5', '6', '7', '8'};
    
    public Player(String name, boolean isWhite, String opponentName) {
        this.name = name;
        this.isWhite = isWhite;
        setupPieces(isWhite);

        this.opponent = new Player(opponentName, !isWhite, name);
        setOpponent(opponentName);

    }
    private void setOpponent(Player opponent) {
        if (this.player == opponent) {
            throw new IllegalArgumentException();
        }
        this.opponent = opponent;
        if (this.player != null) {
            this.opponent.setOpponent(this);
        }
    }
    private void setupPieces(boolean isWhite) {
        if (isWhite) {
            for (int i = 0; i < 8; i++) {
                pieces.add(new Pawn(String.format("%c%c", validColumns[i], '2')));
            }
            pieces.add(new Knight(String format("%c%c", 'b', '1' )));
            pieces.add(new Knight(String format("%c%c", 'g', '1' )));
            pieces.add(new Rook(String format("%c%c", 'a', '1' )));
            pieces.add(new Rook(String format("%c%c", 'h', '1' )));
            pieces.add(new Bishop(String format("%c%c", 'c', '1' )));
            pieces.add(new Bishop(String format("%c%c", 'f', '1' )));

            pieces.add(new King(String format("%c%c", 'd', '1' )));
            pieces.add(new Queen(String format("%c%c", 'e', '1' )));

        } else {
            for (int i = 0; i < 8; i++) {
                pieces.add(new Pawn(String.format("%c%c", validColumns[i], '7')));
            }
            pieces.add(new Knight(String format("%c%c", 'b', '8' )));
            pieces.add(new Knight(String format("%c%c", 'g', '8' )));
            pieces.add(new Rook(String format("%c%c", 'a', '8' )));
            pieces.add(new Rook(String format("%c%c", 'h', '8' )));
            pieces.add(new Bishop(String format("%c%c", 'c', '8' )));
            pieces.add(new Bishop(String format("%c%c", 'f', '8' )));
            
            pieces.add(new King(String format("%c%c", 'd', '8' )));
            pieces.add(new Queen(String format("%c%c", 'e', '8' )));
        }
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return this.name;
    }
    public static void main(String args[]) {
        System.out.println("Hello world!");
    }
}
