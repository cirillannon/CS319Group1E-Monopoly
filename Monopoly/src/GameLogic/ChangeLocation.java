package GameLogic;

public class ChangeLocation implements EffectStrategy {

    private int changeAmount;
    private Player p;

    public ChangeLocation(int changeAmount, Player p){
        this.changeAmount = changeAmount;
        this.p = p;
    }

    @Override
    public void affect() {
        p.move(changeAmount);
    }
}
