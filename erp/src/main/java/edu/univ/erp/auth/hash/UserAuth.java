//Each DAO handles one table in database
//UserAuthDAO.java       → for login users (Auth DB)
//StudentDAO.java        → student table
//InstructorDAO.java     → instructor table
//CourseDAO.java         → courses table
//SectionDAO.java        → sections table
//EnrollmentDAO.java     → enrollments table
//GradeDAO.java          → grades table
//SettingsDAO.java       → settings table (maintenance mode)

package edu.univ.erp.auth.hash;

import edu.univ.erp.domain.User;
import edu.univ.erp.domain.Role;

import java.sql.*;
import java.time.LocalDateTime;
import java.security.MessageDigest;

public class UserAuth
{
    // PASSWORD HASHING
    private String hashPassword(String password)
    {
        try
        {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(password.getBytes("UTF-8"));

            StringBuilder hex = new StringBuilder();
            for (byte b : hash)
            { hex.append(String.format("%02x", b)); }
            return hex.toString();
        } catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    // CHECK USER CREDENTIALS
    public boolean checkUser(String email, String password, String role)
    {
        String sql = "SELECT * FROM users_auth WHERE email_id=? AND password_hash=? AND role=?";

        try (Connection conn = AuthDB.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql))
        {
            pst.setString(1, email);
            pst.setString(2, hashPassword(password));
            pst.setString(3, role);

            ResultSet rs = pst.executeQuery();
            return rs.next();

        } catch (Exception e)
        { e.printStackTrace(); }

        return false;
    }

    // GET USER BY USERNAME
    public User getUserByUsername(String username)
    {
        String sql = "SELECT * FROM users_auth WHERE username = ?";

        try (Connection conn = AuthDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql))
        {
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();

            if (rs.next())
            {
                Timestamp ts = rs.getTimestamp("last_login");
                LocalDateTime lastLogin = (ts == null) ? null : ts.toLocalDateTime();

                return new User(
                    rs.getInt("user_id"),
                    rs.getString("username"),
                    Role.valueOf(rs.getString("role")),
                    rs.getString("email_id"),
                    rs.getString("password_hash"),
                    rs.getString("status"),
                    lastLogin);
            }

        } catch (SQLException e)
        { e.printStackTrace(); }

        return null;
    }

    // GET USER BY EMAILID
    public User getUserByEmail(String email)
    {
        String sql = "SELECT * FROM users_auth WHERE email_id = ?";

        try (Connection conn = AuthDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql))
        {
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();

            if (rs.next())
            {
                Timestamp ts = rs.getTimestamp("last_login");
                LocalDateTime lastLogin = (ts == null) ? null : ts.toLocalDateTime();

                return new User(
                    rs.getInt("user_id"),
                    rs.getString("username"),
                    Role.valueOf(rs.getString("role")),
                    rs.getString("email_id"),
                    rs.getString("password_hash"),
                    rs.getString("status"),
                    lastLogin);
            }

        } catch (SQLException e)
        { e.printStackTrace(); }

        return null;
    }

    // GET USER BY ID
    public User getUserById(int userId)
    {
        String sql = "SELECT * FROM users_auth WHERE user_id = ?";

        try (Connection conn = AuthDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql))
        {
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next())
            {
                Timestamp ts = rs.getTimestamp("last_login");
                LocalDateTime lastLogin = (ts == null) ? null : ts.toLocalDateTime();

                return new User(
                    rs.getInt("user_id"),
                    rs.getString("username"),
                    Role.valueOf(rs.getString("role")),
                    rs.getString("email_id"),
                    rs.getString("password_hash"),
                    rs.getString("status"),
                    lastLogin);
            }

        } catch (SQLException e)
        { e.printStackTrace(); }

        return null;
    }

    // INSERT NEW USER
    public boolean insertUser(User user)
    {
        String sql = "INSERT INTO users_auth (user_id, username, role, email_id, password_hash, status, last_login) "
                   + "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = AuthDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql))
        {
            stmt.setInt(1, user.getUserID());
            stmt.setString(2, user.getUsername());
            stmt.setString(3, user.getRole().name());
            stmt.setString(4, user.getEmailID());
            stmt.setString(5, hashPassword(user.getPasswordHash()));
            stmt.setString(6, user.getStatus());

            if (user.getLastLogin() != null)
            { stmt.setTimestamp(7, Timestamp.valueOf(user.getLastLogin())); }
            else
            { stmt.setTimestamp(7, null); }

            return stmt.executeUpdate() > 0;

        } catch (SQLException e)
        { e.printStackTrace(); }

        return false;
    }

    // UPDATE LAST LOGIN TIME
    public void updateLastLogin(int userId, LocalDateTime time)
    {
        String sql = "UPDATE users_auth SET last_login = ? WHERE user_id = ?";

        try (Connection conn = AuthDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql))
        {
            stmt.setTimestamp(1, Timestamp.valueOf(time));
            stmt.setInt(2, userId);
            stmt.executeUpdate();
        } catch (SQLException e)
        { e.printStackTrace(); }
    }
}
