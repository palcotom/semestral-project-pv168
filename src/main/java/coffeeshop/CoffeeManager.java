package coffeeshop;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CoffeeManager {

    private final DataSource dataSource;

    public CoffeeManager(DataSource dataSource){
        this.dataSource=dataSource;
    }

    public List<Coffee> getCoffees() throws CoffeeException{
        try (Connection con = dataSource.getConnection()) {
            try (PreparedStatement st = con.prepareStatement("select * from coffees")) {
                ResultSet rs = st.executeQuery();
                List<Coffee> books = new ArrayList<>();
                while (rs.next()) {
                    Number id = rs.getLong("id");
                    String name = rs.getString("name");
                    Date date = rs.getDate("coffeeDate");
                    String type = rs.getString("type");
                    Number weight = rs.getLong("weight");
                    String roastingStr = rs.getString("roasting");
                    books.add(new Coffee(id,name,date,type,weight,Roasting.fromString(roastingStr)));
                }
                return books;
            }
        } catch (SQLException e) {
            throw new CoffeeException("database select failed", e);
        }
    }
}
