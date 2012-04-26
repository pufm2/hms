package puf.m2.hms.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SqliteDatabase implements Database {

	private String dbUrl;

	public Connection cnn;

	public SqliteDatabase(String dbUrl) {

		this.dbUrl = dbUrl;
	}

	public void createConnection() {

		try {
			cnn = DriverManager.getConnection(dbUrl);
		} catch (Exception e) {

		}
	}

	@SuppressWarnings("finally")
	public ResultSet executeQuery(String query) {

		ResultSet rs = null;
		try {
			Statement statement = cnn.createStatement();
			rs = statement.executeQuery(query);
		} catch (Exception e) {
		} finally {
			return rs;
		}
	}

	@SuppressWarnings("finally")
	public int executeUpdate(String query) {
		int result = 0;
		try {
			Statement statement = cnn.createStatement();
			result = statement.executeUpdate(query);
		} catch (Exception e) {
		} finally {
			return result;
		}
	}

	public void closeConnection() {
		try {
			cnn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
