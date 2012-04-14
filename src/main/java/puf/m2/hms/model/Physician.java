package puf.m2.hms.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import puf.m2.hms.db.Database;
import puf.m2.hms.db.DatabaseFactory;

public class Physician {

	private static final Database DB = DatabaseFactory.DEFAULT_DB;
	private static final Map<Integer, Physician> PHYSICIAN_MAP = new HashMap<Integer, Physician>();

	public static List<Physician> getDoctors() throws HmsException {
		List<Physician> doctorList = new ArrayList<Physician>();

		final String query = "select * from Physician where role = 'Doctor'";

		try {
			DB.createConnection();

			ResultSet rs = DB.executeQuery(query);

			while (rs.next()) {
				int id = rs.getInt("id");
				Physician physician = PHYSICIAN_MAP.get(id);

				if (physician == null) {
					boolean available = true;
					if (rs.getInt("available") == 0) {
						available = false;
					}
					physician = new Physician(rs.getString("name"),
							rs.getString("role"), available);
					physician.id = id;

					PHYSICIAN_MAP.put(id, physician);
				}

				doctorList.add(physician);
			}

			DB.closeConnection();
		} catch (SQLException e) {
			throw new HmsException(e);
		}

		return doctorList;
	}

	public static List<Physician> getNurses() throws HmsException {
		List<Physician> doctorList = new ArrayList<Physician>();

		final String query = "select * from Physician where role = 'Nurse'";

		try {
			DB.createConnection();

			ResultSet rs = DB.executeQuery(query);

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

			DB.closeConnection();
		} catch (SQLException e) {
			throw new HmsException(e);
		}

		return doctorList;
	}

	public static Physician getPhysicianById(int id) throws HmsException {
		Physician physician = PHYSICIAN_MAP.get(id);
		if (physician != null) {
			return physician;
		}
		try {
			DB.createConnection();

			final String queryTempl = "SELECT * FROM Physician WHERE id = {0}";
			ResultSet rs = DB
					.executeQuery(MessageFormat.format(queryTempl, id));

			if (rs.next()) {
				boolean available = true;
				if (rs.getInt("available") == 0) {
					available = false;
				}

				physician = new Physician(rs.getString("name"),
						rs.getString("role"), available);
				physician.id = rs.getInt("id");
			}
			PHYSICIAN_MAP.put(physician.getId(), physician);
			DB.closeConnection();
		} catch (SQLException e) {
			throw new HmsException(e);
		}

		return physician;
	}

	private boolean available;

	private int id;

	private String name, role;

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

	private int getNextFreeId() throws HmsException {
		int freeId = 1;
		try {
			DB.createConnection();

			String query = "select max(id) as maxId from Physician";

			ResultSet rs = DB.executeQuery(query);

			if (rs.next()) {
				freeId = rs.getInt("maxId") + 1;
			}

			DB.closeConnection();
		} catch (SQLException e) {
			throw new HmsException(e);
		}

		return freeId;
	}

	public String getRole() {
		return role;
	}

	public boolean isAvaiable(Date dateAffect, int physicianID)
			throws SQLException {
		boolean result = false;

		DB.createConnection();

		String query = "SELECT ID FROM Physician" + " WHERE ID = "
				+ physicianID + " AND Available = 1";
		ResultSet rs = DB.executeQuery(query);
		while (rs.next())
			result = true;
		return result;
	}

	public boolean isAvailable() {
		return available;
	}

	public boolean isDoctor(int physicianID) throws SQLException {
		boolean result = false;

		DB.createConnection();
		String query = "SELECT ID FROM Physician" + " WHERE ID = "
				+ physicianID + " AND Role = 'Doctor'";
		ResultSet rs = DB.executeQuery(query);
		while (rs.next())
			result = true;

		return result;
	}

	public boolean isExist(int physicianID) throws SQLException {
		boolean result = false;

		DB.createConnection();
		String query = "SELECT ID FROM Physician" + " WHERE ID = "
				+ physicianID;
		ResultSet rs = DB.executeQuery(query);
		while (rs.next())
			result = true;

		return result;
	}

	public void save() throws HmsException {
		id = getNextFreeId();
		final String queryTemple = "insert into Physician values({0}, ''{1}'', ''{2}'', ''{3}'')";
		try {
			DB.createConnection();

			int available = this.available ? 1 : 0;
			DB.executeUpdate(MessageFormat.format(queryTemple, id, name, role,
					available));
			DB.closeConnection();

			PHYSICIAN_MAP.put(id, this);
		} catch (SQLException e) {
			throw new HmsException(e);
		}
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public void update() throws HmsException {
		final String queryTemple = "update Physician set name = ''{0}'', role = ''{1}'', available = ''{2}'' where id = {4}";
		try {
			DB.createConnection();

			int available = this.available ? 1 : 0;
			DB.executeUpdate(MessageFormat.format(queryTemple, name, role,
					available, id));
			DB.closeConnection();
		} catch (SQLException e) {
			throw new HmsException(e);
		}
	}

	public int updateStatus(String physician) throws SQLException {

		int result = -1;
		int physicianID = Integer.parseInt(physician);
		// Update physician avaiable
		DB.createConnection();

		String query = "UPDATE Physician" + " SET Avaiable = Not(Avaiable)"
				+ " WHERE ID = " + physicianID;
		result = DB.executeUpdate(query);
		DB.closeConnection();

		return result;
	}

}
