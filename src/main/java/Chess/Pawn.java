package Chess;

public class Pawn implements ChessPiece {
    private String position = "a1";
    private Character initial = null;

    public Pawn(String position) {
        this.position = position;
    }
    
    private boolean isValidPosition(String position){
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
