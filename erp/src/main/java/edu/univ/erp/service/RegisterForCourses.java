package edu.univ.erp.service;

import edu.univ.erp.access.Allowed;
import edu.univ.erp.access.MaintenanceMode;
import edu.univ.erp.auth.session.SessionInfo;
import edu.univ.erp.data.SettingsData;
import edu.univ.erp.data.CourseData;
import edu.univ.erp.domain.ActionResult;
import edu.univ.erp.domain.Task;
import edu.univ.erp.domain.Role;

public class RegisterForCourses
{
    public ActionResult register(Role role, Task task, String courseCode)
    {
        if (!MaintenanceMode.can_operate_mm(role, new SettingsData())) { return ActionResult.MAINTENANCE_MODE; }
        if (!Allowed.can_operate_rs(role, task)) { return ActionResult.NOT_ALLOWED; }
        String courseId = CourseData.getCourseIdByCode(courseCode);
        if (courseId == null)
        {
            System.out.println("Course not found in DB: " + courseCode);
            return ActionResult.NOT_FOUND;
        }
        boolean result = CourseData.registerStudent(SessionInfo.getUserID(), courseCode);
        return result ? ActionResult.SUCCESS : ActionResult.DB_ERROR;
    }
}
