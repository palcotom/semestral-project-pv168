package coffeeshop;

import java.awt.event.ActionEvent;

public enum Roasting {
    UNROASTED("Unroasted"),
    LIGHT("Light"),
    MEDIUM("Medium"),
    DARK("Dark");

    private String text;

    Roasting(String text){
        this.text=text;
    }

    // TODO redundant?
    public static Roasting fromString(String txt){
        for (Roasting roasting : Roasting.values()) {
            if (roasting.text.equalsIgnoreCase(txt)) {
                return roasting;
            }
        }
        return null;
    }
}
