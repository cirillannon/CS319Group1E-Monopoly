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
        try{
            for( JsonElement e: getProps){
                properties.add(g.fromJson(e, Property.class));
            }
            player.properties = properties;
        } catch ( Exception e){
            player.properties = new ArrayList<>();
        }

        JsonArray getCards = je.getAsJsonObject().getAsJsonArray("cards");
        try{
            for( JsonElement e: getCards){
                cards.add(g.fromJson(e, Card.class));
            }
            player.cards = cards;
        } catch ( Exception e){
            player.cards = new ArrayList<>();
        }


        return player;
    }
}