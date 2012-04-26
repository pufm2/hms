package puf.m2.hms.model;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Test;

import puf.m2.hms.exception.UserException;

public class UserTest {

	@Test
	public void testLoginFail() throws UserException {
		Object o = User.login("", "");
		assertNull(o);
	}

	@Test
	public void testLoginSuccess() throws UserException {
		Object o = User.login("nhphat", "123");
		assertNotNull(o);
	}
}
