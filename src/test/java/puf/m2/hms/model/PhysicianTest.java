package puf.m2.hms.model;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.List;

import org.junit.Test;

import puf.m2.hms.db.DbException;
import puf.m2.hms.exception.PhysicianException;

public class PhysicianTest extends TestSupport {

    @Test
    public void testSave() throws PhysicianException, IOException {
        try {
            TestSupport.backupDb();
            
            Physician p = new Physician("doctor X", "doctor", true);
            p.save();
            Physician p1 = Physician.getPhysicianById(p.id);
            assertEquals(p.id, p1.id);
        } finally {
            TestSupport.restoreDb();
        }
    }

    @Test
    public void testUpdate() throws PhysicianException, IOException {
        try {
            TestSupport.backupDb();
            
            Physician p = Physician.getPhysicianById(100);
            p.setAvailable(false);
            p.update();
            Physician p1 = Physician.getPhysicianById(100);
            assertEquals(p1.isAvailable(), false);
        } finally {
            TestSupport.restoreDb();
        }
    }
    
    @Test(expected = PhysicianException.class)
    public void testUpdateWithNoDb() throws PhysicianException, DbException {
        Physician p = Physician.getPhysicianById(100);
        
        dbFile.renameTo(dbBackupFile);
        try {
            p.update();
        } finally {
            dbFile.delete();
            dbBackupFile.renameTo(dbFile);
        }
    }
    
    @Test
    public void testGetNurses() throws PhysicianException {
        List<Physician> nurses = Physician.getNurses();
        assertEquals(4, nurses.size());
        
    }
    
    @Test
    public void testGetDoctors() throws PhysicianException {
        List<Physician> doctors = Physician.getDoctors();
        assertEquals(2, doctors.size());
    }

    @Test
    public void testGetPhysicianById() throws PhysicianException {
        Physician p = Physician.getPhysicianById(100);
        assertEquals(100, p.getId());
    }

    @Test
    public void testGetName() throws PhysicianException {
        Physician p = Physician.getPhysicianById(102);
        assertEquals("Nguyễn Thị Hồng Hạnh", p.getName());
    }

    @Test
    public void testGetRole() throws PhysicianException {
        Physician p = Physician.getPhysicianById(103);
        assertEquals("Nurse", p.getRole());
    }

    @Test
    public void testIsAvailable() throws PhysicianException {
        Physician p = Physician.getPhysicianById(104);
        assertEquals(true, p.isAvailable());
    }

    @Test
    public void testSetAvailable() throws PhysicianException {
        Physician p = Physician.getPhysicianById(105);
        p.setAvailable(false);
        assertEquals(false, p.isAvailable());
        
    }

    @Test
    public void testSetName() throws PhysicianException {
        Physician p = Physician.getPhysicianById(101);
        p.setName("Cantona");
        assertEquals("Cantona", p.getName());
    }

    @Test
    public void testSetRole() throws PhysicianException {
        Physician p = Physician.getPhysicianById(100);
        p.setRole("Nurse");
        assertEquals("Nurse", p.getRole());
    }

}
