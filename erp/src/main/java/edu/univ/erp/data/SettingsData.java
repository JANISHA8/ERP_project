//public boolean getMaintenanceMode()

package edu.univ.erp.data;

import edu.univ.erp.domain.Settings;
import java.sql.*;

public class SettingsData
{
    // GET a setting by key
    public static Settings getSetting(String key)
    {
        String sql = "SELECT * FROM settings WHERE `key` = ?";

        try (Connection conn = ERPDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql))
        {

            stmt.setString(1, key);
            ResultSet rs = stmt.executeQuery();

            if (rs.next())
            {
                return new Settings(
                    rs.getString("key"),
                    rs.getString("value"));
            }

        } catch (SQLException e)
        { e.printStackTrace(); }

        return null;
    }

    // UPDATE a setting
    public boolean updateSetting(String key, String value)
    {
        String sql = "UPDATE settings SET value = ? WHERE `key` = ?";

        try (Connection conn = ERPDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql))
        {

            stmt.setString(1, value);
            stmt.setString(2, key);

            return stmt.executeUpdate() > 0;

        } catch (SQLException e)
        { e.printStackTrace(); }

        return false;
    }
}
