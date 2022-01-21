package card;

import java.util.ArrayList;

public class Card {
    /**
     * The integer value of the card
     */
    private int cardValue;

    /**
     * The suit of the card
     */
    public Suit cardSuit;

    /**
     * The card value of the card
     */
    public CardValue card;

    /**
     * It's a constructor
     * @param suit What suit the card will be
     * @param card What card value the card will have
     */
    public Card(Suit suit, CardValue card) {
        this.cardSuit = suit;
        this.card = card;
        this.cardValue = card.cardValue;
    }

    /**
     * Draw a single card
     */
    public void drawCard() {
        char c = cardSuit.character;
        String vv = card.displayString;

        System.out.println("__________");
        System.out.println("|" + c + "       |");
        System.out.println("|        |");
        System.out.println("|   " + vv + "   |");
        System.out.println("|        |");
        System.out.println("|       " + c + "|");
        System.out.println("¯¯¯¯¯¯¯¯¯¯");

        //design template
        //__________
        //|c       |
        //|        |
        //|   vv   |
        //|        |
        //|       c|
        //¯¯¯¯¯¯¯¯¯¯
    }

    /**
     * Returns a String that actually tells you what the card is
     * @return String - Returns the string
     */
    @Override
    public String toString() {
        return card.normalName + " of " + cardSuit.normalName + "s.";
    }

    /**
     * Getter method for the card value
     * @return Returns the cards value
     */
    public int getCardValue() {
        return cardValue;
    }

    /**
     * Setter method for the card value. Prevents you from setting the value of any card to any value
     * @param newValue The value to set it to
     */
    public void setCardValue(int newValue) {
        try {
            if (card == CardValue.ACE) { //check if the method was called on an Ace
                if (newValue == 1 || newValue == 11) { //checks if the new value is either a 1 or 11
                    cardValue = newValue;
                } else {
                    throw new Exception("Can only set Ace cardValue to 1 or 11"); //throws exception
                }
            } else {
                throw new Exception("Can only set card value for Ace cards"); //throws exception
            }
        } catch (Exception e) {
            e.printStackTrace(); //print stack trace so you know where the error is
        }
    }

    /**
     * Set the value of all Aces in the given hand
     * @param hand The hand to use
     * @param newValue The value to set the Aces to
     */
    public static void setAcesValue(ArrayList<Card> hand, int newValue) {
        for (Card e : hand) {
            if (e.card == CardValue.ACE) {
                e.setCardValue(newValue);
            }
        }
    }
}
