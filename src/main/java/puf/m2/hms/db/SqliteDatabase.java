package puf.m2.hms.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

public class SqliteDatabase implements Database {


    private String dbUrl;

    public Connection cnn;

    public SqliteDatabase(String dbUrl) {

        this.dbUrl = dbUrl;
    }

    public void createConnection() throws SQLException {

        cnn = DriverManager.getConnection(dbUrl);
    }

    public ResultSet executeQuery(String query) throws SQLException {

        Statement statement = cnn.createStatement();

        return statement.executeQuery(query);
    }

    public int executeUpdate(String query) throws SQLException {
        Statement statement = cnn.createStatement();

        return statement.executeUpdate(query);
    }
    
    public void closeConnection() throws SQLException {
        cnn.close();
    }


}
