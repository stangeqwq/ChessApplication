package Chess;

import java.util.List;
import java.util.ArrayList;

public class Bishop implements ChessPiece {
    private String position = "a1";
    private Character initial = 'B';
    private Player owner = null;

    public Bishop(String position) {
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
        // diagonal pattern
        List<String> validPositionsTo = new ArrayList<String>();
        boolean firstFriendly = true;
        // up right
        String currentPositionCheck = this.position;
        while (currentPositionCheck.charAt(0) < 'h' && currentPositionCheck.charAt(1) < '8'
                && (!isOccupied(currentPositionCheck) || currentPositionCheck.equals(this.getPosition()))) { // ignore
                                                                                                             // first
                                                                                                             // case of
                                                                                                             // currentposition
                                                                                                             // (bug)
            Character column = currentPositionCheck.charAt(0);
            Character row = currentPositionCheck.charAt(1);
            column = (char) (column + 1);
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
        // up left
        currentPositionCheck = this.position;
        firstFriendly = true;
        while (currentPositionCheck.charAt(0) > 'a' && currentPositionCheck.charAt(1) < '8'
                && (!isOccupied(currentPositionCheck) || currentPositionCheck.equals(this.getPosition()))) {
            Character column = currentPositionCheck.charAt(0);
            Character row = currentPositionCheck.charAt(1);
            column = (char) (column - 1);
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
        // down right
        currentPositionCheck = this.position;
        firstFriendly = true;
        while (currentPositionCheck.charAt(0) < 'h' && currentPositionCheck.charAt(1) > '1'
                && (!isOccupied(currentPositionCheck) || currentPositionCheck.equals(this.getPosition()))) {
            Character column = currentPositionCheck.charAt(0);
            Character row = currentPositionCheck.charAt(1);
            column = (char) (column + 1);
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
        // down left
        currentPositionCheck = this.position;
        firstFriendly = true;
        while (currentPositionCheck.charAt(0) > 'a' && currentPositionCheck.charAt(1) > '1'
                && (!isOccupied(currentPositionCheck) || currentPositionCheck.equals(this.getPosition()))) {
            Character column = currentPositionCheck.charAt(0);
            Character row = currentPositionCheck.charAt(1);
            column = (char) (column - 1);
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

    public boolean isValidPosition(String move) { // takes a position without an initial
        boolean capturing = false;
        String position = move.substring(move.length() - 2);
        if (Character.isLowerCase(move.charAt(0))) {
            return false; // it is a pawn
        } else if (move.charAt(0) == this.getInitial()) {
            if (move.length() >= 4) { // f.e. Bxe5 or Bdxe4 or Bde4
                if (move.charAt(move.length() - 3) == 'x') {
                    capturing = true;
                } else if ((move.charAt(move.length() - 3) == this.getPosition().charAt(0))
                        || (move.charAt(move.length() - 3) == this.getPosition().charAt(1))) {
                    // specified which bishop piece so we check if this bishop is correct (for both
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
            } else { // we check including the "x" pieces but this time specifying only position and
                     // emphasizing not friendly 'f' first piece (again, used in checkmate function
                     // checking)
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
        } else { // not same initial to bishop
            return false;
        }
        return false;
    }

    public void setPosition(String move) {
        if (isValidPosition(move)) {
            // we only take "a4" f.e. type not whole position "Ndxe4" this must be validated
            this.position = move.substring(move.length() - 2);
            this.getOwner().getOpponent().removePieceAtPosition(this.position);
        }
    }

    public Character getInitial() {
        return this.initial;
    }

    public String getPosition() {
        return this.position;
    }

    public List<String> getAttackingPositions() {
        List<String> attackingPositions = getValidPositionsTo(this.getPosition());
        return attackingPositions;
    }
}
