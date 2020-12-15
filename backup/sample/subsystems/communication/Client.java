package sample.subsystems.communication;

import sample.subsystems.GameLogic.*;
import sample.subsystems.Controller.*;
// import sample.subsystems.UserInterface.Monopoly.src.sample.*;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;

public class Client {
    public GameManager gameManager;

    public String serverAddress;

    public Socket socket;
    public BufferedReader in;
    public PrintWriter out;

    public int id = -1;

    private Gson gson;
    private Gson playerGson;

    public Client(String server, GameManager gameManager) {
        this.gameManager = gameManager;
        serverAddress = server;
        gson = new Gson();

        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Player.class, new PlayerDeserializer());
        playerGson = gsonBuilder.create();
    }
    public void startClient(){
        initClient();
        connectClient();
    }

    public void initClient() {
        try {
            if (serverAddress == null)
                System.exit(1);

            serverAddress = serverAddress.trim();
            if (serverAddress.length() == 0)
            {
                System.out.println("Server IP Address or Name can't be blank.");
                initClient();
                return;
            }
            System.out.println("Connecting to server:" + serverAddress);

            // create socket
            InetAddress inetAddress = InetAddress.getByName(serverAddress);
            if (!inetAddress.isReachable(15000))
            {
                System.out.println("Unable to connect to server.");
                System.exit(1);
            }
            initPortNo();
        } catch (SocketException e) {
            System.out.println("Socket Exception: " + e);
            return;
        } catch (Exception e) {
            System.out.println( "Exception: " + e);
            return;
        }
    }

    public void initPortNo() {
        try {
            String portNo = Constants.CommunicationConstants.PORT_NO;

            socket = new Socket(serverAddress, Integer.parseInt( portNo));
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);

        } catch (IOException e) {
            System.out.println("IO Exception:\n" + e);
            return;
        }
    }

    public void acknowledgeRequest(){
        JsonObject outOb = new JsonObject();
        outOb.addProperty( "op_code", -1);
        outOb.addProperty( "player_id", id);
        out.println( gson.toJson( outOb));
    }

    public void sendRequest( JsonObject request){
        try{
            out.println( gson.toJson( request));
//            System.out.println( "Sent from client" + gson.toJson( request));
        } catch (Exception e) {
            System.out.println("Exception on client");
            System.out.println( e.getStackTrace()[0].getLineNumber()  + e.toString());
        }
    }

    public void connectClient() {
        try {
            //initiate relation with server
            JsonObject ob = new JsonObject();
            ob.addProperty("op_code", 0);
            out.println( gson.toJson( ob));

            while (true) {
                String response = in.readLine();
//                System.out.println("data received on client: " + response);

                JsonObject res = gson.fromJson( response, JsonObject.class);

                int op = Integer.parseInt( res.get( "op_code").getAsString());
                switch ( op) {
                    case -1: { //error from server
                        String error = res.get("error").getAsString();
                        JOptionPane.showMessageDialog(null, "" + error, "Error", JOptionPane.INFORMATION_MESSAGE);

                        acknowledgeRequest();
                        break;
                    }
                    case 0: { //received player details
                        // id = Integer.parseInt( res.get( "player_id").getAsString());

                        JsonObject req = new JsonObject();
                        req.addProperty("op_code", 1);
                        out.println( gson.toJson( req));
                        break;

                    } case 1: { //receive players first time
                        Type stringArraylist = new TypeToken<List<String>>() {}.getType();
                        ArrayList<String> players = gson.fromJson( res.get("all_players").getAsString(), stringArraylist);
                        for (int i = 4 - players.size(); i < 4; i++) {
                            players.add("");
                        }
                        gameManager.initGame(players.size(), players.get(0), players.get(1), players.get(2), players.get(3));
                        System.out.println( "Players updated: " + players.toString());

                        // initialize pawns, give them to players (or other way around), put them in starting point
                        ArrayList<Pawn> pawns = new ArrayList<>();
                        /*
                        houses.add( engine.getCurrentPlayer().house.name);
                        for( Player a: engine.players){
                            if( a != engine.getCurrentPlayer())
                                houses.add( a.house.name);
                        }
                        */
                        // WaitScreenController.updateHouses( houses);
                        // NewGameController?

                        acknowledgeRequest();
                        break;
                    } case 2: { //start game
                        System.out.println( "SERVER SAID: START GAME");
                        // WaitScreenController.showMainScreen();
                        acknowledgeRequest();
                        break;
                    } case 3: { // update players for when someone leaves. Below stuff not ours
                        /*
                        Type playersListType = new TypeToken<List<Player>>() {}.getType();
                        ArrayList<Player> players = playerGson.fromJson( res.get("all_players").getAsString(), playersListType );

                        engine.players = players;
                        engine.getCurrentPlayer().id = id;
                        engine.updateNeighbors();

                        // call something to update all cards, all player reaources etc.
                        PlayScreenController.updateDeck( engine.getCurrentPlayer().cards);

                        acknowledgeRequest();
                        break;
                         */
                    } case 4: { // update a players belongings ( money + properties )
                        /*
                        Scoreboard scoreboard = gson.fromJson( res.get("scoreboard").getAsString(), Scoreboard.class);
                        this.engine.updateScoreboard( scoreboard);

                        acknowledgeRequest();
                        break;

                         */
                    } case 5: { // update chat box
                        /*
                        int season = Integer.parseInt( res.get("season").getAsString());
                        this.engine.updateSeason( season);

                        acknowledgeRequest();
                        break;

                         */
                    } case 6: { // audition?
                        /*
                        int age = Integer.parseInt( res.get("age").getAsString());
                        this.engine.updateAge( age);

                        acknowledgeRequest();
                        break;

                         */
                    } case 7: { // game ended
                        gameManager.endGame();
                        break;
                    } case 8: { // ?????????

                        break;
                    }
                    default: {
                        System.out.println( "Client: Invalid opcode");
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Exception on client");
            System.out.println( e.getStackTrace()[0].getLineNumber()  + e.toString());
        }
    }

    public void quitGame(){
        try{
            if( socket != null)
                socket.close();
            if( in != null)
                in.close();
            if( out != null)
                out.close();
        } catch ( Exception e){
            System.out.println("Client not closed");
        }
    }
}
