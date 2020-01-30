package coffeeshop;

import coffeeshop.views.MainWindow;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import javax.sql.DataSource;
import java.awt.*;
import java.io.IOException;
import java.util.Properties;

//TODO swing worker
//TODO clean up main
//TODO possibly add multiple formats of date input
//TODO edit existing items ? possibly not needed but something to consider
//TODO 3/2/2020 od 14:00 a dál

/**
 * @author Tomas Palco
 */

public class Main {
    final static Logger log = LoggerFactory.getLogger(Main.class);

    public static DataSource getDataSource() throws IOException {
        BasicDataSource ds = new BasicDataSource();
        Properties p = new Properties();
        p.load(MainWindow.class.getResourceAsStream("/jdbc.properties"));
        ds.setDriverClassName(p.getProperty("jdbc.driver"));
        ds.setUrl(p.getProperty("jdbc.url"));
        ds.setUsername(p.getProperty("jdbc.user"));
        ds.setPassword(p.getProperty("jdbc.password"));
        new ResourceDatabasePopulator(
                new ClassPathResource("sql_scheme.sql"),
                new ClassPathResource("data.sql"))
                .execute(ds);
        return ds;
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() ->
        {
            try {
                new MainWindow().setVisible(true);
            } catch (CoffeeException e) {
                log.error("Coffee exception in MainWindow", e);
                e.printStackTrace();
            }
        });
    }
}
