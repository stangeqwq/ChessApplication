package Chess;

import java.util.ArrayList;
import java.util.List;

public class Pawn implements ChessPiece {
    private String position = "a1";
    private Character initial = null;
    private Player owner = null;
    private boolean allowCapture = false;
    private boolean firstMove = true;
    private boolean isWhite = true;

    private Character[] validColumns = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h' };
    private Character[] validRows = { '1', '2', '3', '4', '5', '6', '7', '8' };
    private List<String> validPositionsTo = new ArrayList<String>();

    public Pawn(String position, boolean isWhite) {
        this.position = position;
        this.isWhite = isWhite;
        validPositionsTo = generateValidPositionsTo(this.position);
    }

    public Player getOwner() {
        return owner;
    }

    public void setOwner(Player owner) {
        if (this.owner == null) {
            this.owner = owner;
        }
    }

    private List<String> generateValidPositionsTo(String position) {
        Character row = position.charAt(position.length() - 1);
        Character column = position.charAt(position.length() - 2);
        if (isWhite) {
            if (firstMove) {
                // add 1/2 row increase, when row in validrows
                if (row + 1 < '8') {
                    validPositionsTo.add(Character.toString(column) + Character.toString((char) (row + 1)));
                }
                if (row + 2 < '8') {
                    validPositionsTo.add(Character.toString(column) + Character.toString((char) (row + 2)));
                }
                // capturing pieces
                if (row + 1 < '8' && column + 1 <= 'h') {
                    validPositionsTo
                            .add(Character.toString((char) (column + 1)) + Character.toString((char) (row + 1)));
                }

                if (row + 1 < '8' && column - 1 >= 'a') {
                    validPositionsTo
                            .add(Character.toString((char) (column - 1)) + Character.toString((char) (row + 1)));
                }

            } else { // not first move
                // add 1 row increase, when row in valid rows
                if (row + 1 < '8') {
                    validPositionsTo.add(Character.toString(column) + Character.toString((char) (row + 1)));
                }
                if (row + 1 < '8' && column + 1 <= 'h') {
                    validPositionsTo
                            .add(Character.toString((char) (column + 1)) + Character.toString((char) (row + 1)));
                }

                if (row + 1 < '8' && column - 1 >= 'a') {
                    validPositionsTo
                            .add(Character.toString((char) (column - 1)) + Character.toString((char) (row + 1)));
                }
            }
        } else { // it is a black pawn so going downwards
            if (firstMove) {
                // add 1/2 row increase, when row in validrows
                if (row - 1 >= '1') {
                    validPositionsTo.add(Character.toString(column) + Character.toString((char) (row - 1)));
                }
                if (row - 2 >= '1') {
                    validPositionsTo.add(Character.toString(column) + Character.toString((char) (row - 2)));
                }

            } else { // not first move
                // add 1 row increase, when row in valid rows
                if (row - 1 >= '1') {
                    validPositionsTo.add(Character.toString(column) + Character.toString((char) (row - 1)));
                }
            }
        }
        return validPositionsTo;
    }

    public List<String> getValidPositionsTo() {
        return this.validPositionsTo;
    }

    private boolean isOccupiedToPosition(String toPosition) {
        for (String position : this.getOwner().getOpponent().getPiecePositions()) {
            if (position.substring(position.length() - 2) == toPosition) {
                // if a piece is at destination position, then occupied
                if (isWhite) {
                    if (position.charAt(position.length() - 2) == toPosition.charAt(toPosition.length() - 2)
                            && position.charAt(position.length() - 1)
                                    - toPosition.charAt(toPosition.length() - 1) == -1) {
                        // f.e. a piece is infront (d2 -> d4) but there is someone at (d3 or d4), so
                        // occupied
                        // when moving there, there is a piece (same column [length() - 2]) in front)
                        return true;
                    }
                } else { // for black pawns
                    if (position.charAt(position.length() - 2) == toPosition.charAt(toPosition.length() - 2)
                            && position.charAt(position.length() - 1)
                                    - toPosition.charAt(toPosition.length() - 1) == 1) {
                        // f.e. a piece is infront (d7 -> d5) but there is someone at (d6), so occupied
                        // when moving there, there is a piece (same column [length() - 2]) in front)
                        return true;
                    }
                }

                return true;
            }
        }
        return false;
    }

    public boolean isValidPosition(String move) {
        Boolean capturing = false;
        if (move.charAt(move.length() - 3) == 'x') {
            capturing = true;
        }
        if (Character.isLowerCase(move.charAt(0))) {
            for (String validPosition : validPositionsTo) {
                if (!capturing) {
                    if (move.substring(move.length() - 2) == validPosition) {
                        return !isOccupiedToPosition(move.substring(move.length() - 2));
                    }
                }
            }

            // it is a pawn
            // check first move (allow double move in columns)
            if (isWhite) {
                if (firstMove) {
                    if ((move.charAt(move.length() - 1) - this.position.charAt(1) == 2
                            || move.charAt(move.length() - 1) - this.position.charAt(1) == 1)
                            && (move.charAt(move.length() - 2) == this.position.charAt(0))) { // "e2 -> e4"
                        return !isOccupiedToPosition(move.substring(move.length() - 2));
                    } else {
                        return false;
                    }
                    // the pawn can move one row increase with column 1 increase/decrease (capturing
                    // a piece)
                } else { // not first move
                    if (move.charAt(move.length() - 1) - this.position.charAt(1) == 1
                            && move.charAt(move.length() - 2) == this.position.charAt(0)) { // "e2 -> e4"
                        return !isOccupiedToPosition(move);
                    } else {
                        return false;
                    }
                }
            } else { // it is a black pawn so going downwards
                if (firstMove) {
                    if ((move.charAt(move.length() - 1) - this.position.charAt(1) == -2
                            || move.charAt(move.length() - 1) - this.position.charAt(1) == -1)
                            && move.charAt(move.length() - 2) == this.position.charAt(0)) { // "e2 -> e4"
                        return !isOccupiedToPosition(move.substring(move.length() - 2));
                    } else {
                        return false;
                    }
                } else { // not first move
                    if (move.charAt(move.length() - 1) - this.position.charAt(1) == -1
                            && move.charAt(move.length() - 2) == this.position.charAt(0)) { // "e2 -> e4"
                        return true;
                    } else {
                        return false;
                    }
                }
            }
        } else {
            return false; // not a pawn
        }
    }

    public void setPosition(String move) {
        if (isValidPosition(move)) {
            this.position = move.substring(move.length() - 2);
            firstMove = false;
            validPositionsTo.clear();
            validPositionsTo = generateValidPositionsTo(this.position);
        }
    }

    public Character getInitial() {
        return this.initial;
    }

    public String getPosition() {
        return this.position;
    }

    public static void main(String args[]) {
        Pawn pawn1 = new Pawn("e2", true);
        System.out.println(pawn1.getValidPositionsTo());
    }
}
