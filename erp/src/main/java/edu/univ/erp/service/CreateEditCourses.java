package edu.univ.erp.service;

import edu.univ.erp.access.Allowed;
import edu.univ.erp.access.MaintenanceMode;
import edu.univ.erp.data.CourseData;
import edu.univ.erp.data.SettingsData;
import edu.univ.erp.domain.ActionResult;
import edu.univ.erp.domain.Course;
import edu.univ.erp.domain.Role;
import edu.univ.erp.domain.Task;

import java.util.List;

public class CreateEditCourses
{
    // CREATE COURSE
    public ActionResult createCourse(Role role, Course course)
    {
        if (!MaintenanceMode.can_operate_mm(role, new SettingsData())) { return ActionResult.MAINTENANCE_MODE; }

        if (!Allowed.can_operate_rs(role, Task.CREATE_COURSE)) { return ActionResult.NOT_ALLOWED; }

        CourseData cd = new CourseData();
        boolean ok = cd.createCourse(course);
        return ok ? ActionResult.SUCCESS : ActionResult.DB_ERROR;
    }

    // UPDATE COURSE
    public ActionResult updateCourse(Role role, Course course)
    {
        if (!MaintenanceMode.can_operate_mm(role, new SettingsData())) { return ActionResult.MAINTENANCE_MODE; }

        if (!Allowed.can_operate_rs(role, Task.UPDATE_COURSE)) { return ActionResult.NOT_ALLOWED; }

        CourseData cd = new CourseData();
        boolean ok = cd.updateCourse(course);
        return ok ? ActionResult.SUCCESS : ActionResult.DB_ERROR;
    }

    // GET ALL COURSES
    public List<Course> getAllCourses(Role role)
    {
        if (!Allowed.can_operate_rs(role, Task.VIEW_COURSE)) { return null; }
        CourseData cd = new CourseData();
        return cd.getAllCourses();
    }

    public ActionResult deleteCourse(Role role, String code)
    {
        if (role != Role.ADMIN) { return ActionResult.NOT_ALLOWED; }
        if (!MaintenanceMode.can_operate_mm(role, new SettingsData())) { return ActionResult.MAINTENANCE_MODE; }
        CourseData data = new CourseData();
        if (data.areStudentsEnrolled(code)) { return ActionResult.IN_USE; }
        boolean success = data.deleteCourse(code);
        return success ? ActionResult.SUCCESS : ActionResult.DB_ERROR;
    }
}
