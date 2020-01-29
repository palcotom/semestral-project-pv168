package coffeeshop;

public enum Roasting {
    UNROASTED("Unroasted"),
    LIGHT("Light"),
    MEDIUM("Medium");

    private String text;

    Roasting(String text){
        this.text=text;
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
