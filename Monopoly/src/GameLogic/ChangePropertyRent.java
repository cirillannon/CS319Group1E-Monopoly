package GameLogic;

public class ChangePropertyRent implements EffectStrategy {

    private boolean effect; //If true effect is positive, if false negative
    private int percentage; //Rent change percentage
    private Property prp; //Target property

    public ChangePropertyRent( boolean effect, int percentage, Property prp ){
        this.effect = effect;
        this.percentage = percentage;
        this.prp = prp;
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
}
