public class Grade
{
    private String enrollment_id;
    private String component;
    private double score; // marks out of 100
    private String final_grade; // A+, A, A-, B,... // their numeric equivalents ?

    // constructor
    Grade(String enrollment_id, String component, double score, String final_grade)
    {
        this.enrollment_id = enrollment_id;
        this.component = component;
        this.score = score;
        this.final_grade = final_grade;
    }

    // getters

    // setters
}
