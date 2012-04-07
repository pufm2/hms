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
    
    Database db = DatabaseImpl.defaultDb;
	String query = "";
	ResultSet rs;

	public MedicalRecord(int patientID, String dateAffect, String recordDetail) {
        // recordId is auto number
    	this.patientID = patientID;
        this.dateAffect = dateAffect;
        this.recordDetail = recordDetail;
    }

    public MedicalRecord() {
		// TODO Auto-generated constructor stub
	}

	public int insertMedicalRecord() throws SQLException {
    	int result = 0;
    	
    	db.createConnection();
		db.createStatement();
		Statement st = db.createStatement();
		
		query = " INSERT INTO MedicalRecord (PatientID, DateAffect, RecordDetail)" +
				" VALUES (" + patientID + ",'" + dateAffect + "','" + recordDetail + "')";
		
		st.executeUpdate(query);
		db.closeConnection();
		// Add more statement to check status of insert
		
		
		return result;
    }

    public int updateMedicalRecord() throws SQLException {
    	int result = 0;
    	
    	db.createConnection();
		db.createStatement();
		Statement st = db.createStatement();
		
		query = " UPDATE MedicalRecord " +
				" SET 	dateAffect = " + dateAffect + "," +
				" 		recordDetail = " + recordDetail +
				" WHERE recordID = " + recordID +
					" AND patientID = " + patientID;
		
		st.executeUpdate(query);
		// Add more statement to check status of update
		db.closeConnection();
		
		
		return result;
    }

    public int deleteMedicalRecord() {
        int result = 0;
		// Add more statement to check status of update
        
		return result;
    }
    
    public ResultSet loadListOfPatient() throws SQLException{
    	db.createConnection();
		db.createStatement();
		
		query = "SELECT Distinct(PatientID) FROM Patient";
		rs = db.getResultSet(query);
				
		return rs;
	}    
}
