package edu.univ.erp.domain;

import java.time.LocalDateTime;

public class Admin extends User
{
    private String department;

    // constructor
    public Admin(int userID, String username, String EmailID, String passwordHash,
            String status, LocalDateTime lastLogin, String department)
    {
        super(userID, username, Role.ADMIN, EmailID, passwordHash, status, lastLogin);
        this.department = department;
    }

    // getters
    public String getDepartment() { return department; }

    // setters
    public void setDepartment(String department) { this.department = department; }

    ////////////////////////////////////////////////////////////////////
    //////////////// REMOVE THIS IF IT IS NOT USED /////////////////////
    @Override public boolean equals(Object o)
    {
        if (this == o) {  return true;}
        if (o == null || getClass() != o.getClass()) { return false; }
        Admin admin = (Admin) o;
        return this.getUserID() == admin.getUserID();
    }
    ////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////

    @Override public int hashCode() // to ensure admins with same user_id are treated as duplicates; required in HashSet and HashMaps
    { return Integer.hashCode(this.getUserID()); }

    @Override public String toString()
    {
        return "ADMIN:\nUnique ID - " + this.getUserID() +
        "\nName - " + this.getUsername() +
        "\nDepartment - " + this.department +
        "\nEmail ID - " + this.getEmailID();
    }
}

