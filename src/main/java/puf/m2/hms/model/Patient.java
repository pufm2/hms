package puf.m2.hms.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import puf.m2.hms.db.Database;
import puf.m2.hms.db.DatabaseFactory;

public class Patient {

    private static final Database DB = DatabaseFactory.DEFAULT_DB;
    private static final Map<Integer, Patient> PATIENT_MAP = new HashMap<Integer, Patient>();

    private int id;
    private String name;
    private String dateOfBirth;
    private String address;
    private int sex;
    private String phone;
    private String biographicHealth;

    public Patient(String name, String dateOfBirth, String address,
            int sex, String phone, String biographicHealth) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.sex = sex;
        this.phone = phone;
        this.biographicHealth = biographicHealth;
    }

    public Patient(int id) {
        this.id = id;
    }

    public void save() throws HmsException {

        id = getNextFreeId();
    	final String queryTemplate = "insert into Patient values({0},''{1}'',''{2}'',''{3}'',{4},''{5}'',''{6}'')";

        try {
			DB.createConnection();
			
			DB.executeUpdate(MessageFormat.format(queryTemplate, id, name,
	                dateOfBirth, address, sex, phone, biographicHealth));
	        
			DB.closeConnection();
			
			PATIENT_MAP.put(id, this);
		} catch (SQLException e) {
			throw new HmsException(e);
		}
    }
    
    private int getNextFreeId() throws HmsException {
    	int freeId = 1;
		try {
			DB.createConnection();
		
		String query = "select max(id) as maxId from Patient";

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
    
    public static boolean checkExistPatient(int id) throws HmsException {


        boolean existed = false;
        try {
            DB.createConnection();
            final String queryTemplate = "select id from Patient where id = {0}";

            ResultSet rs = DB.executeQuery(MessageFormat.format(queryTemplate, id));

            if (rs.next()) {
                existed = true;
            }

            DB.closeConnection();
        } catch (SQLException e) {
            throw new HmsException(e);

        }
        return existed;

    }

    public static List<Patient> getPatients() throws HmsException {
        List<Patient> patientList = new ArrayList<Patient>();

        final String query = "select * from Patient";

        try {
            DB.createConnection();

            ResultSet rs = DB.executeQuery(query);

            while (rs.next()) {
                int id = rs.getInt("id");
                Patient patient = PATIENT_MAP.get(id);
                
                if (patient == null) {
                    patient = new Patient(rs.getString("name"),
                                rs.getString("dateOfBirth"),
                                rs.getString("address"),
                                rs.getInt("sex"),
                                rs.getString("phone"),
                                rs.getString("biographicHealth"));
                    patient.id = id;
                    
                    PATIENT_MAP.put(id, patient);
                }
                
                patientList.add(patient);
            }

            DB.closeConnection();
        } catch (SQLException e) {
            throw new HmsException(e);
        }

        return patientList;

    }

    public static Patient getPatientById(int id) throws HmsException {

        Patient patient = PATIENT_MAP.get(id);
        if (patient != null) {
            return patient;
        }
        try {
            DB.createConnection();

            final String queryTempl = "SELECT * FROM Patient WHERE id = {0}";
            ResultSet rs = DB.executeQuery(MessageFormat.format(queryTempl, id));

            if (rs.next()) {
                patient = new Patient(rs.getString("name"),
                        rs.getString("dateOfBirth"), rs.getString("address"),
                        rs.getInt("sex"), rs.getString("phone"),
                        rs.getString("biographicHealth"));
                patient.id = id;
                
                PATIENT_MAP.put(patient.getId(), patient);
            }

            DB.closeConnection();
        } catch (SQLException e) {
            throw new HmsException(e);
        }

        return patient;
    }

    public static List<Patient> getPatientByName(String patientName)
            throws SQLException {

        List<Patient> patientList = new ArrayList<Patient>();

        final String queryTemplate = "SELECT * FROM Patient WHERE PatientName = ''{0}''";

        DB.createConnection();

        ResultSet rs = DB.executeQuery(MessageFormat.format(queryTemplate, patientName));

        while (rs.next()) {
            int id = rs.getInt("id");
            Patient patient = PATIENT_MAP.get(id);
            
            if (patient == null) {
                patient = new Patient(rs.getString("name"),
                            rs.getString("dateOfBirth"),
                            rs.getString("address"),
                            rs.getInt("sex"),
                            rs.getString("phone"),
                            rs.getString("biographicHealth"));
                patient.id = id;
                
                PATIENT_MAP.put(id, patient);
            }
            patientList.add(patient);
        }

        DB.closeConnection();
        return patientList;
    }

    public int getId() {
        return id;
    }

    public boolean isExisted() throws HmsException {
        return checkExistPatient(id);
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
