package Chess;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

public class ChessGame {
    private Player player1;
    private Player player2;
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

    public String getMoves() {
        String movelist = "";
        int twoCount = 0;
        int moveCount = 1;
        for (String move : moves) {
            if (twoCount == 0) {
                movelist += String.format("%o. ", moveCount);
            }
            if (twoCount < 2) {
                movelist += move += " ";
                twoCount += 1;
            }
            if (twoCount == 2) {
                movelist += "\n";
                twoCount = 0;
                moveCount += 1;
            }
        }
        return movelist;
    }

    public String getBoard() {
        HashMap<String, String> toPrint = new HashMap<>(); // HASHMAP SER SA DEILIG UT HER AAAAA

        for (ChessPiece piece : player1.getPieces()) {
            if (piece.getInitial() == null) {
                toPrint.put(piece.getPosition(), "P");
            } else {
                toPrint.put(piece.getPosition(), Character.toString(piece.getInitial()));
            }
        }
        for (ChessPiece piece : player2.getPieces()) {
            if (piece.getInitial() == null) {
                toPrint.put(piece.getPosition(), "p");
            } else {
                toPrint.put(piece.getPosition(), Character.toString(Character.toLowerCase(piece.getInitial())));
            }
        }

        String board = String.format("     A     B     C     D     E     F     G     H\n" + //
                "  |—————|—————|—————|—————|—————|—————|—————|—————|\n" + //
                "8 |  %s  |  %s  |  %s  |  %s  |  %s  |  %s  |  %s  |  %s  | 8\n" + //
                "  |—————|—————|—————|—————|—————|—————|—————|—————|\n" + //
                "7 |  %s  |  %s  |  %s  |  %s  |  %s  |  %s  |  %s  |  %s  | 7\n" + //
                "  |—————|—————|—————|—————|—————|—————|—————|—————|\n" + //
                "6 |  %s  |  %s  |  %s  |  %s  |  %s  |  %s  |  %s  |  %s  | 6\n" + //
                "  |—————|—————|—————|—————|—————|—————|—————|—————|\n" + //
                "5 |  %s  |  %s  |  %s  |  %s  |  %s  |  %s  |  %s  |  %s  | 5\n" + //
                "  |—————|—————|—————|—————|—————|—————|—————|—————|\n" + //
                "4 |  %s  |  %s  |  %s  |  %s  |  %s  |  %s  |  %s  |  %s  | 4\n" + //
                "  |—————|—————|—————|—————|—————|—————|—————|—————|\n" + //
                "3 |  %s  |  %s  |  %s  |  %s  |  %s  |  %s  |  %s  |  %s  | 3\n" + //
                "  |—————|—————|—————|—————|—————|—————|—————|—————|\n" + //
                "2 |  %s  |  %s  |  %s  |  %s  |  %s  |  %s  |  %s  |  %s  | 2\n" + //
                "  |—————|—————|—————|—————|—————|—————|—————|—————|\n" + //
                "1 |  %s  |  %s  |  %s  |  %s  |  %s  |  %s  |  %s  |  %s  | 1\n" + //
                "  |—————|—————|—————|—————|—————|—————|—————|—————|\n" + //
                "     A     B     C     D     E     F     G     H", //
                toPrint.getOrDefault("a8", " "), toPrint.getOrDefault("b8", " "), toPrint.getOrDefault("c8", " "),
                toPrint.getOrDefault("d8", " "), toPrint.getOrDefault("e8", " "), toPrint.getOrDefault("f8", " "),
                toPrint.getOrDefault("g8", " "), toPrint.getOrDefault("h8", " "), //
                toPrint.getOrDefault("a7", " "), toPrint.getOrDefault("b7", " "), toPrint.getOrDefault("c7", " "),
                toPrint.getOrDefault("d7", " "), toPrint.getOrDefault("e7", " "), toPrint.getOrDefault("f7", " "),
                toPrint.getOrDefault("g7", " "), toPrint.getOrDefault("h7", " "), //
                toPrint.getOrDefault("a6", " "), toPrint.getOrDefault("b6", " "), toPrint.getOrDefault("5", " "),
                toPrint.getOrDefault("d6", " "), toPrint.getOrDefault("e6", " "), toPrint.getOrDefault("f6", " "),
                toPrint.getOrDefault("g6", " "), toPrint.getOrDefault("h6", " "), //
                toPrint.getOrDefault("a5", " "), toPrint.getOrDefault("b5", " "), toPrint.getOrDefault("c5", " "),
                toPrint.getOrDefault("d5", " "), toPrint.getOrDefault("e5", " "), toPrint.getOrDefault("f5", " "),
                toPrint.getOrDefault("g5", " "), toPrint.getOrDefault("h5", " "), //
                toPrint.getOrDefault("a4", " "), toPrint.getOrDefault("b4", " "), toPrint.getOrDefault("c4", " "),
                toPrint.getOrDefault("d4", " "), toPrint.getOrDefault("e4", " "), toPrint.getOrDefault("f4", " "),
                toPrint.getOrDefault("g4", " "), toPrint.getOrDefault("h4", " "), //
                toPrint.getOrDefault("a3", " "), toPrint.getOrDefault("b3", " "), toPrint.getOrDefault("c3", " "),
                toPrint.getOrDefault("d3", " "), toPrint.getOrDefault("e3", " "), toPrint.getOrDefault("f3", " "),
                toPrint.getOrDefault("g3", " "), toPrint.getOrDefault("h3", " "), //
                toPrint.getOrDefault("a2", " "), toPrint.getOrDefault("b2", " "), toPrint.getOrDefault("c2", " "),
                toPrint.getOrDefault("d2", " "), toPrint.getOrDefault("e2", " "), toPrint.getOrDefault("f2", " "),
                toPrint.getOrDefault("g2", " "), toPrint.getOrDefault("h2", " "), //
                toPrint.getOrDefault("a1", " "), toPrint.getOrDefault("b1", " "), toPrint.getOrDefault("c1", " "),
                toPrint.getOrDefault("d1", " "), toPrint.getOrDefault("e1", " "), toPrint.getOrDefault("f1", " "),
                toPrint.getOrDefault("g1", " "), toPrint.getOrDefault("h1", " "));
        return board;
    }

