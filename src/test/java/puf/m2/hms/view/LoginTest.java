package puf.m2.hms.view;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Test;

import puf.m2.hms.model.User;

public class LoginTest {

	@Test
	public void testLoginSuccess() {
		String username = "nhphat";
		String password = "123";
		User user;
		try {
			user = User.login(username, password);
			assertNotNull(user);
		} finally {

		}
	}

	@Test
	public void testLoginFail() {
		String username = "nhphat";
		String password = "nhphat";
		User user;
		try {
			user = User.login(username, password);
			assertNull(user);
		} finally {

		}
	}
}
