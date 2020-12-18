package GameLogic;

public class ChangeBalance implements EffectStrategy {

    private int amount;
    private Player p;
    private Property prp;

    public ChangeBalance(int amount, Player p){
        this.amount = amount;
        this.p = p;
        prp = null;
    }

    @Override
    public void affect() {
        if(amount > 0)
           p.incrementBalance(amount);
        else
            p.decrementBalance(amount);
    }

    @Override
    public void setTargetPlayer(Player p){
        this.p = p;
    }

    @Override
    public void setTargetProperty(Property prp) {
        this.prp = prp;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
