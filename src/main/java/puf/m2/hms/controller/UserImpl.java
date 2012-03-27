package puf.m2.hms.controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import puf.m2.hms.model.*;

public class UserImpl extends UserAbstract {

    ResultSet rs;

    public UserImpl(ResultSet rs) {
        super();
        this.rs = rs;
    }

    public UserImpl() {

    }

    @Override
    public boolean login(String username, String password) {

        boolean result = false;

        DatabaseAbstract db = new DatabaseImpl();
        try {
            db.createConnection();
            db.createStatement();
            rs = db.getResultSet("SELECT username FROM User WHERE username='"
                    + username + "' " + "and password = '" + password + "'");

            db.closeConnection();
            // if (rs==null) then login unsucessful
            return (rs != null);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}
