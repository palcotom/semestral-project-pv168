package coffeeshop;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

public class CoffeeManager {

    private final DataSource dataSource;

    public CoffeeManager(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<Coffee> getCoffees() throws CoffeeException {
        try (Connection con = dataSource.getConnection()) {
            try (PreparedStatement st = con.prepareStatement("select * from coffees")) {
                ResultSet rs = st.executeQuery();
                List<Coffee> coffees = new ArrayList<>();
                while (rs.next()) {
                    Number id = rs.getLong("id");
                    String name = rs.getString("name");
                    Date date = rs.getDate("coffeeDate");
                    String type = rs.getString("type");
                    Integer weight = rs.getInt("weight");
                    String roastingStr = rs.getString("roasting");
                    coffees.add(new Coffee(name, date, type, weight, Roasting.fromString(roastingStr)));
                }
                return coffees;
            }
        } catch (SQLException e) {
            throw new CoffeeException("database select failed", e);
        }
    }

    public void addCoffee(Coffee coffee) throws CoffeeException {
        try (Connection con = dataSource.getConnection()) {

            try (PreparedStatement st = con.prepareStatement("insert into coffees (name,coffeeDate,weight,type,roasting) values (?,?,?,?,?)",PreparedStatement.RETURN_GENERATED_KEYS)) {
                st.setString(1, coffee.getName());
                st.setDate(2, coffee.getDate());
                st.setInt(3, coffee.getWeight());
                st.setString(4, coffee.getType());
                st.setString(5, coffee.getRoasting().toString());
                st.executeUpdate();
                ResultSet keys = st.getGeneratedKeys();
                if (keys.next()) {
                    coffee.setId(keys.getLong(1));
                }
            }

        } catch (SQLException e) {
            throw new CoffeeException("database insert failed", e);
        }

    }
}
