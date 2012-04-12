package puf.m2.hms.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

public class DatabaseImpl implements Database {

    private static final String SQLITE_DRIVER = "org.sqlite.JDBC";
    private static final String DB_URL = "jdbc:sqlite:HMS.db3";

    private static Map<String, Database> databasePool;

    public static Database defaultDb;
    static {
        databasePool = new HashMap<String, Database>();
        try {
            defaultDb = initDefaultDatabase();
        } catch (SQLException e) {
            new RuntimeException(e);
        }
    }

    private String dbUrl;

    public Connection cnn;

    public DatabaseImpl(String dbUrl) {

        this.dbUrl = dbUrl;
    }

    public static Database initDatabase(String jdbcDriver, String dbUrl)
            throws SQLException {
        Database db = databasePool.get(dbUrl);
        if (db != null) {
            return db;
        } else {
            try {
                Class.forName(jdbcDriver);
            } catch (ClassNotFoundException e) {
                throw new SQLException(e);
            }
            db = new DatabaseImpl(dbUrl);
            databasePool.put(dbUrl, db);
            return db;
        }
    }

    public static Database initDefaultDatabase() throws SQLException {
        return initDatabase(SQLITE_DRIVER, DB_URL);
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
