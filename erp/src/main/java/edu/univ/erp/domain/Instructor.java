package edu.univ.erp.domain;

import java.util.List;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Instructor extends User
{
    private String gender;
    private long contact_no;
    private LocalDate dob;
    private String nationality;
    private String department;
    private List<Course> courses;

    // constructor
    public Instructor(int userID, String username, String EmailID, String passwordHash, String status,
                    LocalDateTime lastLogin, String gender, long contact_no, LocalDate dob, String nationality,
                    String department, List<Course> courses, Role role)
    {
        super(userID, username, Role.INSTRUCTOR, EmailID, passwordHash, status, lastLogin);
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
}
