package edu.univ.erp.auth.hash;

import edu.univ.erp.domain.User;
import edu.univ.erp.domain.Role;

import java.sql.*;
import java.time.LocalDateTime;
import java.security.MessageDigest;

public class UserAuth
{
    // HASH PASSWORD
    public static String hash(String password)
    {
        try
        {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(password.getBytes("UTF-8"));

            StringBuilder hex = new StringBuilder();
            for (byte b : hash)
                hex.append(String.format("%02x", b));
            return hex.toString();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    // VERIFY PASSWORD
    public boolean verifyPassword(int userId, String rawPassword)
    {
        String sql = "SELECT password_hash FROM auth_db.users_auth WHERE user_id = ?";

        try (Connection c = AuthDB.getConnection();
             PreparedStatement ps = c.prepareStatement(sql))
        {
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();

            if (rs.next())
            {
                String storedHash = rs.getString("password_hash");
                return hash(rawPassword).equals(storedHash);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return false;
    }

    // UPDATE PASSWORD
    public boolean updatePassword(int userId, String newHash)
    {
        String sql = "UPDATE auth_db.users_auth SET password_hash = ? WHERE user_id = ?";

        try (Connection c = AuthDB.getConnection();
             PreparedStatement ps = c.prepareStatement(sql))
        {
            ps.setString(1, newHash);
            ps.setInt(2, userId);
            return ps.executeUpdate() > 0;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return false;
    }

    // CHECK USER CREDENTIALS
    public boolean checkUser(String email, String password, String role)
    {
        String sql = "SELECT * FROM auth_db.users_auth WHERE email_id=? AND password_hash=? AND role=?";
        try (Connection conn = AuthDB.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql))
        {
            pst.setString(1, email);
            pst.setString(2, hash(password));
            pst.setString(3, role);
            ResultSet rs = pst.executeQuery();
            return rs.next();
        }
        catch (Exception e) { e.printStackTrace(); }
        return false;
    }

    // GET USER BY USERNAME
    public User getUserByUsername(String username)
    {
        String sql = "SELECT * FROM auth_db.users_auth WHERE username = ?";
        try (Connection conn = AuthDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql))
        {
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            if (rs.next())
            {
                Timestamp ts = rs.getTimestamp("last_login");
                LocalDateTime lastLogin = (ts == null) ? null : ts.toLocalDateTime();
                return new User( rs.getInt("user_id"),
                                rs.getString("username"),
                                Role.valueOf(rs.getString("role")),
                                rs.getString("email_id"),
                                rs.getString("password_hash"),
                                rs.getString("status"),
                                lastLogin);
            }
        }
        catch (SQLException e) { e.printStackTrace(); }
        return null;
    }

    // GET USER BY EMAILID
    public User getUserByEmail(String email)
    {
        String sql = "SELECT * FROM auth_db.users_auth WHERE email_id = ?";
        try (Connection conn = AuthDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql))
        {
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            if (rs.next())
            {
                Timestamp ts = rs.getTimestamp("last_login");
                LocalDateTime lastLogin = (ts == null) ? null : ts.toLocalDateTime();

                return new User( rs.getInt("user_id"),
                                rs.getString("username"),
                                Role.valueOf(rs.getString("role")),
                                rs.getString("email_id"),
                                rs.getString("password_hash"),
                                rs.getString("status"),
                                lastLogin);
            }
        }
        catch (SQLException e) { e.printStackTrace(); }
        return null;
    }

    // GET USER BY ID
    public User getUserById(int userId)
    {
        String sql = "SELECT * FROM auth_db.users_auth WHERE user_id = ?";

        try (Connection conn = AuthDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql))
        {
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next())
            {
                Timestamp ts = rs.getTimestamp("last_login");
                LocalDateTime lastLogin = (ts == null) ? null : ts.toLocalDateTime();

                return new User( rs.getInt("user_id"),
                                rs.getString("username"),
                                Role.valueOf(rs.getString("role")),
                                rs.getString("email_id"),
                                rs.getString("password_hash"),
                                rs.getString("status"),
                                lastLogin);
            }
        }
        catch (SQLException e) { e.printStackTrace(); }
        return null;
    }

