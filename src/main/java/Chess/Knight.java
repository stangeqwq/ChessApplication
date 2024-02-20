package Chess;

import java.util.ArrayList;
import java.util.List;

public class Knight implements ChessPiece {
    private String position = "a1";
    private Character initial = 'N';
    private Player owner = null;

    private Character[] validColumns = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h' };
    private Character[] validRows = { '1', '2', '3', '4', '5', '6', '7', '8' };
    private List<String> validPositionsTo = new ArrayList<String>();

    public Knight(String position) {
        this.position = position;
        validPositionsTo = generateValidPositionsTo(position);
    }

    public Player getOwner() {
        return owner;
    }

    public void setOwner(Player owner) {
        if (this.owner == null) {
            this.owner = owner;
        }
    }

    public List<String> getValidPositionsTo() {
        return this.validPositionsTo;
    }

    private boolean inValidPositionsTo(String toPosition) {
        for (String position : this.getValidPositionsTo()) {
            if (toPosition.equals(position.substring(position.length() - 2))) {
                return true;
            }
        }
        return false;

    }

    public boolean isValidPosition(String move) {
        if (Character.isLowerCase(move.charAt(0))) {
            return false; // it is a pawn
        } else {
            if (move.charAt(0) == this.getInitial()) {
                String toPosition = move.substring(move.length() - 2);
                boolean capturing = false;
                if (move.length() >= 4 && move.charAt(move.length() - 3) == 'x') { // f.e. Nxe4, or Ndxe4
                    capturing = true;
                    if (move.length() >= 5) {
                        if (move.charAt(move.length() - 4) == this.getPosition().charAt(0)
                                || move.charAt(move.length() - 3) == this.getPosition().charAt(1)) {

                        } else {
                            return false;
                        }
                    }
                } else { // not capturing but specified which knight f.e. Nde4 or N1e4
                    if (move.length() >= 4) {
                        if (move.charAt(move.length() - 3) == this.getPosition().charAt(0)
                                || move.charAt(move.length() - 3) == this.getPosition().charAt(1)) {

                        } else {
                            return false;
                        }
                    }
                }
                if (capturing) {
                    for (String position : this.getOwner().getOpponent().getPiecePositions()) {
                        if (position.equals(toPosition) && inValidPositionsTo(toPosition)
                                && !isOwnPieceInPosition(toPosition)) {
                            return true;
                        }
                    }
                    return false; // if you specify x, you must actually occupy an opponent piece position
                } else {
                    return inValidPositionsTo(toPosition) && !isOwnPieceInPosition(toPosition);
                }
            } else { // not the right initial
                return false;
            }
        }
    }

    private List<String> generateValidPositionsTo(String position) {
        List<String> validPositionsTo = new ArrayList<String>();
        Character row = position.charAt(1);
        Character column = position.charAt(0);
        // 2*2*2 valid positions (1 or 2) increase column/row then (- or +) and two
        // order
        // 1 2
        // 2 1
        // -1 2
        // 2 -1
        // 1 -2
        // -2 1
        // -1 -2
        // -2 -1
        if (row + 1 <= '8' && column + 2 <= 'h') {
            validPositionsTo.add(Character.toString((char) (column + 2)) + Character.toString((char) (row + 1)));
        }
        if (row + 2 <= '8' && column + 1 <= 'h') {
            validPositionsTo.add(Character.toString((char) (column + 1)) + Character.toString((char) (row + 2)));
        }
        if (row - 1 >= '1' && column + 2 <= 'h') {
            validPositionsTo.add(Character.toString((char) (column + 2)) + Character.toString((char) (row - 1)));
        }
        if (row + 2 <= '8' && column - 1 >= 'a') {
            validPositionsTo.add(Character.toString((char) (column - 1)) + Character.toString((char) (row + 2)));
        }
        if (row + 1 <= '8' && column - 2 >= 'a') {
            validPositionsTo.add(Character.toString((char) (column - 2)) + Character.toString((char) (row + 1)));
        }
        if (row - 2 >= '1' && column + 1 <= 'h') {
            validPositionsTo.add(Character.toString((char) (column + 1)) + Character.toString((char) (row - 2)));
        }
        if (row - 1 >= '1' && column - 2 >= 'a') {
            validPositionsTo.add(Character.toString((char) (column - 2)) + Character.toString((char) (row - 1)));
        }
        if (row - 2 >= '1' && column - 1 >= 'a') {
            validPositionsTo.add(Character.toString((char) (column - 1)) + Character.toString((char) (row - 2)));
        }
        return validPositionsTo;

    }

    public boolean isOwnPieceInPosition(String toPosition) {
        for (String position : this.getOwner().getPiecePositions()) {
            if (toPosition.equals(position.substring(position.length() - 2))) {
                return true;
            }
        }
        return false;
    }

    public void setPosition(String move) {
        if (isValidPosition(move)) {
            this.position = move.substring(move.length() - 2);
            this.getOwner().getOpponent().removePieceAtPosition(this.position); // for safety if there is a piece there
                                                                                // remove it (buggy implementation but
                                                                                // it works)
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

    public List<String> getAttackingPositions() {
        return getValidPositionsTo();
    }
}
