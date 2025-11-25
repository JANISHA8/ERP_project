package edu.univ.erp.api.student;

import edu.univ.erp.auth.session.SessionInfo;
import edu.univ.erp.domain.Task;
import edu.univ.erp.service.RegisterForCourses;
import edu.univ.erp.service.DropCourses;

public class StudentAPI
{
    // REGISTER COURSE
    public boolean registerCourse(String courseCode)
    {
        if(!SessionInfo.isLoggedIn())
        {
            System.out.println("No active session");
            return false;
        }
        RegisterForCourses service = new RegisterForCourses();
        return service.register(new SessionInfo(), Task.REGISTER_FOR_COURSES, courseCode);
    }

    // DROP COURSE
    public boolean dropCourse(int courseId)
    {
        if(!SessionInfo.isLoggedIn())
        {
            System.out.println("No active session");
            return false;
        }
        DropCourses service = new DropCourses();
        return service.drop(new SessionInfo(), Task.DROP_COURSES, courseId);
    }
}
