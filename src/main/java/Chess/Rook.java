package Chess;

public class Rook implements ChessPiece{
    private String position = "a1";
    private Character initial = 'R';
    
    private Character[] validColumns = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h'};
    private Character[] validRows = {'1', '2', '3', '4', '5', '6', '7', '8'};

    public Rook(String position) {
        this.position = position;
    }
    
    public boolean isValidPosition(String position){
        if (Character.isLowerCase(position.charAt(0))) {
            return false; //it is a pawn
        } else {
            
        }
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
