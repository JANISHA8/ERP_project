package edu.univ.erp.domain;

public class Admin
{
    private int unique_id;
    private String department;
    private String name;
    private String email;

    // constructor
    Admin(int unique_id, String department, String name, String email)
    {
        this.unique_id=unique_id;
        this.department=department;
        this.name=name;
        this.email=email;
    }

    // getters
    public int getUniqueId() { return unique_id; }
    public String getName() { return name; }
    public String getDepartment() { return department; }
    public String getEmail() { return email; }

    // setters
    public void setUserId(int unique_id) { this.unique_id = unique_id; }
    public void setName(String name) { this.name = name; }
    public void setDepartment(String department) { this.department = department; }
    public void setEmail(String email) { this.email = email; }
}

