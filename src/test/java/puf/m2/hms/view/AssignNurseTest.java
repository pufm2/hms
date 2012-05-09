package puf.m2.hms.view;

import org.easymock.EasyMock;
import org.junit.Assert;
import org.junit.Test;

import puf.m2.hms.exception.PatientException;
import puf.m2.hms.exception.PhysicianException;
import puf.m2.hms.model.Patient;
import puf.m2.hms.model.Physician;

public class AssignNurseTest {

	@Test
	public void testIsDuplicateAssign() throws PatientException,
			PhysicianException {
		Patient patient = Patient.getPatientById(5);
		Physician doctor = Physician.getPhysicianById(103);
		AssignDoctor mock = EasyMock.createMock(AssignDoctor.class);
		boolean result = mock.isDuplicateAssign(patient, doctor);
		Assert.assertEquals(false, result);
	}

}
