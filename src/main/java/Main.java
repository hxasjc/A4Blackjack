import card.Card;
import card.CardValue;
import card.Suit;
import hand.HandMethods;

import java.util.*;

import static hand.HandMethods.getHandValue;

public class Main {
    public static ArrayList<Card> cardDeck = new ArrayList<>();

    public static ArrayList<Card> playerHand = new ArrayList<>();
    public static ArrayList<Card> dealerHand = new ArrayList<>();

    public static Scanner scan = new Scanner(System.in);

    public static void initCards() {
        cardDeck.clear();

        for (Suit s : Suit.values()) {
            for (CardValue cv : CardValue.values()) {
                cardDeck.add(new Card(s, cv));
            }
        }
    }

    public static Card pickCard() {
        Random rand = new Random();
        int cardIndex = rand.nextInt(cardDeck.size());
        Card selectedCard = cardDeck.get(cardIndex);
        cardDeck.remove(cardIndex);
        return selectedCard;
    }

    public static int getDealerAceValue() {
        int sum = 0;
        for (Card card : dealerHand) {
            if (card.card == CardValue.ACE) {
                sum += 11;
            } else {
                sum += card.getCardValue();
            }
        }
        return sum;
    }

    public static ActionChoice getPlayerChoice() {
        while (true) {
            System.out.println("Please choose a choice for your turn: Hit, Stay, Fold");
            String strAns = scan.nextLine();
            if (ActionChoice.findByName(strAns) != null) {
                return ActionChoice.findByName(strAns);
            }
            System.out.println("Choice not found");
        }
    }

    public static ActionChoice getDealerChoice() {
        //TODO: add dealer AI
        return ActionChoice.STAY;
    }

    public static String playerTurn() {
        //TODO: allow player to set the value of the aces in their hand
        boolean hitLoop = true;
        while (hitLoop) {
            ActionChoice playerChoice = getPlayerChoice();
            switch (playerChoice) {
                case HIT -> {
                    playerHand.add(pickCard());
                    HandMethods.drawHand(playerHand);
                    break;
                }
                case FOLD -> {
                    return "fold";
                }
                default -> hitLoop = false;
            }
            if (getHandValue(playerHand) > 21) {
                return "bust";
            }
        }
        return "continue";
    }

    public static void dealerTurn() {
        //TODO: add dealer AI
        while (getHandValue(dealerHand) <= 11) {
            int oldValue = getHandValue(dealerHand); //debug
            Card newCard = pickCard();
            System.out.println("Dealer: drew " + newCard + " because old hand value was " + oldValue);
            dealerHand.add(newCard);

            System.out.println("\n\n││77++BÜcM,B\n\n");

            //dealerHand.add(pickCard());
        }
    }

    public static void determineWinner(String str) {
        int playerHandValue = getHandValue(playerHand);
        int dealerHandValue = getHandValue(dealerHand);

        if (playerHandValue == dealerHandValue && playerHandValue > 21) {
            System.out.println("You and the computer both lost because you both had a hand value of over 21");
        } else if (playerHandValue == dealerHandValue) {
            System.out.println("You tied with the computer because you both had the same hand value");
        } else if (playerHandValue > 21) {
            System.out.println("You lost because you had a hand with a value over 21");
        } else if (dealerHandValue > 21) {
            System.out.println("You won because the computer had a hand with a value of over 21");
        } else if (playerHandValue > dealerHandValue) {
            System.out.println("You won because you had a hand value closer to 21 than the computer");
        } else if (dealerHandValue > playerHandValue) {
            System.out.println("You lost because the computer had a hand value closer to 21 than you");
        }

        System.out.println("Your score: " + playerHandValue);
        System.out.println("The computer's score: " + dealerHandValue);
    }

    public static boolean getPlayAgain() {
        System.out.println("Would you like to play another round? Answer either \"Yes\" or \"No\"");
        String strAns = scan.nextLine();
        return strAns.equalsIgnoreCase("yes");
    }

    public static boolean dealRound() {
        initCards();
        playerHand.clear();
        dealerHand.clear();

        playerHand.add(pickCard()); //randomly draw cards and add them to the hands
        dealerHand.add(pickCard());
        playerHand.add(pickCard());
        dealerHand.add(pickCard());

        int acesValue = getDealerAceValue();
        if (acesValue >= 17) {
            Card.setAcesValue(dealerHand, 11);
        } else {
            Card.setAcesValue(dealerHand, 1);
        }

        if (getHandValue(dealerHand) < 17) {
            while (getHandValue(dealerHand) <= 16) {
                dealerHand.add(pickCard());
            }
        }

        System.out.println("Dealer's first card:");
        dealerHand.get(0).drawCard();

        System.out.println("Your hand:");
        HandMethods.drawHand(playerHand);

        String playerTurnResult = playerTurn();

        switch (playerTurnResult) {
            case "fold" -> System.out.println("You lost because you folded");
            case "bust" -> System.out.println("You lost because you had a hand value over 21");
            case "continue" -> {
                //TODO: dealer turn
                dealerTurn();
            }
        }

        System.out.println("Dealer's hand");
        HandMethods.drawHand(dealerHand);

        determineWinner(playerTurnResult);


        return getPlayAgain();
    }

    public static void main(String[] args) {
        //game code
        boolean playGame = true;
        while (playGame) {
            playGame = dealRound();
        }
        System.out.println("Thanks you playing!");
    }
}