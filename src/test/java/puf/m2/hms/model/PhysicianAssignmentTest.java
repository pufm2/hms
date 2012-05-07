package puf.m2.hms.model;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import puf.m2.hms.db.DbException;
import puf.m2.hms.exception.HmsException;
import puf.m2.hms.exception.PhysicianAssignmentException;
import puf.m2.hms.utils.DateUtils;


public class PhysicianAssignmentTest extends TestSupport {

    @Test
    public void testSave() throws HmsException, IOException, DbException {
        backupDb();

        try {
            Patient patient = Patient.getPatientById(5);

            Physician physician = Physician.getPhysicianById(100);

            PhysicianAssignment pa = new PhysicianAssignment(patient, physician, new Date(), new Date());
            pa.save();
            for (PhysicianAssignment pa1 : PhysicianAssignment.getPhysicianAssignments()) {
                if (pa1.id == pa.id) {
                    assertEquals(pa1.getPatient().id, patient.id);
                    assertEquals(pa1.getPhysician().id, physician.id);
                    break;
                }
            }
        } finally {
            restoreDb();
        }
    }
    
    @Test(expected = PhysicianAssignmentException.class)
    public void testSaveWhenDbBroken() throws HmsException, IOException, DbException {
    	
    	Patient patient = Patient.getPatientById(5);

        Physician physician = Physician.getPhysicianById(100);
        
        breakDb();
        try {
            PhysicianAssignment pa = new PhysicianAssignment(patient, physician, new Date(), new Date());
            pa.save();
        } finally {
        	unbreakDb();
        }
    }
    
    @Test
	public void testGetPhysicianAssignments() throws PhysicianAssignmentException {
		List<PhysicianAssignment> pas = PhysicianAssignment.getPhysicianAssignments();
		assertEquals(5, pas.size());
	}

	@Test
	public void testGetEndDate() throws PhysicianAssignmentException {
		
		for (PhysicianAssignment pa : PhysicianAssignment.getPhysicianAssignments()) {
			if (pa.id == 5) {
				assertEquals("28/03/2012 02:08:51", DateUtils.dateToString(pa.getEndDate()));
				return;
			}
		}
		fail("PhysicianAssignment with id 5 does not exist");
	}

	@Test
	public void testGetPatient() throws PhysicianAssignmentException {
		for (PhysicianAssignment pa : PhysicianAssignment.getPhysicianAssignments()) {
			if (pa.id == 6) {
				assertEquals(1, pa.getPatient().id);
				return;
			}
		}
		fail("PhysicianAssignment with id 6 does not exist");
	}

	@Test
	public void testGetPhysician() throws PhysicianAssignmentException {
		for (PhysicianAssignment pa : PhysicianAssignment.getPhysicianAssignments()) {
			if (pa.id == 7) {
				assertEquals(104, pa.getPhysician().id);
				return;
			}
		}
		fail("PhysicianAssignment with id 7 does not exist");
	}

	@Test
	public void testGetStartDate() throws PhysicianAssignmentException {
		for (PhysicianAssignment pa : PhysicianAssignment.getPhysicianAssignments()) {
			if (pa.id == 4) {
				assertEquals("28/03/2012 02:08:45", DateUtils.dateToString(pa.getEndDate()));
				return;
			}
		}
		fail("PhysicianAssignment with id 4 does not exist");
	}

}
