package hand;

import card.Card;

import java.util.ArrayList;

public class HandMethods {
    public static int getHandValue(ArrayList<Card> hand) {
        int sum = 0;
        for (Card e : hand) {
            sum += e.getCardValue();
        }
        return sum;
    }

    public static void drawHand(ArrayList<Card> hand) {
        int handSize = hand.size();
        System.out.println(drawTopHandLine(handSize));
        System.out.println(drawLineTwo(hand));
        System.out.println(drawEmptyHandLine(handSize));
        System.out.println(drawLineFour(hand));
        System.out.println(drawEmptyHandLine(handSize));
        System.out.println(drawLineSix(hand));
        System.out.println(drawBottomHandLine(handSize));
        System.out.println("Hand value: " + getHandValue(hand));
    }

    private static String drawEmptyHandLine(int length) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append("|        |");
            sb.append("\t\t");
        }
        return sb.toString();
    }

    private static String drawTopHandLine(int length) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append("__________");
            sb.append("\t\t");
        }
        return sb.toString();
    }

    private static String drawBottomHandLine(int length) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append("¯¯¯¯¯¯¯¯¯¯");
            sb.append("\t\t");
        }
        return sb.toString();
    }

    private static String drawLineTwo(ArrayList<Card> hand) {
        StringBuilder sb = new StringBuilder();
        hand.forEach((e) -> {
            sb.append("|" + e.cardSuit.character + "       |");
            sb.append("\t\t");
        });
        return sb.toString();
    }

    private static String drawLineFour(ArrayList<Card> hand) {
        StringBuilder sb = new StringBuilder();
        hand.forEach((e) -> {
            StringBuilder cardLine = new StringBuilder("|   ");
            cardLine.append(e.card.displayString);
            cardLine.append("   |");
            sb.append(cardLine);
            sb.append("\t\t");
        });
        return sb.toString();
    }

    private static String drawLineSix(ArrayList<Card> hand) {
        StringBuilder sb = new StringBuilder();
        hand.forEach((e) -> {
            StringBuilder cardLine = new StringBuilder("|       ");
            cardLine.append(e.cardSuit.character);
            cardLine.append("|");
            sb.append(cardLine);
            sb.append("\t\t");
        });
        return sb.toString();
    }
}
