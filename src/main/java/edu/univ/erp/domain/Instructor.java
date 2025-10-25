import java.util.List;
import java.time.LocalDate;

public class Instructor
{
    private int user_id;
    private String name;
    private String gender;
    private long contact_no;
    private LocalDate dob;
    private String nationality;
    private String department;
    private String email_id;
    private List<Course> courses; //sem wise courses

    // constructor
    Instructor(int user_id, String name, String gender, long contact_no, LocalDate dob, String nationality, String department,
                    String email_id, List<Course> courses)
    {
        this.user_id = user_id;
        this.name = name;
        this.gender = gender;
        this.contact_no = contact_no;
        this.dob = dob;
        this.nationality = nationality;
        this.department = department;
        this.email_id = email_id;
        this.courses = courses;
    }

    // getters

    // setters
}
