package edu.univ.erp.access;

import edu.univ.erp.domain.Role;
import edu.univ.erp.data.SettingsData; // for getting curerent maintenance mode
import edu.univ.erp.auth.session.SessionInfo; // for getting current user role

public class MaintenanceMode
{
    // static keyword is used so that we do not need to create an object of Allowed class
    public static boolean can_operate_mm(SessionInfo session, SettingsData settings) // acc to maintenance mode
    {
        boolean maintenance = settings.getMaintenanceMode();
        if (maintenance)
        {
            return session.getRole() == Role.ADMIN;
        }
        return true;
    }
}
