package sample.subsystems.GameLogic;

public class Dice {

    private int diceTotal;
    private boolean isDoubles;

    public Dice(){
        diceTotal = 0;
        isDoubles = false;
    }

    public int getDiceTotal() {
        return diceTotal;
    }

    public boolean getDoubles(){
        return isDoubles;
    }

    public void rollDice(){
        int d1 = (int)(Math.random()*6 + 1);
        int d2 = (int)(Math.random()*6 + 1);

        diceTotal = d1 + d2;
        isDoubles = (d1 == d2);
    }
}
