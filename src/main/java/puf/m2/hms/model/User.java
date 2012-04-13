package puf.m2.hms.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

import puf.m2.hms.db.Database;
import puf.m2.hms.db.DatabaseFactory;

public class User {

	private static final Database DB = DatabaseFactory.DEFAULT_DB;
	private static final Map<Integer, User> USER_MAP = new HashMap<Integer, User>();

	private int id;
	private String name;
	private String password;

	private String email;
	private String role;

	public User(String name, String password, String email, String role) {

		this.name = name;
		this.password = password;
		this.email = email;
		this.role = role;
	}

	public static User login(String username, String password) {

		User user = null;
		try {
			DB.createConnection();
			final String queryTemplate = "select * from User where name = ''{0}'' and password = ''{1}''";
			ResultSet rs = DB.executeQuery(MessageFormat.format(queryTemplate,
					username, password));

			if (rs.next()) {
				int id = rs.getInt("id");
				user = USER_MAP.get(id);
				if (user == null) {
					user = new User(username, password,
							rs.getString("email"), rs.getString("role"));
					user.id = id;
					USER_MAP.put(id, user);
				}
			}

			DB.closeConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return user;
	}
	
	public void save() throws HmsException {
		id = getNextFreeId();
		
		final String queryTemple = "insert into User values({0}, ''{1}'', ''{2}'', ''{3}'', ''{4}'')";
    	try {
    		DB.createConnection();
    		DB.executeUpdate(MessageFormat.format(queryTemple, id, name, password, email, role));
    		DB.closeConnection();
    		
    		USER_MAP.put(id, this);
    	} catch (SQLException e) {
    		throw new HmsException(e);
    	}
	}
	
	public void update() throws HmsException {

		final String queryTemple = "update User set name = ''{0}'', password = ''{1}'', " +
				"email = ''{2}'', role = ''{3}'' where id = {4})";
    	try {
    		DB.createConnection();
    		DB.executeUpdate(MessageFormat.format(queryTemple, name, password, email, role, id));
    		DB.closeConnection();
    	} catch (SQLException e) {
    		throw new HmsException(e);
    	}
	}
	
    private int getNextFreeId() throws HmsException {
        int freeId = 1;
        try {
            DB.createConnection();
        
        String query = "select max(id) as maxId from User";

        ResultSet rs = DB.executeQuery(query);

        if (rs.next()) {
            freeId = rs.getInt("maxId") + 1;
        }

        DB.closeConnection();
        } catch (SQLException e) {
            throw new HmsException(e);
        }

        return freeId;
    }

	public String getUsername() {
		return name;
	}

	public void setUsername(String username) {
		this.name = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUseremail() {
		return email;
	}

	public void setUseremail(String useremail) {
		this.email = useremail;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public int getId() {
		return id;
	}

}
