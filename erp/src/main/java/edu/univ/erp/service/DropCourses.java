package edu.univ.erp.service;

import edu.univ.erp.access.Allowed;
import edu.univ.erp.access.MaintenanceMode;
import edu.univ.erp.auth.session.SessionInfo;
import edu.univ.erp.domain.Task;
import edu.univ.erp.data.SettingsData;

public class DropCourses
{
    public void dropCourses(SessionInfo session, Task task, SettingsData settings)
    {
        boolean allowed = Allowed.can_operate_rs(session, task) && MaintenanceMode.can_operate_mm(session, settings);
        if (allowed)
        {
            // perform this task
        }
        else
        {
            // print error message
        }
    }
}
