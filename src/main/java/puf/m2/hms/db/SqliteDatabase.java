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

	public void createConnection() throws DbException {

		try {
			cnn = DriverManager.getConnection(dbUrl);
		} catch (Exception e) {
		    throw new DbException(e);
		}
	}

	public ResultSet executeQuery(String query) throws DbException {

		try {
			Statement statement = cnn.createStatement();
			return statement.executeQuery(query);
		} catch (Exception e) {
		    try {
                cnn.close();
            } catch (SQLException e1) {

            }
		    throw new DbException(e);
		}

	}

	public int executeUpdate(String query) throws DbException {
		try {
			Statement statement = cnn.createStatement();
			return statement.executeUpdate(query);
		} catch (Exception e) {
		    try {
                cnn.close();
            } catch (SQLException e1) {

            }
		    throw new DbException(e);
		}
	}

	public void closeConnection() throws DbException {
		try {
			cnn.close();
		} catch (SQLException e) {
		    throw new DbException(e);
		}
	}

}
