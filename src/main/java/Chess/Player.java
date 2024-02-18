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
    public void move(String move) {
        // input will be of form "Ke4" (king to e4) or "Ne5" etc. Nxe4, or Ndxe4 (different column) or N3xe4 (different row)
        // validation of the actual position happens in the chess pieces classes
        // isValidPosition checks for (1) initial, (2) right piece is referred if specified (N(d)e4), (3) the actual position
        ChessPiece pieceToMove = null;
        for (ChessPiece piece : pieces) {
            if (piece.isValidPosition(move)) {
                if (pieceToMove == null) {
                    pieceToMove = piece;
                } else {
                    throw new IllegalArgumentException(); 
                    // there were several pieces available (meaning chess notation did not specify which piece f.e.
                    // Nde4 vs. Ne4, throw error
                }
            }
        }
        if (pieceToMove == null) {
            throw new IllegalArgumentException(); // no valid position for the piece type (wrong argument)
        }
        pieceToMove.setPosition(move);
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
                pieces.add(new Pawn(String.format("%c%c", (char)('a' + i), '2'), isWhite));
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
                pieces.add(new Pawn(String.format("%c%c", (char)('a' + i), '7'), isWhite));
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
        for (ChessPiece piece : pieces) {
            piece.setOwner(this);
        }
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return this.name;
    }
    public List<String> getPiecePositions() {
        List<String> piecePositions = new ArrayList<String>();
        for (ChessPiece piece : pieces) {
            if (piece.getInitial() != null) {
                piecePositions.add(piece.getInitial() + piece.getPosition());
            } else {
                piecePositions.add(piece.getPosition());
            }
        }
        return piecePositions;
    }
    public List<ChessPiece> getPieces() {
        return pieces;
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
        System.out.println(game1.getBlackPlayer().getPiecePositions());
        game1.move("e4");
        System.out.println(game1.getWhitePlayer().getPiecePositions());
        System.out.println(game1.getBlackPlayer().getPiecePositions());
        game1.move("e5");
        System.out.println(game1.getWhitePlayer().getPiecePositions());
        System.out.println(game1.getBlackPlayer().getPiecePositions());
        System.out.println(game1.getMoves());
        game1.move("d4");
        System.out.println(game1.getWhitePlayer().getPiecePositions());
        System.out.println(game1.getBlackPlayer().getPiecePositions());
        System.out.println(game1.getMoves());
    }
}
