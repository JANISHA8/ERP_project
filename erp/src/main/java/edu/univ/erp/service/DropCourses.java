package edu.univ.erp.service;

import edu.univ.erp.access.Allowed;
import edu.univ.erp.access.MaintenanceMode;
import edu.univ.erp.auth.session.SessionInfo;
import edu.univ.erp.domain.Task;
import edu.univ.erp.data.SettingsData;
import edu.univ.erp.data.CourseData;

public class DropCourses
{
    public boolean drop(SessionInfo session, Task task, int courseId)
    {
        boolean allowed =
                Allowed.can_operate_rs(session, task) &&
                MaintenanceMode.can_operate_mm(session, new SettingsData());

        if(!allowed) return false;

        return CourseData.dropStudent(SessionInfo.getUserID(), courseId);
    }
}
