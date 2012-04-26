package puf.m2.hms.model;

import java.sql.ResultSet;

import puf.m2.hms.db.Database;
import puf.m2.hms.db.DatabaseFactory;

public class HmsEntity {
	protected static final Database DB = DatabaseFactory.DEFAULT_DB;
	
	protected int getNextFreeId() throws Exception {
		int freeId = 1;

		DB.createConnection();

		String query = "select max(id) as maxId from " + getClass().getSimpleName();

		ResultSet rs = DB.executeQuery(query);

		if (rs.next()) {
			freeId = rs.getInt("maxId") + 1;
		}

		DB.closeConnection();
		return freeId;
	}
}
