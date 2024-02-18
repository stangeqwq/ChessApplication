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
        for (String position : this.getOwner().getOpponent().getPiecePositions()) { // check if enemy pieces on the path
            if (position.equals(toPosition)) {
                return true; // a piece in destination
            }

        }
        for (String position : this.getOwner().getPiecePositions()) { // check if own pieces on the path
            if (position.equals(toPosition)) {
                return true; // a piece in destination
            }
        }
        return false;
    }

    public boolean isValidPosition(String move) {
        Boolean capturing = false;
        String toPosition = move.substring(move.length() - 2);
        Character thisPawnColumn = this.getPosition().charAt(0);
        if (move.length() >= 4) {
            if (move.charAt(move.length() - 3) == 'x') {
                capturing = true;
            }
        }
        if (Character.isLowerCase(move.charAt(0))) {
            if (capturing) {
                for (String validPosition : validPositionsTo) {
                    if (thisPawnColumn == move.charAt(move.length() - 4)) { // the same pawn piece specified in the
                                                                            // move, is this pawn
                        if (toPosition.equals(validPosition)) {
                            boolean isOccupiedToPosition = isOccupiedToPosition(toPosition);
                            if (!isOccupiedToPosition && isEnemyPieceAt(toPosition)) {
                                return true;
                            } else {
                                return false;
                            }
                        }
                    } else {
                        return false;
                    }
                }
            } else {
                for (String validPosition : validPositionsTo) {
                    if (toPosition.equals(validPosition)) {
                        boolean isOccupiedToPosition = isOccupiedToPosition(toPosition);
                        if (isOccupiedToPosition) {
                            return false;
                        } else {
                            return true;
                        }
                    }
                }
                return false;
            }

            return false; // valid position not in validpositionTo go

        } else {
            return false; // not a pawn
        }
    }

    public void setPosition(String move) {
        if (isValidPosition(move)) {
            this.position = move.substring(move.length() - 2);
            this.getOwner().getOpponent().removePieceAtPosition(this.position);
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
