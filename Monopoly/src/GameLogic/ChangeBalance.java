package GameLogic;

public class ChangeBalance implements EffectStrategy {

    private boolean effect; //If true effect is positive, if false negative
    private int amount;
    private Player p;

    public ChangeBalance(boolean effect, int amount, Player p){
        this.effect = effect;
        this.amount = amount;
        this.p = p;
    }

    @Override
    public void affect() {
        if(effect)
           p.incrementBalance(amount);
        else
            p.decrementBalance(amount);
    }
}
