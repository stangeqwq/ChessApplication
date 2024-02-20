package Chess;

import java.util.ArrayList;
import java.util.List;

public class Rook implements ChessPiece {
    private String position = "a1";
    private Character initial = 'R';
    private Player owner = null;
    private boolean hasmoved = false;
    private Character[] validColumns = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h' };
    private Character[] validRows = { '1', '2', '3', '4', '5', '6', '7', '8' };
    private List<String> validPositionsTo = new ArrayList<String>();

    public Rook(String position) {
        this.position = position;
    }

    public Player getOwner() {
        return owner;
    }

    public void setOwner(Player owner) {
        if (this.owner == null) {
            this.owner = owner;
        }
    }

    public boolean getHasmoved() {
        return this.hasmoved;
    }

    private boolean isOccupied(String toPosition) {
        for (ChessPiece piece : this.getOwner().getOpponent().getPieces()) { // check enemy pieces
            if (piece.getPosition().equals(toPosition)) {
                return true;
            }

        }
        for (ChessPiece piece : this.getOwner().getPieces()) { // checks friendly pieces
            if (piece.getPosition().equals(toPosition)) {
                return true;
            }

        }
        return false;
    }

    private boolean isEnemy(String toPosition) {
        for (ChessPiece piece : this.getOwner().getOpponent().getPieces()) {
            if (piece.getPosition().equals(toPosition)) {
                return true;
            }
        }
        return false;
    }

    public List<String> getValidPositionsTo(String position) {
        // cross pattern
        List<String> validPositionsTo = new ArrayList<String>();
        boolean firstFriendly = true;
        // right
        String currentPositionCheck = this.position;
        while (currentPositionCheck.charAt(0) < 'h'
                && (!isOccupied(currentPositionCheck) || currentPositionCheck.equals(this.getPosition()))) { // ignore
                                                                                                             // first
                                                                                                             // case of
                                                                                                             // currentposition
                                                                                                             // (bug)
            Character column = currentPositionCheck.charAt(0);
            Character row = currentPositionCheck.charAt(1);
            column = (char) (column + 1);
            String toPosition = Character.toString(column) + Character.toString(row);
            if (!isOccupied(toPosition)) {
                validPositionsTo.add(toPosition);
            } else if (isEnemy(toPosition)) {
                // add first occupied square if enemy position's with specification 'x' for
                // capturing
                validPositionsTo.add(Character.toString('x') + toPosition);
            } else if (firstFriendly) {
                validPositionsTo.add(Character.toString('f') + toPosition); // add first friendly useful for checkmating
                firstFriendly = false;
            }
            currentPositionCheck = toPosition;
        }
        // left
        currentPositionCheck = this.position;
        firstFriendly = true;
        while (currentPositionCheck.charAt(0) > 'a'
                && (!isOccupied(currentPositionCheck) || currentPositionCheck.equals(this.getPosition()))) {
            Character column = currentPositionCheck.charAt(0);
            Character row = currentPositionCheck.charAt(1);
            column = (char) (column - 1);
            String toPosition = Character.toString(column) + Character.toString(row);
            if (!isOccupied(toPosition)) {
                validPositionsTo.add(toPosition);
            } else if (isEnemy(toPosition)) {
                // add first occupied square if enemy position's with specification 'x' for
                // capturing
                validPositionsTo.add(Character.toString('x') + toPosition);
            } else if (firstFriendly) {
                validPositionsTo.add(Character.toString('f') + toPosition); // add first friendly useful for checkmating
                firstFriendly = false;
            }
            currentPositionCheck = toPosition;
        }
        // up
        currentPositionCheck = this.position;
        firstFriendly = true;
        while (currentPositionCheck.charAt(1) < '8'
                && (!isOccupied(currentPositionCheck) || currentPositionCheck.equals(this.getPosition()))) {
            Character column = currentPositionCheck.charAt(0);
            Character row = currentPositionCheck.charAt(1);
            row = (char) (row + 1);
            String toPosition = Character.toString(column) + Character.toString(row);
            if (!isOccupied(toPosition)) {
                validPositionsTo.add(toPosition);
            } else if (isEnemy(toPosition)) {
                // add first occupied square if enemy position's with specification 'x' for
                // capturing
                validPositionsTo.add(Character.toString('x') + toPosition);
            } else if (firstFriendly) {
                validPositionsTo.add(Character.toString('f') + toPosition); // add first friendly useful for checkmating
                firstFriendly = false;
            }

            currentPositionCheck = toPosition;
        }
        // down
        currentPositionCheck = this.position;
        firstFriendly = true;
        while (currentPositionCheck.charAt(1) > '1'
                && (!isOccupied(currentPositionCheck) || currentPositionCheck.equals(this.getPosition()))) {
            Character column = currentPositionCheck.charAt(0);
            Character row = currentPositionCheck.charAt(1);
            row = (char) (row - 1);
            String toPosition = Character.toString(column) + Character.toString(row);
            if (!isOccupied(toPosition)) {
                validPositionsTo.add(toPosition);
            } else if (isEnemy(toPosition)) {
                // add first occupied square if enemy position's with specification 'x' for
                // capturing
                validPositionsTo.add(Character.toString('x') + toPosition);
            } else if (firstFriendly) {
                validPositionsTo.add(Character.toString('f') + toPosition); // add first friendly useful for checkmating
                firstFriendly = false;
            }
            currentPositionCheck = toPosition;
        }
        return validPositionsTo;
    }

