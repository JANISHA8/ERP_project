package edu.univ.erp.domain;

import java.util.List;
import java.time.LocalDate;
import java.time.LocalTime;

public class Instructor extends User
{
    private String gender;
    private long contact_no;
    private LocalDate dob;
    private String nationality;
    private String department;
    private List<Course> courses; //sem wise courses

    // constructor
    Instructor(int userID, String username, Role INSTRUCTOR, String EmailID, String passwordHash, String status,
                    LocalTime lastLogin, String gender, long contact_no, LocalDate dob, String nationality,
                    String department, String email_id, List<Course> courses, Role role)
    {
        super(userID, username, INSTRUCTOR, EmailID, passwordHash, status, lastLogin);
        this.gender = gender;
        this.contact_no = contact_no;
        this.dob = dob;
        this.nationality = nationality;
        this.department = department;
        this.courses = courses;
    }

    // getters
    public String getGender(){return gender;}
    public long getContactno(){return contact_no;}
    public LocalDate getDob() { return dob; }
    public String getNationality() { return nationality; }
    public String getDepartment() { return department; }
    public List<Course> getCourses() { return courses; }

    // setters
    public void setGender(String gender) { this.gender = gender; }
    public void setContact_no(long contact_no) { this.contact_no = contact_no; }
    public void setDob(LocalDate dob) { this.dob = dob; }
    public void setNationality(String nationality) { this.nationality = nationality; }
    public void setDepartment(String department) { this.department = department; }
    public void setCourses(List<Course> courses) { this.courses = courses; }

    ////////////////////////////////////////////////////////////////////
    //////////////// REMOVE THIS IF IT IS NOT USED /////////////////////
    @Override public boolean equals(Object o)
    {
        if (this == o) {  return true;}
        if (o == null || getClass() != o.getClass()) { return false; }
        Instructor instructor = (Instructor) o;
        return this.getUserID() == instructor.getUserID();
    }
    ////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////

    @Override public int hashCode() // to ensure admins with same user_id are treated as duplicates; required in HashSet and HashMaps
    { return Integer.hashCode(this.getUserID()); }

    @Override public String toString()
    {
        return "INSTRUCTOR:\nUser ID - " + this.getUserID() +
        "\nName - " + this.getUsername() +
        "\nGender - " + this.gender +
        "\nContact No. - " + this.contact_no +
        "\nDOB - " + this.dob +
        "\nNationality - " + this.nationality +
        "\nDepartmemt - " + this.department +
        "\nEmail ID - " + this.getEmailID() +
        "\nCourses - " + this.courses;
    }
}
