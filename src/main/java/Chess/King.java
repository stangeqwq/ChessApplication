package Chess;

public class King implements ChessPiece{
    private String position = "a1";
    private Character initial = 'K';

    private Character[] validColumns = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h'};
    private Character[] validRows = {'1', '2', '3', '4', '5', '6', '7', '8'};
    
    public King(String position) {
        this.position = position;
    }
    
    public boolean isValidPosition(String position){
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
