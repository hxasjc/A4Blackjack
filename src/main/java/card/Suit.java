package card;

public enum Suit {
    HEART("Heart", '♥'),
    DIAMOND("Diamond", '♦'),
    CLUB("Club", '♣'),
    SPADE("Spade", '♠');

    /**
     * A name that is much more reader friendly
     */
    public String normalName;

    /**
     * A character to represent the suit
     */
    public char character;

    /**
     * It's a constructor
     * @param normalName A name that is much more reader friendly
     * @param suitCharacter A character to represent the suit
     */
    private Suit(String normalName, char suitCharacter) {
        this.normalName = normalName;
        this.character = suitCharacter;
    }
}
