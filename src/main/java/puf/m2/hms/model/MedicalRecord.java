package puf.m2.hms.model;

import java.util.Date;
import java.util.List;
import java.util.Map;

import puf.m2.hms.exception.HmsException;
import puf.m2.hms.exception.MedicalRecordException;
import puf.m2.hms.model.support.Condition;

public class MedicalRecord extends HmsEntity {

	protected static Map<Integer, MedicalRecord> MAP = new CacheAwareMap<Integer, MedicalRecord>();

	@DbProp
	private Patient patient;
	@DbProp
	private Date dateAffect;
	@DbProp
	private String detail;

	public MedicalRecord() {
		
	}
	
	public MedicalRecord(Patient patient, Date dateAffect, String detail) {
		this.patient = patient;
		this.dateAffect = dateAffect;
		this.detail = detail;
	}

	public void save() throws MedicalRecordException {
		try {
			super.save();
		} catch (HmsException e) {
			throw new MedicalRecordException(e);
		}
		MAP.put(id, this);
	}

	public void update() throws MedicalRecordException {
		
		try {
			super.update();
		} catch (HmsException e) {
			throw new MedicalRecordException(e);
		}
	}

	public static List<MedicalRecord> loadMedicalRecord(Patient patient)
			throws MedicalRecordException {

		Condition c = new Condition("patientId", Integer.toString(patient.id));
		try {
			return getByCondition(c, MedicalRecord.class);
		} catch (HmsException e) {
			throw new MedicalRecordException(e);
		}
	}

	public static MedicalRecord loadMedicalRecordById(int id)
			throws MedicalRecordException {

		try {
			return getById(id, MedicalRecord.class);
		} catch (HmsException e) {
			throw new MedicalRecordException(e);
		}
	}

	public Date getDateAffect() {
		return dateAffect;
	}

	public void setDateAffect(Date dateAffect) {
		this.dateAffect = dateAffect;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public Patient getPatient() {
		return patient;
	}

}
