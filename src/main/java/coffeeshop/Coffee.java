package coffeeshop;
import java.sql.Date;
import java.util.Objects;

public class Coffee {
    private  Long id;
    private final String name;
    private final Date date;
    private final String type;
    private final Integer weight;
    private final Roasting roasting;

    public Coffee (String name, Date date, String type, Integer weight, Roasting roasting ){
        this.id=null;
        this.name=name;
        this.date=date;
        this.type=type;
        this.weight=weight;
        this.roasting=roasting;
    }
    public Long getId(){return id;}

    public void setId(Long id){this.id = id;}

    public String getName() {
        return name;
    }

    public Date getDate() {
        return date;
    }

    public String getType() {
        return type;
    }

    public Integer getWeight() {
        return weight;
    }

    public Roasting getRoasting() {
        return roasting;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coffee coffee = (Coffee) o;
        return id.equals(coffee.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
