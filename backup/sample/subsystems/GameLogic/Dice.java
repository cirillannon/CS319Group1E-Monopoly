package sample.subsystems.GameLogic;

public class Dice {

    private static int diceTotal;
    private static int d1;
    private static int d2;
    private static boolean isDoubles;

    public Dice(){
        diceTotal = 0;
        isDoubles = false;
    }

    public static int getDiceTotal() {
        return diceTotal;
    }

    public static int d1() {
        return d1;
    }

    public static int d2() {
        return d2;
    }

    public static boolean getDoubles(){
        return isDoubles;
    }

    public static void rollDice(){
        d1 = (int)(Math.random()*6 + 1);
        d2 = (int)(Math.random()*6 + 1);

        diceTotal = d1 + d2;
        isDoubles = (d1 == d2);
    }
}
