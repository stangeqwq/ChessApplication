package Chess;

public class Rook implements ChessPiece{
    private String position = "a1";
    private Character initial = 'R';
    
    public Rook(String position) {
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

    public Character getInitial() {
        return this.initial;
    }
    public static void main(String args[]) {
        System.out.println("Test");
    }
}
