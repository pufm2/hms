package puf.m2.hms.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import puf.m2.hms.db.Database;
import puf.m2.hms.db.DatabaseFactory;
import puf.m2.hms.utils.DateUtils;

public class MedicalRecord {

    private static Map<Integer, MedicalRecord> MR_MAP = new HashMap<Integer, MedicalRecord>();
    private static final Database DB = DatabaseFactory.DEFAULT_DB;
	
	private int id;
    private Patient patient;
    private Date dateAffect;
    private String detail;

	public MedicalRecord(Patient patient, Date dateAffect, String detail) {
    	this.patient = patient;
        this.dateAffect = dateAffect;
        this.detail = detail;
    }

	public void save() throws HmsException {
    	try {
    		id = getNextFreeId();
			DB.createConnection();
			
			String queryTemplate = "insert into MedicalRecord values({0}, {1}, ''{2}'', ''{3}'')";
			
			DB.executeUpdate(MessageFormat.format(queryTemplate, id, patient.getId(),
					DateUtils.dateToString(dateAffect), detail));
			DB.closeConnection();
			
			MR_MAP.put(id, this);
		} catch (SQLException e) {
			throw new HmsException(e);
		}
    }
	
	public void update() throws HmsException {
    	try {
			DB.createConnection();
			
			String queryTemplate = "update MedicalRecord set patientId = {0}, dateAfect = ''{1}'', detail = ''{2}'' where id = {3})";
			
			DB.executeUpdate(MessageFormat.format(queryTemplate, patient.getId(),
					DateUtils.dateToString(dateAffect), detail, id));
			DB.closeConnection();
		} catch (SQLException e) {
			throw new HmsException(e);
		}
    }

    private int getNextFreeId() throws HmsException {
    	int freeId = 1;
		try {
			DB.createConnection();
		
		String query = "select max(id) as maxId from MedicalRecord";

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
    
    public void deleteMedicalRecord() {

    }
  
}
