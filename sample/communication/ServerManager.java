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
    private static Player[] players; // max 4 players with playerID's between 0-3
    private static GameBoard gameBoard;

    Scanner scan = new Scanner(System.in);

    // constructor
    public ServerManager() {
        gson = new Gson();

        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Player.class, new PlayerDeserializer());
        playerGson = gsonBuilder.create();
        gameBoard = null;
        initData();
    }

    public void initData() {

    }

    public void initServer() {
        (new Thread(() -> {
            this.server = new Server(this);
            this.server.startServer();
        })).start();
    }

    public void sendBalanceUpdated() {
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

        for( int i = 0; i < server.clients.size(); i++){

            JsonObject outOb = new JsonObject();
            outOb.addProperty( "op_code", 2);

            server.sendRequest( i, outOb);
        }
    }

    public void viewInitialized(){ // call GameBoard.init?
        gameBoard = GameBoard.initGameBoard();
    }

    public interface Callback{
        public void onCallback();
    }
}
