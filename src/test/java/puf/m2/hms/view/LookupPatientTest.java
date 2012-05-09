package puf.m2.hms.view;

import junit.framework.Assert;

import org.junit.Test;

public class LookupPatientTest {

	@Test
	public void testIsValidCondition_blankPatientIDField() {
		LookupPatient frm = new LookupPatient();
		frm.getRbPatientID().setSelected(true);
		frm.getTxtPatientID().setText("");
		Assert.assertEquals(false, frm.isValidFields());
	}

	@Test
	public void testIsValidCondition_stringPatientID() {
		LookupPatient frm = new LookupPatient();
		frm.getRbPatientID().setSelected(true);
		frm.getTxtPatientID().setText("abc");
		Assert.assertEquals(false, frm.isValidFields());
	}

	@Test
	public void testIsValidCondition_numberPatientID() {
		LookupPatient frm = new LookupPatient();
		frm.getRbPatientID().setSelected(true);
		frm.getTxtPatientID().setText("123");
		Assert.assertEquals(true, frm.isValidFields());
	}

	@Test
	public void testIsValidCondition_blankNameField() {
		LookupPatient frm = new LookupPatient();
		frm.getRbPatientName().setSelected(true);
		frm.getTxtPatientName().setText("");
		Assert.assertEquals(false, frm.isValidFields());
	}

	@Test
	public void testIsValidCondition_stringNameID() {
		LookupPatient frm = new LookupPatient();
		frm.getRbPatientName().setSelected(true);
		frm.getTxtPatientName().setText("nhphat");
		Assert.assertEquals(true, frm.isValidFields());
	}
}
