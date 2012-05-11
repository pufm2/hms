package puf.m2.hms.model;

import java.sql.ResultSet;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import puf.m2.hms.exception.HmsException;
import puf.m2.hms.exception.PhysicianException;

public class Physician extends HmsEntity {

	private static final Map<Integer, Physician> PHYSICIAN_MAP = new CacheAwareMap<Integer, Physician>();

	@DbProp
	private String name;
	@DbProp
	private String role;
	@DbProp
	private boolean available;
	@DbProp
	private boolean deleted;

	public Physician(String name, String role, boolean available) {

		this(name, role, available, false);
	}

	public Physician(String name, String role, boolean available,
			boolean deleted) {

		this.name = name;
		this.role = role;
		this.available = available;
		this.deleted = deleted;
	}

	public static List<Physician> getDoctors() throws PhysicianException {
		List<Physician> doctorList = new ArrayList<Physician>();

		final String query = "select * from Physician where role = 'Doctor'"
				+ " and deleted = 0";

		try {
			ResultSet rs = DB.executeQuery(query);

			while (rs.next()) {
				int id = rs.getInt("id");
				Physician physician = PHYSICIAN_MAP.get(id);

				if (physician == null) {
					boolean available = rs.getInt("available") == 1 ? true
							: false;
					boolean deleted = rs.getInt("deleted") == 1 ? true : false;

					physician = new Physician(rs.getString("name"), "Doctor",
							available, deleted);
					physician.id = id;

					PHYSICIAN_MAP.put(id, physician);
				}

				doctorList.add(physician);
			}
		} catch (Exception e) {
			throw new PhysicianException(e);
		}

		return doctorList;
	}

	public static List<Physician> getNurses() throws PhysicianException {
		List<Physician> doctorList = new ArrayList<Physician>();

		final String query = "select * from Physician where role = 'Nurse'"
				+ " and deleted = 0";

		try {
			ResultSet rs = DB.executeQuery(query);

			while (rs.next()) {
				int id = rs.getInt("id");
				Physician physician = PHYSICIAN_MAP.get(id);

				if (physician == null) {
					boolean available = rs.getInt("available") == 1 ? true
							: false;
					boolean deleted = rs.getInt("deleted") == 1 ? true : false;
					physician = new Physician(rs.getString("name"), "Nurse",
							available, deleted);
					physician.id = id;

					PHYSICIAN_MAP.put(id, physician);
				}

				doctorList.add(physician);

			}
		} catch (Exception e) {
			throw new PhysicianException(e);
		}

		return doctorList;
	}

	public static Physician getPhysicianById(int id) throws PhysicianException {

		Physician physician = PHYSICIAN_MAP.get(id);
		if (physician != null) {
			return physician;
		}

		final String queryTempl = "SELECT * FROM Physician WHERE id = {0} and deleted = 0";

		try {
			ResultSet rs = DB.executeQuery(MessageFormat.format(queryTempl, id));

			if (rs.next()) {
				boolean available = rs.getInt("available") == 1 ? true : false;
				boolean deleted = rs.getInt("deleted") == 1 ? true : false;

				physician = new Physician(rs.getString("name"),
						rs.getString("role"), available, deleted);
				physician.id = id;
				PHYSICIAN_MAP.put(physician.getId(), physician);
			}
		} catch (Exception e) {
			throw new PhysicianException(e);
		}

		return physician;
	}

	public static Physician getPhysicianByName(String name) {

		final String queryTempl = "SELECT * FROM Physician WHERE name = {0} and deleted = 0";

		Physician physician = null;
		try {
			ResultSet rs = DB.executeQuery(MessageFormat.format(queryTempl, name));
			if (rs.next()) {
				int id = rs.getInt("id");
				physician = PHYSICIAN_MAP.get(id);
				if (physician == null) {
					boolean available = rs.getInt("available") == 1 ? true : false;
					boolean deleted = rs.getInt("deleted") == 1 ? true : false;

					physician = new Physician(rs.getString("name"),
							rs.getString("role"), available, deleted);
					physician.id = id;
					PHYSICIAN_MAP.put(physician.getId(), physician);
				}
			}

		} catch (Exception e) {

		}
		return physician;

	}

	public void save() throws PhysicianException {
		try {
			super.save();
		} catch (HmsException e) {
			throw new PhysicianException(e);
		}
		PHYSICIAN_MAP.put(id, this);

	}

	public void update() throws PhysicianException {
		try {
			super.update();
		} catch (HmsException e) {
			throw new PhysicianException(e);
		}

	}

	public String getName() {
		return name;
	}

	public String getRole() {
		return role;
	}

	public boolean isAvailable() {
		return available;
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

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}
}
