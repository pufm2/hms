package puf.m2.hms.controller;

import java.util.Date;

public abstract class MedicalRecordAbstract implements MedicalRecord {

    int RecordID;
    int PatientID;
    String PatientName;
    String PatientSex;
    String PatientAddress;
    Date PatientBirthDate;
    String PatientPhone;
    String PatientBiographicHealth;
}
