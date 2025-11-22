package edu.univ.erp.domain;

public class Course
{
    private String code;
    private String title;
    private int credits;
    private String type; // credit or audit
    private int numStudents; // total no. of students enrolled

    // constructor
    public Course(String code, String title, int credits, String type, int numStudents)
    {
        this.code = code;
        this.title = title;
        this.credits = credits;
        this.type = type;
        this.numStudents = numStudents;
    }

    // getters
    public String getCode() { return code; }
    public String getTitle() { return title; }
    public int getCredits() { return credits; }
    public String getType() { return type; }
    public int getNumStudents() { return numStudents; }

    // setters
    public void setCode(String code) { this.code = code; }
    public void setTitle(String title) { this.title = title;}
    public void setCredits(int credits) { this.credits = credits; }
    public void setType(String type) { this.type = type; }
    public void setNumStudents(int numStudents) { this.numStudents = numStudents; }

    @Override public String toString()
    {
        return "COURSE:\nCode - " + this.code +
        "\nTitle - " + this.title +
        "\nCredits - " + this.credits +
        "\nType - " + this.type +
        "\nNo. of Students - "+ this.numStudents;
    }
}
