package CoffeeShop;
import java.util.Date;

public class Coffee {
    private final String name;
    private final Date date;
    private final String type;
    private final Number weight;
    private final String origin;
    private final String roasting;

    public Coffee (String name, Date date, String type, Number weight, String origin, String roasting ){
        this.name=name;
        this.date=date;
        this.type=type;
        this.weight=weight;
        this.origin=origin;
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

    public String getOrigin() {
        return origin;
    }

    public String getRoasting() {
        return roasting;
    }
}
