package edu.univ.erp.domain;

import java.time.LocalDateTime;

public class User {

    private int userID;
    private String username;
    private Role role;
    private String emailID;
    private String passwordHash;
    private String status;
    private LocalDateTime lastLogin;

    public User(int UserId, String username, Role role, String EmailID, String passwordHash, String status, LocalDateTime LastLogin)
    {
        this.userID = UserId;
        this.username = username;
        this.role = role;
        this.emailID = EmailID;
        this.passwordHash = passwordHash;
        this.status = status;
        this.lastLogin = LastLogin;
    }

    // Getters
    public int getUserID() { return userID; }
    public String getUsername() { return username; }
    public Role getRole() { return role; }
    public String getEmailID() { return emailID; }
    public String getPasswordHash() { return passwordHash; }
    public String getStatus() { return status; }
    public LocalDateTime getLastLogin() { return lastLogin; }

    // Setters
    public void setUserID(int UserID) { this.userID = UserID; }
    public void setUserName(String Username) { this.username = Username; }
    public void setRole(Role role) { this.role = role; }
    public void setEmailID(String EmailID) { this.emailID = EmailID; }
    public void setPasswordHash(String PasswordHash) { this.passwordHash = PasswordHash; }
    public void setLastLogin(LocalDateTime LastLogin) { this.lastLogin = LastLogin; }
}