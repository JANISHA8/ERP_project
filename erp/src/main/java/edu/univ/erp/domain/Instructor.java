package edu.univ.erp.domain;

import java.util.List;
import java.time.LocalDate;

public class Instructor
{
    private int user_id;
    private String name;
    private String gender;
    private long contact_no;
    private LocalDate dob;
    private String nationality;
    private String department;
    private String email_id;
    private List<Course> courses; //sem wise courses
    private Role role = Role.INSTRUCTOR;

    // constructor
    Instructor(int user_id, String name, String gender, long contact_no, LocalDate dob, String nationality, String department,
                    String email_id, List<Course> courses, Role role)
    {
        this.user_id = user_id;
        this.name = name;
        this.gender = gender;
        this.contact_no = contact_no;
        this.dob = dob;
        this.nationality = nationality;
        this.department = department;
        this.email_id = email_id;
        this.courses = courses;
        this.role = role;
    }

    // getters
    public int getUser_id(){return user_id;}
    public String getName(){return name;}
    public String getGender(){return gender;}
    public long getContactno(){return contact_no;}
    public LocalDate getDob() { return dob; }
    public String getNationality() { return nationality; }
    public String getDepartment() { return department; }
    public String getEmail_id() { return email_id; }
    public List<Course> getCourses() { return courses; }
    public Role getRole() { return role; }

    // setters
    public void setUser_id(int user_id) { this.user_id = user_id; }
    public void setName(String name) { this.name = name; }
    public void setGender(String gender) { this.gender = gender; }
    public void setContact_no(long contact_no) { this.contact_no = contact_no; }
    public void setDob(LocalDate dob) { this.dob = dob; }
    public void setNationality(String nationality) { this.nationality = nationality; }
    public void setDepartment(String department) { this.department = department; }
    public void setEmail_id(String email_id) { this.email_id = email_id; }
    public void setCourses(List<Course> courses) { this.courses = courses; }

    ////////////////////////////////////////////////////////////////////
    //////////////// REMOVE THIS IF IT IS NOT USED /////////////////////
    @Override public boolean equals(Object o)
    {
        if (this == o) {  return true;}
        if (o == null || getClass() != o.getClass()) { return false; }
        Instructor instructor = (Instructor) o;
        return this.user_id == instructor.user_id;
    }
    ////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////

    @Override public int hashCode() // to ensure admins with same user_id are treated as duplicates; required in HashSet and HashMaps
    { return Integer.hashCode(this.user_id); }

    @Override public String toString()
    {
        return "INSTRUCTOR:\nUser ID - " + this.user_id +
        "\nName - " + this.name +
        "\nGender - " + this.gender +
        "\nContact No. - " + this.contact_no +
        "\nDOB - " + this.dob +
        "\nNationality - " + this.nationality +
        "\nDepartmemt - " + this.department +
        "\nEmail ID - " + this.email_id +
        "\nCourses - " + this.courses;
    }
}
