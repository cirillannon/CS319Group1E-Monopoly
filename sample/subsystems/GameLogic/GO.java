package sample.subsystems.GameLogic;

public class GO extends Tile{

    private static final int salary = 200;

    public GO(){
        super("GO", 1); //GO is unique so I hardcoded the name and location in constructor
    }

    public static void paySalary( Player p){
        p.updateBalance(salary);
    }

    @Override
    public void onLand(Player p) {
        paySalary(p);
    }
}
