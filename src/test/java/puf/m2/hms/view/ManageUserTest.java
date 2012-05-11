package puf.m2.hms.view;

import org.easymock.EasyMock;
import org.junit.Assert;
import org.junit.Test;

import puf.m2.hms.model.User;

public class ManageUserTest {

	@Test
	public void testIsValidFields_username_is_blank() {
		ManageUser frm = new ManageUser();
		frm.getTxtUsername().setText("");
		Assert.assertEquals("You must put user name", frm.checkValidFields());
	}

	@Test
	public void testIsValidFields_password_is_blank() {
		ManageUser frm = new ManageUser();
		frm.getTxtUsername().setText("nhphat");
		frm.getTxtPassword().setText("");
		Assert.assertEquals("You must put password", frm.checkValidFields());
	}

	@Test
	public void testIsValidFields_email_is_blank() {
		ManageUser frm = new ManageUser();
		frm.getTxtUsername().setText("nhphat");
		frm.getTxtPassword().setText("123");
		frm.getTxtEmail().setText("");
		Assert.assertEquals("You must put email", frm.checkValidFields());
	}

	@Test
	public void testIsValidFields_email_invalid() {
		ManageUser frm = new ManageUser();
		frm.getTxtUsername().setText("nhphat");
		frm.getTxtPassword().setText("123");
		frm.getTxtEmail().setText("abc");
		Assert.assertEquals("Your email address is not valid",
				frm.checkValidFields());
	}

	@Test
	public void testIsValidFields_valid() {
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

		User mock = EasyMock.createMock(User.class);
		String username = "nhphat";
		EasyMock.expect(User.getUserByName(username)).andReturn(null);
		EasyMock.replay(mock);

		Assert.assertEquals(false, frm.isDuplicateUsername("nhphat"));
		EasyMock.verify(mock);
	}

}
