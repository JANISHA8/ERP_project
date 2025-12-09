package edu.univ.erp.service;

import edu.univ.erp.access.Allowed;
import edu.univ.erp.access.MaintenanceMode;
import edu.univ.erp.auth.session.SessionInfo;
import edu.univ.erp.data.CourseData;
import edu.univ.erp.data.SettingsData;
import edu.univ.erp.domain.ActionResult;
import edu.univ.erp.domain.Role;
import edu.univ.erp.domain.Task;

public class DropCourses
{
    public ActionResult drop(Role role, Task task, String courseCode)
    {
        if (!MaintenanceMode.can_operate_mm(role, new SettingsData()))
        {
            System.out.println("SYSTEM IN MAINTENANCE - DROP BLOCKED");
            return ActionResult.MAINTENANCE_MODE;
        }

        if (!Allowed.can_operate_rs(role, task))
        {
            System.out.println("NOT ALLOWED to drop course");
            return ActionResult.NOT_ALLOWED;
        }

        String courseId = CourseData.getCourseIdByCode(courseCode);
        if (courseId == null)
        {
            System.out.println("Course not found : " + courseCode);
            return ActionResult.NOT_FOUND;
        }

        boolean result = CourseData.dropStudent(SessionInfo.getUserID(), courseCode);
        if (result)
        {
            System.out.println("Course dropped successfully: " + courseCode);
            return ActionResult.SUCCESS;
        }
        else
        {
            System.out.println("Student not registered OR DB error for: " + courseCode);
            return ActionResult.DB_ERROR;
        }
    }
}
