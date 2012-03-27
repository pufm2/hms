package puf.m2.hms.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public interface Database {

    // Get connection to database
    public void createConnection(String jdbcDriver, String dbUrl) throws SQLException;

    public void createConnection() throws SQLException;

    // Get statement object from connection
    public Statement createStatement() throws SQLException;

    // Get Resultset returned by query
    public ResultSet getResultSet(String query) throws SQLException;

    // Close database connection
    public void closeConnection() throws SQLException;
}
