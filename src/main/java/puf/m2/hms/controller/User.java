package puf.m2.hms.controller;

public interface User {
    /*
     * Login to database - return TRUE if username and password are corrected -
     * return FALSE if username or password is not correct
     */
    boolean login(String username, String password);
}
