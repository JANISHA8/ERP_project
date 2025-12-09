package edu.univ.erp.service;

import edu.univ.erp.access.Allowed;
import edu.univ.erp.auth.hash.UserAuth;
import edu.univ.erp.data.AdminsData;
import edu.univ.erp.data.InstructorsData;
import edu.univ.erp.data.StudentsData;
import edu.univ.erp.domain.*;

public class AddUsers
{
    public ActionResult addStudent(Role role, Student student)
    {
        if (!Allowed.can_operate_rs(role, Task.ADD_USER)) { return ActionResult.NOT_ALLOWED; }
        UserAuth auth = new UserAuth();
        if (!auth.insertUser(student)) { return ActionResult.DB_ERROR; }
        StudentsData sd = new StudentsData();
        return sd.insertStudent(student)
                ? ActionResult.SUCCESS
                : ActionResult.DB_ERROR;
    }

    public ActionResult addInstructor(Role role, Instructor instructor)
    {
        if (!Allowed.can_operate_rs(role, Task.ADD_USER)) { return ActionResult.NOT_ALLOWED; }
        UserAuth auth = new UserAuth();
        if (!auth.insertUser(instructor)) { return ActionResult.DB_ERROR; }
        InstructorsData data = new InstructorsData();
        return data.insertInstructor(instructor)
                ? ActionResult.SUCCESS
                : ActionResult.DB_ERROR;
    }

    public ActionResult addAdmin(Role role, Admin admin)
    {
        if (!Allowed.can_operate_rs(role, Task.ADD_USER)) { return ActionResult.NOT_ALLOWED; }
        UserAuth auth = new UserAuth();
        if (!auth.insertUser(admin)) { return ActionResult.DB_ERROR; }
        AdminsData data = new AdminsData();
        return data.insertAdmin(admin)
                ? ActionResult.SUCCESS
                : ActionResult.DB_ERROR;
    }
}
