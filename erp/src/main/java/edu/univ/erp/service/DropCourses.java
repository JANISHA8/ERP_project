package edu.univ.erp.service;

import edu.univ.erp.access.Allowed;
import edu.univ.erp.access.MaintenanceMode;
import edu.univ.erp.auth.session.SessionInfo;
import edu.univ.erp.domain.Task;
import edu.univ.erp.data.CourseData;
import edu.univ.erp.data.SettingsData;

public class DropCourses
{
    public boolean drop(SessionInfo session, Task task, int courseId)
    {
        if (!Allowed.can_operate_rs(session, task) || 
            !MaintenanceMode.can_operate_mm(session, new SettingsData()))
        {
            return false;
        }

        // CALL DATA LAYER
        return CourseData.dropStudent(session.getUserID(), courseId);
    }
}
