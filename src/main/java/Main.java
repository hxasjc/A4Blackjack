import card.Card;
import card.CardValue;
import card.Suit;
import hand.HandMethods;

import java.util.*;

import static hand.HandMethods.getHandValue;

public class Main {
    /**
     * The deck of cards
     */
    public static ArrayList<Card> cardDeck = new ArrayList<>();

    /**
     * The player's hand
     */
    public static ArrayList<Card> playerHand = new ArrayList<>();

    /**
     * The dealer's hand
     */
    public static ArrayList<Card> dealerHand = new ArrayList<>();

    /**
     * Scanner to get input from the player
     */
    public static Scanner scan = new Scanner(System.in);

    /**
     * Clears the card deck and adds all cards to it. (Resetting the deck)
     */
    public static void initCards() {
        cardDeck.clear(); //clear the deck

        for (Suit s : Suit.values()) { //loop through suits
            for (CardValue cv : CardValue.values()) { //loop through card values
                cardDeck.add(new Card(s, cv)); //add new card to deck
            }
        }
    }

    /**
     * Picks a random card from the deck and removes it from the deck to prevent it from being picked a second time
     * @return {@link Card} - Returns the chosen card
     */
    public static Card pickCard() {
        Random rand = new Random();
        int cardIndex = rand.nextInt(cardDeck.size());
        Card selectedCard = cardDeck.get(cardIndex);
        cardDeck.remove(cardIndex);
        return selectedCard;
    }

    /**
     * Helper method for helping to determine the value of the Aces in the dealer's hand before the game starts
     * @return int - Returns the sum of the cards in the dealer's hand, assuming Aces have a value of 11
     */
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

    /**
     * Gets the player's choice for what action they want to do on their turn
     * @return {@link ActionChoice} - Returns the selected choice
     */
    public static ActionChoice getPlayerChoice() {
        while (true) {
            System.out.println("Please choose a choice for your turn: Hit, Stay, Fold");
            String strAns = scan.nextLine();
            if (ActionChoice.findByName(strAns) != null) { //uses a helper method to determine if the given input string is a valid Enum
                return ActionChoice.findByName(strAns);
            }
            System.out.println("Choice not found");
        }
    }

    /**
     * Method is not currently used; will be used for a proper AI if I end up doing that
     * @return {@link ActionChoice} - Returns the selected choice
     */
    public static ActionChoice getDealerChoice() {
        //TODO: add dealer AI
        return ActionChoice.STAY;
    }

    /**
     * Method for the player's turn. It gets their choice and acts on that, checking if they bust. Return type could very well be an Enum
     * @return String - Returns the result of the player's turn. Should be an Enum
     */
    public static String playerTurn() {
        //TODO: allow player to set the value of the aces in their hand
        boolean hitLoop = true;
        while (hitLoop) { //loop allows player to hit as mush as they want
            ActionChoice playerChoice = getPlayerChoice(); //get the player choice
            switch (playerChoice) {
                case HIT -> { //hit
                    playerHand.add(pickCard());
                    HandMethods.drawHand(playerHand);
                }
                case FOLD -> { //fold
                    return "fold";
                }
                default -> hitLoop = false; //anything else
            }
            if (getHandValue(playerHand) > 21) { //checks if the player busted
                return "bust";
            }
        }
        return "continue"; //continue playing
    }

    /**
     * Method for the dealer's turn. Simple "AI" at the moment but I might improve it at some point
     */
    public static void dealerTurn() {
        //TODO: add dealer AI
        while (getHandValue(dealerHand) <= 11) { //keep drawing while they have a hand value under 12
            int oldValue = getHandValue(dealerHand); //debug
            Card newCard = pickCard();
            System.out.println("Dealer: drew " + newCard + " because old hand value was " + oldValue); //print statement for debug
            dealerHand.add(newCard);

            //dealerHand.add(pickCard());
        }
    }

    /**
     * Lengthy method for determining the winner
     */
    public static void determineWinner() {
        int playerHandValue = getHandValue(playerHand); //variables so I don't need to call the methods all the time
        int dealerHandValue = getHandValue(dealerHand);

        if (playerHandValue == dealerHandValue && playerHandValue > 21) { //lots of if/else statements to test (I think) all possible cases, probably a better way of doing this
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

        System.out.println(); //print the final score
        System.out.println("Your score: " + playerHandValue);
        System.out.println("The computer's score: " + dealerHandValue);
    }

    /**
     * Asks if the player wants to play again
     * @return boolean - Returns if the player wants to play again
     */
    public static boolean getPlayAgain() {
        System.out.println("Would you like to play another round? Answer either \"Yes\" or \"No\"");
        String strAns = scan.nextLine();
        return strAns.equalsIgnoreCase("yes");
    }

    /**
     * Method for each game. Putting it in a method reduces the amount of variable I need to reset
     * @return boolean - Returns if the player wants to play again
     */
    public static boolean dealRound() {
        initCards(); //resets the ArrayLists
        playerHand.clear();
        dealerHand.clear();

        playerHand.add(pickCard()); //randomly draw cards and add them to the hands
        dealerHand.add(pickCard());
        playerHand.add(pickCard());
        dealerHand.add(pickCard());

        int acesValue = getDealerAceValue(); //check what value to give to Aces the dealer has and set the value using a helper method
        if (acesValue >= 17) {
            Card.setAcesValue(dealerHand, 11);
        } else {
            Card.setAcesValue(dealerHand, 1);
        }

        if (getHandValue(dealerHand) < 17) { //dealer draws until they have a hand value of 17 or higher
            while (getHandValue(dealerHand) <= 16) {
                dealerHand.add(pickCard());
            }
        }

        System.out.println("Dealer's first card:"); //show the dealer's first card
        dealerHand.get(0).drawCard();

        System.out.println("Your hand:"); //print the player's hand
        HandMethods.drawHand(playerHand);

        String playerTurnResult = playerTurn(); //player takes their turn

        switch (playerTurnResult) { //stuff happens based on player turn result
            case "fold" -> System.out.println("You lost because you folded");
            case "bust" -> System.out.println("You lost because you had a hand value over 21");
            case "continue" -> dealerTurn();
        }

        System.out.println("Dealer's hand"); //print the dealer's hand
        HandMethods.drawHand(dealerHand);

        determineWinner(); //determine the winner


        return getPlayAgain();
    }

    public static void main(String[] args) {
        //game code
        boolean playGame = true;
        while (playGame) { //loops as long as the player wants
            playGame = dealRound();
        }
        System.out.println("Thanks you playing!");
    }
}