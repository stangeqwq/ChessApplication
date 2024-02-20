package Chess;

import java.util.List;
import java.util.ArrayList;

public class Bishop implements ChessPiece {
    private String position = "a1";
    private Character initial = 'B';
    private Player owner = null;

    private Character[] validColumns = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h' };
    private Character[] validRows = { '1', '2', '3', '4', '5', '6', '7', '8' };
    private List<String> validPositionsTo = new ArrayList<String>();

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
            if (piece.getPosition() == toPosition) {
                return true;
            }
        }
        return false;
    }

    public List<String> getValidPositionsTo(String position) {
        // diagonal pattern
        List<String> validPositionsTo = new ArrayList<String>();
        // up right
        String currentPositionCheck = this.position;
        while (currentPositionCheck.charAt(0) < 'h' && currentPositionCheck.charAt(1) < '8'
                && !isOccupied(currentPositionCheck)) {
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
            }
            currentPositionCheck = toPosition;
        }
        // up left
        while (currentPositionCheck.charAt(0) > 'a' && currentPositionCheck.charAt(1) < '8'
                && !isOccupied(currentPositionCheck)) {
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
            }
            currentPositionCheck = toPosition;
        }
        // down right
        while (currentPositionCheck.charAt(0) < 'h' && currentPositionCheck.charAt(1) > '1'
                && !isOccupied(currentPositionCheck)) {
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
            }
            currentPositionCheck = toPosition;
        }
        // down left
        while (currentPositionCheck.charAt(0) > 'a' && currentPositionCheck.charAt(1) > '1'
                && !isOccupied(currentPositionCheck)) {
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
            } else { // we check including the "x" pieces but this time specifying only position
                     // interested
                for (String validPositionTo : validPositionsTo) {
                    if (validPositionTo.substring(validPositionTo.length() - 2).equals(position)) {
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
}
