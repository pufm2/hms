/*
 * Unit test for LoginPanel
 * Author: Nguyen Huu Phat
 * Date 2012 Apr 09
 */

package puf.m2.hms.view;

import static org.junit.Assert.*;

import org.easymock.EasyMock;
import org.junit.Test;

import puf.m2.hms.model.User;
import puf.m2.hms.view.LoginPanel;

public class LoginPanelTest {
	
	@Test
	public void testUsernameError() {
		User mock = EasyMock.createNiceMock(User.class);
		LoginPanel loginpanel = new LoginPanel();
		EasyMock.replay(mock);
		assertEquals(loginpanel.checkValidateUser("", ""),-1);
		EasyMock.verify(mock);
	}
	
	@Test
	public void testPasswordError() {
		User mock = EasyMock.createNiceMock(User.class);
		LoginPanel loginpanel = new LoginPanel();
		EasyMock.replay(mock);
		assertEquals(loginpanel.checkValidateUser("aaa", ""),-2);
		EasyMock.verify(mock);
	}
	
}
