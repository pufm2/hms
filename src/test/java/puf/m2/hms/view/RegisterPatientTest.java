package puf.m2.hms.view;

import java.util.Date;

import org.junit.Assert;
import org.junit.Test;

public class RegisterPatientTest {

	@Test
	public void testIsValidFields_patientName() {
		RegisterPatient frm = new RegisterPatient();
		frm.getTxtPatientName().setText("nhphat");
		Assert.assertEquals(false, frm.isValidFields());
	}

	@Test
	public void testIsValidFields_birthDate_isNull() {
		RegisterPatient frm = new RegisterPatient();
		frm.getTxtPatientName().setText("nhphat");
		frm.getTxtBirthdate().setDate(null);
		Assert.assertEquals(false, frm.isValidFields());
	}

	@Test
	public void testIsValidFields_birthDate_isLaterThanCurrent() {
		RegisterPatient frm = new RegisterPatient();
		frm.getTxtPatientName().setText("nhphat");
		frm.getTxtBirthdate().setDate(new Date(2013, 01, 01));
		Assert.assertEquals(false, frm.isValidFields());
	}

	@Test
	public void testIsValidFields_address_isBlank() {
		RegisterPatient frm = new RegisterPatient();
		frm.getTxtPatientName().setText("nhphat");
		frm.getTxtBirthdate().setDate(new Date());
		frm.getTxtAddress().setText("");
		Assert.assertEquals(false, frm.isValidFields());
	}

	@Test
	public void testIsValidFields_phoneNumber_isBlank() {
		RegisterPatient frm = new RegisterPatient();
		frm.getTxtPatientName().setText("nhphat");
		frm.getTxtBirthdate().setDate(new Date());
		frm.getTxtAddress().setText("123 Le Lai");
		frm.getTxtPhoneNumber().setText("");
		Assert.assertEquals(false, frm.isValidFields());
	}

	@Test
	public void testIsValidFields_phoneNumber_isString() {
		RegisterPatient frm = new RegisterPatient();
		frm.getTxtPatientName().setText("nhphat");
		frm.getTxtBirthdate().setDate(new Date());
		frm.getTxtAddress().setText("123 Le Lai");
		frm.getTxtPhoneNumber().setText("abcdefgh");
		frm.getTxtBiographicHealth().setText("First diagnosis");
		Assert.assertEquals(false, frm.isValidFields());
	}

	@Test
	public void testIsValidFields_BioprahicHealth_isBlank() {
		RegisterPatient frm = new RegisterPatient();
		frm.getTxtPatientName().setText("nhphat");
		frm.getTxtBirthdate().setDate(new Date());
		frm.getTxtAddress().setText("123 Le Lai");
		frm.getTxtPhoneNumber().setText("123456789");
		frm.getTxtBiographicHealth().setText("");
		Assert.assertEquals(false, frm.isValidFields());
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
		Assert.assertEquals(false, frm.isValidFields());
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
		Assert.assertEquals(true, frm.isValidFields());
	}
}
