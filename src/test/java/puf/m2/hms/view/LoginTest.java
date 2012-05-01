package puf.m2.hms.view;

import org.easymock.EasyMock;
import org.junit.Assert;
import org.junit.Test;

import puf.m2.hms.exception.UserException;
import puf.m2.hms.model.User;

public class LoginTest {

	@Test
	public void loginWithValidUser() throws UserException {

		/*
		 * User mock = EasyMock.createMock(User.class); User returnUser =
		 * mock.login("nhphat", "123");
		 * 
		 * assertNotNull(returnUser);
		 */
	}

	@Test
	public void loginWithInvalidUser() throws Exception {

		User mock = EasyMock.createMock(User.class);
		User result = null;
		try {
			result = mock.login("", "");

		} catch (UserException e) {
			System.out.println("Login fail");
		}
		Assert.assertEquals(null, result);
	}
}
