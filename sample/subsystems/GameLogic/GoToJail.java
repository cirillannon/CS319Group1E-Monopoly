package sample.subsystems.GameLogic;

public class GoToJail extends Tile{

    public GoToJail(){
        super( "Go To Jail", 31); //GoToJail is unique so I hardcoded the name and location in constructor
    }

    @Override
    public void onLand( Player p){
        jailPlayer(p);
    }

    public static void jailPlayer( Player p){
        p.setInJail(true);
        p.setLocation(11);//The location of Jail tile
        System.out.println("player " + p.getPlayerName() + " going in jail");
    }

    //releasePlayer will be used while playing turns (by GameManager ?)
    //Player will be released from jail but THE LOCATION MUST BE UPDATED according to dice roll
    public static void releasePlayer( Player p){
        p.setInJail(false);
    }
}
