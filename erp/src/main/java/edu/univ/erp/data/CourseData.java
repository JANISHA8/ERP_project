package edu.univ.erp.data;

import edu.univ.erp.domain.Course;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CourseData
{
    public boolean createCourse(Course c)
    {
        String sql = "INSERT INTO erp_db.courses " +
                    "(code,title,credits,type,max_students,num_students,instructor_name) " +
                    "VALUES (?,?,?,?,?,?,?)";

        try (Connection con = ERPDB.getConnection();
            PreparedStatement ps = con.prepareStatement(sql))
        {
            ps.setString(1, c.getCode());
            ps.setString(2, c.getTitle());
            ps.setInt(3, c.getCredits());
            ps.setString(4, c.getType());
            ps.setInt(5, c.getMaxStudents());
            ps.setInt(6, c.getNumStudents());
            ps.setString(7, c.getInstructorName());

            return ps.executeUpdate() > 0;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return false;
        }
    }

    // GET ALL COURSES FROM DB
    public List<Course> getAllCourses()
    {
        List<Course> list = new ArrayList<>();
        String sql = "SELECT * FROM courses";
        try (Connection conn = ERPDB.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery())
        {
            while (rs.next())
            {
                String code = rs.getString("code");
                int enrolled = getEnrolledStudentsCount(code);
                Course c = new Course(code,
                        rs.getString("title"),
                        rs.getInt("credits"),
                        rs.getString("type"),
                        rs.getInt("max_students"),
                        enrolled,
                        rs.getString("instructor_name"));
                list.add(c);
            }
        }
        catch (Exception e) { e.printStackTrace(); }
        return list;
    }

    // Get courseID by courseCode
    public static String getCourseIdByCode(String code)
    {
        String sql = "SELECT code FROM erp_db.courses WHERE code = ?";

        try (Connection con = ERPDB.getConnection();
            PreparedStatement ps = con.prepareStatement(sql))
        {
            ps.setString(1, code);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) { return rs.getString("code"); }
            return null;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    // UPDATE COURSE
    public boolean updateCourse(Course c)
    {
        String sql = "UPDATE erp_db.courses " +
                    "SET title=?, credits=?, type=?, max_students=?, num_students=?, instructor_name=? " +
                    "WHERE code=?";

        try (Connection con = ERPDB.getConnection();
            PreparedStatement ps = con.prepareStatement(sql))
        {
            ps.setString(1, c.getTitle());
            ps.setInt(2, c.getCredits());
            ps.setString(3, c.getType());
            ps.setInt(4, c.getMaxStudents());
            ps.setInt(5, c.getNumStudents());
            ps.setString(6, c.getInstructorName());
            ps.setString(7, c.getCode());

            return ps.executeUpdate() > 0;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return false;
        }
    }

    // Delete a course by code
    public boolean deleteCourse(String code)
    {
        String sql = "DELETE FROM erp_db.courses WHERE code=?";

        try (Connection con = ERPDB.getConnection();
            PreparedStatement ps = con.prepareStatement(sql))
        {
            ps.setString(1, code);
            return ps.executeUpdate() > 0;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return false;
        }
    }

    // Register Student
    public static boolean registerStudent(int studentId, String courseCode)
    {
        String check = "SELECT * FROM erp_db.student_courses WHERE student_id=? AND course_code=?";
        String seatCheck = "SELECT max_students, num_students FROM erp_db.courses WHERE code=?";
        String insert = "INSERT INTO erp_db.student_courses(student_id, course_code) VALUES (?, ?)";
        String updateSeats = "UPDATE erp_db.courses SET num_students = num_students + 1 WHERE code=?";

        try (Connection con = ERPDB.getConnection())
        {
            // Check already enrolled
            PreparedStatement pst = con.prepareStatement(check);
            pst.setInt(1, studentId);
            pst.setString(2, courseCode);
            ResultSet rs = pst.executeQuery();

            if (rs.next())
            {
                System.out.println("Already registered.");
                return false;
            }

            // Check seats available
            PreparedStatement seatPS = con.prepareStatement(seatCheck);
            seatPS.setString(1, courseCode);
            ResultSet seatRS = seatPS.executeQuery();

            if (!seatRS.next())
            {
                System.out.println("Course does not exist");
                return false;
            }

            int max = seatRS.getInt("max_students");
            int current = seatRS.getInt("num_students");

            if ((max - current) <= 0)
            {
                System.out.println("No seats available");
                return false;
            }

            PreparedStatement insertPS = con.prepareStatement(insert);
            insertPS.setInt(1, studentId);
            insertPS.setString(2, courseCode);
            if (insertPS.executeUpdate() == 0) return false;

            PreparedStatement updatePS = con.prepareStatement(updateSeats);
            updatePS.setString(1, courseCode);
            updatePS.executeUpdate();
            return true;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }
    }

    // Drop Student
    public static boolean dropStudent(int studentId, String courseCode)
    {
        String check = "SELECT * FROM erp_db.student_courses WHERE student_id=? AND course_code=?";
        String delete = "DELETE FROM erp_db.student_courses WHERE student_id=? AND course_code=?";
        String updateSeats = "UPDATE erp_db.courses SET num_students = num_students - 1 WHERE code=? AND num_students > 0";

        try (Connection con = ERPDB.getConnection())
        {
            PreparedStatement pst = con.prepareStatement(check);
            pst.setInt(1, studentId);
            pst.setString(2, courseCode);
            ResultSet rs = pst.executeQuery();

            if (!rs.next())
            {
                System.out.println("Student is NOT registered in this course");
                return false;
            }

            PreparedStatement deletePS = con.prepareStatement(delete);
            deletePS.setInt(1, studentId);
            deletePS.setString(2, courseCode);

            if(deletePS.executeUpdate() == 0) { return false; }

            PreparedStatement updatePS = con.prepareStatement(updateSeats);
            updatePS.setString(1, courseCode);
            updatePS.executeUpdate();
            return true;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }
    }

    // GET ALL COURSES FOR A STUDENT
    public List<Course> getCoursesForStudent(int studentId)
    {
        List<Course> list = new ArrayList<>();
        String sql = "SELECT c.code, c.title, c.credits, c.type, " +
                    "c.max_students, c.num_students, c.instructor_name " +
                    "FROM erp_db.student_courses sc " +
                    "JOIN erp_db.courses c ON sc.course_code = c.code " +
                    "WHERE sc.student_id = ?";

        try (Connection con = ERPDB.getConnection();
            PreparedStatement ps = con.prepareStatement(sql))
        {
            ps.setInt(1, studentId);
            ResultSet rs = ps.executeQuery();
            while (rs.next())
            {
                Course c = new Course( rs.getString("code"),
                                        rs.getString("title"),
                                        rs.getInt("credits"),
                                        rs.getString("type"),
                                        rs.getInt("max_students"),
                                        rs.getInt("num_students"),
                                        rs.getString("instructor_name"));
                list.add(c);
            }
        }
        catch (Exception e) { e.printStackTrace(); }
        return list;
    }

    public boolean isCourseOfInstructor(String courseCode, int userId)
    {
        String sql = "SELECT COUNT(*) FROM erp_db.courses c " +
                    "JOIN auth_db.users_auth u ON c.instructor_name = u.username " +
                    "WHERE c.code = ? AND u.user_id = ?";

        try (Connection con = ERPDB.getConnection();
            PreparedStatement ps = con.prepareStatement(sql))
        {
            ps.setString(1, courseCode);
            ps.setInt(2, userId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) { return rs.getInt(1) > 0; }
        }
        catch (Exception e) { e.printStackTrace(); }
        return false;
    }

    public boolean areStudentsEnrolled(String courseCode)
    {
        String sql = "SELECT COUNT(*) FROM student_courses WHERE course_code=?";

        try (Connection conn = ERPDB.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql))
        {
            ps.setString(1, courseCode);

            try (ResultSet rs = ps.executeQuery())
            {
                if (rs.next()) { return rs.getInt(1) > 0; }
            }
        } catch (Exception e) { e.printStackTrace(); }
        return false;
    }

    // GET NUMBER OF STUDENTS ENROLLED IN A COURSE
    public int getEnrolledStudentsCount(String courseCode)
    {
        String sql = "SELECT COUNT(*) FROM student_courses WHERE course_code = ?";

        try (Connection conn = ERPDB.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql))
        {
            ps.setString(1, courseCode);

            try (ResultSet rs = ps.executeQuery())
            {
                if (rs.next())
                    return rs.getInt(1);
            }
        } catch (Exception e) { e.printStackTrace(); }
        return 0;
    }
}
