package edu.univ.erp.service;

import edu.univ.erp.access.Allowed;
import edu.univ.erp.access.MaintenanceMode;
import edu.univ.erp.data.CourseData;
import edu.univ.erp.data.GradesData;
import edu.univ.erp.data.SettingsData;
import edu.univ.erp.domain.Role;
import edu.univ.erp.domain.Task;
import edu.univ.erp.domain.ActionResult;

import java.util.List;

public class EnterScores
{
    // ENTER / UPDATE SCORE
    public ActionResult enterScore(int instructorId, int enrollmentId, String courseCode, String component, double marks)
    {
        if (!MaintenanceMode.can_operate_mm(Role.INSTRUCTOR, new SettingsData()))
        {
            System.out.println("SYSTEM IN MAINTENANCE - SCORE ENTRY BLOCKED");
            return ActionResult.MAINTENANCE_MODE;
        }

        boolean allowed = Allowed.can_operate_rs(Role.INSTRUCTOR, Task.ENTER_SCORES);
        if (!allowed)
        {
            System.out.println("ACCESS DENIED for ENTER_SCORES");
            return ActionResult.NOT_ALLOWED;
        }
        CourseData cd = new CourseData();

        if (!cd.isCourseOfInstructor(courseCode, instructorId))
        {
            System.out.println("Instructor " + instructorId + " does not teach course " + courseCode);
            return ActionResult.NOT_ALLOWED;
        }

        GradesData gd = new GradesData();
        boolean result = gd.saveScore(enrollmentId, courseCode, component, marks);
        if (result)
        {
            System.out.println("Score saved successfully for enrollment " + enrollmentId);
            return ActionResult.SUCCESS;
        }
        else
        {
            System.out.println("DB ERROR: Failed to save score for enrollment " + enrollmentId);
            return ActionResult.DB_ERROR;
        }
    }

    // ADD NEW COMPONENT
    public ActionResult addComponent(int instructorId, String courseCode, String component)
    {
        if (!MaintenanceMode.can_operate_mm(Role.INSTRUCTOR, new SettingsData()))
        {
            System.out.println("SYSTEM IN MAINTENANCE - ADD COMPONENT BLOCKED");
            return ActionResult.MAINTENANCE_MODE;
        }
        CourseData cd = new CourseData();

        if (!cd.isCourseOfInstructor(courseCode, instructorId))
        {
            System.out.println("Instructor " + instructorId + " does not teach course " + courseCode);
            return ActionResult.NOT_ALLOWED;
        }
        GradesData gd = new GradesData();

        List<Integer> enrollments = gd.getEnrollmentsForCourse(courseCode);
        if (enrollments.isEmpty())
        {
            System.out.println("No enrollments found for course " + courseCode);
            return ActionResult.NOT_FOUND;
        }
        boolean success = true;
        for (int eid : enrollments)
        {
            boolean ok = gd.saveScore(eid, courseCode, component, 0);
            if (!ok) { System.out.println("Failed to add component " + component + " for enrollment " + eid); }
            success = success && ok;
        }
        if (success)
        {
            System.out.println("Component " + component + " added for all students in course " + courseCode);
            return ActionResult.SUCCESS;
        }
        else
        {
            System.out.println("DB ERROR: Failed to add component for some students in course " + courseCode);
            return ActionResult.DB_ERROR;
        }
    }
}