    public boolean rightCastling(String castling) {
        if (!this.getOwner().getKing().getHasmoved() && this.hasmoved == false
                && !this.getOwner().getKing().isInAttack(this.getOwner().getKing().getPosition())) {
            if ((castling == "0-0" || castling == "O-O") && this.getOwner().getColorIsWhite()) { // white castling
                                                                                                 // kingside
                if (!isOccupied("f1") && !isOccupied("g1") && !this.getOwner().getKing().isInAttack("f1")
                        && !this.getOwner().getKing().isInAttack("g1")) {
                    return true;
                } else {
                    return false;
                }
            } else if ((castling == "0-0-0" || castling == "O-O-O") && this.getOwner().getColorIsWhite()) { // white
                                                                                                            // castling
                                                                                                            // kingside
                if (!isOccupied("d1") && !isOccupied("c1") && !this.getOwner().getKing().isInAttack("d1")
                        && !this.getOwner().getKing().isInAttack("c1")) {
                    return true;
                } else {
                    return false;
                }
            } else if ((castling == "0-0" || castling == "O-O") && !this.getOwner().getColorIsWhite()) { // black
                                                                                                         // castling
                                                                                                         // kingside
                if (!isOccupied("f8") && !isOccupied("g8") && !this.getOwner().getKing().isInAttack("f8")
                        && !this.getOwner().getKing().isInAttack("g8")) {
                    return true;
                } else {
                    return false;
                }

            } else if ((castling == "0-0-0" || castling == "O-O-O") && !this.getOwner().getColorIsWhite()) { // black
                                                                                                             // castling
                                                                                                             // queenside
                if (!isOccupied("c8") && !isOccupied("d8") && !this.getOwner().getKing().isInAttack("c8")
                        && !this.getOwner().getKing().isInAttack("d8")) {
                    return true;
                } else {
                    return false;
                }

            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public boolean isValidPosition(String move) {
        boolean capturing = false;
        String position = move.substring(move.length() - 2);
        // check if castling is 0-0 or 0-0-0 or O-O or O-O-O is indicated
        if (move == "0-0" || move == "0-0-0" || move == "O-O" || move == "O-O-O") {
            if (rightCastling(move)) {
                return true;
            } else {
                return false;
            }
        }
        if (Character.isLowerCase(move.charAt(0))) {
            return false; // it is a pawn
        } else if (move.charAt(0) == this.getInitial()) {
            if (move.length() >= 4) { // f.e. Rxe5 or Rdxe4 or Rde4
                if (move.charAt(move.length() - 3) == 'x') {
                    capturing = true;
                } else if ((move.charAt(move.length() - 3) == this.getPosition().charAt(0))
                        || (move.charAt(move.length() - 3) == this.getPosition().charAt(1))) {
                    // specified which rook piece so we check if this rook is correct (for both
                    // row and column)
                    // do nothing we continue below

                } else {
                    return false; // this is the wrong bishop (different column / row to specified)
                }
            }
            List<String> validPositionsTo = getValidPositionsTo(this.getPosition());
            if (!capturing) { // position must not be occupied and in validPositionsTo
                for (String validPositionTo : validPositionsTo) {
                    if (validPositionTo.equals(position)) {
                        return true;
                    }
                }
            } else { // we check including the "x" pieces but this time specifying only position
                     // interested
                for (String validPositionTo : validPositionsTo) {
                    if (validPositionTo.substring(validPositionTo.length() - 2).equals(position)
                            && validPositionTo.length() == 3 && validPositionTo.charAt(0) == 'x') { // actually have to
                                                                                                    // capture a piece
                                                                                                    // when capturing
                        return true;
                    }
                }

            }
        } else { // not same initial to rook
            return false;
        }
        return false;
    }

    public void setPosition(String move) {
        if (isValidPosition(move)) {
            if (move == "0-0" || move == "O-O" && this.getOwner().getColorIsWhite()) { // set white kingside castles
                this.position = "f1";
                this.getOwner().getKing().forceSetPosition("g1");

            } else if (move == "0-0-0" || move == "O-O-O" && this.getOwner().getColorIsWhite()) { // set white queenside
                // castles
                this.position = "d1";
                this.getOwner().getKing().forceSetPosition("c1");
            } else if (move == "0-0" || move == "O-O" && !this.getOwner().getColorIsWhite()) { // set black kingside
                                                                                               // castles
                this.position = "f8";
                this.getOwner().getKing().forceSetPosition("g8");

            } else if (move == "0-0-0" || move == "O-O-O" && !this.getOwner().getColorIsWhite()) { // set black
                                                                                                   // queenside
                this.position = "d8";
                this.getOwner().getKing().forceSetPosition("c8");

            } else {
                this.position = move.substring(move.length() - 2);
                this.getOwner().getOpponent().removePieceAtPosition(this.position);
            }
            this.hasmoved = true;
        }
    }

    public String getPosition() {
        return this.position;
    }

    public Character getInitial() {
        return this.initial;
    }

    public List<String> getAttackingPositions() {
        List<String> attackingPositions = getValidPositionsTo(this.getPosition());
        return attackingPositions;
    }

    public static void main(String args[]) {
        System.out.println("Test");
    }
}