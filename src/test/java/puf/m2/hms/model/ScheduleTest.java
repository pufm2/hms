package puf.m2.hms.model;

import puf.m2.hms.exception.HmsException;

//TODO add at least one test for each method of the Schedule class

public class ScheduleTest {

    // TODO you should use a Schedule mock object for this case (if there is a bug in the Schedule class, this test
    // could
    // fail)
    // it will also allow you to check the interaction between the Physician and Schedule classes
    @Test
    public void testSave() throws HmsException {
        Physician p = Physician.getPhysicianById(107);
        Schedule s = new Schedule(p, new Date(), new Date(), true);
        s.save();
        for (Schedule s1 : Schedule.loadSchedule(p)) {
            if (s1.getId() == s.getId()) {
                return;
            }
        }
        fail();

    }

    // TODO Why is this test empty?
    @Test
    public void testUpdate() throws Exception {

    }

}
