package puf.m2.hms.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import puf.m2.hms.db.Database;
import puf.m2.hms.db.DatabaseImpl;

public class Patient {

    private static Database db = DatabaseImpl.defaultDb;
    
    private ResultSet rs;
    private String query;

    private int id;
    private String name;
    private String dateOfBirth;
    private String address;
    private int sex;
    private String phone;
    private String biographicHealth;

    public Patient(int id,
            String name, String dateOfBirth, String address, int sex,
            String phone, String biographicHealth) {
        this.id = id;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.sex = sex;
        this.phone = phone;
        this.biographicHealth = biographicHealth;
    }

    public int registerNewPatient() throws SQLException {

        int result = 0;
        db.createConnection();
        Statement st = db.createStatement();

        // Check if PatientID exist in database or not
        if (checkExistPatient(id))
            // patientID already exists in database
            return -1;
        else {
            // insert new patient to database
            query = "INSERT INTO Patient (id, name, dateOfBirth, address,"
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
            result = st.executeUpdate(query); // result != 0 is sucess
        }

        db.closeConnection();
        return result; // 0 is okey
    }

    public boolean checkExistPatient(int patientID) throws SQLException {

        boolean result = false;
        String query = "";

        db.createConnection();
        db.createStatement();

        query = "SELECT patientID FROM Patient WHERE PatientID = " + patientID;
        rs = db.getResultSet(query);

        // If rs is not null, then patientID already exists in database
        while (rs.next())
            result = true;

        db.closeConnection();
        return result;
    }

    public ArrayList<Object> lookupPatientInformation(int PatientID) throws SQLException {

        ArrayList<Object> result = null;
        db.createConnection();
        db.createStatement();

        query = "SELECT * FROM Patient WHERE PatientID = " + id + ")";
        rs = db.getResultSet(query);

        // PatientID exist in database
        if (rs != null) {
            result = new ArrayList<Object>();
            result.add(rs.getInt("PatientID"));
            result.add(rs.getInt("PatientName"));
            result.add(rs.getInt("PatientBirthdate"));
            result.add(rs.getInt("patientAddress"));
            result.add(rs.getInt("patientSex"));
            result.add(rs.getInt("patientPhone"));
            result.add(rs.getInt("patientBiographicHealth"));
        } else {
            // method will return null
        }

        db.closeConnection();
        return result;
    }

    public int getNewPatientID() throws SQLException {

        int result = 0;
        db.createConnection();
        db.createStatement();
        query = "SELECT max(patientID) AS NewPatientID FROM Patient";

        rs = db.getResultSet(query);

        while (rs.next()) {
            result = rs.getInt("NewPatientID") + 1;
        }

        db.closeConnection();

        return result;
    }

    public void setPatientID(int newPatientID) {
        this.id = newPatientID;
    }

    public int getPatientID() {
        return id;
    }

    public static ResultSet lookupPatient(int patientID, String patientName) throws SQLException {

        ResultSet result = null;

        final String query = "SELECT * FROM Patient WHERE PatientID = " + patientID
                + " AND PatientName = '" + patientName + "'";

        // MUST BE CHANGED
        JOptionPane.showMessageDialog(null, "Should be changed later");

        db.createConnection();
        db.createStatement();

        result = db.getResultSet(query);

        db.closeConnection();
        return result;
    }

}
