package card;

public enum CardValue {
    ACE("Ace", 0, " A"),
    TWO("Two", 2, " 2"),
    THREE("Three", 3, " 3"),
    FOUR("Four", 4, " 4"),
    FIVE("Five", 5, " 5"),
    SIX("Six", 6, " 6"),
    SEVEN("Seven", 7, " 7"),
    EIGHT("Eight", 8, " 8"),
    NINE("Nine", 9, " 9"),
    TEN("Ten", 10, "10"),
    JACK("Jack", 10, " J"),
    QUEEN("Queen", 10, " Q"),
    KING("King", 10, " K");

    /**
     * A name that is much more reader friendly
     */
    public String normalName;

    /**
     * The numeric value of the card
     */
    public int cardValue;

    /**
     * 2 character string representing the card
     */
    public String displayString;

    /**
     * It's a constructor
     * @param normalName A name that is much more reader friendly
     * @param cardValue The numeric value of the card
     * @param displayName 2 character string representing the card
     */
    private CardValue(String normalName, int cardValue, String displayName) {
        this.normalName = normalName;
        this.cardValue = cardValue;
        this.displayString = displayName;
    }
}
