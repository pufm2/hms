package puf.m2.hms.model;

import java.sql.ResultSet;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import puf.m2.hms.utils.DateUtils;

public class MedicalRecord extends HmsEntity {

	private static Map<Integer, MedicalRecord> MR_MAP = new HashMap<Integer, MedicalRecord>();

	private int id;
	private Patient patient;
	private Date dateAffect;
	private String detail;

	public MedicalRecord(Patient patient, Date dateAffect, String detail) {
		this.patient = patient;
		this.dateAffect = dateAffect;
		this.detail = detail;
	}

	public MedicalRecord() {

	}

	public void save() throws Exception {
		String queryTemplate = "";
		id = getNextFreeId();

		DB.createConnection();

		queryTemplate = "insert into MedicalRecord values({0}, {1}, ''{2}'', ''{3}'')";

		DB.executeUpdate(MessageFormat.format(queryTemplate, id,
				patient.getId(), DateUtils.dateToString(dateAffect), detail));
		DB.closeConnection();

		MR_MAP.put(id, this);
	}

	public void update() throws Exception {
		String queryTemplate = "";
		DB.createConnection();

		queryTemplate = "update MedicalRecord set patientId = {0}, dateAfect = ''{1}'', detail = ''{2}'' where id = {3})";

		DB.executeUpdate(MessageFormat.format(queryTemplate, patient.getId(),
				DateUtils.dateToString(dateAffect), detail, id));
		DB.closeConnection();

	}

	public static List<MedicalRecord> loadMedicalRecord(Patient patient)
			throws Exception {

		final String queryTemplate = "SELECT * FROM MedicalRecord WHERE patientId = {0}";
		List<MedicalRecord> mrList = new ArrayList<MedicalRecord>();

		DB.createConnection();

		ResultSet rs = DB.executeQuery(MessageFormat.format(queryTemplate,
				patient.getId()));

		while (rs.next()) {
			int id = rs.getInt("id");
			MedicalRecord mr = MR_MAP.get(id);

			if (mr == null) {
				Date dateAffect = DateUtils.parseDate(rs
						.getString("dateAffect"));

				mr = new MedicalRecord(patient, dateAffect,
						rs.getString("detail"));
				mr.id = id;

				MR_MAP.put(id, mr);
			}

			mrList.add(mr);
		}

		DB.closeConnection();
		return mrList;
	}

	public static MedicalRecord loadMedicalRecordById(int id) throws Exception {

		final String queryTemplate = "SELECT * FROM MedicalRecord WHERE id = {0}";

		MedicalRecord mr = MR_MAP.get(id);
		if (mr == null) {

			DB.createConnection();

			ResultSet rs = DB.executeQuery(MessageFormat.format(queryTemplate,
					id));

			if (rs.next()) {
				Date dateAffect = DateUtils.parseDate(rs
						.getString("dateAffect"));
				int patientId = rs.getInt("patientId");

				mr = new MedicalRecord(Patient.getPatientById(patientId),
						dateAffect, rs.getString("detail"));
				mr.id = id;

				MR_MAP.put(id, mr);

			}

			DB.closeConnection();

		}
		return mr;
	}

	public void deleteMedicalRecord() {

	}

	public Date getDateAffect() {
		return dateAffect;
	}

	public void setDateAffect(Date dateAffect) {
		this.dateAffect = dateAffect;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public int getId() {
		return id;
	}

	public static void main(String[] args) throws Exception {
		MedicalRecord r = new MedicalRecord();
		System.out.println(r.getNextFreeId());

	}
}
