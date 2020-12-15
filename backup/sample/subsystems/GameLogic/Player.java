package sample.subsystems.GameLogic;

// import sample.subsystems.Controller.GameManager;

import java.util.Scanner;
import java.util.ArrayList;
import java.lang.*;

public class Player {

    private int balance;
    private int housesOwned;
    private int hotelsOwned;
    private int location;
    private boolean bankruptcy;
    private boolean inJail;
    private String playerName;
    private int playerID;
    private ArrayList<String> monopolyColor;
    private int numberOfStations;
    private boolean playerHasTurn;
    private ArrayList<Integer> cardsOwned;
    private int numOfJailCards;
    private boolean bothUtilities;
    private boolean outOfJailCard;
    private ArrayList<Property> propertiesOwned;
    private Pawn pawn;

    public Player( String name, int ID, String pawnColor){
        balance = Constants.PlayerConstants.STARTING_AMOUNT; // Start balance can be changed
        location = 1;
        housesOwned = 0;
        hotelsOwned = 0;
        bankruptcy = false;
        inJail = false;
        playerName = name;
        playerID = ID;
        monopolyColor = new ArrayList<>();
        numberOfStations = 0;
        playerHasTurn = false;
        cardsOwned = new ArrayList<>();
        propertiesOwned = new ArrayList<>();
        bothUtilities = false;
        numOfJailCards = 0;
        outOfJailCard = false;
        pawn = new Pawn(this, pawnColor);
    }

    //for console testing
    public String toString() {
        String props = "Properties owned:\n";
        for (int count = 0; count < propertiesOwned.size(); count++) {
            props = props + "   " + propertiesOwned.get(count) + "\n";
        }
        return props + "Balance: " + balance;
    }

    public int getBalance(){
        return this.balance;
    }

    public boolean updateBalance(int changeBalance){
        System.out.println("in updateBalance");
        this.balance += changeBalance;
        if(this.balance < 0 && propertiesOwned.size() == 0) {
            this.balance = 0;
            this.setBankruptcy(true);
            System.out.println("bankrupted.");
            return false;
        } else if (balance < 0) {
            this.balance -= changeBalance;
            System.out.println("not enough money. ");
            return false;
        } else {
            System.out.println("player " + getPlayerName() + " balance updated: " + balance);
            return true;
        }
    }

    public int getLocation(){
        return this.location;
    }

    public void setLocation( int loc){
        pawn.setPawnLocation(loc);
        this.location = loc;
    }

    public boolean getBankruptcy(){
        return this.bankruptcy;
    }

    public void setBankruptcy( boolean b){
        this.bankruptcy = b;
    }

    public boolean getInJail(){
        return this.inJail;
    }

    public void setInJail( boolean b){
        this.inJail = b;
    }

    public void setBothUtilities( boolean b){
        this.bothUtilities = b;
    }

    public boolean hasBothUtilities(){
        return bothUtilities;
    }

    public void move(int diceTotal){
        this.location = (this.location + diceTotal)%40 + 1;
        System.out.println(getPlayerName() + " in " + GameBoard.getTile(location).toString());
        System.out.println(toString());
        GameBoard.getTile(location).onLand(this);

    }

    public String getPlayerName(){
        return this.playerName;
    }

    public void setPlayerName(String s){
        this.playerName = s;
    }

    public boolean isTurnOver(){
        return !(this.playerHasTurn);
    }

    public boolean hasTurn(){
        return this.playerHasTurn;
    }

    public void setPlayerHasTurn(boolean b){
        playerHasTurn = b;
    }

    public boolean isInJail(){
        return this.inJail;
    }

    public boolean hasOutOfJailFreeCard(){
        return this.outOfJailCard;
    }

    public void setOutOfJailCard(boolean b){
        this.outOfJailCard = b;
    }

    public boolean hasMonopoly( String color ){
        return this.monopolyColor.contains(color);
    }

    public boolean addMonopoly( String color){
        if (monopolyColor.contains(color)){
            return false;
        } else {
            this.monopolyColor.add(color);
            return true;
        }
    }

    public boolean removeMonopoly( String color){
        if (monopolyColor.contains(color)) {
            this.monopolyColor.remove(color);
            return true;
        } else {
            return false;
        }
    }

    public int getNumberOfStations(){
        return this.numberOfStations;
    }

    public void setNumberOfStations(int i){
        this.numberOfStations = i;
    }

    public void addCard( int cardID){
        this.cardsOwned.add(cardID);
    }

    public void removeCard( int cardID){
        this.cardsOwned.remove(cardID);
    }

    public ArrayList<Property> getProperties() {
        return propertiesOwned;
    }
    public void addProperty( Property p){
        this.propertiesOwned.add(p);
        p.setOwner(this);
        p.setOwned(true);
        if (p instanceof ColoredProperty) {
            boolean hasMonopoly = true;
            String color = ((ColoredProperty) p).getColor();
            ColoredProperty[] neighborhood = GameBoard.getPropertiesOfColor(color);
            for (ColoredProperty property : neighborhood) {
                if (property.getOwner() != this) {
                    hasMonopoly = false;
                }
            }
            if (hasMonopoly){
                addMonopoly(color);
            }
        }
    }

    public void removeProperty( Property p){
        this.propertiesOwned.remove(p);
        p.setOwner(null);
        p.setOwned(false);
        if (p instanceof ColoredProperty) {
            String color = ((ColoredProperty) p).getColor();
            removeMonopoly(color);
        }
    }

