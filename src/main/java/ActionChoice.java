public enum ActionChoice {
    HIT,
    STAY,
    FOLD;

    public static ActionChoice findByName(String name) {
        ActionChoice result = null;
        for (ActionChoice choice : values()) {
            if (choice.name().equalsIgnoreCase(name)) {
                result = choice;
                break;
            }
        }
        return result;
    }
}
