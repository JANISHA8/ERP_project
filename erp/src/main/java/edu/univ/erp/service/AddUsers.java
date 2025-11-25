package edu.univ.erp.service;

import edu.univ.erp.access.Allowed;
import edu.univ.erp.access.MaintenanceMode;
import edu.univ.erp.auth.session.SessionInfo;
import edu.univ.erp.domain.Task;
import edu.univ.erp.data.SettingsData;

public class AddUsers
{
    public void addUsers(SessionInfo session, Task task, SettingsData settings)
    {
        boolean allowed = Allowed.can_operate_rs(session, task) && MaintenanceMode.can_operate_mm(session, settings);
        if (allowed)
        {
            // perform this task
            // call DB layers (DB layers has the code of performing functions and connections to mysql)
        }
        else
        {
            // print error message
        }
    }
}
