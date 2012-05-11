package puf.m2.hms.model;

import java.util.Date;
import java.util.List;
import java.util.Map;

import puf.m2.hms.exception.HmsException;
import puf.m2.hms.exception.PhysicianAssignmentException;

public class PhysicianAssignment extends HmsEntity {

	protected static final Map<Integer, PhysicianAssignment> MAP = new CacheAwareMap<Integer, PhysicianAssignment>();

    @DbProp
	private Patient patient;
    @DbProp
    private Physician physician;
    @DbProp
    private Date startDate;
    @DbProp
    private Date endDate;

    public PhysicianAssignment() {
    	
    }
    
    public PhysicianAssignment(Patient patient, Physician physician) {
        this(patient, physician, new Date(), new Date());
    }

    public PhysicianAssignment(Patient patient, Physician physician,
            Date startDate, Date endDate) {

        this.patient = patient;
        this.physician = physician;
        this.startDate = startDate;
        this.endDate = endDate;
    }
    

    public void save() throws PhysicianAssignmentException {
        
        try {
			super.save();
		} catch (HmsException e) {
			throw new PhysicianAssignmentException(e);
		}
		
		MAP.put(id, this);
    }

    
	public static List<PhysicianAssignment> getPhysicianAssignments() throws PhysicianAssignmentException {

		try {
			return getByCondition(null, PhysicianAssignment.class);
		} catch (HmsException e) {
			throw new PhysicianAssignmentException(e);
		}
	}

	public Date getEndDate() {
		return endDate;
	}

	public Patient getPatient() {
		return patient;
	}

	public Physician getPhysician() {
		return physician;
	}

	public Date getStartDate() {
		return startDate;
	}

}
