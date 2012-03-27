package puf.m2.hms.model;

import java.sql.SQLException;
import java.sql.Statement;

import puf.m2.hms.db.Database;
import puf.m2.hms.db.DatabaseImpl;


public class Physician {

    private static Database db = DatabaseImpl.defaultDb;

    public int updateStatus(String physician) throws SQLException {

        int result = -1;
        int physicianID = Integer.parseInt(physician);
        // Update physician avaiable
        db.createConnection();
        Statement st = db.createStatement();
        String query = "UPDATE Physician" + " SET Avaiable = Not(Avaiable)"
                + " WHERE PhysicianID = " + physicianID;
        result = st.executeUpdate(query);
        db.closeConnection();

        return result;
    }

}
