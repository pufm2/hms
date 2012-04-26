package puf.m2.hms.view;

import org.easymock.EasyMock;
import org.junit.Before;

import puf.m2.hms.model.User;

public class LoginTest {

	User mock = null;

	@Before
	public void setUp() {
		mock = EasyMock.createMock(User.class);
	}

	/*
	 * @Test public void loginWithValidUser() throws UserException {
	 * 
	 * User returnUser = mock.login("nhphat", "123"); assertNotNull(returnUser);
	 * }
	 * 
	 * @Test(expected = UserException.class) public void loginWithInvalidUser()
	 * throws UserException {
	 * 
	 * 
	 * mock.login("", ""); EasyMock.expectLastCall().andThrow(new
	 * UserException("", "")); EasyMock.replay(mock); try { User result =
	 * mock.login("", ""); } catch (UserException e) {
	 * System.out.println("Login fail"); }
	 * 
	 * EasyMock.verify(mock);
	 * 
	 * 
	 * 
	 * int id = 1000; mock.update(); EasyMock.expectLastCall().andThrow(new
	 * PhysicianException(id)); EasyMock.replay(mock);
	 * 
	 * try { mock.update(); } catch (PhysicianException e) {
	 * System.out.println("Can not update physician with Id = " + id); }
	 * 
	 * EasyMock.verify(mock);
	 * 
	 * 
	 * }
	 */
}
