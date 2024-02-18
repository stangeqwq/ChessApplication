package Chess;

public class Rook implements ChessPiece{
    private String position = "a1";
    private Character initial = 'R';
    private Player owner = null;
    
    private Character[] validColumns = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h'};
    private Character[] validRows = {'1', '2', '3', '4', '5', '6', '7', '8'};

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
    
    public boolean isValidPosition(String move){
        if (Character.isLowerCase(move.charAt(0))) {
            return false; //it is a pawn
        } else {
            
        }
        return true;
    }
    public void setPosition(String move) {
        if (isValidPosition(move)) {
            this.position = move.substring(move.length()-2);
        }

    }
    public String getPosition() {
        return this.position;
    }

    public Character getInitial() {
        return this.initial;
    }
    public static void main(String args[]) {
        System.out.println("Test");
    }
}
