package edu.univ.erp.api.admin;

import edu.univ.erp.auth.session.SessionInfo;
import edu.univ.erp.domain.ActionResult;
import edu.univ.erp.domain.Course;
import edu.univ.erp.domain.Role;
import edu.univ.erp.service.CreateEditCourses;

import java.util.List;

public class CourseAPI
{
    private final CreateEditCourses service = new CreateEditCourses();

    // GET ALL COURSES
    public List<Course> fetchAllCourses(Role role)
    {
        if (!SessionInfo.isLoggedIn()) { return null; }
        return service.getAllCourses(role);
    }

    // CREATE COURSE
    public ActionResult createCourse(Role role, Course c)
    {
        if (!SessionInfo.isLoggedIn()) { return ActionResult.NOT_ALLOWED; }
        return service.createCourse(role, c);
    }

    // UPDATE COURSE
    public ActionResult updateCourse(Role role, Course c)
    {
        if (!SessionInfo.isLoggedIn()) { return ActionResult.NOT_ALLOWED; }
        return service.updateCourse(role, c);
    }

    // DELETE COURSE
    public ActionResult deleteCourse(Role role, String code)
    {
        if (!SessionInfo.isLoggedIn()) { return ActionResult.NOT_ALLOWED; }
        return service.deleteCourse(role, code);
    }
}
