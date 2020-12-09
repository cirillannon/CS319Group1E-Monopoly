package sample.subsystems.GameLogic;

import java.util.ArrayList;

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

    public Player( String name, int ID){
        balance = 1500; // Start balance can be changed
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
    }

    public int getBalance(){
        return this.balance;
    }

    public void updateBalance(int changeBalance){
        this.balance += changeBalance;

        if(this.balance < 0)
            this.balance = 0;
            this.setBankruptcy(true);
    }

    public int getLocation(){
        return this.location;
    }

    public void setLocation( int loc){
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
        this.location = (this.location + diceTotal)%40;
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

    public boolean isInJail(){
        return this.inJail;
    }

    public boolean hasOutOfJailFreeCard(){
        return this.outOfJailCard;
    }

    public void setOutOfJailCard(boolean b){
        this.outOfJailCard = b;
    }

    public boolean hasMonopoly(){
        return !(this.monopolyColor.isEmpty());
    }

    public void addMonopoly( String color){
        this.monopolyColor.add(color);
    }

    public void removeMonopoly( String color){
        this.monopolyColor.remove(color);
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

    public void addProperty( Property p){
        this.propertiesOwned.add(p);
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




}
