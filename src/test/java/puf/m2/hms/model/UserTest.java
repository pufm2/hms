package puf.m2.hms.model;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Test;

import puf.m2.hms.exception.UserException;

public class UserTest {

	@Test
	public void testLoginFail() throws UserException {
		User user = new User();
		User result = user.login("", "");
		assertNull(result);
	}

	@Test
	public void testLoginSuccess() throws UserException {
		User user = new User();
		User result = user.login("nhphat", "123");
		assertNotNull(result);
	}
}
