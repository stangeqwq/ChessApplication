package Chess;

public interface ChessPiece {
    public void setPosition(String);
    public void getPosition();
    private boolean isValidPosition(String);
}
