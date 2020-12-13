package sample.subsystems.GameLogic;

public class Jail extends Tile {
    public Jail(){
        super("Jail", 11 ); //Jail is unique so I hardcoded the name and location in constructor
    }

    @Override
    public void onLand(Player p) {
        //Do nothing
        System.out.println("Jail");
    }
}
