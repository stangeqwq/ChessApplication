package Chess;

public class Pawn implements ChessPiece {
    private String position = "a1";
    private Character initial = null;
    private Player owner = null;
    private boolean allowCapture = false;
    private boolean firstMove = true;
    private boolean isWhite = true;

    private Character[] validColumns = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h'};
    private Character[] validRows = {'1', '2', '3', '4', '5', '6', '7', '8'};

    public Pawn(String position, boolean isWhite) {
        this.position = position;
        this.isWhite = isWhite;
    }
    public Player getOwner() {
        return owner;
    }
    public void setOwner(Player owner) {
        if (this.owner == null) {
            this.owner = owner;
        }
    }
    private boolean isOccupiedToPosition(String toPosition) {
        for (String position : this.getOwner().getOpponent().getPiecePositions()) {
            if (position.substring(position.length()-2) == toPosition) {  
                // if a piece is at destination position, then occupied
                if (position.charAt(position.length() - 2) == toPosition.charAt(toPosition.length() - 2) && position.charAt(position.length() - 1) - toPosition.charAt(toPosition.length() - 1) == 1)
                // f.e. a piece is infront (d2 -> d4) but there is someone at (d3 or d4), so occupied
                // when moving there, there is a piece (same column [length() - 2]) in front)
                return true;
            } 
        }
        return false;
    }
    
    public boolean isValidPosition(String move){
        if (Character.isLowerCase(move.charAt(0))) {
            //it is a pawn
            //check first move (allow double move in columns)
            if (isWhite) {
                if (firstMove) {
                    if ((move.charAt(move.length()-2) == this.position.charAt(0))) { // must be same column (e)4 -> (e)2
                        if ((move.charAt(move.length()-1) - this.position.charAt(1) == 2 || move.charAt(move.length()-1) - this.position.charAt(1) == 1)) { // must be one or two squares in front
                            return !isOccupiedToPosition(move.substring(move.length()-2));
                        }  
                    } else {
                        return false;
                    }
                } else { // not first move
                    if (move.charAt(move.length()-1) - this.position.charAt(1) == 1 && move.charAt(move.length()-2) == this.position.charAt(0)) { // "e2 -> e4"

                    }
                }
            } else {
                if (firstMove) {
                    if ((move.charAt(move.length()-1) - this.position.charAt(1) == -2 || move.charAt(move.length()-1) - this.position.charAt(1) == -1) && move.charAt(move.length()-2) == this.position.charAt(0)) { // "e2 -> e4"
                        return !isOccupiedToPosition(move.substring(move.length()-2));
                    } else {
                        return false;
                    }
                } else { // not first move
                    if (move.charAt(move.length()-1)  - this.position.charAt(1) == -1 && move.charAt(move.length()-2) == this.position.charAt(0)) { // "e2 -> e4"

                    }
                }
            }

        } else {
            return false; // not a pawn
        }
        return true;
    }
    public void setPosition(String move) {
        if (isValidPosition(move)) {
            this.position = move.substring(move.length()-2);
            firstMove = false;
        }
    }
    public Character getInitial() {
        return this.initial;
    }
    public String getPosition() {
        return this.position;
    }
   
}
