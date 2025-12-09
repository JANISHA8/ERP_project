package edu.univ.erp.domain;

public class Grade
{
    private int enrollment_id;
    private String courseCode;
    private String component; // mid-sem, end-sem, etc.
    private double score; // marks out of 100
    private String final_grade; // A+, A, A-, B,...

    // constructor
    public Grade(int enrollment_id, String courseCode, String component, double score, String final_grade)
    {
        this.enrollment_id = enrollment_id;
        this.courseCode = courseCode;
        this.component = component;
        this.score = score;
        this.final_grade = final_grade;
    }

    // getters
    public int getEnrollment_id(){return enrollment_id;}
    public String getCourseCode() { return courseCode; }
    public String getComponent(){return component;}
    public double getScore(){return score;}
    public String getFinalGrade(){return final_grade;}

    // setters
    public void setEnrollment_id(int enrollment_id) { this.enrollment_id = enrollment_id; }
    public void setCourseCode(String courseCode) { this.courseCode = courseCode; }
    public void setComponent(String component) { this.component = component; }
    public void setScore(double score) { this.score = score; }
    public void setFinalGrade(String final_grade) { this.final_grade = final_grade; }
}  