    public boolean sellProperty( Property p, Player buyer, int price) {
        if (p.getOwner() != this) {
            return false;
        }
        return buyer.buyProperty(p, this, price);
    }

    public boolean buyProperty( Property p, Player from, int price) {
        Scanner scan = new Scanner(System.in);
        if (updateBalance(p.getValue() * -1)) {
            System.out.println(this.getPlayerName() + ": will you buy " + p.getTileName() + " from "
                    + from.getPlayerName() + " for " + price + "? (yes/no)");
            String choose = scan.next();
            if (choose.equals("yes")) {
                addProperty(p);
                from.removeProperty(p);
                return true;
            }
            return false;
        } return false;
    }

    public boolean buyProperty( Property p) {
        System.out.println("in buyProperty");
        if (updateBalance(p.getValue() * -1)) {
            addProperty(p);
            System.out.println("property added");
            return true;
        } return false;
    }

    public void addJailCard(){
        this.outOfJailCard = true;
        this.numOfJailCards++;
    }

    public void removeJailCard(){
        if (outOfJailCard){
            this.numOfJailCards--;
            if (numOfJailCards == 0)
                this.outOfJailCard = false;
        }
    }

    public int getNumOfJailCards(){
        return numOfJailCards;
    }

    public void addHouse(){
        this.housesOwned++;
    }

    public void addHotels(){
        this.hotelsOwned++;
    }

    public void removeHouse(){
        if(this.housesOwned != 0){
            housesOwned--;
        }
    }

    public void removeHotels(){
        if(this.hotelsOwned != 0){
            hotelsOwned--;
        }
    }

    public int getHousesOwned(){
        return this.housesOwned;
    }

    public int getHotelsOwned(){
        return this.hotelsOwned;
    }

    public int getPlayerID(){
        return this.playerID;
    }

    public boolean hasLeft() {
        // return true depending on the input from UI
        return false;
    }

    public void playTurn() {
        Scanner scan = new Scanner(System.in);
        if(isInJail()){
            Dice.rollDice();
            System.out.println(getPlayerName() + " rolled dice: " + Dice.d1() + ", " + Dice.d2());
            if(Dice.getDoubles()) {
                move(Dice.getDiceTotal());
                System.out.println("Doubles.. getting out of jail");
            }
            else if (hasOutOfJailFreeCard()) {
                System.out.println("Use out of jail card ? Y/N");
                Character input = scan.next().charAt(0);
                if ( input.equals('Y') || input.equals('y')){
                    removeJailCard();
                    GoToJail.releasePlayer(this);
                    System.out.println("out fo jail card used.. rolling dice");
                    Dice.rollDice();
                    System.out.println(getPlayerName() + " rolled dice: " + Dice.d1() + ", " + Dice.d2());
                    move(Dice.getDiceTotal());
                    if (Dice.getDoubles()){
                        Dice.rollDice();
                        System.out.println(getPlayerName() + " rolled dice: " + Dice.d1() + ", " + Dice.d2());
                        move(Dice.getDiceTotal());
                        if (Dice.getDoubles()){
                            Dice.rollDice();
                            System.out.println(getPlayerName() + " rolled dice: " + Dice.d1() + ", " + Dice.d2());
                            move(Dice.getDiceTotal());
                            if(Dice.getDoubles()){
                                System.out.println("three doubles.. player goes to jail");
                                GoToJail.jailPlayer(this);
                            }
                        }
                    }
                }
            }
            else if(getBalance() >= 50){
                System.out.println("Pay $50 to leave jail ? Y/N");
                Character input = scan.next().charAt(0);
                if ( input.equals('Y') || input.equals('y')){
                    updateBalance(-50);
                    GoToJail.releasePlayer(this);
                    Dice.rollDice();
                    System.out.println(getPlayerName() + " rolled dice: " + Dice.d1() + ", " + Dice.d2());
                    move(Dice.getDiceTotal());
                    if (Dice.getDoubles()){
                        Dice.rollDice();
                        System.out.println(getPlayerName() + " rolled dice: " + Dice.d1() + ", " + Dice.d2());
                        move(Dice.getDiceTotal());
                        if (Dice.getDoubles()){
                            Dice.rollDice();
                            System.out.println(getPlayerName() + " rolled dice: " + Dice.d1() + ", " + Dice.d2());
                            move(Dice.getDiceTotal());
                            if(Dice.getDoubles()){
                                System.out.println("three doubles.. player goes to jail");
                                GoToJail.jailPlayer(this);
                            }
                        }
                    }
                }
            }
        } else {
            Dice.rollDice();
            System.out.println(getPlayerName() + " rolled dice: " + Dice.d1() + ", " + Dice.d2());
            move(Dice.getDiceTotal());
            if (Dice.getDoubles()) {
                Dice.rollDice();
                System.out.println(getPlayerName() + " rolled dice: " + Dice.d1() + ", " + Dice.d2());
                move(Dice.getDiceTotal());
                if (Dice.getDoubles()) {
                    Dice.rollDice();
                    System.out.println(getPlayerName() + " rolled dice: " + Dice.d1() + ", " + Dice.d2());
                    move(Dice.getDiceTotal());
                    if (Dice.getDoubles()) {
                        System.out.println("three doubles.. player goes to jail");
                        GoToJail.jailPlayer(this);
                    }
                }
            }
        }
    }
}
