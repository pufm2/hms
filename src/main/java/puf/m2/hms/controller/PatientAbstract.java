package puf.m2.hms.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public abstract class PatientAbstract {

    int patientID;
    String patientName;
    Date patientBirthdate;
    String patientAddress;
    String patientSex;
    String patientPhone;
    String patientBiographicHealth;

    protected abstract int registerNewPatient() throws ClassNotFoundException,
            SQLException;

    protected abstract boolean checkExistPatient(int patientID)
            throws ClassNotFoundException, SQLException;

    protected abstract ArrayList<Object> lookupPatientInformation(int PatientID)
            throws ClassNotFoundException, SQLException;

}
