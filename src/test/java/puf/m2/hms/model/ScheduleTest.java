/*
 * Create a mock
 * call expect(mock.[method call]).andReturn([result]) for each expected call
 * call mock.[method call], then EasyMock.expectLastCall() for each expected void call
 * call replay(mock) to switch from “record” mode to “playback” mode
 * inject the mock as needed
 * call the test method
 * call verify(mock) to assure that all expected calls happened
 */

package puf.m2.hms.model;

import static org.easymock.EasyMock.expectLastCall;
import static org.easymock.EasyMock.replay;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

import puf.m2.hms.exception.ScheduleException;

public class ScheduleTest {

	Schedule mock;

	@Before
	public void setUp() {
		mock = EasyMock.createMock(Schedule.class);
	}

	@Test
	public void testSave() throws ScheduleException {
		mock.save();
		expectLastCall();
		replay(mock);
	}

	@Test
	public void testUpdate() throws Exception {
		mock.update(1);
		expectLastCall();
		replay(mock);
	}

	/*
	 * @Test public void testLoadSchedule() throws Exception { Physician doctor
	 * = new Physician("Alice", "Doctor", true);
	 * EasyMock.expect(Schedule.loadSchedule(doctor)).andReturn(null);
	 * 
	 * List<Schedule> actual = Schedule.loadSchedule(doctor); expectLastCall();
	 * replay(mock);
	 * 
	 * Assert.assertNull(actual); }
	 */
}
