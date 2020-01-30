package coffeeshop;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CoffeeManager {

    private final DataSource dataSource;
    final static Logger log = LoggerFactory.getLogger(Main.class);

    public CoffeeManager(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<Coffee> getCoffees() throws CoffeeException {
        try (Connection con = dataSource.getConnection()) {
            try (PreparedStatement st = con.prepareStatement("select * from coffees")) {
                ResultSet rs = st.executeQuery();
                List<Coffee> coffees = new ArrayList<>();
                while (rs.next()) {
                    Long id = rs.getLong("id");
                    String name = rs.getString("name");
                    Date date = rs.getDate("coffeeDate");
                    String type = rs.getString("type");
                    Integer weight = rs.getInt("weight");
                    String roastingStr = rs.getString("roasting");
                    Coffee coffee = new Coffee(name, date, type, weight, Roasting.fromString(roastingStr));
                    coffee.setId(id);
                    coffees.add(coffee);
                }
                return coffees;
            }
        } catch (SQLException e) {
            log.error("SQL select error",e);
            throw new CoffeeException("database select failed", e);
        }
    }

    public void addCoffee(Coffee coffee) throws CoffeeException {
        try (Connection con = dataSource.getConnection()) {
            try (PreparedStatement st = con.prepareStatement("insert into coffees (name,coffeeDate,weight,type,roasting) values (?,?,?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS)) {
                st.setString(1, coffee.getName());
                st.setDate(2, coffee.getDate());
                st.setInt(3, coffee.getWeight());
                st.setString(4, coffee.getType());
                st.setString(5, coffee.getRoasting().toString());
                st.executeUpdate();
                ResultSet keys = st.getGeneratedKeys();
                if (keys.next()) {
                    coffee.setId(keys.getLong(1));
                }else{
                    log.error("No keys generated");
                }
            }
        } catch (SQLException e) {
            log.error("SQL insert error",e);
            throw new CoffeeException("database insert failed", e);
        }

    }

    public void deleteCoffee(Long id) throws CoffeeException{
        try (Connection con = dataSource.getConnection()) {
            try (PreparedStatement st = con.prepareStatement("delete from coffees where id=?")) {
                st.setLong(1,id);
                int n = st.executeUpdate();
                if (n == 0) {
                    throw new CoffeeException("couldn't delete item " + id, null);
                }
            }
        } catch (SQLException e) {
            log.error("SQL delete error", e);
            throw new CoffeeException("database insert failed", e);
        }

    }

    public void printDB() throws CoffeeException {
        try (Connection con = dataSource.getConnection()) {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from coffees");
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnsNumber = rsmd.getColumnCount();
            while (rs.next()) {
                for (int i = 1; i <= columnsNumber; i++) {
                    System.out.print(rs.getString(i) + " "); //Print one element of a row
                }
                System.out.println();//Move to the next line to print the next row.
            }
    } catch(SQLException e)
    {
        throw new CoffeeException("database insert failed", e);
    }


}

}
