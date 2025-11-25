package edu.univ.erp.data;

import edu.univ.erp.auth.hash.AuthDB;
import edu.univ.erp.domain.Course;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CourseData
{
    // In-memory storage for courses
    private List<Course> courses;

    // Constructor
    public CourseData()
    { courses = new ArrayList<>(); }

    // Add a new course
    public void addCourse(Course course)
    { courses.add(course); }

    // Get course by code
    public Course getCourseByCode(String code)
    {
        for (Course c : courses)
        {
            if (c.getCode().equals(code))
            { return c; }
        }
        return null; // not found
    }

    // Get all courses
    public List<Course> getAllCourses()
    { return new ArrayList<>(courses); } // return a copy

    // Get course ID by course CODE from DB
    public static int getCourseIdByCode(String code)
    {
        String sql = "SELECT course_id FROM courses WHERE code = ?";

        try (Connection con = AuthDB.getConnection();
            PreparedStatement ps = con.prepareStatement(sql))
        {
            ps.setString(1, code);
            ResultSet rs = ps.executeQuery();

            if(rs.next())
            {
                return rs.getInt("course_id");
            }

        } catch (Exception e)
        {
            e.printStackTrace();
        }

        return -1; // not found
    }

    // Update a course
    public boolean updateCourse(Course updatedCourse)
    {
        for (int i = 0; i < courses.size(); i++)
        {
            Course c = courses.get(i);
            if (c.getCode().equals(updatedCourse.getCode()))
            {
                courses.set(i, updatedCourse);
                return true; // updated successfully
            }
        }
        return false; // not found
    }

    // Delete a course by code
    public boolean deleteCourse(String code)
    { return courses.removeIf(c -> c.getCode().equals(code)); }

    // Increment number of students enrolled
    public boolean incrementNumStudents(String code, int count)
    {
        Course c = getCourseByCode(code);
        if (c != null)
        {
            c.setNumStudents(c.getNumStudents() + count);
            return true;
        }
        return false;
    }

    // Register Student
    public static boolean registerStudent(int studentId, String courseCode)
    {
        String check = "SELECT * FROM student_courses WHERE student_id=? AND course_id=?";
        String sql = "INSERT INTO student_courses(student_id, course_id) VALUES (?, ?)";

        try (Connection con = AuthDB.getConnection())
        {
            // check if already registered
            PreparedStatement pst = con.prepareStatement(check);
            pst.setInt(1, studentId);
            pst.setString(2, courseCode);

            ResultSet rs = pst.executeQuery();
            if (rs.next())
            {
                System.out.println("Student already registered for this course.");
                return false;
            }

            // insert if not exists
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, studentId);
            ps.setString(2, courseCode);

            return ps.executeUpdate() > 0;

        } catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }
    }

    // Drop Student
    public static boolean dropStudent(int studentId, int courseId)
    {
        String sql = "DELETE FROM student_courses WHERE student_id=? AND course_id=?";

        try (Connection con = AuthDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql))
        {
            ps.setInt(1, studentId);
            ps.setInt(2, courseId);

            return ps.executeUpdate() > 0;

        } catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }
    }

    // Decrement number of students enrolled
    public boolean decrementNumStudents(String code, int count)
    {
        Course c = getCourseByCode(code);
        if (c != null)
        {
            int newCount = c.getNumStudents() - count;
            c.setNumStudents(Math.max(newCount, 0)); // avoid negative
            return true;
        }
        return false;
    }
}
