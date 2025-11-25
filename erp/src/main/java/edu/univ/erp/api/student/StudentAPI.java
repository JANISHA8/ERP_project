package edu.univ.erp.api.student;

import edu.univ.erp.auth.session.SessionInfo;
import edu.univ.erp.domain.Task;
import edu.univ.erp.service.RegisterForCourses;
import edu.univ.erp.service.DropCourses;

public class StudentAPI
{
    public boolean registerCourse(int courseId)
    {
        SessionInfo session = SessionInfo.getCurrentSession();

        if(session == null) {
            System.out.println("No active session");
            return false;
        }

        RegisterForCourses service = new RegisterForCourses();
        return service.register(session, Task.REGISTER_FOR_COURSES, courseId);
    }

    public boolean dropCourse(int courseId)
    {
        SessionInfo session = SessionInfo.getCurrentSession();

        if(session == null) {
            System.out.println("No active session");
            return false;
        }

        DropCourses service = new DropCourses();
        return service.drop(session, Task.DROP_COURSES, courseId);
    }
}
