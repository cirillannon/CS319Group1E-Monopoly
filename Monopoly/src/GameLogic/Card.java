package GameLogic;

public abstract class Card 
{

    private String cardDescription;

    public Card(String cardDescription )
    {
        this.cardDescription = cardDescription;
    }

    String getCardDescription() 
    {
        return cardDescription;
    }
}
