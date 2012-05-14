package puf.m2.hms.view;

import org.junit.Assert;
import org.junit.Test;

public class ManageUserTest {

	@Test
	public void testIsValidFields_blankUsername() {
		ManageUser frm = new ManageUser();
		frm.getTxtUsername().setText("");
		Assert.assertEquals("You must put user name", frm.checkValidFields());
	}

	@Test
	public void testIsValidFields_blankPassword() {
		ManageUser frm = new ManageUser();
		frm.getTxtUsername().setText("nhphat");
		frm.getTxtPassword().setText("");
		Assert.assertEquals("You must put password", frm.checkValidFields());
	}

	@Test
	public void testIsValidFields_blankEmail() {
		ManageUser frm = new ManageUser();
		frm.getTxtUsername().setText("nhphat");
		frm.getTxtPassword().setText("123");
		frm.getTxtEmail().setText("");
		Assert.assertEquals("You must put email", frm.checkValidFields());
	}

	@Test
	public void testIsValidFields_invalidEmail() {
		ManageUser frm = new ManageUser();
		frm.getTxtUsername().setText("nhphat");
		frm.getTxtPassword().setText("123");
		frm.getTxtEmail().setText("abc");
		Assert.assertEquals("Your email address is not valid",
				frm.checkValidFields());
	}

	@Test
	public void testIsValidFields_validFields() {
		ManageUser frm = new ManageUser();
		frm.getTxtUsername().setText("nhphat");
		frm.getTxtPassword().setText("123");
		frm.getTxtEmail().setText("phatpt01@gmail.com");
		Assert.assertEquals("True", frm.checkValidFields());
	}

	@Test
	public void testIsDuplicateUsername() {
		ManageUser frm = new ManageUser();
		frm.getTxtUsername().setText("nhphat");
		frm.getTxtPassword().setText("123");
		frm.getTxtEmail().setText("phatpt01@gmail.com");

		Assert.assertEquals(true, frm.isDuplicateUsername("nhphat"));

	}

}
