package card;

public enum Suit {
    HEART("Heart", '♥'),
    DIAMOND("Diamond", '♦'),
    CLUB("Club", '♣'),
    SPADE("Spade", '♠');

    public String normalName;
    public char character;

    private Suit(String normalName, char suitCharacter) {
        this.normalName = normalName;
        this.character = suitCharacter;
    }
}
