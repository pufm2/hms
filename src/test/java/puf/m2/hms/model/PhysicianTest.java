package puf.m2.hms.model;

import static org.junit.Assert.*;
import org.junit.Test;

import puf.m2.hms.exception.HmsException;

public class PhysicianTest extends TestSupport {

	@Test
	public void testSave() throws HmsException {
	    Physician p = new Physician("doctor X", "doctor", true);
	    p.save();
	    Physician p1 = Physician.getPhysicianById(p.id);
	    assertEquals(p.id, p1.id);
	}

	@Test
	public void testUpdate() throws HmsException {
	    Physician p = Physician.getPhysicianById(100);
	    p.setAvailable(false);
	    p.update();
	    Physician p1 = Physician.getPhysicianById(100);
        assertEquals(p1.isAvailable(), false);
	}

}
