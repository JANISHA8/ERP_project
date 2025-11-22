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
        String sql =
            "SELECT s.*, u.username, u.email_id AS user_email, u.password_hash, " +
            "u.status, u.last_login " +
            "FROM students s " +
            "JOIN users_auth u ON s.user_id = u.user_id " +
            "WHERE s.user_id = ?";

        try (Connection conn = ERPDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql))
        {
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next())
            {
                // Handle DOB
                Date dobSql = rs.getDate("dob");
                LocalDate dob = (dobSql == null) ? null : dobSql.toLocalDate();

                // Handle last login
                Timestamp ts = rs.getTimestamp("last_login");
                LocalDateTime lastLogin = (ts == null) ? null : ts.toLocalDateTime();

                return new Student(
                    rs.getInt("user_id"),             // userID
                    rs.getString("username"),         // username
                    rs.getString("user_email"),       // EmailID (from users_auth)
                    rs.getString("password_hash"),    // passwordHash
                    rs.getString("status"),           // status
                    lastLogin,                                     // lastLogin
                    rs.getInt("roll_no"),             // RollNO
                    rs.getString("gender"),           // gender
                    rs.getLong("contact_no"),         // contact_no
                    dob,                                           // dob
                    rs.getString("nationality"),      // nationality
                    rs.getString("email_id"),         // email_id (student table)
                    rs.getString("program"),          // program
                    rs.getString("branch"),           // branch
                    rs.getInt("current_year"),        // current_year
                    rs.getInt("current_sem"),         // current_sem
                    rs.getInt("graduation_year"),     // graduation_year
                    null                                  // courses
                );
            }
        } catch (SQLException e)
        { e.printStackTrace(); }

        return null;
    }

    // GET ALL STUDENTS
    public List<Student> getAllStudents()
    {
        List<Student> list = new ArrayList<>();

        String sql =
            "SELECT s.*, u.username, u.email_id AS user_email, u.password_hash, " +
            "u.status, u.last_login " +
            "FROM students s " +
            "JOIN users_auth u ON s.user_id = u.user_id";

        try (Connection conn = ERPDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery())
        {
            while (rs.next())
            {
                // Handle DOB
                Date dobSql = rs.getDate("dob");
                LocalDate dob = (dobSql == null) ? null : dobSql.toLocalDate();

                // Handle last login
                Timestamp ts = rs.getTimestamp("last_login");
                LocalDateTime lastLogin = (ts == null) ? null : ts.toLocalDateTime();

                list.add(new Student(
                    rs.getInt("user_id"),             // userID
                    rs.getString("username"),         // username
                    rs.getString("user_email"),       // EmailID (user table)
                    rs.getString("password_hash"),    // passwordHash
                    rs.getString("status"),           // status
                    lastLogin,                                    // lastLogin
                    rs.getInt("roll_no"),             // RollNO
                    rs.getString("gender"),           // gender
                    rs.getLong("contact_no"),         // contact_no
                    dob,                                          // dob
                    rs.getString("nationality"),      // nationality
                    rs.getString("email_id"),         // email_id (student table)
                    rs.getString("program"),          // program
                    rs.getString("branch"),           // branch
                    rs.getInt("current_year"),        // current_year
                    rs.getInt("current_sem"),         // current_sem
                    rs.getInt("graduation_year"),     // graduation_year
                    null                                  // courses
                ));
            }
        } catch (SQLException e)
        { e.printStackTrace(); }

        return list;
    }

    // INSERT STUDENT
    public boolean insertStudent(Student s)
    {
        String sql =
            "INSERT INTO students (user_id, roll_no, gender, contact_no, dob, nationality, " +
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
        } catch (SQLException e)
        { e.printStackTrace(); }

        return false;
    }

    // UPDATE STUDENT INFO
    public boolean updateStudent(Student s)
    {
        String sql =
            "UPDATE students SET roll_no=?, gender=?, contact_no=?, dob=?, nationality=?, " +
            "email_id=?, program=?, branch=?, current_year=?, current_sem=?, graduation_year=? " +
            "WHERE user_id=?";

        try (Connection conn = ERPDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql))
        {
            stmt.setInt(1, s.getRollNO());
            stmt.setString(2, s.getGender());
            stmt.setLong(3, s.getContact_no());
            stmt.setDate(4, Date.valueOf(s.getDob()));
            stmt.setString(5, s.getNationality());
            stmt.setString(6, s.getEmailID());
            stmt.setString(7, s.getProgram());
            stmt.setString(8, s.getBranch());
            stmt.setInt(9, s.getCurrent_year());
            stmt.setInt(10, s.getCurrent_sem());
            stmt.setInt(11, s.getGraduation_year());
            stmt.setInt(12, s.getUserID());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e)
        { e.printStackTrace(); }

        return false;
    }
}
