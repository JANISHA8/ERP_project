package edu.univ.erp.domain;

import java.time.LocalDateTime;

public class Admin extends User
{
    private String department;

    // constructor
    public Admin(int userID, String username, String EmailID, String passwordHash, String status, LocalDateTime lastLogin, String department)
    {
        super(userID, username, Role.ADMIN, EmailID, passwordHash, status, lastLogin);
        this.department = department;
    }

    // getters
    public String getDepartment() { return department; }

    // setters
    public void setDepartment(String department) { this.department = department; }
}
