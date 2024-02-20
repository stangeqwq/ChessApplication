package Chess;

import java.util.ArrayList;
import java.util.List;

public class King implements ChessPiece {
    private String position = "a1";
    private Character initial = 'K';
    private Player owner = null;

    private Character[] validColumns = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h' };
    private Character[] validRows = { '1', '2', '3', '4', '5', '6', '7', '8' };
    private List<String> validPositionsTo = new ArrayList<String>();

    public King(String position) {
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
        // cross pattern and diagonal pattern (essentially bishop + rook class) without
        // range so no while loop
        List<String> validPositionsTo = new ArrayList<String>();
        // right
        String currentPositionCheck = this.position;
        if (currentPositionCheck.charAt(0) < 'h') {
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
            }
        }
        // left
        if (currentPositionCheck.charAt(0) > 'a'
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
            }
        }
        // up
        if (currentPositionCheck.charAt(1) < '8') {
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
            }
        }
        // down
        if (currentPositionCheck.charAt(1) > '1') {
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
            }
        }

        // up right
        if (currentPositionCheck.charAt(0) < 'h' && currentPositionCheck.charAt(1) < '8') {
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
        }
        // up left
        if (currentPositionCheck.charAt(0) > 'a' && currentPositionCheck.charAt(1) < '8') {
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
        }
        // down right
        if (currentPositionCheck.charAt(0) < 'h' && currentPositionCheck.charAt(1) > '1') {
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
        }
        // down left
        if (currentPositionCheck.charAt(0) > 'a' && currentPositionCheck.charAt(1) > '1') {
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
        }
        // now check for each validPositionsTo if a piece is attacking it/covers it in
        // their validPositionsTo (remove it)
        // in such a case, that position can't be moved to
        // checkmate is if king is (in check+ will be coded) + no validPositionsTo
        List<String> finalValidPositionsTo = new ArrayList();
        for (String validPositionTo : validPositionsTo) {
            if (isInAttack(validPositionTo)) {
            } else {
                finalValidPositionsTo.add(validPositionTo);
            }

        }
        return finalValidPositionsTo;
    }

    public boolean isInAttack(String positionTo) {
        for (ChessPiece piece : this.getOwner().getOpponent().getPieces()) {
            for (String attackedPosition : piece.getAttackingPositions()) {
                if (positionTo.equals(attackedPosition.substring(attackedPosition.length() - 2))) {
                    return true;
                }
            }
        }
        return false;
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
                    if (validPositionTo.substring(validPositionTo.length() - 2).equals(position)
                            && validPositionTo.length() == 3) { // actually have to capture a piece when capturing
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
        List<String> attackingPositions = new ArrayList<String>(); // should be all possible moves since one square only
                                                                   // here we don't check for if it is in attack or not
                                                                   // as we are not moving but attacking

        // check for checked positions
        // cross pattern and diagonal pattern (essentially bishop + rook class) without
        // range so no while loop
        // right
        String currentPositionCheck = this.position;
        if (currentPositionCheck.charAt(0) < 'h') {
            Character column = currentPositionCheck.charAt(0);
            Character row = currentPositionCheck.charAt(1);
            column = (char) (column + 1);
            String toPosition = Character.toString(column) + Character.toString(row);
            if (!isOccupied(toPosition)) {
                attackingPositions.add(toPosition);
            } else if (isEnemy(toPosition)) {
                // add first occupied square if enemy position's with specification 'x' for
                // capturing
                attackingPositions.add(Character.toString('x') + toPosition);
            }
        }
        // left
        if (currentPositionCheck.charAt(0) > 'a'
                && (!isOccupied(currentPositionCheck) || currentPositionCheck.equals(this.getPosition()))) {
            Character column = currentPositionCheck.charAt(0);
            Character row = currentPositionCheck.charAt(1);
            column = (char) (column - 1);
            String toPosition = Character.toString(column) + Character.toString(row);
            if (!isOccupied(toPosition)) {
                attackingPositions.add(toPosition);
            } else if (isEnemy(toPosition)) {
                // add first occupied square if enemy position's with specification 'x' for
                // capturing
                attackingPositions.add(Character.toString('x') + toPosition);
            }
        }
        // up
        if (currentPositionCheck.charAt(1) < '8') {
            Character column = currentPositionCheck.charAt(0);
            Character row = currentPositionCheck.charAt(1);
            row = (char) (row + 1);
            String toPosition = Character.toString(column) + Character.toString(row);
            if (!isOccupied(toPosition)) {
                attackingPositions.add(toPosition);
            } else if (isEnemy(toPosition)) {
                // add first occupied square if enemy position's with specification 'x' for
                // capturing
                attackingPositions.add(Character.toString('x') + toPosition);
            }
        }
        // down
        if (currentPositionCheck.charAt(1) > '1') {
            Character column = currentPositionCheck.charAt(0);
            Character row = currentPositionCheck.charAt(1);
            row = (char) (row - 1);
            String toPosition = Character.toString(column) + Character.toString(row);
            if (!isOccupied(toPosition)) {
                attackingPositions.add(toPosition);
            } else if (isEnemy(toPosition)) {
                // add first occupied square if enemy position's with specification 'x' for
                // capturing
                attackingPositions.add(Character.toString('x') + toPosition);
            }
        }

        // up right
        if (currentPositionCheck.charAt(0) < 'h' && currentPositionCheck.charAt(1) < '8') {
            Character column = currentPositionCheck.charAt(0);
            Character row = currentPositionCheck.charAt(1);
            column = (char) (column + 1);
            row = (char) (row + 1);
            String toPosition = Character.toString(column) + Character.toString(row);
            if (!isOccupied(toPosition)) {
                attackingPositions.add(toPosition);
            } else if (isEnemy(toPosition)) {
                // add first occupied square if enemy position's with specification 'x' for
                // capturing
                attackingPositions.add(Character.toString('x') + toPosition);
            }
        }
        // up left
        if (currentPositionCheck.charAt(0) > 'a' && currentPositionCheck.charAt(1) < '8') {
            Character column = currentPositionCheck.charAt(0);
            Character row = currentPositionCheck.charAt(1);
            column = (char) (column - 1);
            row = (char) (row + 1);
            String toPosition = Character.toString(column) + Character.toString(row);
            if (!isOccupied(toPosition)) {
                attackingPositions.add(toPosition);
            } else if (isEnemy(toPosition)) {
                // add first occupied square if enemy position's with specification 'x' for
                // capturing
                attackingPositions.add(Character.toString('x') + toPosition);
            }
        }
        // down right
        if (currentPositionCheck.charAt(0) < 'h' && currentPositionCheck.charAt(1) > '1') {
            Character column = currentPositionCheck.charAt(0);
            Character row = currentPositionCheck.charAt(1);
            column = (char) (column + 1);
            row = (char) (row - 1);
            String toPosition = Character.toString(column) + Character.toString(row);
            if (!isOccupied(toPosition)) {
                attackingPositions.add(toPosition);
            } else if (isEnemy(toPosition)) {
                // add first occupied square if enemy position's with specification 'x' for
                // capturing
                attackingPositions.add(Character.toString('x') + toPosition);
            }
        }
        // down left
        if (currentPositionCheck.charAt(0) > 'a' && currentPositionCheck.charAt(1) > '1') {
            Character column = currentPositionCheck.charAt(0);
            Character row = currentPositionCheck.charAt(1);
            column = (char) (column - 1);
            row = (char) (row - 1);
            String toPosition = Character.toString(column) + Character.toString(row);
            if (!isOccupied(toPosition)) {
                attackingPositions.add(toPosition);
            } else if (isEnemy(toPosition)) {
                // add first occupied square if enemy position's with specification 'x' for
                // capturing
                attackingPositions.add(Character.toString('x') + toPosition);
            }
        }
        return attackingPositions;
    }
}
