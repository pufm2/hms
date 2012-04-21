package puf.m2.hms.view;

import static org.junit.Assert.assertEquals;

import java.sql.SQLException;
import java.util.Date;

import org.easymock.EasyMock;
import org.junit.Test;

import puf.m2.hms.model.HmsException;
import puf.m2.hms.model.Patient;
import puf.m2.hms.model.Physician;

public class AssignDoctorTest {

	@Test
	public void testIsExistPatient() throws SQLException, HmsException {
		// For test
		Physician mockPhysician = EasyMock.createMock(Physician.class);
		mockPhysician.setId(101);
		EasyMock.expect(mockPhysician.isExist(mockPhysician.getId()))
				.andReturn(true);

		Patient mockPatient = EasyMock.createMock(Patient.class);
		mockPatient.setId(1);
		EasyMock.expect(Patient.checkExistPatient(mockPatient.getId()))
				.andReturn(true);
		EasyMock.replay(mockPatient);

		AssignDoctor assignDoctor = new AssignDoctor();
		@SuppressWarnings("deprecation")
		int actual = assignDoctor.assign(mockPhysician, mockPatient, new Date(
				2012, 12, 02), new Date(2012, 12, 02));

		EasyMock.replay(mockPatient);
		assertEquals(1, actual);
	}

	@Test
	public void testIsExistPhysician() throws SQLException, HmsException {

		Physician mockPhysician = EasyMock.createMock(Physician.class);
		mockPhysician.setId(100);
		EasyMock.expect(mockPhysician.isExist(mockPhysician.getId()))
				.andReturn(false);
		EasyMock.replay(mockPhysician);

		Patient mockPatient = EasyMock.createMock(Patient.class);
		mockPatient.setId(1);
		EasyMock.expect(Patient.checkExistPatient(mockPatient.getId()))
				.andReturn(true);
		EasyMock.replay(mockPatient);

		AssignDoctor assignDoctor = new AssignDoctor();
		@SuppressWarnings("deprecation")
		int actual = assignDoctor.assign(mockPhysician, mockPatient, new Date(
				2012, 12, 02), new Date(2012, 12, 02));

		assertEquals(-2, actual);
	}

	@SuppressWarnings("deprecation")
	@Test
	public void testPhysicianIsDoctor() throws SQLException, HmsException {

		Physician mockPhysician = EasyMock.createMock(Physician.class);
		mockPhysician.setId(100);
		EasyMock.expect(mockPhysician.isExist(mockPhysician.getId()))
				.andReturn(true);
		EasyMock.expect(mockPhysician.isDoctor(mockPhysician.getId()))
				.andReturn(false);
		EasyMock.replay(mockPhysician);

		Patient mockPatient = EasyMock.createMock(Patient.class);
		mockPatient.setId(1);
		EasyMock.expect(Patient.checkExistPatient(mockPatient.getId()))
				.andReturn(true);
		EasyMock.replay(mockPatient);

		AssignDoctor assignDoctor = new AssignDoctor();
		int actual = assignDoctor.assign(mockPhysician, mockPatient, new Date(
				2012, 12, 02), new Date(2012, 12, 02));

		System.out.println(actual);

		assertEquals(-3, actual);
	}

	@SuppressWarnings("deprecation")
	@Test
	public void testPhysicianIsAvaiable() throws SQLException, HmsException {

		Physician mockPhysician = EasyMock.createMock(Physician.class);
		mockPhysician.setId(100);
		EasyMock.expect(mockPhysician.isExist(mockPhysician.getId()))
				.andReturn(true);
		EasyMock.expect(mockPhysician.isDoctor(mockPhysician.getId()))
				.andReturn(true);
		EasyMock.expect(
				mockPhysician.isAvaiable(new Date(2012, 12, 02),
						mockPhysician.getId())).andReturn(false);
		EasyMock.replay(mockPhysician);

		Patient mockPatient = EasyMock.createMock(Patient.class);
		mockPatient.setId(1);
		EasyMock.expect(Patient.checkExistPatient(mockPatient.getId()))
				.andReturn(true);
		EasyMock.replay(mockPatient);

		AssignDoctor assignDoctor = new AssignDoctor();
		int actual = assignDoctor.assign(mockPhysician, mockPatient, new Date(
				2012, 12, 02), new Date(2012, 12, 02));

		System.out.println(actual);

		assertEquals(-4, actual);
	}

	@SuppressWarnings("deprecation")
	@Test
	public void testInsertSuccessful() throws SQLException, HmsException {
		Physician mockPhysician = EasyMock.createMock(Physician.class);
		mockPhysician.setId(100);
		EasyMock.expect(mockPhysician.isExist(mockPhysician.getId()))
				.andReturn(true);
		EasyMock.expect(mockPhysician.isDoctor(mockPhysician.getId()))
				.andReturn(true);
		EasyMock.expect(
				mockPhysician.isAvaiable(new Date(2012, 12, 02),
						mockPhysician.getId())).andReturn(true);
		EasyMock.replay(mockPhysician);

		Patient mockPatient = EasyMock.createMock(Patient.class);
		mockPatient.setId(1);
		EasyMock.expect(Patient.checkExistPatient(mockPatient.getId()))
				.andReturn(true);
		EasyMock.replay(mockPatient);

		AssignDoctor assignDoctor = new AssignDoctor();
		int actual = assignDoctor.assign(mockPhysician, mockPatient, new Date(
				2012, 12, 02), new Date(2012, 12, 02));

		System.out.println(actual);

		assertEquals(1, actual);
	}
}
