package sample.subsystems.GameLogic;

import java.util.Scanner;

public abstract class Property extends Tile {

    private int value;
    private Player owner;
    private int baseRent;
    private boolean owned;
    private boolean mortgaged;


    public abstract void payRent(Player landed);

    public Property(int val, int r, String tileName, int tileLocation){
        super(tileName, tileLocation);
        value = val;
        owner = null;
        baseRent = r;
        owned = false;
        mortgaged = false;
    }


    public boolean isOwned(){
        return owned;
    }

    public void setOwned(boolean b){
        owned = b;
    }

    public boolean isMortgaged(){
        return mortgaged;
    }

    public void setMortgaged(boolean b) {
        mortgaged = b;
    }

    public Player getOwner(){
        return owner;
    }

    public void setOwner(Player p){
        owner = p;
    }

    public int getRent(){
        return baseRent;
    }

    public void setRent(int rent){
        this.baseRent = rent;
    }

    public int getValue(){
        return value;
    }

    public void setValue(int value){
        this.value = value;
    }

    public int mortgageAmount (){ return baseRent/2;}

    @Override
    public void onLand(Player landed) {
        Scanner scan = new Scanner(System.in);
        if (isOwned() && getOwner() == landed) {
            if (this instanceof ColoredProperty) {
                ColoredProperty temp = ((ColoredProperty) this);
                if (landed.hasMonopoly(temp.getColor())) {
                    System.out.println("you have " + temp.getNumberOfHouses() + " houses and " + temp.getNumberOfHotels() + " hotels.");
                    System.out.println("build or remove house/hotel/none: (choose one)");
                    String choose = scan.next();
                    if (choose != "none") {
                        int count;
                        if (choose == "house") {
                            count = -6;
                            while (count != 0) {
                                System.out.println("Enter no. of houses you want to build on " + this.getTileName() + " for " + temp.getHouseCost());
                                count = scan.nextInt();
                                if (!temp.updateHouses(count)) {
                                    System.out.println("stay between 0 and 5 houses. You have " + temp.getNumberOfHouses());
                                } else {
                                    System.out.println(count + " houses has been built on" + this.getTileName());
                                    break;
                                }
                            }
                        }
                        if (choose == "hotel") {
                            count = -6;
                            while (count != 0) {
                                System.out.println("Enter no. of hotels you want to build on " + this.getTileName() + " for " + temp.getHotelCost());
                                count = scan.nextInt();
                                if (!temp.updateHotels(count)) {
                                    System.out.println("stay between 0 and 5 houses. You have " + temp.getNumberOfHotels());
                                } else {
                                    System.out.println(count + " hotels has been built on" + this.getTileName());
                                    break;
                                }
                            }
                        }
                    }
                }
            } else if (isOwned()) {
                ((ColoredProperty) this).payRent(landed);
            } else {
                System.out.println("Do you want to buy " + this.getTileName() + " for " + this.getValue() + " ? (yes/no)");
                String result = scan.next();
                if (result == "yes") {
                    landed.buyProperty(this);
                }
            }
        }
    }

    public String toString() {
        return this.getTileName() + "   " + this.getTileLocation() + "   " + this.getValue() + "   " + this.getOwner() + "   " + this.getRent();
    }
}
