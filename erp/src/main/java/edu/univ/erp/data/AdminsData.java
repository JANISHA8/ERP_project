package edu.univ.erp.data;

import edu.univ.erp.domain.Admin;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class AdminsData
{
    // GET ONE ADMIN BY USER ID
    public Admin getAdminByUserId(int userId)
    {
        String sql = "SELECT a.*, u.username, u.email_id AS user_email, " +
                    "u.password_hash, u.status, u.last_login " +
                    "FROM admins a " +
                    "JOIN auth_db.users_auth u ON a.user_id = u.user_id " +
                    "WHERE a.user_id = ?";

        try (Connection conn = ERPDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql))
        {
            ps.setInt(1, userId);
            try (ResultSet rs = ps.executeQuery())
            {
                if (rs.next())
                {
                    Timestamp ts = rs.getTimestamp("last_login");
                    LocalDateTime lastLogin = (ts == null) ? null : ts.toLocalDateTime();

                    return new Admin( rs.getInt("user_id"),
                                    rs.getString("username"),
                                    rs.getString("user_email"),
                                    rs.getString("password_hash"),
                                    rs.getString("status"),
                                    lastLogin,
                                    rs.getString("department"));
                }
            }
        }
        catch (SQLException e) { e.printStackTrace(); }
        return null;
    }

    // GET ONE ADMIN BY USER ID
    public Admin getAdminByUserEmail(String email)
    {
        String sql = "SELECT a.*, u.username, u.email_id AS user_email, " +
                    "u.password_hash, u.status, u.last_login " +
                    "FROM admins a " +
                    "JOIN auth_db.users_auth u ON a.user_id = u.user_id " +
                    "WHERE u.email_id = ?";

        try (Connection conn = ERPDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql))
        {
            ps.setString(1, email);
            try (ResultSet rs = ps.executeQuery())
            {
                if (rs.next())
                {
                    Timestamp ts = rs.getTimestamp("last_login");
                    LocalDateTime lastLogin = (ts == null) ? null : ts.toLocalDateTime();

                    return new Admin( rs.getInt("user_id"),
                                    rs.getString("username"),
                                    rs.getString("user_email"),
                                    rs.getString("password_hash"),
                                    rs.getString("status"),
                                    lastLogin,
                                    rs.getString("department"));
                }
            }
        }
        catch (SQLException e) { e.printStackTrace(); }
        return null;
    }

    // GET ALL ADMINS
    public List<Admin> getAllAdmins()
    {
        List<Admin> list = new ArrayList<>();
        String sql = "SELECT a.*, u.username, u.email_id AS user_email, " +
                    "u.password_hash, u.status, u.last_login " +
                    "FROM admins a " +
                    "JOIN auth_db.users_auth u ON a.user_id = u.user_id";

        try (Connection conn = ERPDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery())
        {
            while (rs.next())
            {
                Timestamp ts = rs.getTimestamp("last_login");
                LocalDateTime lastLogin = (ts == null) ? null : ts.toLocalDateTime();

                list.add(new Admin(rs.getInt("user_id"),
                                    rs.getString("username"),
                                    rs.getString("user_email"),
                                    rs.getString("password_hash"),
                                    rs.getString("status"),
                                    lastLogin,
                                    rs.getString("department")));
            }
        }
        catch (SQLException e) { e.printStackTrace(); }
        return list;
    }

    // INSERT ADMIN (after user_auth row exists)
    public boolean insertAdmin(Admin a)
    {
        String sql = "INSERT INTO admins (user_id, department) VALUES (?, ?)";

        try (Connection conn = ERPDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql))
        {
            ps.setInt(1, a.getUserID());
            ps.setString(2, a.getDepartment());
            return ps.executeUpdate() > 0;
        }
        catch (SQLException e) { e.printStackTrace(); }
        return false;
    }

    // UPDATE ADMIN
    public boolean updateAdmin(Admin a)
    {
        String sql1 = "UPDATE admins SET department=? WHERE user_id=?";
        String sql2 = "UPDATE auth_db.users_auth SET username=?, email_id=? WHERE user_id=?";

        try (Connection conn = ERPDB.getConnection())
        {
            PreparedStatement ps1 = conn.prepareStatement(sql1);
            ps1.setString(1, a.getDepartment());
            ps1.setInt(2, a.getUserID());

            PreparedStatement ps2 = conn.prepareStatement(sql2);
            ps2.setString(1, a.getUsername());
            ps2.setString(2, a.getEmailID());
            ps2.setInt(3, a.getUserID());

            return ps1.executeUpdate() > 0 && ps2.executeUpdate() > 0;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return false;
    }

    // DELETE ADMIN
    public boolean deleteAdmin(int userId)
    {
        String sql = "DELETE FROM admins WHERE user_id=?";
        try (Connection conn = ERPDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql))
        {
            ps.setInt(1, userId);
            return ps.executeUpdate() > 0;
        }
        catch (SQLException e) { e.printStackTrace(); }
        return false;
    }
}
