///*
//package sample.subsystems.communication;
// */
//
//import sample.subsystems.Controller.GameManager;
//import sample.subsystems.GameLogic.*;
//import com.google.gson.Gson;
//import com.google.gson.GsonBuilder;
//import com.google.gson.JsonObject;
//import com.google.gson.reflect.TypeToken;
//
//import java.io.*;
//import java.lang.reflect.Type;
//import java.util.*;
//
//public class ServerManager {
//    private boolean gameStarted;
//    private static int turnOf;
//    private boolean gameOver;
//    private static int numOfPlayers;
//    private static Player[] players; // max 4 players with playerID's between 0-3
//    private Card drawnCard; // The latest drawn card is held here
//    Scanner scan = new Scanner(System.in);
//
//    // constructor
//    public GameManager(String server) {
//        gameStarted = false;
//        gameOver = false;
//        client = new Client(server, this);
//    }
//
//    // methods
//    public void initGame(int playerNumber, String playerName1, String playerName2,
//                         String playerName3, String playerName4) {
//        String[] playerNames = {playerName1, playerName2, playerName3, playerName4};
//        Bank bank = initBank();
//        //setPawnStartPosition();
//        //startPawnSelection();
//        GameBoard.initBoard();
//        numOfPlayers = playerNumber;
//        players = new Player[playerNumber];
//        for (int i = 0; i < numOfPlayers; i++){
//            players[i] = new Player(playerNames[i], i);
//        }
//    }
//
//    // how to reach pawns?
//    public void setPawnStartPosition() {
//
//    }
//
//    //
//    public void startPawnSelection() {
//
//    }
//
//    public Bank initBank() {
//        Bank bank = Bank.initBank();
//        return bank;
//    }
//
//    public void startGame() {
//        turnOf = 0;
//        char input;
//        while(!isGameOver()){
//            if(GameManager.currentTurn().isInJail()){
//                Dice.rollDice();
//                if(Dice.getDoubles())
//                    players[turnOf].move(Dice.getDiceTotal());
//                else if (players[turnOf].hasOutOfJailFreeCard()) {
//                    System.out.println("Use out of jail card ? Y/N");
//                    input = scan.next().charAt(0);
//                    if ( input == 'Y' || input == 'y'){
//                        players[turnOf].removeJailCard();
//                        GoToJail.releasePlayer(players[turnOf]);
//                        Dice.rollDice();
//                        players[turnOf].move(Dice.getDiceTotal());
//                        if (Dice.getDoubles()){
//                            Dice.rollDice();
//                            players[turnOf].move(Dice.getDiceTotal());
//                            if (Dice.getDoubles()){
//                                Dice.rollDice();
//                                players[turnOf].move(Dice.getDiceTotal());
//                                if(Dice.getDoubles()){
//                                    GoToJail.jailPlayer(players[turnOf]);
//                                    passTurn();
//                                    continue;
//                                }
//                            }
//                        }
//                    }
//                }
//                else if(players[turnOf].getBalance() >= 50){
//                    System.out.println("Pay $50 to leave jail ? Y/N");
//                    input = scan.next().charAt(0);
//                    if ( input == 'Y' || input == 'y'){
//                        players[turnOf].updateBalance(-50);
//                        GoToJail.releasePlayer(players[turnOf]);
//                        Dice.rollDice();
//                        players[turnOf].move(Dice.getDiceTotal());
//                        GameBoard.getTiles()[players[turnOf].getLocation()].onLand(players[turnOf]);
//                        if (Dice.getDoubles()){
//                            Dice.rollDice();
//                            players[turnOf].move(Dice.getDiceTotal());
//                            GameBoard.getTiles()[players[turnOf].getLocation()].onLand(players[turnOf]);
//                            if (Dice.getDoubles()){
//                                Dice.rollDice();
//                                players[turnOf].move(Dice.getDiceTotal());
//                                GameBoard.getTiles()[players[turnOf].getLocation()].onLand(players[turnOf]);
//                                if(Dice.getDoubles()){
//                                    GoToJail.jailPlayer(players[turnOf]);
//                                    passTurn();
//                                    continue;
//                                }
//                            }
//                        }
//                    }
//                    else passTurn();
//                    continue;
//                }
//            }
//            Dice.rollDice();
//            players[turnOf].move(Dice.getDiceTotal());
//            GameBoard.getTiles()[players[turnOf].getLocation()].onLand(players[turnOf]);
//            if (Dice.getDoubles()){
//                Dice.rollDice();
//                players[turnOf].move(Dice.getDiceTotal());
//                GameBoard.getTiles()[players[turnOf].getLocation()].onLand(players[turnOf]);
//                if (Dice.getDoubles()){
//                    Dice.rollDice();
//                    players[turnOf].move(Dice.getDiceTotal());
//                    GameBoard.getTiles()[players[turnOf].getLocation()].onLand(players[turnOf]);
//                    if(Dice.getDoubles()){
//                        GoToJail.jailPlayer(players[turnOf]);
//                        passTurn();
//                        continue;
//                    }
//                }
//            }
//        }
//    }
//
//    public void startBank(){
//
//    }
//
//    public void endGame() {
//
//    }
//
//    public boolean isGameOver() {
//        return false;
//    }
//
//    private void initCards(){
//
//    }
//
//    private void passTurn(){
//        players[turnOf].setPlayerHasTurn(false);
//        turnOf++;
//        turnOf = turnOf % numOfPlayers;
//        players[turnOf].setPlayerHasTurn(true);
//    }
//
//    public static int getNumOfPlayers(){
//        return numOfPlayers;
//    }
//
//    public static Player[] getPlayers(){
//        return players;
//    }
//
//    public static Player currentTurn(){
//        return players[turnOf];
//    }
//
//    // ******************** new
//    /*
//    public static void setCurrentTurn(int id){
//        turnOf = id;
//    }
//    */
//    public Card getDrawnCard() {
//        return drawnCard;
//    }
//
//    // a method to update the players etc when someone gets bankrupted.
//
//    /*
//    public Host host;
//    private Gson gson;
//    private Gson playerGson;
//
//    public ArrayList<Player> players;
//    /*
//    public int cardsSelectedCount = 0;
//    public int militaryConflictCount = 0;
//
//    public boolean firstTurnOfAge = false;
//
//    public static ArrayList<PlayerCost> tradingCosts;
//    */
//
//
//    public ServerManager(){
//        gson = new Gson();
//
//        GsonBuilder gsonBuilder = new GsonBuilder();
//        gsonBuilder.registerTypeAdapter(Player.class, new PlayerDeserializer());
//        playerGson = gsonBuilder.create();
//
//
//        initData();
//    }
//
//    public void initData(){
//        //initialize lists - but we initialize everything in GameManager ????????
//        players = new ArrayList<>();
//    }
//
//    public void initServer(){
//        (new Thread(() -> {
//            this.host = new Host( this);
//            this.host.startServer();
//        })).start();
//    }
//
//    /*
//    public void initHouse(){
//        int index = (int) (Math.random() * allHouses.size());
//        Player newPlayer = new Player();
//        newPlayer.house = allHouses.get(index);
//        newPlayer.id = players.size();
//        players.add( newPlayer);
//        allHouses.remove( index);
//    }
//
//    public void populateHouses() {
//
//        Type houseListType = new TypeToken<List<House>>() {}.getType();
//        try {
//            InputStream n = getClass().getResourceAsStream("/assets/houses.json");
//            InputStreamReader in = new InputStreamReader( n, "UTF-8");
//            ArrayList<House> houses = gson.fromJson( in , houseListType );
//            for(House h: houses){
//                if(h.name.equalsIgnoreCase("tyrell"))//house buff tyrell
//                    h.addCoins(3);
//
//                if(h.name.equalsIgnoreCase("lannister")) //House buff lannister
//                    h.addCoins(3);
//
//                if(h.name.equalsIgnoreCase("targaryen")) //House buff targaryen
//                    h.militaryShields++;
//
//            }
//            System.out.println( "Houses found " + houses.size());
//            allHouses = houses;
//        } catch (Exception e) {
//            System.out.println("File not found exception");
//            e.printStackTrace();
//        }
//    }
//
//    public void populateAges(){
//
//        try{
//            Deck deck1 = new Deck( players.size(), 1);
//            Deck deck2= new Deck( players.size(), 2);
//            Deck deck3 = new Deck( players.size(), 3);
//
////            Deck deck1 = new Deck( 3, 1);
////            Deck deck2= new Deck( 3, 2);
////            Deck deck3 = new Deck( 3, 3);
//
//            Collections.shuffle(deck1.getCards());
//            Collections.shuffle(deck2.getCards());
//            Collections.shuffle(deck3.getCards());
//
//            ages.add( new Age( deck1));
//            ages.add( new Age( deck2));
//            ages.add( new Age( deck3));
//        } catch ( Exception e){
//            System.out.println( "EXCEPTION::::" + e.toString() + e.getStackTrace());
//        }
//
//    }
//
//    public void incrementAge(){
//        if( currentAge < 2) {
//            currentAge++;
//            for( int i = 0; i < host.clients.size(); i++){
//
//                JsonObject outOb = new JsonObject();
//                outOb.addProperty( "op_code", 6);
//                outOb.addProperty( "age", currentAge + 1);
//
//                host.sendRequest( i, outOb);
//            }
//            firstTurnOfAge = true;
//
//            playTurn();
//
//
//        } else {
//            //game ended
//            updateScoreboard();
//            for( int i = 0; i < host.clients.size(); i++){
//
//                JsonObject outOb = new JsonObject();
//                outOb.addProperty( "op_code", 7);
//
//                host.sendRequest( i, outOb);
//            }
//        }
//    }
//    public void changeSeason(){
//        int season = ((int)(Math.random() * 4)) + 1;
//        for( int i = 0; i < host.clients.size(); i++){
//
//            JsonObject outOb = new JsonObject();
//            outOb.addProperty( "op_code", 5);
//            outOb.addProperty( "season", season);
//
//            host.sendRequest( i, outOb);
//        }
//    }
//
//    public boolean shuffleCards(){
//        if( firstTurnOfAge){
//            ArrayList<Card> cards = ages.get( currentAge).getDeck().getCards();
//            int start = 0;
//            for( int i = 0; i < players.size(); i++){
//                players.get( i).cards = new ArrayList<>( cards.subList( start, start + 7));
//                start += 7;
//            }
//            return true;
//        } else {
//            if( ages.get( currentAge).getDeck().getDirection()){
//                ArrayList<Card> cardsTemp = players.get( 0).cards;
//
//                //proceed to next age
//                if( cardsTemp.size() == 1)
//                    return false;
//
//                ArrayList<Card> cardsTemp2 = null;
//                for( int i = 1; i < players.size(); i++){
//                    cardsTemp2 = players.get( i).cards;
//                    players.get( i).cards = cardsTemp;
//                    cardsTemp = cardsTemp2;
//                }
//                players.get( 0).cards = cardsTemp;
//                return true;
//            } else {
//                ArrayList<Card> cardsTemp = players.get( players.size() - 1).cards;
//
//                //proceed to next age
//                if( cardsTemp.size() == 1)
//                    return false;
//
//                ArrayList<Card> cardsTemp2 = null;
//                for( int i = players.size() - 2; i >= 0; i--){
//                    cardsTemp2 = players.get( i).cards;
//                    players.get( i).cards = cardsTemp;
//                    cardsTemp = cardsTemp2;
//                }
//                players.get( players.size() - 1).cards = cardsTemp;
//                return true;
//            }
//        }
//
//    }
//    */
//    public void playTurn(){
//
//    }
//
//    // SENDING STUFF THROUGH THREAD
//    /*
//    public void sendHouses(){
//        //updating trading costs for neighbors
//        for( PlayerCost p: tradingCosts){
//            players.get( p.playerId).house.coins += p.cost;
//            System.out.println( "Player cost: " + p.playerId + "cost: " + p.cost);
//        }
//        tradingCosts.clear();
//
//        for( int i = 0; i < host.clients.size(); i++){
//
//            JsonObject outOb = new JsonObject();
//            outOb.addProperty( "op_code", 3);
//            outOb.addProperty( "all_players", gson.toJson(this.players));
//
//            host.sendRequest( i, outOb);
//        }
//    }
//
//    public void sendScoreboard(){
//        for( int i = 0; i < host.clients.size(); i++){
//
//            JsonObject outOb = new JsonObject();
//            outOb.addProperty( "op_code", 4);
//            outOb.addProperty( "scoreboard", gson.toJson(this.scoreboard));
//
//            host.sendRequest( i, outOb);
//        }
//    }
//
//    public void startMilitaryConflict( Callback c){
//        int additionPoint = (currentAge * 2) + 1;
//        int starkFlag = 0;
//        for( int i = 0; i < players.size(); i++){
//            int currentMil = 0;
//            int shieldChange = 0;
//            int coinChange = 0;
//            int prevIndex = (i - 1) >= 0 ? (i - 1) : (players.size() - 1);
//            int nextIndex = (i + 1) < players.size() ? (i + 1) : 0;
//
//            if( players.get( i).house.militaryShields > players.get( nextIndex).house.militaryShields){
//                currentMil += additionPoint;
//                if( players.get(i).house.name.equalsIgnoreCase("white walkers") || players.get( i).house.name.equalsIgnoreCase("baratheon"))
//                    shieldChange++;
//                else if (players.get( i).house.name.equalsIgnoreCase("stark") || players.get( i).house.name.equalsIgnoreCase("greyjoy"))
//                    coinChange++;
//            }
//            else if( players.get( i).house.militaryShields < players.get( nextIndex).house.militaryShields)
//            {
//                currentMil --;
//                if (players.get( i).house.name.equalsIgnoreCase("white walkers") ||  players.get( i).house.name.equalsIgnoreCase("targaryen") || players.get( i).house.name.equalsIgnoreCase("baratheon"))
//                    shieldChange--;
//                else if (players.get( i).house.name.equalsIgnoreCase("tyrell") || players.get( i).house.name.equalsIgnoreCase("greyjoy"))
//                    coinChange--;
//                else if(players.get( i).house.name.equalsIgnoreCase("stark") && players.get( nextIndex).house.name.equalsIgnoreCase("lannister"))
//                    starkFlag=1;
//            }
//            if( players.get( i).house.militaryShields > players.get( prevIndex).house.militaryShields){
//                currentMil += additionPoint;
//                if( players.get(i).house.name.equalsIgnoreCase("white walkers") || players.get( i).house.name.equalsIgnoreCase("baratheon"))
//                    shieldChange++;
//                else if (players.get( i).house.name.equalsIgnoreCase("stark") || players.get( i).house.name.equalsIgnoreCase("greyjoy"))
//                    coinChange++;
//            }
//            else if( players.get( i).house.militaryShields < players.get( prevIndex).house.militaryShields)
//            {
//                currentMil--;
//                if (players.get( i).house.name.equalsIgnoreCase("white walkers") ||  players.get( i).house.name.equalsIgnoreCase("targaryen") || players.get( i).house.name.equalsIgnoreCase("baratheon"))
//                    shieldChange--;
//                else if (players.get( i).house.name.equalsIgnoreCase("tyrell") || players.get( i).house.name.equalsIgnoreCase("greyjoy"))
//                    coinChange--;
//                else if(players.get( i).house.name.equalsIgnoreCase("stark") && players.get( prevIndex).house.name.equalsIgnoreCase("lannister"))
//                    starkFlag=1;
//            }
//            players.get( i).currentMilitaryPoints += currentMil;
//            players.get( i).house.militaryShields += shieldChange; //baratheon and white walkers buff and nerfs
//            players.get( i).house.addCoins(coinChange);
//            if( starkFlag == 1)
//                players.get( i).house.militaryShields = 0;
//
//        }
//        updateScoreboard();
//        militaryConflictEnded( c);
//    }
//
//    public void militaryConflictEnded( Callback c){
//        for( int i = 0; i < host.clients.size(); i++){
//
//            JsonObject outOb = new JsonObject();
//            outOb.addProperty( "op_code", 8);
//
//            host.sendRequest( i, outOb);
//        }
//        new Timer().schedule(new TimerTask() {
//            @Override
//            public void run() {
//                c.onCallback();
//            }
//        }, 3000);
//
//    }
//    */
//
//    // UPDATE STUFF
//    public void updatePlayer( Player player, int id){
//        //this.players.set( id, player);
//    }
//
//    public void updateScoreboard() {
//        /*
//        for( int i = 0; i < players.size(); i++){
//            Player currPlayer = players.get( i);
//
//            scoreboard.scores.get( i).set( scoreboard.MILITARY_POINTS_INDEX, currPlayer.currentMilitaryPoints);
//            scoreboard.scores.get( i).set( scoreboard.COIN_POINTS_INDEX, currPlayer.calculateCoinPoints());
//            scoreboard.scores.get( i).set( scoreboard.WONDER_POINTS_INDEX, currPlayer.calculateWonderPoints());
//            scoreboard.scores.get( i).set( scoreboard.COMMERCE_POINTS_INDEX, currPlayer.calculateCommercePoints());
//            scoreboard.scores.get( i).set( scoreboard.SCIENCE_POINTS_INDEX, currPlayer.calculateSciencePoints());
//            scoreboard.scores.get( i).set( scoreboard.CIVIC_POINTS_INDEX, currPlayer.calculateCivicPoints());
//            scoreboard.scores.get( i).set( scoreboard.VICTORY_POINTS_INDEX,
//                    currPlayer.currentMilitaryPoints + currPlayer.calculateVictoryPoints());
//        }
//
//        sendScoreboard();
//
//
//    }
//
//    public void startGame(){
//        /*
////        if( !host.requestsAcknowledged())
////            return;
//        host.isReceiving = false;
//
//        for( int i = 0; i < host.clients.size(); i++){
//
//            JsonObject outOb = new JsonObject();
//            outOb.addProperty( "op_code", 2);
//
//            host.sendRequest( i, outOb);
//        }
//
//         */
//    }
//
//    public void viewInitialized(){
//        /*
//        cardsSelectedCount++;
//        if (cardsSelectedCount >= players.size()){
//            cardsSelectedCount = 0;
//
//            populateAges();
//            scoreboard = new Scoreboard( players.size());
//            incrementAge();
//        }
//
//
//    }
//
//    public interface Callback{
//        public void onCallback();
//    }
//}
//*/
////        class UpdateSeason extends TimerTask {
////            public void run() {
////                changeSeason();
////            }
////        }
////        Timer timer = new Timer();
////        timer.schedule(new UpdateSeason(), 0, 3000);
