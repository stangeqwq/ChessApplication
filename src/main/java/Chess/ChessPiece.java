package Chess;

import java.util.List;

public interface ChessPiece {
    public void setPosition(String move);

    public String getPosition();

    public boolean isValidPosition(String move);

    public List<String> getAttackingPositions();

    public Character getInitial();

    public Player getOwner();

    public void setOwner(Player owner);
}
