package edu.univ.erp.auth.session;

import edu.univ.erp.auth.hash.AuthDB;
import java.sql.*;
import java.time.LocalDateTime;

public class SessionData
{
    public void updateLoginStatus(int userId, boolean isOnline, LocalDateTime time)
    {
        try (Connection c = AuthDB.getConnection())
        {
            if (isOnline)
            {
                String sql =
                    "UPDATE auth_db.user_session_status " +
                    "SET status = 'ONLINE', last_login = ? " +
                    "WHERE user_id = ?";

                try (PreparedStatement ps = c.prepareStatement(sql))
                {
                    ps.setTimestamp(1, Timestamp.valueOf(time));
                    ps.setInt(2, userId);
                    ps.executeUpdate();
                }
            }
            else
            {
                String sql =
                    "UPDATE auth_db.user_session_status " +
                    "SET status = 'OFFLINE' " +
                    "WHERE user_id = ?";

                try (PreparedStatement ps = c.prepareStatement(sql))
                {
                    ps.setInt(1, userId);
                    ps.executeUpdate();
                }
            }
        }
        catch (Exception e) { e.printStackTrace(); }
    }
}
