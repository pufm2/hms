package puf.m2.hms.controller;

import java.sql.SQLException;

public abstract class PhysicianAbstract {

    int PhysicianID;
    String PhysicianName;
    String PhysicianRole;
    boolean Avaiable;

    public abstract int updateStatus(String physicianID)
            throws ClassNotFoundException, SQLException;

}
