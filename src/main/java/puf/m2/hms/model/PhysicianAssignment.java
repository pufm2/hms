package puf.m2.hms.model;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import puf.m2.hms.db.Database;
import puf.m2.hms.db.DatabaseImpl;


public class PhysicianAssignment {

    private int assignID;
    private int patientID;
    private int physicianID;
    private Date startDate;
    private Date endTime;
    private Date startTime;
    private Date endDate;

    private static Database db = DatabaseImpl.defaultDb;


    public PhysicianAssignment(int assignID, int patientID, int physicianID,
            Date startDate, Date endTime, Date startTime, Date endDate) {

        this.assignID = assignID;
        this.patientID = patientID;
        this.physicianID = physicianID;
        this.startDate = startDate;
        this.endTime = endTime;
        this.startTime = startTime;
        this.endDate = endDate;
    }

    public PhysicianAssignment() {
        
    }

    public int insertNewAssign(String patientID, String physicianID) throws SQLException {
        
        db.createConnection();
        String query = "INSERT INTO Assign (PatientID, PhysicianID) " + " VALUES ( "
                + patientID + "," + physicianID + ")";
        int result = db.executeUpdate(query);

        db.closeConnection();
        return result;
    }

    public int getAssignID() {
        return assignID;
    }

    public int getPatientID() {
        return patientID;
    }

    public int getPhysicianID() {
        return physicianID;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndTime() {
        return endTime;
    }

    public Date getStartTime() {
        return startTime;
    }

    public Date getEndDate() {
        return endDate;
    }

}
