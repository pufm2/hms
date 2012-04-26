package puf.m2.hms.db;

import java.sql.ResultSet;

public interface Database {

	public void createConnection();

	// Get Resultset returned by query
	public ResultSet executeQuery(String query);

	public int executeUpdate(String query);

	// Close database connection
	public void closeConnection();
}
