import java.util.HashMap;
import java.time.LocalDate;

public class Student
{
    private int unique_id;
    private int roll_no;
    private String name;
    private String gender;
    private long contact_no;
    private LocalDate dob;
    private String nationality;
    private String email_id;
    private String program; //BTch, MTech, PhD
    private String branch; //CSAM, etc.
    private int current_year;
    private int current_sem;
    private int graduation_year;
    private HashMap<Integer,String[]> courses; //sem wise courses

    // constructor
    Student(int unique_id, int roll_no, String name, String gender, long contact_no, LocalDate dob, String nationality,
                String email_id, String program, String branch, int current_year, int current_sem, int graduation_year,
                    HashMap<Integer,String[]> courses)
    {
        this.unique_id = unique_id;
        this.roll_no = roll_no;
        this.name = name;
        this.gender = gender;
        this.contact_no = contact_no;
        this.dob = dob;
        this.nationality = nationality;
        this.email_id = email_id;
        this.program = program;
        this.branch = branch;
        this.current_year = current_year;
        this.current_sem = current_sem;
        this.graduation_year = graduation_year;
        this.courses = courses;
    }

    // getters

    // setters


    
}
