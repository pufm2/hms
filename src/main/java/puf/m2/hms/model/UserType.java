package puf.m2.hms.model;

public class UserType extends HmsEntity {

    private String userTypeCode;
    private String userTypeName;
    
    public UserType(String userTypeCode, String userTypeName) {

    }
    
    public String getUserTypeCode() {
        return userTypeCode;
    }

    public void setUserTypeCode(String userTypeCode) {
        this.userTypeCode = userTypeCode;
    }

    public String getUserTypeName() {
        return userTypeName;
    }

    public void setUserTypeName(String userTypeName) {
        this.userTypeName = userTypeName;
    }
}
