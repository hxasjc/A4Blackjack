public enum ActionChoice {
    HIT,
    STAY,
    FOLD;

    /**
     * Takes a string and finds the matching Enum value
     * @param name The name of the Enum value to find
     * @return ActionChoice - Returns the Enum value
     */
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
