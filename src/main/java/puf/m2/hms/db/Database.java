package puf.m2.hms.db;

import java.sql.ResultSet;

public interface Database {

	public void createConnection() throws DbException;

	public ResultSet executeQuery(String query) throws DbException;

	public int executeUpdate(String query) throws DbException;

	public void closeConnection() throws DbException;
}
