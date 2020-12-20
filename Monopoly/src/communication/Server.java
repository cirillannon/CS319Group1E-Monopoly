package communication;

import Controller.GameManager;
import GameLogic.*;
import UserInterface.Monopoly;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
// import com.sun.org.apache.xerces.internal.util.SynchronizedSymbolTable;
// import jdk.nashorn.internal.runtime.ECMAException;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Server {
    public ServerManager serverManager;

    public ArrayList<ClientThread> clients;
    public ServerSocket serverSocket;
    // taking players in
    public boolean isReceiving = true;
    public String serverIP="";

    private Gson gson;
    private Gson playerGson;


    private HashMap<Integer, Integer> requests;

    public Server(ServerManager controller){
        System.out.println("in Server constructor");
        this.serverManager = controller;
        gson = new Gson();
        clients = new ArrayList<>();
        requests = new HashMap<>();

        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Player.class, new PlayerDeserializer());
        playerGson = gsonBuilder.create();
    }



    public void startServer() {
        String port = Constants.CommunicationConstants.PORT_NO;

        try {
            System.out.println("in Server startServer");
            int portNo = Integer.valueOf(port);
            serverSocket = new ServerSocket(portNo, 0, InetAddress.getLocalHost());
            System.out.println(serverSocket);

            System.out.println(serverSocket.getInetAddress().getHostName() + ":"
                    + serverSocket.getLocalPort() + " : " + serverSocket.getInetAddress().getHostAddress());

            serverIP = "" + serverSocket.getInetAddress().getHostAddress();

            addCurrentClient( "" + serverSocket.getInetAddress().getHostAddress());

            while (isReceiving ) {
                Socket socket = serverSocket.accept();
                // don't start until 4 players??
                if( clients.size() < 4) {
                    clients.add(new ClientThread(clients.size(), socket, this));
                    serverManager.initPlayer();
                }
                else {
                    isReceiving = false;
                    PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

                    JsonObject outOb = new JsonObject();
                    outOb.addProperty( "op_code", -1);
                    outOb.addProperty( "error", "Player limit reached");
                    out.println( gson.toJson(outOb));
                }
            }
        } catch (IOException e) {
            System.out.println("IO Exception:" + e);
        } catch (NumberFormatException e) {
            System.out.println("Number Format Exception:" + e);
        }
    }

    public void addCurrentClient( String ip) {
        (new Thread(() -> {
            Monopoly.gameManager.client = new Client( ip, Monopoly.gameManager);
            Monopoly.gameManager.client.startClient();
        })).start();

//        addSampleClients( ip);
    }
/*
    public void addSampleClients( String ip){
        (new Thread(() -> {
            Client client = new Client( ip, new GameManager());
            client.startClient();
        })).start();
        (new Thread(() -> {
            Client client = new Client( ip, new GameManager());
            client.startClient();
        })).start();
    }
*/

    public void sendError( int id, String error){
        JsonObject outOb = new JsonObject();
        outOb.addProperty( "op_code", -1);
        outOb.addProperty( "error", error);
        sendRequest( id, outOb);
    }

    public void sendRequest( int id, JsonObject request){
        requests.put( id, 1);
        this.clients.get( id).sendRequestToClient( request);
    }

    public boolean requestsAcknowledged(){
        if( requests.isEmpty())
            return true;
        return false;
    }

    public void quitHost(){
        try {
            clients = null;
            if( serverSocket != null){
                serverSocket.close();
            }
        } catch (Exception e) {
            System.out.println("Exception killing server");
        }

    }

    public void receiveRequest( int id, JsonObject request){

        if( requests.containsKey( id))
            requests.remove( id);

        int op = Integer.parseInt( request.get( "op_code").getAsString());
        System.out.println("op in server: " + op);
        switch ( op) {
            case -1: {
                System.out.println( "Request acknowledged");
                break;
            }
            case 0: { //client identified
                JsonObject outOb = new JsonObject();
                outOb.addProperty( "op_code", 0);
                outOb.addProperty( "player_id", id);

                sendRequest( id, outOb);
                break;
            }
            case 1: { //update players on all clients during wait ??
                serverManager.updatePlayer();
                if (serverManager.getNumOfPlayers() >= 2) {
                    serverManager.startGame();
                }
                break;
            } case 2: { //update players and their properties
//                serverManager.cardsSelectedCount++;
//                Player player = playerGson.fromJson( request.get("player").getAsString(), Player.class );
//                serverManager.updatePlayer( player, id);
//                if( serverManager.cardsSelectedCount >= (clients.size())) {
//                    System.out.println("Play next turn");
//                    serverManager.playTurn();
//                }
                break;
            } case 3: { //playScreen initialized
                Type playersListType = new TypeToken<List<Player>>() {}.getType();
                ArrayList<Player> players = gson.fromJson( request.get("all_players").getAsString(), playersListType );
                serverManager.updatePlayers(players);
                break;
            } case 4: { // players updated
//                serverManager.militaryConflictCount++;
//                serverManager.cardsSelectedCount++;
//                Player player = playerGson.fromJson( request.get("player").getAsString(), Player.class );
//                serverManager.updatePlayer( player, id);
//                if( serverManager.cardsSelectedCount >= (clients.size())) {
//                    System.out.println("Play next turn");
//                    serverManager.playTurn();
//                }
                break;
            }
            default:
                System.out.println( "Invalid opcode");
        }
    }


}
