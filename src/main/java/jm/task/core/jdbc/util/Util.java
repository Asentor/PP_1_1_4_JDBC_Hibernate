package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    Connection connection;
    public Util() throws SQLException {
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydbtest?" +
                "user=root&password=1234");
    }
    public Connection getConnection() {
        return connection;
    }
}
