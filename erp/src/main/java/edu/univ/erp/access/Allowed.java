package edu.univ.erp.access;

import edu.univ.erp.domain.Role;
import edu.univ.erp.domain.Task;

public class Allowed
{
    public static boolean can_operate_rs(Role role, Task task)
    {
        if (role == null) return false;

        switch (role)
        {
            case ADMIN:
                return true;

            case INSTRUCTOR:
                return task==Task.UPDATE_PROFILE || task==Task.LOGIN || task==Task.ENTER_SCORES || task==Task.COMPUTE_FINAL || task==Task.SEE_MY_SECTIONS || task==Task.SEE_CLASS_STATS;

            case STUDENT:
                return task==Task.UPDATE_PROFILE || task==Task.LOGIN || task==Task.DROP_COURSES || task==Task.REGISTER_FOR_COURSES || task==Task.VIEW_TIMETABLE || task==Task.VIEW_GRADES;

            default:
                return false;
        }
    }
}
