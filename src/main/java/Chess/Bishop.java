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

    public List<String> getValidPositionsTo(String position) {
        // diagonal pattern
        List<String> validPositionsTo = new ArrayList<String>();
        // up right
        String currentPositionCheck = this.position;
        while (currentPositionCheck.CharAt(0) >=) {

        }
        // up left
        // down right
        // down left
        return validPositionsTo;
    }

    public boolean isValidPosition(String move) { // takes a position without an initial
        if (Character.isLowerCase(move.charAt(0))) {
            return false; // it is a pawn
        } else if (move.charAt(0) == this.getInitial()) {
            List<String> validPositionsTo = getValidPositionsTo(this.getPosition());
        } else {
            return false;
        }
        return false;
    }

    public void setPosition(String move) {
        if (isValidPosition(move)) {
            // we only take "a4" f.e. type not whole position "Ndxe4" this must be validated
            this.position = move.substring(move.length() - 2);
        }
    }

    public Character getInitial() {
        return this.initial;
    }

    public String getPosition() {
        return this.position;
    }
}
