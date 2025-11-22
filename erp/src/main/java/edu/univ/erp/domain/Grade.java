package edu.univ.erp.domain;

public class Grade
{
    private String enrollment_id;
    private String component; // mid-sem, end-sem, etc.
    private double score; // marks out of 100
    private String final_grade; // A+, A, A-, B,... // their numeric equivalents ?

    // constructor
    public Grade(String enrollment_id, String component, double score, String final_grade)
    {
        this.enrollment_id = enrollment_id;
        this.component = component;
        this.score = score;
        this.final_grade = final_grade;
    }

    // getters
    public String getEnrollment_id(){return enrollment_id;}
    public String getComponent(){return component;}
    public double getScore(){return score;}
    public String getFinalgrade(){return final_grade;}

    // setters
    public void setEnrollment_id(String enrollment_id) { this.enrollment_id = enrollment_id; }
    public void setComponent(String component) { this.component = component; }
    public void setScore(double score) { this.score = score; }
    public void setFinal_grade(String final_grade) { this.final_grade = final_grade; }

    @Override public String toString()
    {
        return "GRADE:\nEnrollment ID - " + this.enrollment_id +
        "\nComponent - " + this.component +
        "\nScore - " + this.score +
        "\nFinal Grade - " + this.final_grade;
    }
}  
