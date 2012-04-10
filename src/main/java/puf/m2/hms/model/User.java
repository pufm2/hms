package puf.m2.hms.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import puf.m2.hms.db.Database;
import puf.m2.hms.db.DatabaseImpl;

public class User {
    
    private static Database db = DatabaseImpl.defaultDb;
    
    private String username;
    private String password;
    private String useremail;
    private String usertypeCode;

    public String login(String username, String password) {
    	
    	String result = "";
        try {
            db.createConnection();
            ResultSet rs = db.executeQuery("SELECT usertypecode FROM User WHERE username='"
                    + username + "' " + "and password = '" + password + "'");

            
            // if (rs==null) then login unsucessful
            while (rs.next())
            	result = rs.getString("usertypecode");
            
            db.closeConnection();           
        } catch (SQLException e) {
            e.printStackTrace();
        }
         
        return result;
    }
    
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getUseremail() {
        return useremail;
    }
    public void setUseremail(String useremail) {
        this.useremail = useremail;
    }
    public String getUsertypeCode() {
        return usertypeCode;
    }
    public void setUsertypeCode(String usertypeCode) {
        this.usertypeCode = usertypeCode;
    }

}
