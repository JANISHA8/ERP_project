package edu.univ.erp.service;

import edu.univ.erp.auth.session.SessionInfo;
import edu.univ.erp.data.InstructorsData;
import edu.univ.erp.data.SettingsData;
import edu.univ.erp.domain.ActionResult;
import edu.univ.erp.domain.Instructor;
import edu.univ.erp.domain.Role;
import edu.univ.erp.domain.Task;
import edu.univ.erp.access.MaintenanceMode;
import edu.univ.erp.access.Allowed;

public class UpdateInstructorProfile
{
    public ActionResult update(Instructor i)
    {
        if(!SessionInfo.isLoggedIn()) { return ActionResult.NOT_ALLOWED; }
        if (!MaintenanceMode.can_operate_mm(Role.INSTRUCTOR, new SettingsData())) { return ActionResult.MAINTENANCE_MODE; }
        if (!Allowed.can_operate_rs(Role.INSTRUCTOR, Task.UPDATE_PROFILE)) { return ActionResult.NOT_ALLOWED; }
        InstructorsData data = new InstructorsData();
        boolean result = data.updateInstructor(i);
        System.out.println("Instructor profile DB update = " + result);
        return result ? ActionResult.SUCCESS : ActionResult.DB_ERROR;
    }
}
