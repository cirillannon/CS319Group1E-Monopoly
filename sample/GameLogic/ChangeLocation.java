package GameLogic;


public class ChangeLocation implements EffectStrategy {

    private int target;
    private Player p;
    private Property prp;

    public ChangeLocation(int target, Player p){
        this.target = target;
        this.p = p;
        prp = null;
    }

    @Override
    public void affect() {
        p.setLocation(target);
        if(target != 31 || (target < p.getLocation()))
            p.incrementBalance(GameLogic.Constants.TileConstants.SALARY);
        if(target == 31)
            p.setInJail(true);
    }

    @Override
    public void setTargetPlayer(Player p){
        this.p = p;
    }

    @Override
    public void setTargetProperty(Property prp) {
        this.prp = prp;
    }

    public void setTarget(int target){
        this.target = target;
    }
}
