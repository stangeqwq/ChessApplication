package Chess;

import java.util.ArrayList;
import java.util.List;

public class Queen implements ChessPiece {
    private String position = "a1";
    private Character initial = 'Q';
    private Player owner = null;

    private Character[] validColumns = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h' };
    private Character[] validRows = { '1', '2', '3', '4', '5', '6', '7', '8' };
    private List<String> validPositionsTo = new ArrayList<String>();

    public Queen(String position) {
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
        // cross pattern and diagonal pattern (essentially bishop + rook class)
        List<String> validPositionsTo = new ArrayList<String>();
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
            }
            currentPositionCheck = toPosition;
        }
        // left
        currentPositionCheck = this.position;
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
            }
            currentPositionCheck = toPosition;
        }
        // up
        currentPositionCheck = this.position;
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
            }
            currentPositionCheck = toPosition;
        }
        // down
        currentPositionCheck = this.position;
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
            }
            currentPositionCheck = toPosition;
        }

        // up right
        currentPositionCheck = this.position;
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
            }
            currentPositionCheck = toPosition;
        }
        // up left
        currentPositionCheck = this.position;
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
            }
            currentPositionCheck = toPosition;
        }
        // down right
        currentPositionCheck = this.position;
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
            }
            currentPositionCheck = toPosition;
        }
        // down left
        currentPositionCheck = this.position;
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
            }
            currentPositionCheck = toPosition;
        }
        return validPositionsTo;
    }

    public boolean isValidPosition(String move) {
        if (Character.isLowerCase(move.charAt(0))) {
            return false; // it is a pawn
        } else {
        }
        return false;
    }

    public void setPosition(String move) {
        if (isValidPosition(move)) {
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
