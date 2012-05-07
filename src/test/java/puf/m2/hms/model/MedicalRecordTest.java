package puf.m2.hms.model;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import puf.m2.hms.db.DbException;
import puf.m2.hms.exception.MedicalRecordException;
import puf.m2.hms.exception.PatientException;
import puf.m2.hms.utils.DateUtils;

public class MedicalRecordTest extends TestSupport {

	@Test
	public void testSave() throws IOException, DbException, MedicalRecordException, PatientException {
		backupDb();

        try {
            Patient patient = Patient.getPatientById(5);


            MedicalRecord mr = new MedicalRecord(patient, new Date(), "so detailed");
            mr.save();
            
            MedicalRecord mr1 = MedicalRecord.loadMedicalRecordById(mr.id);
            
            assertEquals(5, mr1.getPatient().id);
            
        } finally {
            restoreDb();
        }
	}

	@Test
	public void testUpdate() throws IOException, MedicalRecordException, DbException {
		backupDb();

        try {

            MedicalRecord mr = MedicalRecord.loadMedicalRecordById(7);
            mr.setDetail("mad");
            mr.update();
            
            MedicalRecord mr1 = MedicalRecord.loadMedicalRecordById(7);
            assertEquals("mad", mr1.getDetail());
            
        } finally {
            restoreDb();
        }
	}

	@Test
	public void testLoadMedicalRecord() throws PatientException, MedicalRecordException {
		Patient p = Patient.getPatientById(2);
		List<MedicalRecord> mrs = MedicalRecord.loadMedicalRecord(p);
		assertEquals(2, mrs.size());
		for (MedicalRecord mr : mrs) {
			assertEquals(2, mr.getPatient().id);
		}
	}

	@Test
	public void testLoadMedicalRecordById() throws MedicalRecordException {
		MedicalRecord mr = MedicalRecord.loadMedicalRecordById(9);
		
		assertEquals("12/12/2012 12:12:12", DateUtils.dateToString(mr.getDateAffect()));
	}
	
	@Test(expected = MedicalRecordException.class)
	public void testLoadMedicalRecordByIdWhenDbbroken() throws MedicalRecordException, DbException {

		breakDb();
        try {
        	MedicalRecord mr = MedicalRecord.loadMedicalRecordById(9);
        } finally {
        	unbreakDb();
        }
	}

	@Test
	public void testGetDateAffect() throws MedicalRecordException {
		MedicalRecord mr = MedicalRecord.loadMedicalRecordById(11);
		
		assertEquals("12/03/2012 12:12:12", DateUtils.dateToString(mr.getDateAffect()));
	}

	@Test
	public void testSetDateAffect() throws MedicalRecordException {
		MedicalRecord mr = MedicalRecord.loadMedicalRecordById(11);
		mr.setDateAffect(DateUtils.parseDate("12/03/2012 12:12:13"));
		
		assertEquals("12/03/2012 12:12:13", DateUtils.dateToString(mr.getDateAffect()));
	}

	@Test
	public void testGetDetail() throws MedicalRecordException {
		MedicalRecord mr = MedicalRecord.loadMedicalRecordById(1);
		
		assertEquals("abc", mr.getDetail());
	}

	@Test
	public void testSetDetail() throws MedicalRecordException {
		MedicalRecord mr = MedicalRecord.loadMedicalRecordById(3);
		mr.setDetail("parkinson");
		
		assertEquals("parkinson", mr.getDetail());
	}

	@Test
	public void testGetPatient() throws MedicalRecordException {
		MedicalRecord mr = MedicalRecord.loadMedicalRecordById(9);
		
		assertEquals(1, mr.getPatient().id);
	}

}
