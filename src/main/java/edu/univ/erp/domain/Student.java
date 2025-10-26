import java.util.List;
import java.time.LocalDate;

public class Student
{
    private String user_id;
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
    private List<Course> courses; //sem wise courses

    // constructor
    Student(String user_id, int roll_no, String name, String gender, long contact_no, LocalDate dob, String nationality,
                String email_id, String program, String branch, int current_year, int current_sem, int graduation_year,
                    List<Course> courses)
    {
        this.user_id = user_id;
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
    public String getUser_id() { return user_id; }
    public int getRoll_no() { return roll_no; }
    public String getName() { return name; }
    public String getGender() { return gender; }
    public long getContact_no() { return contact_no; }
    public LocalDate getDob() { return dob; }
    public String getNationality() { return nationality; }
    public String getEmail_id() { return email_id; }
    public String getProgram() { return program; }
    public String getBranch() { return branch; }
    public int getCurrent_year() { return current_year; }
    public int getCurrent_sem() { return current_sem; }
    public int getGraduation_year() { return graduation_year; }
    public List<Course> getCourses() { return courses; }
    // setters
    public void setUser_id(String user_id) { this.user_id = user_id; }
    public void setRoll_no(int roll_no) { this.roll_no = roll_no; }
    public void setName(String name) { this.name = name; }
    public void setGender(String gender) { this.gender = gender; }
    public void setContact_no(long contact_no) { this.contact_no = contact_no; }
    public void setDob(LocalDate dob) { this.dob = dob; }
    public void setNationality(String nationality) { this.nationality = nationality; }
    public void setEmail_id(String email_id) { this.email_id = email_id; }
    public void setProgram(String program) { this.program = program; }
    public void setBranch(String branch) { this.branch = branch; }
    public void setCurrent_year(int current_year) { this.current_year = current_year; }
    public void setCurrent_sem(int current_sem) { this.current_sem = current_sem; }
    public void setGraduation_year(int graduation_year) { this.graduation_year = graduation_year; }
    public void setCourses(List<Course> courses) { this.courses = courses; }
}
