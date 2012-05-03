package puf.m2.hms.model;



import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;

import puf.m2.hms.exception.HmsException;

// TODO add at least one test for each method of the PhysicianAssignment class

public class PhysicianAssignmentTest {

    @Test
    public void testSave() throws HmsException {
        Patient patient = Patient.getPatientById(5);
        Physician physician = Physician.getPhysicianById(106);

        PhysicianAssignment pa = new PhysicianAssignment(patient, physician, new Date(), new Date());
        pa.save();
        for (PhysicianAssignment pa1 : PhysicianAssignment.getPhysicianAssignments()) {
            if (pa1.id == pa.id) {
                assertEquals(pa1.getPatient().id, patient.id);
                assertEquals(pa1.getPhysician().id, physician.id);
                break;
            }
        }
    }

}
