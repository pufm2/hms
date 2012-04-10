package puf.m2.hms.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import puf.m2.hms.db.Database;
import puf.m2.hms.db.DatabaseImpl;

public class Patient {

    private static final Database DB = DatabaseImpl.defaultDb;
    private static final Map<Integer, Patient> PATIENT_MAP = new HashMap<Integer, Patient>(); 

    private int id;
    private String name;
    private String dateOfBirth;
    private String address;
    private int sex;
    private String phone;
    private String biographicHealth;

    public Patient(int id, String name, String dateOfBirth, String address, int sex,
                        String phone, String biographicHealth) {
        this.id = id;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.sex = sex;
        this.phone = phone;
        this.biographicHealth = biographicHealth;
    }
    
    public Patient(int id){
    	this.id = id;
    }

    public int registerNewPatient() throws SQLException {

        int result = 0;
        // Check if PatientID exist in database or not
        if (checkExistPatient(id))
            // patientID already exists in database
            return -1;
        else {
            // insert new patient to database
            String query = "INSERT INTO Patient (id, name, dateOfBirth, address,"
                    + "sex, phone, biographicHealth) VALUES ("
                    + this.id
                    + ",'"
                    + this.name
                    + "','"
                    + this.dateOfBirth
                    + "','"
                    + this.address
                    + "',"
                    + this.sex
                    + ",'"
                    + this.phone
                    + "','"
                    + this.biographicHealth + "')";
            DB.createConnection();
            result = DB.executeUpdate(query); // result != 0 is sucess
            DB.closeConnection();
        }

        return result; // 0 is okey
    }

    public static boolean checkExistPatient(int patientID) throws SQLException {

        DB.createConnection();

        final String queryTemplate = "SELECT patientID FROM Patient WHERE PatientID = {0}";
        
        ResultSet rs = DB.executeQuery(MessageFormat.format(queryTemplate, patientID));

        boolean existed = false;
        
        if (rs.next()) {
            existed = true;
        }
        
        DB.closeConnection();
        return existed;

        
    }

    public static Patient getPatientById(int id) throws SQLException {

        Patient patient = PATIENT_MAP.get(id);
        if (patient != null) {
            return patient;
        }
        DB.createConnection();

        final String queryTempl = "SELECT * FROM Patient WHERE PatientID = {0}";
        ResultSet rs = DB.executeQuery(MessageFormat.format(queryTempl, id));

        if (rs.next()) {
            patient = new Patient(
                rs.getInt("PatientID"),
                rs.getString("PatientName"),
                rs.getString("PatientBirthdate"),
                rs.getString("PatientAddress"),
                rs.getInt("PatientSex"),
                rs.getString("PatientPhone"),
                rs.getString("PatientBiographicHealth"));
        }
        PATIENT_MAP.put(patient.getPatientID(), patient);
        DB.closeConnection();
        return patient;
    }
    

    public static List<Patient> getPatientByName(String patientName) throws SQLException {

        List<Patient> patientList = new ArrayList<Patient>();

        final String queryTemplate = "SELECT * FROM Patient WHERE PatientName = '{0}'";

        DB.createConnection();

        ResultSet rs = DB.executeQuery(MessageFormat.format(queryTemplate, patientName));
        
        while (rs.next()) {
            int id = rs.getInt("PatientID");
            Patient patient = PATIENT_MAP.get(id);
            if (patient == null) {
                patient = new Patient(
                            id,
                            rs.getString("PatientName"),
                            rs.getString("PatientBirthdate"),
                            rs.getString("PatientAddress"),
                            rs.getInt("PatientSex"),
                            rs.getString("PatientPhone"),
                            rs.getString("PatientBiographicHealth"));
                PATIENT_MAP.put(id, patient);
            }
            patientList.add(patient);
        }

        DB.closeConnection();
        return patientList;
    }

    public static int getNewPatientID() throws SQLException {

        int result = 1;
        DB.createConnection();
        String query = "SELECT max(patientID) AS NewPatientID FROM Patient";

        ResultSet rs = DB.executeQuery(query);

        while (rs.next()) {
            result = rs.getInt("NewPatientID") + 1;
        }

        DB.closeConnection();

        return result;
    }

    public void setPatientID(int newPatientID) {
        this.id = newPatientID;
    }

    public int getPatientID() {
        return id;
    }


	public boolean isExist() {
		// TODO Auto-generated method stub
		return false;
	}

}
