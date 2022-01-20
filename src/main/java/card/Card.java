package card;

import java.util.ArrayList;

public class Card {
    private int cardValue;
    public Suit cardSuit;
    public CardValue card;

    public Card(Suit suit, CardValue card) {
        this.cardSuit = suit;
        this.card = card;
        this.cardValue = card.cardValue;
    }

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

    @Override
    public String toString() {
        return card.normalName + " of " + cardSuit.normalName + "s.";
    }

    public int getCardValue() {
        return cardValue;
    }

    public void setCardValue(int newValue) {
        try {
            if (card == CardValue.ACE) {
                if (newValue == 1 || newValue == 11) {
                    cardValue = newValue;
                } else {
                    throw new Exception("Can only set Ace cardValue to 1 or 11");
                }
            } else {
                throw new Exception("Can only set card value for Ace cards");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void setAcesValue(ArrayList<Card> hand, int newValue) {
        for (Card e : hand) {
            if (e.card == CardValue.ACE) {
                e.setCardValue(newValue);
            }
        }
    }
}
