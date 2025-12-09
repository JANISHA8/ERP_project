package edu.univ.erp.service;

import edu.univ.erp.access.Allowed;
import edu.univ.erp.access.MaintenanceMode;
import edu.univ.erp.data.CourseData;
import edu.univ.erp.data.GradesData;
import edu.univ.erp.data.SettingsData;
import edu.univ.erp.domain.ActionResult;
import edu.univ.erp.domain.Grade;
import edu.univ.erp.domain.Role;
import edu.univ.erp.domain.Task;

import java.util.List;

public class ComputeFinal
{
    public ActionResult computeFinal(int instructorId, int enrollmentId, String courseCode, StringBuilder resultOut)
    {
        if (!MaintenanceMode.can_operate_mm(Role.INSTRUCTOR, new SettingsData())) { return ActionResult.MAINTENANCE_MODE; }
        if (!Allowed.can_operate_rs(Role.INSTRUCTOR, Task.COMPUTE_FINAL)) { return ActionResult.NOT_ALLOWED; }
        CourseData cd = new CourseData();
        if (!cd.isCourseOfInstructor(courseCode, instructorId))
        {
            System.out.println("You do NOT teach this course");
            return ActionResult.NOT_ALLOWED;
        }

        GradesData gd = new GradesData();
        List<Grade> grades = gd.getGradesByEnrollmentId(enrollmentId);

        if (grades == null || grades.isEmpty())
        {
            System.out.println("No marks found for this student");
            return ActionResult.NOT_FOUND;
        }

        double total = 0;
        for (Grade g : grades)
        {
            if (g.getCourseCode().equals(courseCode) && !g.getComponent().equalsIgnoreCase("FINAL"))
            { total += g.getScore(); }
        }

        // Assign final letter grade
        String grade = total >= 90 ? "A+" :
                        total >= 80 ? "A"  :
                        total >= 70 ? "B"  :
                        total >= 60 ? "C"  : "F";
        boolean updated = gd.updateFinalGrade(enrollmentId, courseCode, grade);
        if (!updated)
        {
            System.out.println("Failed to update DB");
            return ActionResult.DB_ERROR;
        }

        resultOut.append("Total: ")
                 .append(total)
                 .append(" | Grade: ")
                 .append(grade);
        return ActionResult.SUCCESS;
    }
}
