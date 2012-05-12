package puf.m2.hms.model;

import java.util.List;
import java.util.Map;

import puf.m2.hms.exception.HmsException;
import puf.m2.hms.exception.UserException;
import puf.m2.hms.model.support.Condition;

public class User extends HmsEntity {

	protected static final Map<Integer, User> MAP = new CacheAwareMap<Integer, User>();

	@DbProp
	private String name;
	@DbProp
	private String password;
	@DbProp
	private String email;
	@DbProp
	private String role;
	@DbProp
	private boolean deleted;

	public User() {

	}

	public User(String name, String password, String email, String role,
			boolean deleted) {

		this.name = name;
		this.password = password;
		this.email = email;
		this.role = role;
		this.deleted = deleted;
	}

	public User(String name, String password, String email, String role) {

		this(name, password, email, role, false);
	}

	public static User login(String username, String password) {

		Condition c = new Condition("name", username);
		c.and(new Condition("password", password)).and(
				new Condition("deleted", "0"));

		User user = null;
		try {
			List<User> userList = getByCondition(c, User.class);
			if (userList.size() == 1) {
				user = userList.get(0);
			}
		} catch (HmsException e) {

		}

		return user;

	}

	public static User getUserByName(String username) {

		Condition c = new Condition("name", username);
		c.and(new Condition("deleted", "0"));

		User user = null;
		try {
			List<User> userList = getByCondition(c, User.class);
			if (userList.size() == 1) {
				user = userList.get(0);
			}
		} catch (HmsException e) {

		}

		return user;

	}

	public void save() throws UserException {
		try {
			super.save();
		} catch (HmsException e) {
			throw new UserException(e);
		}
		MAP.put(id, this);
	}

	public void update() throws UserException {

		try {
			super.update();
		} catch (HmsException e) {
			throw new UserException(e);
		}
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

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

}
