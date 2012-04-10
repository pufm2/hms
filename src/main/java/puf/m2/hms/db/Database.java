package puf.m2.hms.db;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface Database {

    public void createConnection() throws SQLException;

    // Get Resultset returned by query
    public ResultSet executeQuery(String query) throws SQLException;
    
    public int executeUpdate(String query) throws SQLException;

    // Close database connection
    public void closeConnection() throws SQLException;
}
