package puf.m2.hms.model;

import java.sql.*;

public class DatabaseImpl extends DatabaseAbstract {

    private static final String SQLITE_DRIVER = "org.sqlite.JDBC";
    private static final String DB_URL = "jdbc:sqlite:HMS.db";
    
	private Connection cnn = null;
	private Statement statement = null;
	private ResultSet rs = null;

	@Override
    public void createConnection(String jdbcDriver, String dbUrl) throws SQLException {
		try {
            Class.forName(jdbcDriver);
        } catch (ClassNotFoundException e) {
            throw new SQLException(e);
        }

		//Connect to database
		cnn = DriverManager.getConnection(dbUrl);
	}

	public void createConnection() throws SQLException {

		createConnection(SQLITE_DRIVER, DB_URL);
	}

	@Override
	public Statement createStatement() throws SQLException {
		
		statement = cnn.createStatement();
		return statement;
	}

	@Override
	public ResultSet getResultSet(String query) throws SQLException {
		
		rs = statement.executeQuery(query);
		return rs;
	}

	@Override
	public void closeConnection() throws SQLException {
		cnn.close();
	}

}
