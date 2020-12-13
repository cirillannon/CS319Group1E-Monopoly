package sample.subsystems.GameLogic;

public class Pawn {
    private int pawnLocation;
    private Player pawnOwner;
    private String pawnColor;

    public Pawn(Player pawnOwner, String pawnColor) {
        pawnLocation = 1;
        this.pawnOwner = pawnOwner;
        this.pawnColor = pawnColor;
    }

    public int getPawnLocation() {
        return pawnLocation;
    }

    public void setPawnLocation(int pawnLocation) {
        this.pawnLocation = pawnLocation;
    }

    public Player getPawnOwner() {
        return pawnOwner;
    }

    public void setPawnOwner(Player p) {
        pawnOwner = p;
    }
}
