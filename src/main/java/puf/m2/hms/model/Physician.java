package puf.m2.hms.model;

import java.util.List;
import java.util.Map;

import puf.m2.hms.exception.HmsException;
import puf.m2.hms.exception.PhysicianException;
import puf.m2.hms.model.support.Condition;

public class Physician extends HmsEntity {

	protected static final Map<Integer, Physician> MAP = new CacheAwareMap<Integer, Physician>();

	@DbProp
	private String name;
	@DbProp
	private String role;
	@DbProp
	private boolean available;
	@DbProp
	private boolean deleted;

	public Physician() {
		
	}
	
	public Physician(String name, String role, boolean available) {

		this(name, role, available, false);
	}

	public Physician(String name, String role, boolean available,
			boolean deleted) {

		this.name = name;
		this.role = role;
		this.available = available;
		this.deleted = deleted;
	}

	public static List<Physician> getDoctors() throws PhysicianException {
		
		Condition c = new Condition("role", Role.Doctor.name());
		c.and(new Condition("deleted", "0"));
		try {
			return getByCondition(c, Physician.class);
		} catch (HmsException e) {
			throw new PhysicianException(e);
		}
	}

	public static List<Physician> getNurses() throws PhysicianException {
		Condition c = new Condition("role", Role.Nurse.name());
		c.and(new Condition("deleted", "0"));
		try {
			return getByCondition(c, Physician.class);
		} catch (HmsException e) {
			throw new PhysicianException(e);
		}
	}

	public static Physician getPhysicianById(int id) throws PhysicianException {

		Condition c = new Condition("id", Integer.toString(id));
		c.and(new Condition("deleted", "0"));
		try {
			List<Physician> physicians = getByCondition(c, Physician.class);
			if (physicians.size() == 1) {
				return physicians.get(0);
			} else {
				return null;
			}
		} catch (HmsException e) {
			throw new PhysicianException(e);
		}
	}

	public static Physician getPhysicianByName(String name) {

		Condition c = new Condition("name", name);
		c.and(new Condition("deleted", "0"));
		Physician physician = null;
		try {
			List<Physician> physicians = getByCondition(c, Physician.class);
			if (physicians.size() == 1) {
				physician = physicians.get(0);
			}
		} catch (HmsException e) {
			
		}
		return physician;
	}

	public void save() throws PhysicianException {
		try {
			super.save();
		} catch (HmsException e) {
			throw new PhysicianException(e);
		}
		MAP.put(id, this);

	}

	public void update() throws PhysicianException {
		try {
			super.update();
		} catch (HmsException e) {
			throw new PhysicianException(e);
		}

	}

	public String getName() {
		return name;
	}

	public String getRole() {
		return role;
	}

	public boolean isAvailable() {
		return available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}

	public void setName(String name) {
		this.name = name;
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
