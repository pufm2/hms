package puf.m2.hms.model;

import java.util.Date;

import static org.junit.Assert.*;
import org.junit.Test;

import puf.m2.hms.exception.HmsException;

public class ScheduleTest {

	@Test
	public void testSave() throws HmsException {
		Physician p = Physician.getPhysicianById(107);
	    Schedule s = new Schedule(p, new Date(), new Date(), true);
	    s.save();
	    for(Schedule s1 : Schedule.loadSchedule(p)) {
	        if (s1.getId() == s.getId()) {
	            return;
	        }
	    }
	    fail();
	    
	}

	@Test
	public void testUpdate() throws Exception {
		
	}

}
