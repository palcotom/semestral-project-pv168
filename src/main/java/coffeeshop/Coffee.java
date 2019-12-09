package coffeeshop;
import java.util.Date;

public class Coffee {
    private final String name;
    private final Date date;
    private final String type;
    private final Number weight;
    private final Roasting roasting;

    public Coffee (String name, Date date, String type, Number weight, Roasting roasting ){
        this.name=name;
        this.date=date;
        this.type=type;
        this.weight=weight;
        this.roasting=roasting;
    }

    public String getName() {
        return name;
    }

    public Date getDate() {
        return date;
    }

    public String getType() {
        return type;
    }

    public Number getWeight() {
        return weight;
    }

    public Roasting getRoasting() {
        return roasting;
    }
}
