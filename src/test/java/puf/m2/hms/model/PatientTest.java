package puf.m2.hms.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.util.List;

import org.junit.Test;

import puf.m2.hms.db.DbException;
import puf.m2.hms.exception.HmsException;
import puf.m2.hms.exception.PatientException;
import puf.m2.hms.model.support.Condition;

public class PatientTest extends TestSupport {

	@Test
	public void testSave() throws PatientException, IOException, DbException {
		backupDb();

		try {
			Patient patient = new Patient("Patient 1", "2000", "55 abc st", 1,
					"324234", "high blood pressure");
			patient.save();
			Patient patient1 = Patient.getPatientById(patient.id);
			assertNotNull(patient1);
		} finally {
			restoreDb();
		}
	}

	@Test(expected = PatientException.class)
	public void testSaveInvalidSex() throws PatientException, IOException,
			DbException {
		backupDb();

		try {
			Patient patient = new Patient("Patient 1", "2000", "55 abc st", 2,
					"324234", "high blood pressure");
			patient.save();
			Patient patient1 = Patient.getPatientById(patient.id);
			assertNotNull(patient1);
		} finally {
			restoreDb();
		}
	}

	@Test
	public void testGetPatients() throws PatientException {
		List<Patient> patients = Patient.getPatients();
		assertEquals(6, patients.size());
	}

	@Test
	public void testGetPatientById() throws PatientException {
		Patient patient = Patient.getPatientById(2);
		assertEquals("1993", patient.getDateOfBirth());
		assertEquals(0, patient.getSex());

	}

	@Test
	public void testGetPatientByName() throws PatientException {
		List<Patient> patients = Patient.getPatientByName("Ha Ngoc Quoc Vuong");
		assertEquals(1, patients.size());

		Patient patient = patients.get(0);
		assertEquals("21/12/2000", patient.getDateOfBirth());
		assertEquals(1, patient.getSex());
	}

	@Test
	public void testGetName() throws PatientException {
		Patient patient = Patient.getPatientById(6);
		assertEquals("Nguyen Huu Hung", patient.getName());
	}

	@Test
	public void testSetName() {
		Patient patient = new Patient("Patient 1", "2000", "55 abc st", 1,
				"324234", "high blood pressure");
		patient.setName("Patient 2");
		assertEquals("Patient 2", patient.getName());
	}

	@Test
	public void testGetDateOfBirth() throws PatientException {
		Patient patient = Patient.getPatientById(6);
		assertEquals("1994", patient.getDateOfBirth());
	}

	@Test
	public void testSetDateOfBirth() throws PatientException {
		Patient patient = Patient.getPatientById(6);
		patient.setDateOfBirth("1995");
		assertEquals("1995", patient.getDateOfBirth());
	}

	@Test
	public void testGetAddress() throws PatientException {
		Patient patient = Patient.getPatientById(4);
		assertEquals("Sai Gon", patient.getAddress());
	}

	@Test
	public void testSetAddress() throws PatientException {
		Patient patient = Patient.getPatientById(4);
		patient.setAddress("Munich");
		assertEquals("Munich", patient.getAddress());
	}

	@Test
	public void testGetSex() throws PatientException {
		Patient patient = Patient.getPatientById(1);
		assertEquals(1, patient.getSex());
	}

	@Test
	public void testSetSex() throws PatientException {
		Patient patient = Patient.getPatientById(1);
		patient.setSex(0);
		assertEquals(0, patient.getSex());
	}

	@Test
	public void testGetPhone() throws PatientException {
		Patient patient = Patient.getPatientById(6);
		assertEquals("903351857", patient.getPhone());
	}

	@Test
	public void testSetPhone() throws PatientException {
		Patient patient = Patient.getPatientById(5);
		patient.setPhone("235085094");
		assertEquals("235085094", patient.getPhone());
	}

	@Test
	public void testGetBiographicHealth() throws PatientException {
		Patient patient = Patient.getPatientById(1);
		assertEquals("None", patient.getBiographicHealth());
	}

	@Test
	public void testSetBiographicHealth() throws PatientException {
		Patient patient = Patient.getPatientById(5);
		patient.setBiographicHealth("diabet");
		assertEquals("diabet", patient.getBiographicHealth());
	}

}
