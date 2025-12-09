package edu.univ.erp.data;

import edu.univ.erp.domain.Grade;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GradesData
{
    // GET ALL GRADES FOR ONE ENROLLMENT
    public List<Grade> getGradesByEnrollmentId(int enrollmentId)
    {
        List<Grade> list = new ArrayList<>();
        String sql = "SELECT enrollment_id, course_code, component, score, final_grade " +
                    "FROM erp_db.student_grades WHERE enrollment_id = ?";

        try (Connection con = ERPDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql))
        {
            ps.setInt(1, enrollmentId);
            ResultSet rs = ps.executeQuery();
            while (rs.next())
            {
                list.add(new Grade( rs.getInt("enrollment_id"),
                                    rs.getString("course_code"),
                                    rs.getString("component"),
                                    rs.getDouble("score"),
                                    rs.getString("final_grade")));
            }
        }
        catch (Exception e)
        { e.printStackTrace(); }
        return list;
    }

    // SAVE / UPDATE SCORE
    public boolean saveScore(int enrollmentId, String courseCode, String component, double marks)
    {
        String sql = "INSERT INTO erp_db.student_grades (enrollment_id, course_code, component, score) " +
                    "VALUES (?, ?, ?, ?) " +
                    "ON DUPLICATE KEY UPDATE score = VALUES(score)";

        try (Connection conn = ERPDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql))
        {
            ps.setInt(1, enrollmentId);
            ps.setString(2, courseCode);
            ps.setString(3, component);
            ps.setDouble(4, marks);
            return ps.executeUpdate() > 0;
        }
        catch (SQLException e)
        { e.printStackTrace(); }
        return false;
    }

    // GET ALL STUDENTS ENROLLED IN A COURSE
    public List<Integer> getEnrollmentsForCourse(String courseCode)
    {
        List<Integer> list = new ArrayList<>();
        String sql = "SELECT enrollment_id FROM erp_db.student_courses WHERE code=?";
        try (Connection con = ERPDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql))
        {
            ps.setString(1, courseCode);
            ResultSet rs = ps.executeQuery();
            while (rs.next())
            { list.add(rs.getInt("enrollment_id")); }
        }
        catch(Exception e) { e.printStackTrace(); }
        return list;
    }

    // UPDATE FINAL GRADE IN DATABASE
    public boolean updateFinalGrade(int enrollmentId, String courseCode, String finalGrade)
    {
        String sql = "UPDATE erp_db.student_grades " +
                    "SET final_grade = ? " +
                    "WHERE enrollment_id = ? AND course_code = ?";

        try (Connection con = ERPDB.getConnection();
            PreparedStatement ps = con.prepareStatement(sql))
        {
            ps.setString(1, finalGrade);
            ps.setInt(2, enrollmentId);
            ps.setString(3, courseCode);
            return ps.executeUpdate() > 0;
        }
        catch (Exception e) { e.printStackTrace(); }
        return false;
    }

    // Average final grade
    public double getCourseAverage(String courseCode)
    {
        String sql = "SELECT AVG(score) FROM erp_db.student_grades " +
                    "WHERE course_code = ? AND component = 'FINAL'";

        try (Connection con = ERPDB.getConnection();
            PreparedStatement ps = con.prepareStatement(sql))
        {
            ps.setString(1, courseCode);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) { return rs.getDouble(1); }
        }
        catch (Exception e) { e.printStackTrace(); }
        return 0;
    }

    // Highest score
    public double getHighestScore(String courseCode)
    {
        String sql = "SELECT MAX(score) FROM erp_db.student_grades " +
                    "WHERE course_code = ? AND component = 'FINAL'";

        try (Connection con = ERPDB.getConnection();
            PreparedStatement ps = con.prepareStatement(sql))
        {
            ps.setString(1, courseCode);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) { return rs.getDouble(1); }
        }
        catch (Exception e) { e.printStackTrace(); }
        return 0;
    }

    // Lowest score
    public double getLowestScore(String courseCode)
    {
        String sql = "SELECT MIN(score) FROM erp_db.student_grades " +
                    "WHERE course_code = ? AND component = 'FINAL'";

        try (Connection con = ERPDB.getConnection();
            PreparedStatement ps = con.prepareStatement(sql))
        {
            ps.setString(1, courseCode);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) { return rs.getDouble(1); }
        }
        catch (Exception e) { e.printStackTrace(); }
        return 0;
    }

    // Student count
    public int getStudentCount(String courseCode)
    {
        String sql = "SELECT COUNT(DISTINCT enrollment_id) " +
                    "FROM erp_db.student_courses WHERE code = ?";

        try (Connection con = ERPDB.getConnection();
            PreparedStatement ps = con.prepareStatement(sql))
        {
            ps.setString(1, courseCode);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) { return rs.getInt(1); }
        }
        catch (Exception e) { e.printStackTrace(); }
        return 0;
    }
}
