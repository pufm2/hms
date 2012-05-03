package puf.m2.hms.model;

import static org.junit.Assert.*;

import org.junit.Test;

import puf.m2.hms.exception.UserException;

//TODO add at least one test for each method of the User class

public class UserTest {

    // TODO you should add tests that raise UserException
    @Test
    public void testLoginFail() throws UserException {
        User result = User.login("", "");
        assertNull(result);
    }

    @Test
    public void testLoginSuccess() throws UserException {
        User result = User.login("nhphat", "123");
        assertNotNull(result);
    }
}
