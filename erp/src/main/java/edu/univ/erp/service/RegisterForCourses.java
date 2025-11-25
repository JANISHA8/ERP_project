package edu.univ.erp.service;

import edu.univ.erp.access.Allowed;
import edu.univ.erp.access.MaintenanceMode;
import edu.univ.erp.auth.session.SessionInfo;
import edu.univ.erp.domain.Task;
import edu.univ.erp.data.SettingsData;
import edu.univ.erp.data.CourseData;

public class RegisterForCourses
{
    public boolean register(SessionInfo session, Task task, String courseCode)
    {
        boolean allowed =
                Allowed.can_operate_rs(session, task) &&
                MaintenanceMode.can_operate_mm(session, new SettingsData());

        if(!allowed) return false;

        return CourseData.registerStudent(SessionInfo.getUserID(), courseCode);
    }
}
