package communication;

import GameLogic.*;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.*;

public class ServerManager {

    public Server server;

    private Gson gson;
    private Gson playerGson;

    private static int numOfPlayers;
    private static Player[] players; // max 4 players with playerID's between 0-3

    Scanner scan = new Scanner(System.in);

    // constructor
    public ServerManager() {
        gson = new Gson();

        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Player.class, new PlayerDeserializer());
        playerGson = gsonBuilder.create();

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
        for (int i = 0; i < host.clients.size(); i++) {
            JsonObject outOb = new JsonObject();
            outOb.addProperty("op_code", 1);
            outOb.addProperty("all_players", gson.toJson(this.players));
            host.sendRequest(i, outOb);
        }
    }
}