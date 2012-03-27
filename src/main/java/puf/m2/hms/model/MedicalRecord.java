package puf.m2.hms.model;

import java.util.Date;

public class MedicalRecord {

    private int recordID;
    private int patientID;
    private String patientName;
    private String patientSex;
    private String patientAddress;
    private Date patientBirthDate;
    private String patientPhone;
    private String patientBiographicHealth;
    
    public MedicalRecord(int recordID, int patientID, String patientName,
            String patientSex, String patientAddress, Date patientBirthDate,
            String patientPhone, String patientBiographicHealth) {
        this.recordID = recordID;
        this.patientID = patientID;
        this.patientName = patientName;
        this.patientSex = patientSex;
        this.patientAddress = patientAddress;
        this.patientBirthDate = patientBirthDate;
        this.patientPhone = patientPhone;
        this.patientBiographicHealth = patientBiographicHealth;
    }

    public int getRecordID() {
        return recordID;
    }

    public int getPatientID() {
        return patientID;
    }

    public String getPatientName() {
        return patientName;
    }

    public String getPatientSex() {
        return patientSex;
    }

    public String getPatientAddress() {
        return patientAddress;
    }

    public Date getPatientBirthDate() {
        return patientBirthDate;
    }

    public String getPatientPhone() {
        return patientPhone;
    }

    public String getPatientBiographicHealth() {
        return patientBiographicHealth;
    }

    public void setRecordID(int recordID) {
        this.recordID = recordID;
    }

    public void setPatientID(int patientID) {
        this.patientID = patientID;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public void setPatientSex(String patientSex) {
        this.patientSex = patientSex;
    }

    public void setPatientAddress(String patientAddress) {
        this.patientAddress = patientAddress;
    }

    public void setPatientBirthDate(Date patientBirthDate) {
        this.patientBirthDate = patientBirthDate;
    }

    public void setPatientPhone(String patientPhone) {
        this.patientPhone = patientPhone;
    }

    public void setPatientBiographicHealth(String patientBiographicHealth) {
        this.patientBiographicHealth = patientBiographicHealth;
    }

    public void save() {
        
        
    }

    public void update() {
        
        
    }

    public void remove() {
        
        
    }
    
    
    
}
