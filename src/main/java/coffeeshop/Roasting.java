package coffeeshop;

public enum Roasting {
    LIGHT("Light"),MEDIUM("Medium"),HEAVY("Heavy");
    private String text;

    Roasting(String text){
        this.text=text;
    }

    public String getText() {
        return text;
    }

    public static Roasting fromString(String txt){
        for (Roasting roasting : Roasting.values()) {
            if (roasting.text.equalsIgnoreCase(txt)) {
                return roasting;
            }
        }
        return null;
    }
}
