package communication;

import Controller.GameManager;
import GameLogic.*;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import java.util.*;
import java.io.*;
import java.lang.reflect.Type;

public class ServerManager {

    public Server server;

    private Gson gson;
    private Gson playerGson;

    private static int numOfPlayers;
    private ArrayList<Player> players; // max 4 players with playerID's between 0-3
    private static GameBoard gameBoard;
    private static String[] colors = {"orange", "yellow", "red", "purple"};
    private static String[] names = {"orange", "yellow", "red", "purple"};

    private int turn;

    Scanner scan = new Scanner(System.in);

    // constructor
    public ServerManager() {
        System.out.println("initializing servermanager");

        gson = new Gson();

        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Player.class, new PlayerDeserializer());
        playerGson = gsonBuilder.create();
        gameBoard = null;
        initData();
        turn = 0;
    }

    public void initData() {
        System.out.println("in initData");
        players = new ArrayList<>();
    }

    public void initPlayer(){
        Player newPlayer = new Player(names[numOfPlayers], colors[numOfPlayers], numOfPlayers);
        players.add( newPlayer);
        numOfPlayers++;
    }

    public void initServer() {
        System.out.println("in servermanager initServer");
        (new Thread(() -> {
            this.server = new Server(this);
            this.server.startServer();
        })).start();
    }

    public void updatePlayers(ArrayList<Player> players) {
        this.players = players;
        System.out.println("server manager updatePlayers");
        for (int i = 0; i < server.clients.size(); i++) {
            JsonObject outOb = new JsonObject();
            outOb.addProperty("op_code", 3);
            outOb.addProperty("all_players", gson.toJson(this.players));
            outOb.addProperty("turn", gson.toJson(turn));
            server.sendRequest(i, outOb);
        }
        turn++;
    }

    public void updatePlayer() {
        for (int i = 0; i < server.clients.size(); i++) {
            JsonObject outOb = new JsonObject();
            outOb.addProperty("op_code", 1);
            outOb.addProperty("all_players", gson.toJson(this.players));
            server.sendRequest(i, outOb);
        }
    }

    public void startGame(){
//        if( !host.requestsAcknowledged())
//            return;
        server.isReceiving = false;
        System.out.println("server manager startGame");

        for( int i = 0; i < server.clients.size(); i++){

            JsonObject outOb = new JsonObject();
            outOb.addProperty( "op_code", 2);

            server.sendRequest( i, outOb);
        }
    }

    public void viewInitialized(){ // call GameBoard.init?
        System.out.println("server manager viewInitialized");
        gameBoard = GameBoard.initGameBoard();
    }

    public interface Callback{
        public void onCallback();
    }

    public int getNumOfPlayers() {
        return numOfPlayers;
    }
}
