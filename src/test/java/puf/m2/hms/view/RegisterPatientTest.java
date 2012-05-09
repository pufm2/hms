package puf.m2.hms.view;

import java.util.Date;

import org.junit.Assert;
import org.junit.Test;

public class RegisterPatientTest {

	@Test
	public void testIsValidFields_patientName() {
		RegisterPatient frm = new RegisterPatient();
		frm.getTxtPatientName().setText("");
		Assert.assertEquals("You must put name of patient",
				frm.checkValidFields());
	}

	@Test
	public void testIsValidFields_birthDate_isBlank() {
		RegisterPatient frm = new RegisterPatient();
		frm.getTxtPatientName().setText("nhphat");
		frm.getTxtBirthdate().setDate(null);
		Assert.assertEquals("You must put a valid birthdate of patient",
				frm.checkValidFields());
	}

	@Test
	public void testIsValidFields_birthDate_isLaterThanCurrent() {
		RegisterPatient frm = new RegisterPatient();
		frm.getTxtPatientName().setText("nhphat");
		frm.getTxtBirthdate().setDate(new Date(2013, 01, 01));
		Assert.assertEquals("Birthdate must be ealier than current date",
				frm.checkValidFields());
	}

	@Test
	public void testIsValidFields_address_isBlank() {
		RegisterPatient frm = new RegisterPatient();
		frm.getTxtPatientName().setText("nhphat");
		frm.getTxtBirthdate().setDate(new Date());
		frm.getTxtAddress().setText("");
		Assert.assertEquals("You must put address of patient",
				frm.checkValidFields());
	}

	@Test
	public void testIsValidFields_phoneNumber_isBlank() {
		RegisterPatient frm = new RegisterPatient();
		frm.getTxtPatientName().setText("nhphat");
		frm.getTxtBirthdate().setDate(new Date());
		frm.getTxtAddress().setText("123 Le Lai");
		frm.getTxtPhoneNumber().setText("");
		Assert.assertEquals("You must put phone number of patient",
				frm.checkValidFields());
	}

	@Test
	public void testIsValidFields_phoneNumber_isString() {
		RegisterPatient frm = new RegisterPatient();
		frm.getTxtPatientName().setText("nhphat");
		frm.getTxtBirthdate().setDate(new Date());
		frm.getTxtAddress().setText("123 Le Lai");
		frm.getTxtPhoneNumber().setText("abcdefgh");
		frm.getTxtBiographicHealth().setText("First diagnosis");
		Assert.assertEquals(
				"Phone number does not accept character, only 0-9 is acceptable",
				frm.checkValidFields());
	}

	@Test
	public void testIsValidFields_BioprahicHealth_isBlank() {
		RegisterPatient frm = new RegisterPatient();
		frm.getTxtPatientName().setText("nhphat");
		frm.getTxtBirthdate().setDate(new Date());
		frm.getTxtAddress().setText("123 Le Lai");
		frm.getTxtPhoneNumber().setText("123456789");
		frm.getTxtBiographicHealth().setText("");
		Assert.assertEquals("You must put a biographic health of patient",
				frm.checkValidFields());
	}

	@Test
	public void testIsValidFields_Sex_noChoice() {
		RegisterPatient frm = new RegisterPatient();
		frm.getTxtPatientName().setText("nhphat");
		frm.getTxtBirthdate().setDate(new Date());
		frm.getTxtAddress().setText("123 Le Lai");
		frm.getTxtPhoneNumber().setText("123456789");
		frm.getTxtBiographicHealth().setText("First diagnosis");
		frm.getRbMale().setSelected(false);
		frm.getRbFemale().setSelected(false);
		Assert.assertEquals("You must choose patient's gender",
				frm.checkValidFields());
	}

	@Test
	public void testIsValidFields_valid() {
		RegisterPatient frm = new RegisterPatient();
		frm.getTxtPatientName().setText("nhphat");
		frm.getTxtBirthdate().setDate(new Date());
		frm.getTxtAddress().setText("123 Le Lai");
		frm.getTxtPhoneNumber().setText("123456789");
		frm.getTxtBiographicHealth().setText("First diagnosis");
		frm.getRbMale().setSelected(true);
		Assert.assertEquals("True", frm.checkValidFields());
	}
}
