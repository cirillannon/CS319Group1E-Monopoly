package GameLogic;

public interface EffectStrategy {
    public void affect();
    public void setTargetPlayer(Player p);
    public void setTargetProperty(Property prp);
}
