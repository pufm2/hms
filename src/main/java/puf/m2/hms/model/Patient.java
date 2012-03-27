package puf.m2.hms.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import puf.m2.hms.db.Database;
import puf.m2.hms.db.DatabaseImpl;

public class Patient {

    Database db;
    ResultSet rs;
    String query = "";

    int patientID;
    String patientName;
    String patientBirthdate;
    String patientAddress;
    int patientSex;
    String patientPhone;
    String patientBiographicHealth;

    public Patient(int patientID, String patientName,
            String patientBirthdate, String patientAddress, int patientSex,
            String patientPhone, String patientBiographicHealth) {

        this.patientID = patientID;
        this.patientName = patientName;
        this.patientBirthdate = patientBirthdate;
        this.patientAddress = patientAddress;
        this.patientSex = patientSex;
        this.patientPhone = patientPhone;
        this.patientBiographicHealth = patientBiographicHealth;

        db = new DatabaseImpl();
    }

    // Constructor
    public Patient() {

        this.patientID = 0;
        this.patientName = "";
        this.patientBirthdate = "";
        this.patientAddress = "";
        this.patientSex = 0;
        this.patientPhone = "";
        this.patientBiographicHealth = "";

        db = new DatabaseImpl();
    }

    public int registerNewPatient() throws SQLException {

        int result = 0;

        db.createConnection();
        Statement st = db.createStatement();

        // Check if PatientID exist in database or not
        if (checkExistPatient(patientID))
            // patientID already exists in database
            return -1;
        else {
            // insert new patient to database
            query = "INSERT INTO Patient (patientID, patientName, patientBirthdate, patientAddress,"
                    + "patientSex, patientPhone, patientBiographicHealth) VALUES ("
                    + this.patientID
                    + ",'"
                    + this.patientName
                    + "','"
                    + this.patientBirthdate
                    + "','"
                    + this.patientAddress
                    + "',"
                    + this.patientSex
                    + ",'"
                    + this.patientPhone
                    + "','"
                    + this.patientBiographicHealth + "')";
            // System.out.println(query);
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

        query = "SELECT * FROM Patient WHERE PatientID = " + patientID + ")";
        System.out.println(query);
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
        this.patientID = newPatientID;
    }

    public int getPatientID() {
        return patientID;
    }

    public ResultSet lookupPatient(int patientID, String patientName) throws SQLException {

        ResultSet result = null;

        query = "SELECT * FROM Patient WHERE PatientID = " + patientID
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
