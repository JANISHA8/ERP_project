package edu.univ.erp.domain;

public class Admin
{
    private int user_id;
    private String name;
    private String department;
    private String email_id;
    private Role role = Role.ADMIN;

    // constructor
    Admin(int user_id, String name, String department, String email_id, Role role)
    {
        this.user_id = user_id;
        this.name = name;
        this.department = department;
        this.email_id = email_id;
        this.role = role;
    }

    // getters
    public int getUniqueId() { return user_id; }
    public String getName() { return name; }
    public String getDepartment() { return department; }
    public String getEmail_id() { return email_id; }
    public Role getRole() { return role; }

    // setters
    public void setUserId(int user_id) { this.user_id = user_id; }
    public void setName(String name) { this.name = name; }
    public void setDepartment(String department) { this.department = department; }
    public void setEmail_id(String email_id) { this.email_id = email_id; }
    public void setRole(Role role) { this.role = role; }

    ////////////////////////////////////////////////////////////////////
    //////////////// REMOVE THIS IF IT IS NOT USED /////////////////////
    @Override public boolean equals(Object o)
    {
        if (this == o) {  return true;}
        if (o == null || getClass() != o.getClass()) { return false; }
        Admin admin = (Admin) o;
        return user_id == admin.user_id;
    }
    ////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////

    @Override public int hashCode() // to ensure admins with same user_id are treated as duplicates; required in HashSet and HashMaps
    { return Integer.hashCode(this.user_id); }

    @Override public String toString()
    {
        return "ADMIN:\nUnique ID - " + this.user_id +
        "\nName - " + this.name +
        "\nDepartment - " + this.department +
        "\nEmail ID - " + this.email_id;
    }
}

