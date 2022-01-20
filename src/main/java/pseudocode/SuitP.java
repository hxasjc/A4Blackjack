package pseudocode;

public enum SuitP {
    //enum of the suits to use
    //Diamonds, Clubs, Hearts, Spades

    //example:
    DIAMOND("Diamond", '♦');
    //SUIT("more user friendly name", 'corresponding character')

    //more user friendly name
    public String normalName;

    //corresponding character
    public char character;

    private SuitP(String normalName, char suitCharacter) {
        //constructor
    }

}
