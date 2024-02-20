package Chess;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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
                if (row + 1 <= '8') {
                    validPositionsTo.add(Character.toString(column) + Character.toString((char) (row + 1)));
                }
                if (row + 2 <= '8') {
                    validPositionsTo.add(Character.toString(column) + Character.toString((char) (row + 2)));
                }
                // capturing pieces
                if (row + 1 <= '8' && column + 1 <= 'h') {
                    validPositionsTo
                            .add(Character.toString((char) (column + 1)) + Character.toString((char) (row + 1)));
                }

                if (row + 1 <= '8' && column - 1 >= 'a') {
                    validPositionsTo
                            .add(Character.toString((char) (column - 1)) + Character.toString((char) (row + 1)));
                }

            } else { // not first move
                // add 1 row increase, when row in valid rows
                if (row + 1 <= '8') {
                    validPositionsTo.add(Character.toString(column) + Character.toString((char) (row + 1)));
                }
                if (row + 1 <= '8' && column + 1 <= 'h') {
                    validPositionsTo
                            .add(Character.toString((char) (column + 1)) + Character.toString((char) (row + 1)));
                }

                if (row + 1 <= '8' && column - 1 >= 'a') {
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
                // capturing pieces
                if (row - 1 >= '1' && column + 1 <= 'h') {
                    validPositionsTo
                            .add(Character.toString((char) (column + 1)) + Character.toString((char) (row - 1)));
                }

                if (row - 1 >= '1' && column - 1 >= 'a') {
                    validPositionsTo
                            .add(Character.toString((char) (column - 1)) + Character.toString((char) (row - 1)));
                }

            } else { // not first move
                // add 1 row increase, when row in valid rows
                if (row - 1 >= '1') {
                    validPositionsTo.add(Character.toString(column) + Character.toString((char) (row - 1)));
                }
                // capturing pieces
                if (row - 1 >= '1' && column + 1 <= 'h') {
                    validPositionsTo
                            .add(Character.toString((char) (column + 1)) + Character.toString((char) (row - 1)));
                }

                if (row - 1 >= '1' && column - 1 >= 'a') {
                    validPositionsTo
                            .add(Character.toString((char) (column - 1)) + Character.toString((char) (row - 1)));
                }
            }
        }
        return validPositionsTo;
    }

    public List<String> getValidPositionsTo() {
        return this.validPositionsTo;
    }

    private boolean isOccupiedToPosition(String toPosition, boolean capturing) {
        List<String> piecesToCheck = new ArrayList<String>();
        for (String position : this.getOwner().getOpponent().getPiecePositions()) {
            piecesToCheck.add(position);
        }
        for (String position : this.getOwner().getPiecePositions()) {
            if (this.position.equals(position.substring(position.length() - 2))) {
                continue; // don't add current piece position
            }
            piecesToCheck.add(position);
        }
        for (String position : piecesToCheck) {
            if (position.substring(position.length() - 2).equals(toPosition)) {
                // if a piece is at destination position, then occupied
                return true;
            } else if (!capturing) {
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
            }
        }
        return false;
    }

    public boolean isValidPosition(String move) {
        if (!Character.isLowerCase(move.charAt(0))) {
            return false; // first check that the move is actually specifying pawn
        }
        Boolean capturing = false;
        if (move.length() >= 4) {
            if (move.charAt(move.length() - 3) == 'x') {
                capturing = true;
            }
        }
        if (!capturing && (move.charAt(move.length() - 2) == (char) (this.position.charAt(0) - 1)
                || move.charAt(move.length() - 2) == (char) (this.position.charAt(0) + 1))) { // check if pawn not
                                                                                              // capturing but specified
                                                                                              // move is not forward
            return false;
        }
        for (String friendlyPosition : this.getOwner().getPiecePositions()) {
            if (move.substring(move.length() - 2).equals(friendlyPosition.substring(friendlyPosition.length() - 2))) {
                return false;
            }
        }
        if (Character.isLowerCase(move.charAt(0))) {
            if (capturing) {
                for (String validPosition : validPositionsTo) {
                    if (this.getPosition().charAt(0) == move.charAt(move.length() - 4)) { // piece doing the capturing
                                                                                          // must be specified (f.e.
                                                                                          // dxe5)
                        if (move.substring(move.length() - 2).equals(validPosition)) {
                            return isOccupiedToPosition(move.substring(move.length() - 2), capturing);
                        }
                    } else {
                        return false;
                    }
                }
            } else {
                for (String validPosition : validPositionsTo) {
                    if (move.substring(move.length() - 2).equals(validPosition)) {
                        return !isOccupiedToPosition(move.substring(move.length() - 2), capturing);
                    }
                }
            }

            return false;

        } else {
            return false; // not a pawn
        }
    }

    private void transformPawn() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Convert Pawn to (R, N, B, Q):");
        String pieceType = scan.nextLine();
        if (pieceType.equals("R") || pieceType.equals("N") || pieceType.equals("B") || pieceType.equals("Q")) {
            this.getOwner().removePieceAtPosition(this.position);
            this.getOwner().addPiece(this.position, pieceType.charAt(0));
        } else {
            throw new IllegalArgumentException();
        }
    }

    public void setPosition(String move) {
        if (isValidPosition(move)) {
            this.position = move.substring(move.length() - 2);
            if (this.position.charAt(1) == '1' || this.position.charAt(1) == '8') { // the pawn can become the other
                                                                                    // pieces
                transformPawn();
            }
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
        boolean capturing = false;
        String move = "f3";
        String position = "g2";
        System.out.println(move.charAt(move.length() - 1));
        System.out.println((char) (position.charAt(0) - 1));
        System.out.println((!capturing && (move.charAt(move.length() - 1) == (char) (position.charAt(0) - 1)
                || move.charAt(move.length() - 1) == (char) (position.charAt(0) + 1))));
    }

    public List<String> getAttackingPositions() {
        List<String> attackingPositions = new ArrayList<String>(); // forward positions are not
                                                                   // valid
                                                                   // capturing pieces
        String position = this.getPosition();
        Character row = position.charAt(1);
        Character column = position.charAt(0);
        if (isWhite) {
            if (row + 1 >= '1' && column + 1 <= 'h') {
                attackingPositions
                        .add(Character.toString((char) (column + 1)) + Character.toString((char) (row - 1)));
            }

            if (row + 1 >= '1' && column - 1 >= 'a') {
                attackingPositions
                        .add(Character.toString((char) (column - 1)) + Character.toString((char) (row - 1)));
            }
        } else {
            if (row - 1 >= '1' && column + 1 <= 'h') {
                attackingPositions
                        .add(Character.toString((char) (column + 1)) + Character.toString((char) (row - 1)));
            }

            if (row - 1 >= '1' && column - 1 >= 'a') {
                attackingPositions
                        .add(Character.toString((char) (column - 1)) + Character.toString((char) (row - 1)));
            }
        }

        return attackingPositions;
    }
}
