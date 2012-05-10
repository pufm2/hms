package puf.m2.hms.db;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import puf.m2.hms.model.TestSupport;

public class SqliteDatabaseTest {

    private static File dbFile = new File("HMS-SqliteDatabaseTest.db3");;
	private static SqliteDatabase db;
	
	@BeforeClass
	public static void beforeClass() throws IOException, DbException, ClassNotFoundException {
		
    	Class.forName("org.sqlite.JDBC");

		TestSupport.copyFile(new File("HMS-test.db3"), dbFile);
		db = new SqliteDatabase("jdbc:sqlite:HMS-SqliteDatabaseTest.db3");
		db.createConnection();
	}
	
	@AfterClass
	public static void afterClass() throws DbException {
		db.closeConnection();
		dbFile.delete();

	}
	
	@Test
	public void testExecuteQuery() throws DbException, SQLException {
		ResultSet rs = db.executeQuery("select * from Physician where id = 100");
		assertEquals(true, rs.next());
		assertEquals("Tran The Hien", rs.getString("name"));
		assertEquals(1, rs.getInt("available"));
		assertEquals(false, rs.next());
	}

	@Test
	public void testExecuteUpdate() throws DbException {
		int result = db.executeUpdate("update Physician set available = 1 where id = 102");
		assertEquals(1, result);
	}

}
