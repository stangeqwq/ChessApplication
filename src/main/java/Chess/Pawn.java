package Chess;

public class Pawn implements ChessPiece {
    private String position = "a1";
    private Character initial = null;
    private Player owner = null;

    private Character[] validColumns = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h'};
    private Character[] validRows = {'1', '2', '3', '4', '5', '6', '7', '8'};

    public Pawn(String position) {
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
    
    public boolean isValidPosition(String position){
        if (Character.isLowerCase(position.charAt(0))) {
            //it is a pawn
        } else {
            return false; // not a pawn
        }
        return true;
    }
    public void setPosition(String position) {
        if (isValidPosition(position)) {
            this.position = position;
        }
    }
    public Character getInitial() {
        return this.initial;
    }
    public String getPosition() {
        return this.position;
    }
   
}
