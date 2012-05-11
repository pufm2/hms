package puf.m2.hms.model;

import java.util.List;
import java.util.Map;

import puf.m2.hms.exception.HmsException;
import puf.m2.hms.exception.PatientException;
import puf.m2.hms.model.support.Condition;

public class Patient extends HmsEntity {

    protected static final Map<Integer, Patient> MAP = new CacheAwareMap<Integer, Patient>();

    @DbProp
    private String name;
    @DbProp
    private String dateOfBirth;
    @DbProp
    private String address;
    @DbProp
    private int sex;
    @DbProp
    private String phone;
    @DbProp
    private String biographicHealth;

    public Patient(String name, String dateOfBirth, String address, int sex,
            String phone, String biographicHealth) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.sex = sex;
        this.phone = phone;
        this.biographicHealth = biographicHealth;
    }

    public Patient(int id) {
        super(id);
    }
    
    public Patient() {
    	
    }

    public void save() throws PatientException {
        try {
            super.save();
        } catch (HmsException e) {
            throw new PatientException(e);
        }

        MAP.put(id, this);
    }

    public static List<Patient> getPatients() throws PatientException {
        
    	try {
			return getByCondition(null, Patient.class);
		} catch (HmsException e) {
			throw new PatientException(e);
		}
    }

    public static Patient getPatientById(int id) throws PatientException {

        try {
			return getById(id, Patient.class);
		} catch (HmsException e) {
			throw new PatientException(e);
		}
    }

    public static List<Patient> getPatientByName(String name)
            throws PatientException {

        try {
			return getByCondition(new Condition("name", name), Patient.class);
		} catch (HmsException e) {
			throw new PatientException(e); 
		}
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getBiographicHealth() {
        return biographicHealth;
    }

    public void setBiographicHealth(String biographicHealth) {
        this.biographicHealth = biographicHealth;
    }

}
