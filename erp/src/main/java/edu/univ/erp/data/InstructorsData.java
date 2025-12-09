package edu.univ.erp.data;

import edu.univ.erp.domain.Instructor;
import edu.univ.erp.domain.Course;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class InstructorsData
{
    // GET ONE INSTRUCTOR BY USER ID
    public Instructor getInstructorByUserId(int userId)
    {
        String sql = "SELECT i.*, u.username, u.email_id AS user_email, u.password_hash, " +
                    "u.status, u.last_login " +
                    "FROM instructors i " +
                    "JOIN auth_db.users_auth u ON i.user_id = u.user_id " +
                    "WHERE i.user_id = ?";

        try (Connection conn = ERPDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql))
        {
            ps.setInt(1, userId);
            try (ResultSet rs = ps.executeQuery())
            {
                if (rs.next())
                {
                    Date dobSql = rs.getDate("dob");
                    LocalDate dob = (dobSql == null) ? null : dobSql.toLocalDate();
                    Timestamp ts = rs.getTimestamp("last_login");
                    LocalDateTime lastLogin = (ts == null) ? null : ts.toLocalDateTime();

                    return new Instructor( rs.getInt("user_id"),
                                            rs.getString("username"),
                                            rs.getString("user_email"),
                                            rs.getString("password_hash"),
                                            rs.getString("status"),
                                            lastLogin,
                                            rs.getString("gender"),
                                            rs.getLong("contact_no"),
                                            dob,
                                            rs.getString("nationality"),
                                            rs.getString("department"),
                                            null,
                                            null);
                }
            }
        }
        catch (SQLException e) { e.printStackTrace(); }
        return null;
    }

    // GET ONE INSTRUCTOR BY EMAIL ID
    public Instructor getInstructorByUserEmail(String email)
    {
        String sql = "SELECT i.*, u.username, u.email_id AS user_email, u.password_hash, " +
                    "u.status, u.last_login " +
                    "FROM instructors i " +
                    "JOIN auth_db.users_auth u ON i.user_id = u.user_id " +
                    "WHERE u.email_id = ?";

        try (Connection conn = ERPDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql))
        {
            ps.setString(1, email);
            try (ResultSet rs = ps.executeQuery())
            {
                if (rs.next())
                {
                    Date dobSql = rs.getDate("dob");
                    LocalDate dob = (dobSql == null) ? null : dobSql.toLocalDate();
                    Timestamp ts = rs.getTimestamp("last_login");
                    LocalDateTime lastLogin = (ts == null) ? null : ts.toLocalDateTime();

                    return new Instructor( rs.getInt("user_id"),
                                            rs.getString("username"),
                                            rs.getString("user_email"),
                                            rs.getString("password_hash"),
                                            rs.getString("status"),
                                            lastLogin,
                                            rs.getString("gender"),
                                            rs.getLong("contact_no"),
                                            dob,
                                            rs.getString("nationality"),
                                            rs.getString("department"),
                                            null,
                                            null );
                }
            }
        }
        catch (SQLException e) { e.printStackTrace(); }
        return null;
    }

    // GET ALL INSTRUCTORS
    public List<Instructor> getAllInstructors()
    {
        List<Instructor> list = new ArrayList<>();
        String sql = "SELECT i.*, u.username, u.email_id AS user_email, u.password_hash, " +
                    "u.status, u.last_login " +
                    "FROM instructors i " +
                    "JOIN auth_db.users_auth u ON i.user_id = u.user_id";

        try (Connection conn = ERPDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery())
        {
            while (rs.next())
            {
                Date dobSql = rs.getDate("dob");
                LocalDate dob = (dobSql == null) ? null : dobSql.toLocalDate();
                Timestamp ts = rs.getTimestamp("last_login");
                LocalDateTime lastLogin = (ts == null) ? null : ts.toLocalDateTime();

                list.add(new Instructor( rs.getInt("user_id"),
                                        rs.getString("username"),
                                        rs.getString("user_email"),
                                        rs.getString("password_hash"),
                                        rs.getString("status"),
                                        lastLogin,
                                        rs.getString("gender"),
                                        rs.getLong("contact_no"),
                                        dob,
                                        rs.getString("nationality"),
                                        rs.getString("department"),
                                        null,
                                        null ));
            }
        }
        catch (SQLException e) { e.printStackTrace(); }
        return list;
    }

    // INSERT INSTRUCTOR ROW (after user_auth row exists)
    public boolean insertInstructor(Instructor i)
    {
        String sql = "INSERT INTO erp_db.instructors " +
                    "(user_id, gender, contact_no, dob, nationality, email_id, department) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = ERPDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql))
        {
            ps.setInt(1, i.getUserID());
            ps.setString(2, i.getGender());
            ps.setLong(3, i.getContactno());
            ps.setDate(4, i.getDob() == null ? null : Date.valueOf(i.getDob()));
            ps.setString(5, i.getNationality());
            ps.setString(6, i.getEmailID());
            ps.setString(7, i.getDepartment());
            return ps.executeUpdate() > 0;
        }
        catch (SQLException e) { e.printStackTrace(); }
        return false;
    }

    // UPDATE INSTRUCTOR
    public boolean updateInstructor(Instructor i)
    {
        String sql1 = "UPDATE erp_db.instructors SET contact_no=? WHERE user_id=?";
        String sql2 = "UPDATE auth_db.users_auth SET username=? WHERE user_id=?";

        try (Connection conn = ERPDB.getConnection())
        {
            // Update contact number
            try (PreparedStatement ps1 = conn.prepareStatement(sql1))
            {
                ps1.setLong(1, i.getContactno());
                ps1.setInt(2, i.getUserID());
                ps1.executeUpdate();
            }

            // Update username
            try (PreparedStatement ps2 = conn.prepareStatement(sql2))
            {
                ps2.setString(1, i.getUsername());
                ps2.setInt(2, i.getUserID());
                ps2.executeUpdate();
            }
            return true;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }
    }

    // DELETE INSTRUCTOR
    public boolean deleteInstructor(int userId)
    {
        String sql = "DELETE FROM erp_db.instructors WHERE user_id=?";
        try (Connection conn = ERPDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql))
        {
            ps.setInt(1, userId);
            return ps.executeUpdate() > 0;
        }
        catch (SQLException e) { e.printStackTrace(); }
        return false;
    }

    // GET COURSES TAUGHT BY THIS INSTRUCTOR
    public List<Course> getCoursesForInstructor(int instructorUserId)
    {
        List<Course> list = new ArrayList<>();
        Instructor instructor = getInstructorByUserId(instructorUserId);
        if (instructor == null) { return list; }

        String sql = "SELECT code, title, credits, type, max_students, num_students, instructor_name " +
                    "FROM erp_db.courses WHERE instructor_name = ?";

        try (Connection conn = ERPDB.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql))
        {
            ps.setString(1, instructor.getUsername());   // match name stored in DB
            try (ResultSet rs = ps.executeQuery())
            {
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
        }
        catch (SQLException e) { e.printStackTrace(); }
        return list;
    }
}
