package puf.m2.hms.model;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.Date;

import org.junit.Test;

import puf.m2.hms.exception.HmsException;


public class PhysicianAssignmentTest {

    @Test
    public void testSave() throws HmsException, IOException {
        TestSupport.backupDb();

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
            TestSupport.restoreDb();
        }
    }

}
