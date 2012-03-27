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
    private static final String DB_URL = "jdbc:sqlite:HMS.db";
    
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
    
    private Connection cnn;
	private Statement statement;
	private ResultSet rs;
    
    private DatabaseImpl(String dbUrl) {
        super();
        this.dbUrl = dbUrl;
    }

    public static Database initDatabase(String jdbcDriver, String dbUrl) throws SQLException {
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

	public Statement createStatement() throws SQLException {
		
		statement = cnn.createStatement();
		return statement;
	}

	public ResultSet getResultSet(String query) throws SQLException {
		
		rs = statement.executeQuery(query);
		return rs;
	}

	public void closeConnection() throws SQLException {
		cnn.close();
	}

}
