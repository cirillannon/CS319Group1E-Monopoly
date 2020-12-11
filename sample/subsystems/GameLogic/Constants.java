package sample.subsystems.GameLogic;

public class Constants {

  public static class BankConstants {
    public static final int NO_OF_HOUSES = 32;
    public static final int NO_OF_HOTELS = 12;
  }

  public static class GameBoardConstants {
    public static final String[] CHANCE_CARD_DESCRIPTIONS = {
            "Advance to Go (Collect $200)",
            "Advance to Trafalgar Square — If you pass Go, collect $200",
            "Advance to Pall Mall – If you pass Go, collect $200",
            "Advance token to nearest Utility. If unowned, you may buy it from the Bank. " +
                    "If owned, throw dice and pay the owner a total ten times the amount thrown.",
            "Advance token to the nearest Station and pay the owner twice the rental to which he/she {he} is otherwise entitled." +
                    " If Station is unowned, you may buy it from the Bank.",
            "Bank pays you dividend of $50",
            "Get Out of Jail Free",
            "Go Back 3 Spaces",
            "Go to Jail – Go directly to Jail–Do not pass Go, do not collect $200",
            "Make general repairs on all your property – For each house pay $25 – For each hotel $100",
            "Pay poor tax of $15",
            "Take a trip to Kings Cross Station – If you pass Go, collect $200",
            "Take a walk on the Mayfair – Advance token to Mayfair",
            "You have been elected Chairman of the Board – Pay each player $50",
            "Your building and loan matures — Collect $150",
            "You have won a crossword competition - Collect $100",
            "Organize a festival on the property of your choice — Property’s rent and value are increased by 25%",
            "Organize this year Olympics on the property of your choice — Property’s rent and value are increased by 50%",
            "Set a property on fire — Property’s rent and value are decreased by 25% permanently",
            "Cause an earthquake to damage a property—Property’s rent and value are decreased by 50% permanently"};

    public static final String[] COMMUNITY_CHEST_CARD_CONSTANTS = {
            "Advance to Go (Collect $200)",
            "Bank error in your favor—Collect $200",
            "Doctor's fee — Pay $50",
            "From sale of stock you get $50",
            "Get Out of Jail Free",
            "Go to Jail – Go directly to jail – Do not pass Go–Do not collect $200",
            "Grand Opera Night — Collect $50 from every player for opening night seats",
            "Holiday Fund matures — Receive $100",
            "Income tax refund–Collect $20",
            "It is your birthday—Collect $10",
            "Life insurance matures–Collect $100",
            "Pay hospital fees of $100",
            "Pay school fees of $150",
            "Receive $25 consultancy fee",
            "You are assessed for street repairs – $40 per house – $115 per hotel",
            "You have won second prize in a beauty contest – Collect $10",
            "You inherit $100"};

    public String[] getChanceCardDescriptions() {
      return CHANCE_CARD_DESCRIPTIONS;
    }
  }
  public static class TileConstants {
    public static final int SALARY = 200;
    public static final int INCOME_TAX = -200;
    public static final int SUPER_TAX = -100;
  }


  public class PlayerConstants {
    public static final int STARTING_AMOUNT = 1500;
  }

  public static class CommunicationConstants {
    public static String PORT_NO="1122";
  }
}