package Chess;

public class Knight implements ChessPiece{
    private String position = "a1";

    public Knight(String position) {
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
