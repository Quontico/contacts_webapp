package util;

import java.beans.PropertyVetoException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbUtil {
    private static Connection connection = null;

    public static Connection getConnection() {
        if (connection != null)
            return connection;
        else {
            try {
                connection = DataSource.getInstance().getConnection();
                connection.setAutoCommit(false);
            } catch (IOException | PropertyVetoException | SQLException e) {
                e.printStackTrace();
            }
            return connection;
        }
    }
}
