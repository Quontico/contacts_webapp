package util;

import org.apache.commons.dbcp.BasicDataSource;

import java.beans.PropertyVetoException;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;
import java.util.ResourceBundle;

class DataSource {

    private static DataSource datasource;
    private BasicDataSource ds;

    private DataSource() throws IOException, SQLException, PropertyVetoException {
        ResourceBundle bundle = ResourceBundle.getBundle("db");

        ds = new BasicDataSource();
        ds.setDriverClassName(bundle.getString("jdbc.driverClassName"));
        ds.setUsername(bundle.getString("jdbc.username"));
        ds.setPassword(bundle.getString("jdbc.password"));
        ds.setUrl(bundle.getString("jdbc.url"));

        ds.setMinIdle(5);
        ds.setMaxIdle(20);
        ds.setMaxOpenPreparedStatements(180);

    }

    static DataSource getInstance() throws IOException, SQLException, PropertyVetoException {
        if (datasource == null) {
            datasource = new DataSource();
            return datasource;
        } else {
            return datasource;
        }
    }

    Connection getConnection() throws SQLException {
        return this.ds.getConnection();
    }

}