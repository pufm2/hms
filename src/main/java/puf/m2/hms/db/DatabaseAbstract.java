package puf.m2.hms.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class DatabaseAbstract implements Database {

    // Get connection to database
    public abstract void createConnection(String jdbcDriver, String dbUrl) throws SQLException;

    public abstract void createConnection() throws SQLException;

    // Get statement object from connection
    public abstract Statement createStatement() throws SQLException;

    // Get Resultset returned by query
    public abstract ResultSet getResultSet(String query) throws SQLException;

    // Close database connection
    public abstract void closeConnection() throws SQLException;

}
