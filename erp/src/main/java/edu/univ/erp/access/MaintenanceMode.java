package edu.univ.erp.access;

import edu.univ.erp.domain.Role;
import edu.univ.erp.domain.Settings;
import edu.univ.erp.data.SettingsData;

public class MaintenanceMode
{
    public static boolean can_operate_mm(Role role, SettingsData settingsData)
    {
        Settings setting = SettingsData.getSetting("Maintenance_Mode");
        if (setting == null) { return true; }

        boolean maintenance = Boolean.parseBoolean(setting.getValue());
        if (!maintenance) { return true; }

        return role == Role.ADMIN;
    }
}
