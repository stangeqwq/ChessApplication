package Chess;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private String name;
    private Player opponent;

    private boolean isWhite = true;
    private List<ChessPiece> pieces = new ArrayList<ChessPiece>();

    public Player(String name, boolean isWhite) {
        this.name = name;
        this.isWhite = isWhite;
        setupPieces(isWhite);
    }
    public Player(String name, boolean isWhite, Player opponent) {
        this.name = name;
        this.isWhite = isWhite;
        setupPieces(isWhite);
        this.setOpponent(opponent);
    }
    public List<ChessPiece> findValidPieces(Character pieceInitial) {
        List<ChessPiece> validPieces = new ArrayList<ChessPiece>();
        for (ChessPiece piece : pieces) {
            if (piece.getInitial() == pieceInitial) {
                validPieces.add(piece);
            }
        }
        if (validPieces.isEmpty()) { // if the move is valid then there would at least be one piece available to set position
            throw new IllegalArgumentException();
        }
        return validPieces;
    }
    public void move(String move) {
        // input will be of form "Ke4" (king to e4) or "Ne5" etc. Nxe4, or Ndxe4 (different column) or N3xe4 (different row)
        List<ChessPiece> piecesToCheck = findValidPieces(move.charAt(0));
        // check which chess piece to move in the players list of chess pieces
        // have
        // validation of the actual position happens in the chess pieces classes
    }

    public boolean getColorIsWhite() {
        return this.isWhite;
    }
    
    private void setOpponent(Player opponent) {
        if (this.opponent == opponent) {
            return; // already same opponent return, automatic 1-1 connection
        }
        this.opponent = opponent;
        if (this.opponent != null) {
            this.opponent.setOpponent(this);
        }
    }
    public Player getOpponent() {
        return this.opponent;
    }

    private void setupPieces(boolean isWhite) {
        if (isWhite) {
            for (int i = 0; i < 8; i++) {
                pieces.add(new Pawn(String.format("%c%c", (char)('a' + i), '2')));
            }
            pieces.add(new Knight(String.format("%c%c", 'b', '1' )));
            pieces.add(new Knight(String.format("%c%c", 'g', '1' )));
            pieces.add(new Rook(String.format("%c%c", 'a', '1' )));
            pieces.add(new Rook(String.format("%c%c", 'h', '1' )));
            pieces.add(new Bishop(String.format("%c%c", 'c', '1' )));
            pieces.add(new Bishop(String.format("%c%c", 'f', '1' )));

            pieces.add(new King(String.format("%c%c", 'd', '1' )));
            pieces.add(new Queen(String.format("%c%c", 'e', '1' )));

        } else {
            for (int i = 0; i < 8; i++) {
                pieces.add(new Pawn(String.format("%c%c", (char)('a' + i), '7')));
            }
            pieces.add(new Knight(String.format("%c%c", 'b', '8' )));
            pieces.add(new Knight(String.format("%c%c", 'g', '8' )));
            pieces.add(new Rook(String.format("%c%c", 'a', '8' )));
            pieces.add(new Rook(String.format("%c%c", 'h', '8' )));
            pieces.add(new Bishop(String.format("%c%c", 'c', '8' )));
            pieces.add(new Bishop(String.format("%c%c", 'f', '8' )));
            
            pieces.add(new King(String.format("%c%c", 'd', '8' )));
            pieces.add(new Queen(String.format("%c%c", 'e', '8' )));
        }
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return this.name;
    }
    public String getPiecePositions() {
        String piecePositions = "";
        for (ChessPiece piece : pieces) {
            if (piece.getInitial() != null) {
                piecePositions += piece.getInitial() + piece.getPosition() + " ";

            } else {
                piecePositions += piece.getPosition() + " ";
            }
        }
        return piecePositions;
    }
    public static void main(String args[]) {
        System.out.println("Hello world!");
        ChessGame game1 = new ChessGame("Alice", "Bob");
        System.out.println(game1.getWhitePlayer().getOpponent().getName());
        System.out.println(game1.getBlackPlayer().getOpponent().getName());
        game1.getWhitePlayer().setName("Charlie");
        System.out.println(game1.getBlackPlayer().getOpponent().getName());
        System.out.println((char) ('a'+1));
        System.out.println(game1.getWhitePlayer().getPiecePositions());
    }
}