    // INSERT NEW USER
    public boolean insertUser(User user)
    {
        String sql = "INSERT INTO auth_db.users_auth (user_id, username, role, email_id, password_hash, status, last_login) "
           + "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = AuthDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql))
        {
            stmt.setInt(1, user.getUserID());
            stmt.setString(2, user.getUsername());
            stmt.setString(3, user.getRole().name());
            stmt.setString(4, user.getEmailID());
            stmt.setString(5, hash(user.getPasswordHash()));
            stmt.setString(6, user.getStatus());

            if (user.getLastLogin() != null)
            { stmt.setTimestamp(7, Timestamp.valueOf(user.getLastLogin())); }
            else
            { stmt.setTimestamp(7, null); }

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) { e.printStackTrace(); }
        return false;
    }

    // UPDATE LAST LOGIN TIME
    public void updateLastLogin(int userId, LocalDateTime time)
    {
        String sql = "UPDATE auth_db.users_auth SET last_login = ? WHERE user_id = ?";

        try (Connection conn = AuthDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql))
        {
            stmt.setTimestamp(1, Timestamp.valueOf(time));
            stmt.setInt(2, userId);
            stmt.executeUpdate();
        }
        catch (SQLException e) { e.printStackTrace(); }
    }

    public boolean updateStatus(int userId, String status)
    {
        String sql1 = "UPDATE users_auth SET status = ? WHERE user_id = ?";
        String sql2 = "UPDATE user_session_status SET status = ? WHERE user_id = ?";

        try (Connection c = AuthDB.getConnection())
        {
            // Update users_auth
            try (PreparedStatement ps1 = c.prepareStatement(sql1))
            {
                ps1.setString(1, status);
                ps1.setInt(2, userId);
                ps1.executeUpdate();
            }

            // Update user_session_status
            try (PreparedStatement ps2 = c.prepareStatement(sql2))
            {
                ps2.setString(1, status);
                ps2.setInt(2, userId);
                ps2.executeUpdate();
            }

            return true;
        }
        catch (Exception e) { e.printStackTrace(); }
        return false;
    }

    public boolean isUserBlocked(int userId)
    {
        String sql = "SELECT locked_until FROM auth_db.users_auth WHERE user_id = ?";

        try (Connection c = AuthDB.getConnection();
            PreparedStatement ps = c.prepareStatement(sql))
        {
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();

            if (rs.next())
            {
                Timestamp ts = rs.getTimestamp("locked_until");

                if (ts != null)
                {
                    LocalDateTime lockedUntil = ts.toLocalDateTime();
                    return lockedUntil.isAfter(LocalDateTime.now());
                }
            }
        }
        catch (Exception e) { e.printStackTrace(); }

        return false;
    }

    public void recordFailedAttempt(int userId)
    {
        String sql =
            "UPDATE auth_db.users_auth " +
            "SET failed_attempts = failed_attempts + 1, " +
            "locked_until = CASE WHEN failed_attempts + 1 >= 5 " +
            "THEN DATE_ADD(NOW(), INTERVAL 1 MINUTE) ELSE locked_until END " +
            "WHERE user_id = ?";

        try (Connection c = AuthDB.getConnection();
            PreparedStatement ps = c.prepareStatement(sql))
        {
            ps.setInt(1, userId);
            ps.executeUpdate();
        }
        catch (Exception e) { e.printStackTrace(); }
    }

    public void resetAttempts(int userId)
    {
        String sql =
            "UPDATE auth_db.users_auth SET failed_attempts = 0, locked_until = NULL WHERE user_id = ?";

        try (Connection c = AuthDB.getConnection();
            PreparedStatement ps = c.prepareStatement(sql))
        {
            ps.setInt(1, userId);
            ps.executeUpdate();
        }
        catch (Exception e) { e.printStackTrace(); }
    }
}
