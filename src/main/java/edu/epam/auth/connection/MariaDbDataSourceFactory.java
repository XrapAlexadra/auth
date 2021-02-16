package edu.epam.auth.connection;

import org.mariadb.jdbc.MariaDbDataSource;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class MariaDbDataSourceFactory {

    public static DataSource createSqlDataSource(){
        MariaDbDataSource dataSource = null;
        ResourceBundle resource = ResourceBundle.getBundle("db");
        String url = resource.getString("url");
        String user = resource.getString("user");
        String password = resource.getString("password");
        try {
            dataSource = new MariaDbDataSource();
            dataSource.setUrl(url);
            dataSource.setUser(user);
            dataSource.setPassword(password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dataSource;
    }
}
