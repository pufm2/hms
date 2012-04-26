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
import puf.m2.hms.exception.DbException;
import puf.m2.hms.utils.DateUtils;

public class PhysicianAssignment {

	private static final Map<Integer, PhysicianAssignment> PA_MAP = new HashMap<Integer, PhysicianAssignment>();
	private static final Database DB = DatabaseFactory.DEFAULT_DB;

	public static List<PhysicianAssignment> getPhysicianAssignments()
			throws Exception {

		List<PhysicianAssignment> paList = new ArrayList<PhysicianAssignment>();

		final String query = "select * from PhysicianAssignment";
		try {
			DB.createConnection();
			ResultSet rs = DB.executeQuery(query);

			while (rs.next()) {
				int id = rs.getInt("id");

				PhysicianAssignment pa = PA_MAP.get(id);
				if (pa == null) {
					Date startDate = DateUtils.parseDate(rs
							.getString("startDate"));
					Date endDate = DateUtils.parseDate(rs.getString("endDate"));
					Patient patient = Patient.getPatientById(rs
							.getInt("patientId"));
					Physician physician = Physician.getPhysicianById(rs
							.getInt("physicianId"));

					pa = new PhysicianAssignment(patient, physician, startDate,
							endDate);

					PA_MAP.put(id, pa);
				}
				paList.add(pa);
			}
			DB.closeConnection();
		} catch (Exception e) {
			throw new DbException(query);
		}

		return paList;

	}

	private int id;
	private Patient patient;
	private Physician physician;
	private Date startDate;

	private Date endDate;

	public PhysicianAssignment(Patient patient, Physician physician) {
		this(patient, physician, new Date(), new Date());
	}

	public PhysicianAssignment(Patient patient, Physician physician,
			Date startDate, Date endDate) {

		this.patient = patient;
		this.physician = physician;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public int getId() {
		return id;
	}

	private int getNextFreeId() throws Exception {
		int freeId = 1;
		String query = "";
		try {
			DB.createConnection();

			query = "select max(id) as maxId from PhysicianAssignment";

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

	public Patient getPatient() {
		return patient;
	}

	public Physician getPhysician() {
		return physician;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void save() throws Exception {
		id = getNextFreeId();
		final String queryTemple = "insert into PhysicianAssignment values({0}, {1}, {2}, ''{3}'', ''{4}'')";
		DB.createConnection();
		DB.executeUpdate(MessageFormat.format(queryTemple, id, patient.getId(),
				physician.getId(), DateUtils.dateToString(startDate),
				DateUtils.dateToString(endDate)));
		DB.closeConnection();

		PA_MAP.put(id, this);

		physician.setAvailable(false);
		physician.save();
	}

}
