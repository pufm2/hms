package puf.m2.hms.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

import puf.m2.hms.exception.HmsException;
import puf.m2.hms.exception.UserException;

public class User extends HmsEntity {

	private static final Map<Integer, User> USER_MAP = new HashMap<Integer, User>();

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

	public User() {

	}

	public static User login(String username, String password) throws UserException {

		User user = null;
		DB.createConnection();
		final String queryTemplate = "select * from User where name = ''{0}'' and password = ''{1}''";
		ResultSet rs = DB.executeQuery(MessageFormat.format(queryTemplate,
				username, password));

		try {
			if (rs != null) {
				int id = rs.getInt("id");
				user = USER_MAP.get(id);
				if (user == null) {
					user = new User(username, password, rs.getString("email"),
							rs.getString("role"));
					user.id = id;
					USER_MAP.put(id, user);
				}
			} else {
				throw new UserException(username, password);
			}
		} catch (SQLException e) {
		}

		DB.closeConnection();
		return user;
	}

	public void save() throws HmsException {
		id = getNextFreeId();

		final String queryTemple = "insert into User values({0}, ''{1}'', ''{2}'', ''{3}'', ''{4}'')";
		DB.createConnection();
		DB.executeUpdate(MessageFormat.format(queryTemple, id, name, password,
				email, role));
		DB.closeConnection();

		USER_MAP.put(id, this);
	}

	public void update() throws HmsException {

		final String queryTemple = "update User set name = ''{0}'', password = ''{1}'', "
				+ "email = ''{2}'', role = ''{3}'' where id = {4})";
		DB.createConnection();
		DB.executeUpdate(MessageFormat.format(queryTemple, name, password,
				email, role, id));
		DB.closeConnection();
	}

	public boolean isValidUser() {
		if (this.name != "" & this.password != "")
			return true;
		return false;
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

}
