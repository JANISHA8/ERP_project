package edu.univ.erp.data;

import edu.univ.erp.domain.Student;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class StudentsData
{
    // GET STUDENT BY USER ID
    public Student getStudentByUserId(int userId)
    {
        String sql = "SELECT s.*, u.username, u.email_id AS user_email, u.password_hash, " +
                    "u.status, u.last_login " +
                    "FROM erp_db.students s " +
                    "JOIN auth_db.users_auth u ON s.user_id = u.user_id " +
                    "WHERE s.user_id = ?";

        try (Connection conn = ERPDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql))
        {
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next())
            {
                Date dobSql = rs.getDate("dob");
                LocalDate dob = (dobSql == null) ? null : dobSql.toLocalDate();
                Timestamp ts = rs.getTimestamp("last_login");
                LocalDateTime lastLogin = (ts == null) ? null : ts.toLocalDateTime();

                return new Student(rs.getInt("user_id"),
                                    rs.getString("username"),
                                    rs.getString("user_email"),
                                    rs.getString("password_hash"),
                                    rs.getString("status"),
                                    lastLogin,
                                    rs.getInt("roll_no"),
                                    rs.getString("gender"),
                                    rs.getLong("contact_no"),
                                    dob,
                                    rs.getString("nationality"),
                                    rs.getString("email_id"),
                                    rs.getString("program"),
                                    rs.getString("branch"),
                                    rs.getInt("current_year"),
                                    rs.getInt("current_sem"),
                                    rs.getInt("graduation_year"),
                                    null);
            }
        }
        catch (SQLException e) { e.printStackTrace(); }
        return null;
    }

    // GET STUDENT BY USER ID
    public Student getStudentByUserEmail(String email)
    {
        String sql = "SELECT s.*, u.username, u.email_id AS user_email, u.password_hash, " +
                    "u.status, u.last_login " +
                    "FROM erp_db.students s " +
                    "JOIN auth_db.users_auth u ON s.user_id = u.user_id " +
                    "WHERE u.email_id = ?";
        try (Connection conn = ERPDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql))
        {
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            if (rs.next())
            {
                Date dobSql = rs.getDate("dob");
                LocalDate dob = (dobSql == null) ? null : dobSql.toLocalDate();
                Timestamp ts = rs.getTimestamp("last_login");
                LocalDateTime lastLogin = (ts == null) ? null : ts.toLocalDateTime();

                return new Student(rs.getInt("user_id"),
                                rs.getString("username"),
                                rs.getString("user_email"),
                                rs.getString("password_hash"),
                                rs.getString("status"),
                                lastLogin,
                                rs.getInt("roll_no"),
                                rs.getString("gender"),
                                rs.getLong("contact_no"), 
                                dob,
                                rs.getString("nationality"),
                                rs.getString("email_id"),
                                rs.getString("program"),
                                rs.getString("branch"),
                                rs.getInt("current_year"),
                                rs.getInt("current_sem"),
                                rs.getInt("graduation_year"),
                                null);
            }
        }
        catch (SQLException e) { e.printStackTrace(); }
        return null;
    }

    // GET ALL STUDENTS
    public List<Student> getAllStudents()
    {
        List<Student> list = new ArrayList<>();
        String sql = "SELECT s.*, u.username, u.email_id AS user_email, u.password_hash, " +
                    "u.status, u.last_login " +
                    "FROM erp_db.students s " +
                    "JOIN auth_db.users_auth u ON s.user_id = u.user_id";
        try (Connection conn = ERPDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery())
        {
            while (rs.next())
            {
                Date dobSql = rs.getDate("dob");
                LocalDate dob = (dobSql == null) ? null : dobSql.toLocalDate();
                Timestamp ts = rs.getTimestamp("last_login");
                LocalDateTime lastLogin = (ts == null) ? null : ts.toLocalDateTime();

                list.add(new Student(rs.getInt("user_id"),
                                    rs.getString("username"),
                                    rs.getString("user_email"),
                                    rs.getString("password_hash"),
                                    rs.getString("status"),
                                    lastLogin,
                                    rs.getInt("roll_no"),
                                    rs.getString("gender"),
                                    rs.getLong("contact_no"),
                                    dob,
                                    rs.getString("nationality"),
                                    rs.getString("email_id"),
                                    rs.getString("program"),
                                    rs.getString("branch"),
                                    rs.getInt("current_year"),
                                    rs.getInt("current_sem"),
                                    rs.getInt("graduation_year"),
                                    null));
            }
        }
        catch (SQLException e) { e.printStackTrace(); }
        return list;
    }

    // INSERT STUDENT
    public boolean insertStudent(Student s)
    {
        String sql = "INSERT INTO erp_db.students (user_id, roll_no, gender, contact_no, dob, nationality, " +
                    "email_id, program, branch, current_year, current_sem, graduation_year) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = ERPDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql))
        {
            stmt.setInt(1, s.getUserID());
            stmt.setInt(2, s.getRollNO());
            stmt.setString(3, s.getGender());
            stmt.setLong(4, s.getContact_no());
            stmt.setDate(5, Date.valueOf(s.getDob()));
            stmt.setString(6, s.getNationality());
            stmt.setString(7, s.getEmailID());
            stmt.setString(8, s.getProgram());
            stmt.setString(9, s.getBranch());
            stmt.setInt(10, s.getCurrent_year());
            stmt.setInt(11, s.getCurrent_sem());
            stmt.setInt(12, s.getGraduation_year());
            return stmt.executeUpdate() > 0;
        }
        catch (SQLException e) { e.printStackTrace(); }
        return false;
    }

    // UPDATE STUDENT INFO
    public boolean updateStudent(Student s)
    {
        String sql1 = "UPDATE erp_db.students SET contact_no = ? WHERE user_id = ?";
        String sql2 = "UPDATE auth_db.users_auth SET username = ? WHERE user_id = ?";

        try (Connection conn = ERPDB.getConnection())
        {
            // Update CONTACT NUMBER in STUDENTS table
            int rows1;
            try (PreparedStatement ps1 = conn.prepareStatement(sql1))
            {
                ps1.setLong(1, s.getContact_no());
                ps1.setInt(2, s.getUserID());
                rows1 = ps1.executeUpdate();
            }

            // Update USERNAME in USERS_AUTH table
            int rows2;
            try (PreparedStatement ps2 = conn.prepareStatement(sql2))
            {
                ps2.setString(1, s.getUsername());
                ps2.setInt(2, s.getUserID());
                rows2 = ps2.executeUpdate();
            }
            return rows1 > 0 && rows2 > 0;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return false;
        }
    }
}
