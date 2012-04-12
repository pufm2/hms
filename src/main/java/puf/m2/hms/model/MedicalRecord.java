package puf.m2.hms.model;

import java.sql.SQLException;
import java.util.Date;

import puf.m2.hms.db.Database;
import puf.m2.hms.db.DatabaseImpl;

public class MedicalRecord {

    private int recordID;
    private int patientID;
    private Date dateAffect;
    private String recordDetail;
    
    private Database db = DatabaseImpl.defaultDb;

	public MedicalRecord(int patientID, Date dateAffect, String recordDetail) {
        // recordId is auto number
    	this.patientID = patientID;
        this.dateAffect = dateAffect;
        this.recordDetail = recordDetail;
    }

    public MedicalRecord() {

	}

	public void save() throws HmsException {
    	
    	try {
			db.createConnection();
			
			String query = "INSERT INTO MedicalRecord (PatientID, DateAffect, RecordDetail)" +
					" VALUES (" + patientID + ",'" + dateAffect + "','" + recordDetail + "')";
			
			db.executeUpdate(query);
			db.closeConnection();
		} catch (SQLException e) {
			throw new HmsException(e);
		}


    }

    public void update() throws SQLException {

    	db.createConnection();
		
		String query = "UPDATE MedicalRecord " +
				" SET 	dateAffect = " + dateAffect + "," +
				" 		recordDetail = " + recordDetail +
				" WHERE recordID = " + recordID +
					" AND patientID = " + patientID;
		
		db.executeUpdate(query);
		db.closeConnection();

    }

    public void deleteMedicalRecord() {

    }
  
}
