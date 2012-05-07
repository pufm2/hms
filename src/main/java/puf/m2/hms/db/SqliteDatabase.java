package puf.m2.hms.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class SqliteDatabase implements Database {

	private String dbUrl;

	public Connection cnn;

	public SqliteDatabase(String dbUrl) {

		this.dbUrl = dbUrl;
	}

	public ResultSet executeQuery(String query) throws DbException {

		try {
			if (cnn == null || cnn.isClosed()) {
				cnn = DriverManager.getConnection(dbUrl);
			}
			Statement statement = cnn.createStatement();
			return statement.executeQuery(query);
		} catch (Exception e) {
		    throw new DbException(e);
		}

	}

	public int executeUpdate(String query) throws DbException {
		try {

			Statement statement = cnn.createStatement();
			return statement.executeUpdate(query);
		} catch (Exception e) {
		    throw new DbException(e);
		}
	}

	public void createConnection() throws DbException {
		try {
			if (cnn == null || cnn.isClosed()) {
				cnn = DriverManager.getConnection(dbUrl);
			}
			
		} catch (Exception e) {
		    throw new DbException(e);
		}
		
	}

	public void closeConnection() throws DbException {
		try {
			if (cnn != null && !cnn.isClosed()) {
				cnn.close();				
			}

		} catch (Exception e) {
		    throw new DbException(e);
		}
		
	}

}
