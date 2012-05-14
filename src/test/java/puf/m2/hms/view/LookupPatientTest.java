package puf.m2.hms.view;

import junit.framework.Assert;

import org.junit.Test;

public class LookupPatientTest {

	@Test
	public void testIsValidCondition_blankPatientID() {
		LookupPatient frm = new LookupPatient();
		frm.getRbPatientID().setSelected(true);
		frm.getTxtPatientID().setText("");
		Assert.assertEquals("You must put patient ID", frm.checkValidFields());
	}

	@Test
	public void testIsValidCondition_stringPatientID() {
		LookupPatient frm = new LookupPatient();
		frm.getRbPatientID().setSelected(true);
		frm.getTxtPatientID().setText("abc");
		Assert.assertEquals(
				"Patient ID does not accept character, only 0-9 is acceptable",
				frm.checkValidFields());
	}

	@Test
	public void testIsValidCondition_numberPatientID() {
		LookupPatient frm = new LookupPatient();
		frm.getRbPatientID().setSelected(true);
		frm.getTxtPatientID().setText("123");
		Assert.assertEquals("True", frm.checkValidFields());
	}

	@Test
	public void testIsValidCondition_blankNameField() {
		LookupPatient frm = new LookupPatient();
		frm.getRbPatientName().setSelected(true);
		frm.getTxtPatientName().setText("");
		Assert.assertEquals("You must put patient name", frm.checkValidFields());
	}

	@Test
	public void testIsValidCondition_stringNameID() {
		LookupPatient frm = new LookupPatient();
		frm.getRbPatientName().setSelected(true);
		frm.getTxtPatientName().setText("nhphat");
		Assert.assertEquals("True", frm.checkValidFields());
	}
}
