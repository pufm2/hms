package puf.m2.hms.model;

import java.sql.SQLException;
import java.sql.Statement;

import puf.m2.hms.db.Database;
import puf.m2.hms.db.DatabaseImpl;


public class Physician {

    Database db;
    Statement st;
    String query;

    public Physician(Database db) {
        this.db = db;
        query = "";
    }

    public Physician() {

        this.db = new DatabaseImpl();
        query = "";
    }

    public int updateStatus(String physician) throws SQLException {

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
