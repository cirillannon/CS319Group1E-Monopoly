package sample.subsystems.GameLogic;

public class Dice {

    private static int diceTotal;
    private static boolean isDoubles;

    public Dice(){
        diceTotal = 0;
        isDoubles = false;
    }

    public static int getDiceTotal() {
        return diceTotal;
    }

    public static boolean getDoubles(){
        return isDoubles;
    }

    public static void rollDice(){
        int d1 = (int)(Math.random()*6 + 1);
        int d2 = (int)(Math.random()*6 + 1);

        diceTotal = d1 + d2;
        isDoubles = (d1 == d2);
    }
}
