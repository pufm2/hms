package puf.m2.hms.controller;

import java.sql.SQLException;
import java.sql.Statement;

import puf.m2.hms.model.DatabaseAbstract;
import puf.m2.hms.model.DatabaseImpl;

public class AssignPhysicianImpl extends AssignPhysicianAbstract {

    DatabaseAbstract db;
    Statement st;
    String query;

    // Constructor
    public AssignPhysicianImpl() {
        super();
        db = new DatabaseImpl();
        query = "";
    }

    // Constructor
    public AssignPhysicianImpl(DatabaseAbstract db, Statement st, String query) {
        super();
        this.db = db;
        this.st = st;
        this.query = query;
    }

    @Override
    public int insertNewAssign(String patientID, String physicianID)
            throws ClassNotFoundException, SQLException {
        int result = -1;

        db.createConnection();
        st = db.createStatement();
        query = "INSERT INTO Assign (PatientID, PhysicianID) " + " VALUES ( "
                + patientID + "," + physicianID + ")";
        result = st.executeUpdate(query);

        db.closeConnection();
        return result;
    }

}
