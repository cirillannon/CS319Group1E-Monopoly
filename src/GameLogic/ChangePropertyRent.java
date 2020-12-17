package GameLogic;

public class ChangePropertyRent implements EffectStrategy {

    private boolean effect; //If true effect is positive, if false negative
    private int percentage; //Rent change percentage
    private Property prp; //Target property
    private Player p;

    public ChangePropertyRent( boolean effect, int percentage, Property prp ){
        this.effect = effect;
        this.percentage = percentage;
        this.prp = prp;
        p = null;
    }

    @Override
    public void affect() {
        int rentBefore = prp.getRent();
        int rentFinal;

        if(effect){
            rentFinal = ((rentBefore * percentage) / 100) + rentBefore;
        }
        else
            rentFinal = rentBefore - ((rentBefore * percentage) / 100);

        prp.setRent(rentFinal);
    }

    @Override
    public void setTargetPlayer(Player p){
        this.p = p;
    }

    @Override
    public void setTargetProperty(Property prp) {
        this.prp = prp;
    }
}
