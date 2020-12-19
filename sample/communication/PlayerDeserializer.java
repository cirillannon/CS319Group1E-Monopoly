package communication;

import Controller.*;
import GameLogic.*;

import com.google.gson.*;
// import sun.security.ec.ECDHKeyAgreement;

import javax.swing.*;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class PlayerDeserializer implements JsonDeserializer<Player>
{
    private static final String CLASSNAME = "CLASSNAME";
    private static final String INSTANCE  = "INSTANCE";

//    @Override
//    public JsonElement serialize(ArrayList<Player> src, Type typeOfSrc,
//                                 JsonSerializationContext context) {
//
//        System.out.println("Card serialized");
//
//        JsonObject retValue = new JsonObject();
//        String className = src.getClass().getName();
//        retValue.addProperty(CLASSNAME, className);
//        JsonElement elem = context.serialize(src);
//        retValue.add(INSTANCE, elem);
//        return retValue;
//    }

    @Override
    public Player deserialize(JsonElement je, Type t, JsonDeserializationContext jdc)
            throws JsonParseException
    {

        Gson g = new Gson();
        JsonObject object = je.getAsJsonObject();
        Player player = g.fromJson( object, Player.class);

        ArrayList<Card> cards = new ArrayList<>();
        ArrayList<Property> properties = new ArrayList<>();
        JsonArray getProps = je.getAsJsonObject().getAsJsonArray("properties");
        for( JsonElement e: jo){
            properties.add(g.fromJson(e, Property.class))
        }
        player.properties = properties;
        getCards = je.getAsJsonObject().getAsJsonArray("cards");
        try{
            ArrayList<Card> playedCards = new ArrayList<>();
            jo = je.getAsJsonObject().getAsJsonArray("playedCards");
            for( JsonElement e: jo){
                String type = e.getAsJsonObject().get("cardType").getAsString();
                switch ( type){
                    case "resource":
                        playedCards.add( g.fromJson( e, Resource.class));
                        break;
                    case "military":
                        playedCards.add( g.fromJson( e, Military.class));
                        break;
                    case "science":
                        playedCards.add( g.fromJson( e, Science.class));
                        break;
                    case "civic":
                        playedCards.add( g.fromJson( e, Civic.class));
                        break;
                    case "commerce":
                        playedCards.add( g.fromJson( e, Commerce.class));
                        break;
                    case "crisis":
                        playedCards.add( g.fromJson( e, Crisis.class));
                        break;
                    default:
                        System.out.println("Invalid card");
                        break;

                }
            }
            player.playedCards = playedCards;
        } catch ( Exception e){
            player.playedCards = new ArrayList<>();
        }


        return player;
    }
}