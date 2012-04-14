package puf.m2.hms.view;

import static org.junit.Assert.assertEquals;

import java.sql.SQLException;

import org.easymock.EasyMock;
import org.junit.Test;

import puf.m2.hms.model.MedicalRecord;
import puf.m2.hms.model.Patient;

public class ManageMedicalRecordTest {

	@Test
	public void testVaidateInputForm() throws SQLException {

		Patient patient = new Patient();
		patient.setId(-10);

		MedicalRecord mockMedicalRecord = EasyMock
				.createMock(MedicalRecord.class);
		mockMedicalRecord.setPatient(patient);
		EasyMock.expect(mockMedicalRecord.validateInputForm()).andReturn(-1);
		EasyMock.replay(mockMedicalRecord);

		assertEquals(-1, mockMedicalRecord.validateInputForm());
	}
}
