package puf.m2.hms.model;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

import puf.m2.hms.db.DbException;
import puf.m2.hms.exception.UserException;

public class UserTest extends TestSupport {

	@Test
	public void testSave() throws IOException, UserException, DbException {
		backupDb();
        
        try {
            User user = new User("someone", "something", "somemail@mail.com", "Doctor");
            user.save();
            User user1 = User.login("someone", "something");
            
            assertEquals(user.id, user1.id);
        } finally {
            restoreDb();
        }
	}

	@Test
	public void testUpdate() throws IOException, UserException, DbException {
		backupDb();
        
        try {
            User user = User.login("lxhoan", "123");
            user.setPassword("abc");
            user.update();
            
            User user1 = User.login("lxhoan", "123");
            assertNull(user1);
            
            user1 = User.login("lxhoan", "abc");
            assertEquals("lxhoan", user1.getUsername());
            
            user.setDeleted(true);
            user.update();
            
            user1 = User.login("lxhoan", "abc");
            assertNull(user1);
        } finally {
            restoreDb();
        }
	}
	
	@Test(expected = UserException.class)
    public void testUpdateWithNoDb() throws DbException, UserException {
		User user = User.login("lxhoan", "123");
		user.setPassword("abc");
        breakDb();
        try {
            user.update();
        } finally {
        	unbreakDb();
        }
    }
	@Test
	public void testLogin() {
		User user = User.login("lxhoan", "123");
		assertEquals("lxhoan", user.getUsername());
	}

	@Test
	public void testGetUsername() {
		User user = User.login("nhphat", "123");
		assertEquals("nhphat", user.getUsername());
	}

	@Test
	public void testSetUsername() {
		User user = User.login("nmathu", "123");
		user.setUsername("ngominhanhthu");
		assertEquals("ngominhanhthu", user.getUsername());
	}

	@Test
	public void testGetPassword() {
		User user = User.login("nhphat", "123");
		assertEquals("123", user.getPassword());
	}

	@Test
	public void testSetPassword() {
		User user = User.login("nmathu", "123");
		user.setPassword("abc");
		assertEquals("abc", user.getPassword());
	}

	@Test
	public void testGetUseremail() {
		User user = User.login("tlnquynh", "123");
		assertEquals("bachthuconuong@gmail.com", user.getUseremail());
	}

	@Test
	public void testSetUseremail() {
		User user = User.login("tlnquynh", "123");
		user.setUseremail("bachthuconuong@yahoo.com");
		assertEquals("bachthuconuong@yahoo.com", user.getUseremail());
	}

	@Test
	public void testGetRole() {
		User user = User.login("nhphat", "123");
		assertEquals("Receptionist", user.getRole());
	}

	@Test
	public void testSetRole() {
		User user = User.login("nhphat", "123");
		user.setRole("Doctor");
		assertEquals("Doctor", user.getRole());
	}

}
