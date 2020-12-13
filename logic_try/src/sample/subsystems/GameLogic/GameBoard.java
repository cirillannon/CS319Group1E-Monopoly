package sample.subsystems.GameLogic;

import java.util.ArrayList;
import java.util.Collections;

public class GameBoard {

    private static ArrayList<Card> chanceCards;
    private static ArrayList<Card> communityChestCards;
    private static Tile[] tiles; // index 0 is empty, we have tiles between 1-40

    public void update() {

    }

    public static void initBoard() {
        initCards();
        System.out.println("cards initialized");
        initTiles();
        System.out.println("tiles initialized");
    }

    public static void initCards(){
        for (int i = 0; i < 20; i++){
            chanceCards = new ArrayList<Card>();
            chanceCards.add(new ChanceCard(Constants.GameBoardConstants.CHANCE_CARD_DESCRIPTIONS[i], (i + 1)));
        }
        Collections.shuffle(chanceCards);

        for(int i = 0; i < 16; i++){
            communityChestCards = new ArrayList<Card>();
            communityChestCards.add(new CommunityChestCard(Constants.GameBoardConstants.COMMUNITY_CHEST_CARD_CONSTANTS[i],(21 +i) ));
        }
        Collections.shuffle(communityChestCards);
    }

    public static ArrayList<Card> getChanceCards(){
        return chanceCards;
    }

    public static ArrayList<Card> getCommunityChestCards(){
        return communityChestCards;
    }

    public static Tile[] getTiles(){
        return tiles;
    }

    public static Tile getTile( int location) { return tiles[location]; }

    public static void initTiles(){
        tiles = new Tile[41];

        tiles[0] = null;
        tiles[1] = new GO();
        tiles[2] = new ColoredProperty(60, 2, "Old Kent Road",
                2, "Brown", 10,30,90,
                160,250,50,50,30);
        tiles[3] = new DrawCardTile("Community Chest",3);
        tiles[4] = new ColoredProperty(60, 4, "WhiteChapel Road",
                4, "Brown", 20,60,180,
                320,450,50,50,30);
        tiles[5] = new IncomeTax(5);
        tiles[6] = new Station(200, 25, "Kings Cross Station", 6);
        tiles[7] = new ColoredProperty(100, 6, "The Angel, Islington",
                7, "Light Blue", 30,90,270,
                400,550,50,50,50);
        tiles[8] = new DrawCardTile("Chance", 8);
        tiles[9] = new ColoredProperty(100, 6, "Euston Road",
                9, "Light Blue", 30,90,270,
                400,550,50,50,50);
        tiles[10] = new ColoredProperty(120, 8, "Pentonville Road",
                10, "Light Blue", 40,100,300,
                450,600,50,50,60);
        tiles[11] = new Jail();
        tiles[12] = new ColoredProperty(140, 10, "Pall Mall",
                12, "Pink", 50,150,450,
                625,750,100,100,70);
        tiles[13] = new ElectricCompany(150,1);
        tiles[14] = new ColoredProperty(140, 10, "Whitehall",
                14, "Pink", 50,150,450,
                625,750,100,100,70);
        tiles[15] = new ColoredProperty(160, 12, "Northumberl'd Avenue",
                15, "Pink", 60,180,500,
                700,900,100,100,80);
        tiles[16] = new Station(200, 25, "Marylebone Station", 16);
        tiles[17] = new ColoredProperty(180, 14, "Bow Street",
                17, "Orange", 70,200,550,
                750,950,100,100,90);
        tiles[18] = new DrawCardTile("Community Chest",18);
        tiles[19] = new ColoredProperty(180, 14, "Marlborough Street",
                19, "Orange", 70,200,550,
                750,950,100,100,90);
        tiles[20] = new ColoredProperty(200, 16, "Vine Street",
                20, "Orange", 80,220,600,
                800,1000,100,100,100);
        tiles[21] = new FreeParking();
        tiles[22] = new ColoredProperty(220, 18, "The Strand",
                22, "Red", 90,250,700,
                875,1050,150,150,110);
        tiles[23] = new DrawCardTile("Chance Card",23);
        tiles[24] = new ColoredProperty(220, 18, "Fleet Street",
                24, "Red", 90,250,700,
                875,1050,150,150,110);
        tiles[25] = new ColoredProperty(240, 20, "Trafalgar Square",
                25, "Red", 100,300,750,
                925,1100,150,150,120);
        tiles[26] = new Station(200, 25, "Fenchurch St. Station", 26);
        tiles[27] = new ColoredProperty(260, 22, "Leicester Square",
                27, "Yellow", 110,330,800,
                975,1150,150,150,130);
        tiles[28] = new ColoredProperty(260, 22, "Coventry Street",
                28, "Yellow", 110,330,800,
                975,1150,150,150,130);
        tiles[29] = new Waterworks(150, 1, 29);
        tiles[30] = new ColoredProperty(280, 22, "Coventry Street",
                30, "Yellow", 120,360,850,
                1025,1200,150,150,140);
        tiles[31] = new GoToJail();
        tiles[32] = new ColoredProperty(300, 26, "Regent Street",
                32, "Green", 130,390,900,
                1100,1275,200,200,150);
        tiles[33] = new ColoredProperty(300, 26, "Oxford Street",
                33, "Green", 130,390,900,
                1100,1275,200,200,150);
        tiles[34] = new DrawCardTile("Community Chest",34);
        tiles[35] = new ColoredProperty(320, 28, "Bond Street",
                35, "Green", 150,450,1000,
                1200,1400,200,200,160);
        tiles[36] = new Station(200, 25, "Liverpool St. Station", 36);
        tiles[37] = new DrawCardTile("Chance",37);
        tiles[38] = new ColoredProperty(350, 35, "Park Lane",
                38, "Dark Blue", 175,500,1100,
                1300,1500,200,200,175);
        tiles[40] = new ColoredProperty(400, 50, "Mayfair",
                40, "Dark Blue", 200,600,1400,
                1700,2000,200,200,200);
    }

    public static ColoredProperty[] getPropertiesOfColor( String color) {
        ColoredProperty[] neighborhood;
        if (color == "Brown" || color == "Dark Blue") {
            neighborhood = new ColoredProperty[2];
        } else {
            neighborhood = new ColoredProperty[3];
        }
        int count = 0;
        for (Tile tile : tiles) {
            if (tile instanceof ColoredProperty && ((ColoredProperty) tile).getColor() == color){
                neighborhood[count] = (ColoredProperty) tile;
                count++;
            }
        }
        return neighborhood;
    }
}
