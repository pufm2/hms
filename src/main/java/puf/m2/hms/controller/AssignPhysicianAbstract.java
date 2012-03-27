package puf.m2.hms.controller;

import java.sql.SQLException;
import java.util.Date;

public abstract class AssignPhysicianAbstract {

    int assignID;
    int patientID;
    int physicianID;
    Date startDate;
    Date endTime;
    Date startTime;
    Date endDate;

    public abstract int insertNewAssign(String patientID, String physicianID)
            throws ClassNotFoundException, SQLException;
}
