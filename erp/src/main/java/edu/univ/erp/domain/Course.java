package edu.univ.erp.domain;

public class Course
{
    private String code;
    private String title;
    private int credits;
    private String type;
    private int maxStudents;
    private int numStudents;
    private String instructorName;

    // CONSTRUCTOR
    public Course(String code, String title, int credits, String type, int maxStudents, int numStudents, String instructorName)
    {
        this.code = code;
        this.title = title;
        this.credits = credits;
        this.type = type;
        this.maxStudents = maxStudents;
        this.numStudents = numStudents;
        this.instructorName = instructorName;
    }

    // GETTERS
    public String getCode() { return code; }
    public String getTitle() { return title; }
    public int getCredits() { return credits; }
    public String getType() { return type; }
    public int getMaxStudents() { return maxStudents; }
    public int getNumStudents() { return numStudents; }
    public String getInstructorName() { return instructorName; }

    // SETTERS
    public void setCode(String code) { this.code = code; }
    public void setTitle(String title) { this.title = title; }
    public void setCredits(int credits) { this.credits = credits; }
    public void setType(String type) { this.type = type; }
    public void setMaxStudents(int maxStudents) { this.maxStudents = maxStudents; }
    public void setNumStudents(int numStudents) { this.numStudents = numStudents; }
    public void setInstructorName(String instructorName) { this.instructorName = instructorName; }
}
