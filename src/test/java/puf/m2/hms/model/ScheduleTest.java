package puf.m2.hms.model;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import puf.m2.hms.db.DbException;
import puf.m2.hms.exception.HmsException;
import puf.m2.hms.exception.PhysicianException;
import puf.m2.hms.exception.ScheduleException;
import puf.m2.hms.utils.DateUtils;

public class ScheduleTest extends TestSupport {

    @Test
    public void testSave() throws HmsException, IOException, DbException {
        backupDb();
        
        try {
            Physician p = Physician.getPhysicianById(101);
            Schedule s = new Schedule(p, new Date(), new Date(), true);
            s.save();
            for (Schedule s1 : Schedule.loadSchedule(p)) {
                if (s1.getId() == s.getId()) {
                    return;
                }
            }
        } finally {
            restoreDb();
        }
        fail();

    }

	@Test
	public void testUpdate() throws PhysicianException, ScheduleException, IOException, DbException {
		backupDb();
        
        try {
            Physician p = Physician.getPhysicianById(100);
            Schedule s = Schedule.loadSchedule(p).get(0);
            s.setEndDate(DateUtils.parseDate("05/12/2012 15:47:15"));
            s.update();
            
            Schedule s1 = Schedule.loadSchedule(p).get(0);
            assertEquals("05/12/2012 15:47:15", DateUtils.dateToString(s1.getEndDate()));
            
        } finally {
            restoreDb();
        }
	}
	
	@Test(expected = ScheduleException.class)
	public void testUpdateWithBrokenDb() throws PhysicianException, ScheduleException, IOException, DbException {
		Physician p = Physician.getPhysicianById(100);
        Schedule s = Schedule.loadSchedule(p).get(0);
        s.setEndDate(DateUtils.parseDate("05/12/2012 15:47:15"));
        breakDb();
        try {
            s.update();
           
        } finally {
            unbreakDb();
        }
	}

	@Test
	public void testLoadSchedule() throws PhysicianException, ScheduleException {
		Physician p = Physician.getPhysicianById(100);
		List<Schedule> ses = Schedule.loadSchedule(p);
		assertEquals(1, ses.size());
		
		Schedule s = ses.get(0);
		assertEquals("05/04/2012 15:47:15", DateUtils.dateToString(s.getEndDate()));
		
	}
	
	@Test
	public void testGetPhysician() throws PhysicianException, ScheduleException {
		Physician p = Physician.getPhysicianById(100);
        Schedule s = Schedule.loadSchedule(p).get(0);
        assertEquals(100, s.getPhysician().id);
	}

	@Test
	public void testSetPhysician() throws PhysicianException, ScheduleException {
		Physician p = Physician.getPhysicianById(100);
        Schedule s = Schedule.loadSchedule(p).get(0);
        
        s.setPhysician(Physician.getPhysicianById(101));
        assertEquals(101, s.getPhysician().id);
	}

	@Test
	public void testGetStartDate() throws PhysicianException, ScheduleException {
		Physician p = Physician.getPhysicianById(100);
        Schedule s = Schedule.loadSchedule(p).get(0);
        
        assertEquals("05/04/2012 15:47:15", DateUtils.dateToString(s.getStartDate()));
	}

	@Test
	public void testSetStartDate() throws PhysicianException, ScheduleException {
		Physician p = Physician.getPhysicianById(100);
        Schedule s = Schedule.loadSchedule(p).get(0);
        
        s.setStartDate(DateUtils.parseDate("05/05/2012 15:47:15"));
        assertEquals("05/05/2012 15:47:15", DateUtils.dateToString(s.getStartDate()));
	}

	@Test
	public void testGetEndDate() throws PhysicianException, ScheduleException {
		Physician p = Physician.getPhysicianById(100);
        Schedule s = Schedule.loadSchedule(p).get(0);
        
        assertEquals("05/04/2012 15:47:15", DateUtils.dateToString(s.getEndDate()));
	}

	@Test
	public void testSetEndDate() throws PhysicianException, ScheduleException {
		Physician p = Physician.getPhysicianById(100);
        Schedule s = Schedule.loadSchedule(p).get(0);
        
        s.setEndDate(DateUtils.parseDate("05/06/2012 15:47:15"));
        assertEquals("05/06/2012 15:47:15", DateUtils.dateToString(s.getEndDate()));
	}

	@Test
	public void testIsAvailable() throws PhysicianException, ScheduleException {
		Physician p = Physician.getPhysicianById(100);
        Schedule s = Schedule.loadSchedule(p).get(0);
        
        assertEquals(true, s.isAvailable());
	}

	@Test
	public void testSetAvailable() throws PhysicianException, ScheduleException {
		Physician p = Physician.getPhysicianById(100);
        Schedule s = Schedule.loadSchedule(p).get(0);
        
        s.setAvailable(false);
        assertEquals(false, s.isAvailable());
	}

}
