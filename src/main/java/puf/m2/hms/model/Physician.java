package puf.m2.hms.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import puf.m2.hms.db.Database;
import puf.m2.hms.db.DatabaseFactory;
import puf.m2.hms.exception.DbException;
import puf.m2.hms.exception.PhysicianException;

public class Physician {

	private static final Database DB = DatabaseFactory.DEFAULT_DB;
	private static final Map<Integer, Physician> PHYSICIAN_MAP = new HashMap<Integer, Physician>();

	public static List<Physician> getDoctors() throws PhysicianException {
		List<Physician> doctorList = new ArrayList<Physician>();

		final String query = "select * from Physician where role = 'Doctor'";

		DB.createConnection();

		ResultSet rs = DB.executeQuery(query);
		try {
			while (rs.next()) {
				int id = rs.getInt("id");
				Physician physician = PHYSICIAN_MAP.get(id);

				if (physician == null) {
					boolean available = true;
					if (rs.getInt("available") == 0) {
						available = false;
					}
					physician = new Physician(rs.getString("name"), "Doctor",
							available);
					physician.id = id;

					PHYSICIAN_MAP.put(id, physician);
				}

				doctorList.add(physician);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DB.closeConnection();

		return doctorList;
	}

	public static List<Physician> getNurses() throws PhysicianException {
		List<Physician> doctorList = new ArrayList<Physician>();

		final String query = "select * from Physician where role = 'Nurse'";

		DB.createConnection();

		ResultSet rs = DB.executeQuery(query);

		try {
			while (rs.next()) {
				int id = rs.getInt("id");
				Physician physician = PHYSICIAN_MAP.get(id);

				if (physician == null) {
					boolean available = true;
					if (rs.getInt("available") == 0) {
						available = false;
					}
					physician = new Physician(rs.getString("name"), "Nurse",
							available);
					physician.id = id;

					PHYSICIAN_MAP.put(id, physician);
				}

				doctorList.add(physician);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		DB.closeConnection();
		return doctorList;
	}

	public static Physician getPhysicianById(int id) throws PhysicianException {
		String queryTempl = "";

		Physician physician = PHYSICIAN_MAP.get(id);
		if (physician != null) {
			return physician;
		}

		DB.createConnection();

		queryTempl = "SELECT * FROM Physician WHERE id = {0}";
		ResultSet rs = DB.executeQuery(MessageFormat.format(queryTempl, id));

		try {
			if (rs.next()) {
				boolean available = true;
				if (rs.getInt("available") == 0) {
					available = false;
				}

				physician = new Physician(rs.getString("name"),
						rs.getString("role"), available);
				physician.id = rs.getInt("id");
				PHYSICIAN_MAP.put(physician.getId(), physician);
			} else {
				throw new PhysicianException(id);
			}
		} catch (SQLException sqlException) {
			System.out.println("Error related to SQL Exception: "
					+ sqlException.getMessage());
		} catch (NullPointerException nullPointerException) {
			System.out.println("Error: ID" + id + " does not exists");
		}

		DB.closeConnection();

		return physician;
	}

	private int id;

	private String name, role;

	private boolean available;

	public Physician(String name, String role, boolean available) {

		this.name = name;
		this.role = role;
		this.available = available;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	private int getNextFreeId() throws Exception {
		int freeId = 1;
		String query = "";
		try {

			DB.createConnection();

			query = "select max(id) as maxId from Physician";

			ResultSet rs = DB.executeQuery(query);

			if (rs.next()) {
				freeId = rs.getInt("maxId") + 1;
			}

			DB.closeConnection();
		} catch (SQLException e) {
			throw new DbException(query);
		}

		return freeId;
	}

	public String getRole() {
		return role;
	}

	public boolean isAvailable() {
		return available;
	}

	public void save() throws Exception {
		id = getNextFreeId();
		final String queryTemple = "insert into Physician values({0}, ''{1}'', ''{2}'', ''{3}'')";

		DB.createConnection();

		int available = this.available ? 1 : 0;
		DB.executeUpdate(MessageFormat.format(queryTemple, id, name, role,
				available));
		DB.closeConnection();

		PHYSICIAN_MAP.put(id, this);

	}

	public void setAvailable(boolean available) {
		this.available = available;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public void update() throws Exception {
		final String queryTemple = "update Physician set name = ''{0}'', role = ''{1}'', available = ''{2}'' where id = {4}";

		DB.createConnection();

		int available = this.available ? 1 : 0;
		DB.executeUpdate(MessageFormat.format(queryTemple, name, role,
				available, id));
		DB.closeConnection();

	}

}
