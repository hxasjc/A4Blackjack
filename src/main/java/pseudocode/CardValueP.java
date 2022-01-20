package pseudocode;

public enum CardValueP {
    //the different card faces to use
    //A, 2, 3, 4, 5, 6, 7, 8, 9, 10, J, Q, K

    //example:
    ACE("Ace", 1, " A");
    //CARD("more user friendly name", card value, "2 character display name")

    //more user friendly name
    public String normalName;

    //card value
    public int cardValue;

    //2 character display name
    public String displayString;

    private CardValueP(String normalName, int cardValue, String displayName){
        //constructor
    }
}
