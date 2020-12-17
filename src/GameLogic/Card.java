package GameLogic;

public abstract class Card 
{

    private String cardDescription;
    private EffectStrategy strategy;

    public Card(String cardDescription, EffectStrategy strategy )
    {
        this.strategy = strategy;
        this.cardDescription = cardDescription;
    }

    String getCardDescription() 
    {
        return cardDescription;
    }

    public EffectStrategy getStrategy(){
        return this.strategy;
    }
}
