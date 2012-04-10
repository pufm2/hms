package puf.m2.hms.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import puf.m2.hms.db.Database;
import puf.m2.hms.db.DatabaseImpl;

public class MedicalRecord {

    private int recordID;
    private int patientID;
    private String dateAffect;
    private String recordDetail;
    
    private Database db = DatabaseImpl.defaultDb;

	public MedicalRecord(int patientID, String dateAffect, String recordDetail) {
        // recordId is auto number
    	this.patientID = patientID;
        this.dateAffect = dateAffect;
        this.recordDetail = recordDetail;
    }

    public MedicalRecord() {

	}

	public void insertMedicalRecord() throws SQLException {
    	
    	db.createConnection();
		
		String query = "INSERT INTO MedicalRecord (PatientID, DateAffect, RecordDetail)" +
				" VALUES (" + patientID + ",'" + dateAffect + "','" + recordDetail + "')";
		
		db.executeUpdate(query);
		db.closeConnection();

    }

    public void updateMedicalRecord() throws SQLException {

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
    
    public ResultSet loadListOfPatient() throws SQLException{
    	db.createConnection();
		
		String query = "SELECT Distinct(PatientID) FROM Patient";
		ResultSet rs = db.executeQuery(query);
				
		return rs;
	}    
}
