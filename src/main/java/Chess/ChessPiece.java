package Chess;

public interface ChessPiece {
    public void setPosition(String position);
    public String getPosition();
    public boolean isValidPosition(String position);
    public Character getInitial();
}
