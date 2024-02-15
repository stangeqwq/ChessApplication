package Chess;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private boolean isWhite = true;
    private List<ChessPiece> pieces = new ArrayList<ChessPiece>();
    private Character[] validColumns = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h'};
    private int[] validRows = {'1', '2', '3', '4', '5', '6', '7', '8'};
    
    public Player(boolean isWhite) {
        this.isWhite = isWhite;
        if (isWhite) {
            for (int i = 0; i < 8; i++) {
                pieces.add(new Pawn(String.format("%c%i", validColumns[i], 2)));
            }
        } else {
            for (int i = 0; i < 8; i++) {
                pieces.add(new Pawn(String.format("%c%i", validColumns[i], 2)));
            }
        }
    }
    public static void main(String args[]) {
        System.out.println("Hello world!");
    }
}
