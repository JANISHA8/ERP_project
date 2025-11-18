package edu.univ.erp.access;

import edu.univ.erp.domain.Role;
import edu.univ.erp.domain.Task; // for getting to know the task (service) being performed
import edu.univ.erp.auth.session.SessionInfo; // for getting current user role

public class Allowed
{
    // static keyword is used so that we do not need to create an object of Allowed class
    public static boolean can_operate_rs(SessionInfo session, Task task) // acc to user specific services
    {
        Role role = session.getRole();
        switch (role)
        {
            // update when more tasks are added
            case ADMIN:
                return true; // ADMIN can perform all tasks
            case INSTRUCTOR:
                return task==Task.ENTER_SCORES || task==Task.COMPUTE_FINAL || task==Task.SEE_MY_SECTIONS || task==Task.SEE_CLASS_STATS;
            case STUDENT:
                return task==Task.DROP_COURSES || task==Task.REGISTER_FOR_COURSES || task==Task.VIEW_TIMETABLE || task==Task.VIEW_GRADES || task==Task.DOWNLOAD_TRANSCRIPT; // not always; need to put more conditions
            default:
                return false; // or error message
        }
    }
}