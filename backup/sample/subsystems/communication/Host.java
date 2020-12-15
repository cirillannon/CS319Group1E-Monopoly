package sample.subsystems.communication;

import sample.subsystems.GameLogic.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
// import com.sun.org.apache.xerces.internal.util.SynchronizedSymbolTable;
// import jdk.nashorn.internal.runtime.ECMAException;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

public class Host {
    public ServerManager serverManager;

    public ArrayList<ClientThread> clients;
    public ServerSocket serverSocket;
    public boolean isReceiving = true;
    public String serverIP="";

    private Gson gson;
    private Gson playerGson;


    private HashMap<Integer, Integer> requests;

    public Host(ServerManager controller){
        this.serverManager = controller;
        gson = new Gson();
        clients = new ArrayList<>();
        requests = new HashMap<>();

        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Player.class, new PlayerDeserializer());
        playerGson = gsonBuilder.create();
    }



    public void startServer() {
        String port = constants.PORT_NO;
        try {

            int portNo = Integer.valueOf(port);
            serverSocket = new ServerSocket(portNo, 0, InetAddress.getLocalHost());
            System.out.println(serverSocket);

            System.out.println(serverSocket.getInetAddress().getHostName() + ":"
                    + serverSocket.getLocalPort() + " : " + serverSocket.getInetAddress().getHostAddress());

            serverIP = "" + serverSocket.getInetAddress().getHostAddress();

            addCurrentClient( "" + serverSocket.getInetAddress().getHostAddress());


            while (isReceiving ) {
                Socket socket = serverSocket.accept();
                if( clients.size() < 7) {
                    clients.add(new ClientThread(clients.size(), socket, this));

                    serverManager.initHouse();

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
            Main.gameEngine.client = new GameClient( ip, Main.gameEngine);
            Main.gameEngine.client.startClient();
        })).start();

//        addSampleClients( ip);
    }

    public void addSampleClients( String ip){
        (new Thread(() -> {
            GameClient client = new GameClient( ip, new GameEngine());
            client.startClient();
        })).start();
        (new Thread(() -> {
            GameClient client = new GameClient( ip, new GameEngine());
            client.startClient();
        })).start();
    }


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
            case 1: { //update players on all clients during wait
                serverManager.sendHouseJoined();
                break;
            } case 2: { //card selected
                serverManager.cardsSelectedCount++;
                Player player = playerGson.fromJson( request.get("player").getAsString(), Player.class );
                serverManager.updatePlayer( player, id);
                if( serverManager.cardsSelectedCount >= (clients.size())) {
                    System.out.println("Play next turn");
                    serverManager.playTurn();
                }
                break;
            } case 3: { //playScreen initialized
                serverManager.viewInitialized();


                break;
            } case 4: { //crisis card played
                serverManager.militaryConflictCount++;
                serverManager.cardsSelectedCount++;
                Player player = playerGson.fromJson( request.get("player").getAsString(), Player.class );
                serverManager.updatePlayer( player, id);
                if( serverManager.cardsSelectedCount >= (clients.size())) {
                    System.out.println("Play next turn");
                    serverManager.playTurn();
                }
                break;
            } case 5:{ //trading done
                int playerId = request.get("player_id").getAsInt();
                int cost = request.get("cost").getAsInt();
                serverManager.tradingCosts.add( new PlayerCost( playerId, cost));
                break;
            }
            default:
                System.out.println( "Invalid opcode");
        }
    }


}
