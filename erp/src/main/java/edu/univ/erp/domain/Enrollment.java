package edu.univ.erp.domain;

public class Enrollment
{
    private String student_id;
    private String section_id;
    private String status;

    // constructor
    public Enrollment(String student_id, String section_id, String status)
    {
        this.student_id = student_id;
        this.section_id = section_id;
        this.status = status;
    }

    // getters
    public String getStudent_id() { return student_id; }
    public String getSection_id() { return section_id; }
    public String getStatus() { return status; }

    // setters
    public void setStudent_id(String student_id) { this.student_id = student_id; }
    public void setSection_id(String section_id) { this.section_id = section_id; }
    public void setStatus(String status) { this.status = status; }

    @Override public String toString()
    {
        return "ENROLLMENT:\nStudent ID - " + this.student_id +
        "\nSection ID - " + this.section_id +
        "\nStatus - " + this.status;
    }
}
