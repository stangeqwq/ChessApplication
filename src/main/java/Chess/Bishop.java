package Chess;

public class Bishop implements ChessPiece {
    private String position = "a1";

    public Bishop(String position) {
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
    public String getPosition() {
        return this.position;
    }   
}
