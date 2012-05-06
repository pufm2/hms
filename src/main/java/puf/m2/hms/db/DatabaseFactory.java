package puf.m2.hms.db;

import java.util.HashMap;
import java.util.Map;

public class DatabaseFactory {

	public static final String SQLITE_DRIVER = "org.sqlite.JDBC";
	private static final String DB_URL = "jdbc:sqlite:HMS.db3";

	private static final Map<String, Database> DB_POOL;

	public static final Database DEFAULT_DB;

	static {
		DB_POOL = new HashMap<String, Database>();
		DEFAULT_DB = createDatabase(SQLITE_DRIVER, DB_URL);
	}

	public static Database createDatabase(String jdbcDriver, String dbUrl) {
		if (DB_POOL.containsKey(dbUrl)) {
			return DB_POOL.get(dbUrl);
		}

		try {
			Class.forName(jdbcDriver);
		} catch (ClassNotFoundException e) {
			return null;
		}

		Database db = null;
		if (SQLITE_DRIVER.equals(jdbcDriver)) {
			db = new SqliteDatabase(dbUrl);
		}
		DB_POOL.put(dbUrl, db);
		return db;
	}
}
