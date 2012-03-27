package puf.m2.hms.controller;

public abstract class UserTypeAbstract implements UserType {

    private String userTypeCode;
    private String userTypeName;

    public UserTypeAbstract(String userTypeCode, String userTypeName) {
        super();
        this.userTypeCode = userTypeCode;
        this.userTypeName = userTypeName;
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
