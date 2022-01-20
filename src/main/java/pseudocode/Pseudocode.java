package pseudocode;

import java.util.ArrayList;

public class Pseudocode {
    //the deck of cards to use (standard deck)
    public static ArrayList<CardP> cardDeck;

    //hands for player and dealer respectively
    public static ArrayList<CardP> playerHand;
    public static ArrayList<CardP> dealerHand;

    public static void initCards() {
        //reset ArrayList
        //create the cards and add them to the ArrayList
        //loop through suits and then loop through cards
    }

    public static CardP pickCard() {
        //randomly pick a card from the ArrayList, and then remove it (to prevent duplicates)
        return new CardP(null, null);
    }

    public static void pickPlayerChoice() {
        //allows the player to pick an option
        //using ActionChoiceP enum
    }
}
