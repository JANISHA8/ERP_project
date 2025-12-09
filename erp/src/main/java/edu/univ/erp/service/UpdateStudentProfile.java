package edu.univ.erp.service;

import edu.univ.erp.access.Allowed;
import edu.univ.erp.access.MaintenanceMode;
import edu.univ.erp.data.SettingsData;
import edu.univ.erp.data.StudentsData;
import edu.univ.erp.domain.Student;
import edu.univ.erp.domain.Task;
import edu.univ.erp.domain.ActionResult;
import edu.univ.erp.domain.Role;

public class UpdateStudentProfile
{
    public ActionResult update(Student s)
    {
        if (!MaintenanceMode.can_operate_mm(Role.STUDENT, new SettingsData())) { return ActionResult.MAINTENANCE_MODE; }
        boolean allowed = Allowed.can_operate_rs(Role.STUDENT, Task.UPDATE_PROFILE);
        if (!allowed) { return ActionResult.NOT_ALLOWED; }
        StudentsData data = new StudentsData();
        boolean ok = data.updateStudent(s);
        return ok ? ActionResult.SUCCESS : ActionResult.DB_ERROR;
    }
}
    