package puf.m2.hms.model;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

import puf.m2.hms.exception.HmsException;
import puf.m2.hms.exception.PhysicianException;

public class PhysicianTest {

	Physician mock;

	@Before
	public void setUp() {
		mock = EasyMock.createMock(Physician.class);
	}

	@Test
	public void testSave() throws Exception {
		int id = 1000;
		mock.save();
		EasyMock.expectLastCall().andThrow(new PhysicianException(id));
		EasyMock.replay(mock);

		try {
			mock.save();
		} catch (HmsException e) {
			System.out.println("Can not save physician with Id = " + id);
		}

		EasyMock.verify(mock);
	}

	@Test
	public void testUpdate() throws Exception {
		int id = 1000;
		mock.update();
		EasyMock.expectLastCall().andThrow(new PhysicianException(id));
		EasyMock.replay(mock);

		try {
			mock.update();
		} catch (HmsException e) {
			System.out.println("Can not update physician with Id = " + id);
		}

		EasyMock.verify(mock);
	}

}
