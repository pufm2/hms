package puf.m2.hms.controller;

import java.sql.SQLException;
import java.sql.Statement;

import puf.m2.hms.model.DatabaseAbstract;
import puf.m2.hms.model.DatabaseImpl;

public class PhysicianImpl extends PhysicianAbstract {

    DatabaseAbstract db;
    Statement st;
    String query;

    public PhysicianImpl(DatabaseAbstract db) {
        super();
        this.db = db;
        query = "";
    }

    public PhysicianImpl() {
        super();
        this.db = new DatabaseImpl();
        query = "";
    }

    @Override
    public int updateStatus(String physician) throws ClassNotFoundException,
            SQLException {

        int result = -1;
        int physicianID = Integer.parseInt(physician);
        // Update physician avaiable
        db.createConnection();
        st = db.createStatement();
        query = "UPDATE Physician" + " SET Avaiable = Not(Avaiable)"
                + " WHERE PhysicianID = " + physicianID;
        result = st.executeUpdate(query);
        db.closeConnection();

        return result;
    }

}
