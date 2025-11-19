package edu.univ.erp.domain;

import java.util.List;
import java.time.LocalDate;
import java.time.LocalTime;

public class Student extends User
{
    private int rollNO;
    private String gender;
    private long contact_no;
    private LocalDate dob;
    private String nationality;
    private String program; //BTch, MTech, PhD
    private String branch; //CSAM, etc.
    private int current_year;
    private int current_sem;
    private int graduation_year;
    private List<Course> courses; //sem wise courses

    // constructor
    Student(int userID, String username, Role STUDENT, String EmailID, String passwordHash, String status, LocalTime lastLogin,
                int RollNO, String gender, long contact_no, LocalDate dob, String nationality,
                String email_id, String program, String branch, int current_year, int current_sem, int graduation_year,
                List<Course> courses)
    {
        super(userID, username, STUDENT, EmailID, passwordHash, status, lastLogin);
        this.rollNO = RollNO;
        this.gender = gender;
        this.contact_no = contact_no;
        this.dob = dob;
        this.nationality = nationality;
        this.program = program;
        this.branch = branch;
        this.current_year = current_year;
        this.current_sem = current_sem;
        this.graduation_year = graduation_year;
        this.courses = courses;
    }

    // getters
    public int getRollNO() { return rollNO; }
    public String getGender() { return gender; }
    public long getContact_no() { return contact_no; }
    public LocalDate getDob() { return dob; }
    public String getNationality() { return nationality; }
    public String getProgram() { return program; }
    public String getBranch() { return branch; }
    public int getCurrent_year() { return current_year; }
    public int getCurrent_sem() { return current_sem; }
    public int getGraduation_year() { return graduation_year; }
    public List<Course> getCourses() { return courses; }

    // setters
    public void setRoll_no(int RollNO) { this.rollNO = RollNO; }
    public void setGender(String gender) { this.gender = gender; }
    public void setContact_no(long contact_no) { this.contact_no = contact_no; }
    public void setDob(LocalDate dob) { this.dob = dob; }
    public void setNationality(String nationality) { this.nationality = nationality; }
    public void setProgram(String program) { this.program = program; }
    public void setBranch(String branch) { this.branch = branch; }
    public void setCurrent_year(int current_year) { this.current_year = current_year; }
    public void setCurrent_sem(int current_sem) { this.current_sem = current_sem; }
    public void setGraduation_year(int graduation_year) { this.graduation_year = graduation_year; }
    public void setCourses(List<Course> courses) { this.courses = courses; }

    ////////////////////////////////////////////////////////////////////
    //////////////// REMOVE THIS IF IT IS NOT USED /////////////////////
    @Override public boolean equals(Object o)
    {
        if (this == o) {  return true;}
        if (o == null || getClass() != o.getClass()) { return false; }
        Student student = (Student) o;
        return this.getUserID() == student.getUserID();
    }
    ////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////

    @Override public int hashCode() // to ensure admins with same user_id are treated as duplicates; required in HashSet and HashMaps
    { return Integer.hashCode(this.getUserID()); }

    @Override public String toString()
    {
        return "STUDENT:\nUser ID - " + this.getUserID() +
        "\nRoll No. - " + this.rollNO +
        "\nName - " + this.getUsername() +
        "\nGender - " + this.gender +
        "\nContact No. - " + this.contact_no +
        "\nDOB - " + this.dob +
        "\nNationality - " + this.nationality +
        "\nEmail ID - " + this.getEmailID() +
        "\nProgram - " + this.program +
        "\nBranch - " + this.branch +
        "\nCurrent Year - " + this.current_year +
        "\nCurrent Sem - " + this.current_sem +
        "\nGraduation Year - " + this.graduation_year +
        "\nCourses - " + this.courses;
    }
}