    public String toString() {
        return String.format("Players: %s (white), %s (black) %s", player1.getName(), player2.getName(),
                "\n" + this.getMoves());
    }

    public static void main(String args[]) {
        // TESTING PAWNS
        ChessGame game1 = new ChessGame("Alice", "Bob");
        game1.move("e4");
        System.out.println(game1.getBoard());
        System.out.println(game1.getWhitePlayer().getPiecePositions());
        game1.move("e5");
        System.out.println(game1.getBoard());
        game1.move("d4");
        System.out.println(game1.toString());
        System.out.println(game1.getBoard());
        System.out.println(game1.getWhitePlayer().getPiecePositions());
        game1.move("d5");
        System.out.println(game1.toString());
        System.out.println(game1.getBoard());
        System.out.println(game1.getBlackPlayer().getPiecePositions());
        System.out.println(game1.toString());
        game1.move("dxe5");
        System.out.println(game1.getBoard());
        System.out.println(game1.getBlackPlayer().getPiecePositions()); // black piece needs to be removed from the
                                                                        // player when captured
        game1.move("c5");
        System.out.println(game1.getBoard());
        // game1.move("e5"); // testing when own piece moves to own occupied piece ERROR
        // AS EXPECTED
        game1.move("f4");
        System.out.println(game1.getBoard());
        game1.move("c4");
        System.out.println(game1.getBoard());
        // game1.move("f3"); //error as expected when piece tries to capture when
        // nothing
        game1.move("g4");
        System.out.println(game1.getBoard());
        game1.move("c3");
        System.out.println(game1.getBoard());
        game1.move("bxc3");
        System.out.println(game1.getBoard());
        System.out.println(game1.getBlackPlayer().getPiecePositions()); // black piece needs to be removed from the
        // player when captured
        System.out.println(game1.toString());
        game1.move("f6");
        System.out.println(game1.getBoard());
        game1.move("e6");
        System.out.println(game1.getBoard());
        game1.move("f5");
        System.out.println(game1.getBoard());
        game1.move("e7");
        System.out.println(game1.getBoard());
        game1.move("fxg4");
        System.out.println(game1.getBoard());
        // PAWN TRANSFORMS TO ANOTHER CLASS AT THAT POSITION (remove and add class at
        // that position) (to transform you have to remove yourself and add)
        // game1.move("exf8"); // commented out to speed up debugging
        // System.out.println(game1.getBoard());

        // TESTING OUT THE KNIGHT CLASSES
        ChessGame game2 = new ChessGame("Alice", "Bob");
        System.out.println(game2.getBoard());
        game2.move("Nf3");
        System.out.println(game2.getBoard());
        game2.move("e5");
        System.out.println(game2.getBoard());
        game2.move("Nxe5");
        System.out.println(game2.getBoard());
        System.out.println(game2.getBlackPlayer().getPiecePositions());
        System.out.println(game2.toString());
        game2.move("Nc6");
        System.out.println(game2.getBoard());
        // game2.move("Nd2"); // should get error capturing own piece ERROR AS EXPECTED
        game2.move("Nc3");
        System.out.println(game2.getBoard());
        game2.move("f5");
        System.out.println(game2.getBoard());
        game2.move("Ne4");
        System.out.println(game2.getBoard());
        game2.move("f4");
        System.out.println(game2.getBoard());
        game2.move("Nc5");
        System.out.println(game2.getBoard());
        game2.move("f3");
        System.out.println(game2.getBoard());
        // game2.move("Nxd7"); // error as expected knight not specified
        game2.move("Ncxd7");
        System.out.println(game2.getBoard());

        // TESTING OUT THE BISHOP CLASSES
        ChessGame game3 = new ChessGame("Jomah", "Barry");
        System.out.println(game3.getBoard());
        game3.move("e4");
        System.out.println(game3.getBoard());
        game3.move("e5");
        System.out.println(game3.getBoard());
        game3.move("Bb5");
        System.out.println(game3.getBoard());
        game3.move("c6");
        System.out.println(game3.getBoard());
        // game3.move("Bxa4"); //error as expected
        game3.move("Bxc6");
        System.out.println(game3.getBoard());
        System.out.println(game3.getBoard());
        System.out.println(game3.getBlackPlayer().getPiecePositions());
        // game3.move("Bxe7"); // error as expected
        game3.move("bxc6");
        System.out.println(game3.getBoard());
        game3.move("Nf3");
        System.out.println(game3.getBoard());
        game3.move("Ba6");
        System.out.println(game3.getBoard());
        game3.move("c4");
        System.out.println(game3.getBoard());
        game3.move("Bxc4");
        System.out.println(game3.getBoard());
        // game3.move("Bd2"); // error as expected
        System.out.println(game3.getBoard());

        // TESTING OUT ROOK CLASSES AND QUEEN CLASSES (based from previous game as well)
        ChessGame game4 = new ChessGame("Howard", "Barry");
        System.out.println(game4.getBoard());
        game4.move("e4");
        System.out.println(game4.getBoard());
        game4.move("e5");
        System.out.println(game4.getBoard());
        game4.move("Bb5");
        System.out.println(game4.getBoard());
        game4.move("c6");
        System.out.println(game4.getBoard());
        // game3.move("Bxa4"); //error as expected
        game4.move("Bxc6");
        System.out.println(game4.getBoard());
        System.out.println(game4.getBoard());
        System.out.println(game4.getBlackPlayer().getPiecePositions());
        // game3.move("Bxe7"); // error as expected
        game4.move("bxc6");
        System.out.println(game4.getBoard());
        game4.move("Nf3");
        System.out.println(game4.getBoard());
        game4.move("Ba6");
        System.out.println(game4.getBoard());
        game4.move("c4");
        System.out.println(game4.getBoard());
        game4.move("Bxc4");
        System.out.println(game4.getBoard());
        // game3.move("Bd2"); // error as expected
        System.out.println(game4.getBoard());
        // game4.move("Ra2"); // error as expected
        game4.move("Rf1");
        System.out.println(game4.getBoard());
        // game4.move("f3"); //error as expected
        System.out.println(game4.getBoard());
        game4.move("c5");
        System.out.println(game4.getBoard());
        game4.move("Rg1");
        System.out.println(game4.getBoard());
        game4.move("d5");
        System.out.println(game4.getBoard());
        game4.move("g4");
        System.out.println(game4.getBoard());
        game4.move("d4");
        System.out.println(game4.getBoard());
        // game4.move("Rg4"); //error as expected
        game4.move("Rg3");
        System.out.println(game4.getBoard());
        System.out.println(game4.toString());
        game4.move("Qa5");
        System.out.println(game4.getBoard());
        game4.move("Qa4");
        System.out.println(game4.getBoard());
        game4.move("Qxa4");
        System.out.println(game4.getBoard());
        System.out.println(game4.getWhitePlayer().getPiecePositions());
        System.out.println(game4.toString());
    }
}
