package puf.m2.hms.model;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.Date;

import org.junit.Test;

import puf.m2.hms.exception.HmsException;

//TODO add at least one test for each method of the Schedule class

public class ScheduleTest {

    // TODO you should use a Schedule mock object for this case (if there is a bug in the Schedule class, this test
    // could
    // fail)
    // it will also allow you to check the interaction between the Physician and Schedule classes
    @Test
    public void testSave() throws HmsException, IOException {
        TestSupport.backupDb();
        
        try {
            Physician p = Physician.getPhysicianById(107);
            Schedule s = new Schedule(p, new Date(), new Date(), true);
            s.save();
            for (Schedule s1 : Schedule.loadSchedule(p)) {
                if (s1.getId() == s.getId()) {
                    return;
                }
            }
        } finally {
            TestSupport.restoreDb();
        }
        fail();

    }

    // TODO Why is this test empty?
    @Test
    public void testUpdate() throws Exception {

    }

}
