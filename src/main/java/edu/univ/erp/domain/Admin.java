import java.util.HashMap;
public class Admin
{
    private int unique_id;
    private String department;
    private String name;
    private String email;
    private HashMap<Integer,String[]> courses;

    //constructor
    Admin(int unique_id, String department, String name, String email,
    HashMap<Integer,String[]> courses){
        this.unique_id=unique_id;
        this.department=department;
        this.name=name;
        this.email=email;
    }

    //getters and setters
    public int getUniqueId() { return unique_id; }
    public void setUserId(int unique_id) { this.unique_id = unique_id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
}

