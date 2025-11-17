package edu.univ.erp.domain;

public class Course
{
    private String code;
    private String title;
    private int credits;
    // credit or audit
    // total no. of students enrolled

    // constructor
    Course(String code, String title, int credits)
    {
        this.code = code;
        this.title = title;
        this.credits = credits;
    }

    // getters
    public String getCode() { return code; }
    public String getTitle() { return title; }
    public int getCredits() { return credits; }

    // setters
    public void setCode(String code) { this.code = code; }
    public void setTitle(String title) { this.title = title;}
    public void setCredits(int credits) { this.credits = credits; }

    @Override public String toString()
    {
        return "COURSE:\nCode - " + this.code +
        "\nTitle - " + this.title +
        "\nCredits - " + this.credits;
    }
}
