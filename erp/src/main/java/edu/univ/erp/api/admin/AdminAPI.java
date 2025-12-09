package edu.univ.erp.api.admin;

import edu.univ.erp.auth.session.SessionInfo;
import javax.swing.table.DefaultTableModel;

import edu.univ.erp.auth.hash.UserAuth;
import edu.univ.erp.domain.*;
import edu.univ.erp.service.*;

public class AdminAPI
{
    // UPDATE ADMIN PROFILE
    public ActionResult updateAdminProfile(Admin admin)
    {
        if(!SessionInfo.isLoggedIn()) { return ActionResult.NOT_ALLOWED; }

        UpdateAdminProfile service = new UpdateAdminProfile();
        return service.update(admin);
    }

    // ADD STUDENT
    public ActionResult addStudent(Student student)
    {
        if(!SessionInfo.isLoggedIn()) { return ActionResult.NOT_ALLOWED; }

        AddUsers service = new AddUsers();
        return service.addStudent(SessionInfo.getRole(), student);
    }

    // ADD INSTRUCTOR
    public ActionResult addInstructor(Instructor instructor)
    {
        if(!SessionInfo.isLoggedIn()) { return ActionResult.NOT_ALLOWED; }

        AddUsers service = new AddUsers();
        return service.addInstructor(SessionInfo.getRole(), instructor);
    }

    // ADD ADMIN
    public ActionResult addAdmin(Admin admin)
    {
        if(!SessionInfo.isLoggedIn()) { return ActionResult.NOT_ALLOWED; }

        AddUsers service = new AddUsers();
        return service.addAdmin(SessionInfo.getRole(), admin);
    }

    // GET ADMIN
    public Admin getAdminByEmail(String email)
    {
        if(!SessionInfo.isLoggedIn()) { return null; }

        GetAdmin ga = new GetAdmin();
        return ga.getAdmin(email);
    }

    // CHECK USER EXISTS
    public boolean checkUserExists(String userEmail)
    {
        UserAuth ua = new UserAuth();
        return ua.getUserByEmail(userEmail) != null;
    }

    // CREATE COURSE
    public ActionResult createCourse(Course course)
    {
        if(!SessionInfo.isLoggedIn()) { return ActionResult.NOT_ALLOWED; }

        CreateEditCourses service = new CreateEditCourses();
        return service.createCourse(SessionInfo.getRole(), course);
    }

    // UPDATE COURSE
    public ActionResult updateCourse(Course course)
    {
        if(!SessionInfo.isLoggedIn()) { return ActionResult.NOT_ALLOWED; }

        CreateEditCourses service = new CreateEditCourses();
        return service.updateCourse(SessionInfo.getRole(), course);
    }

    // DELETE COURSE
    public ActionResult deleteCourse(String code)
    {
        if(!SessionInfo.isLoggedIn()) { return ActionResult.NOT_ALLOWED; }

        CreateEditCourses service = new CreateEditCourses();
        return service.deleteCourse(SessionInfo.getRole(), code);
    }

    // ASSIGN INSTRUCTOR TO SECTION
    public ActionResult assignInstructor(int sectionId, int instructorId)
    {
        if(!SessionInfo.isLoggedIn()) { return ActionResult.NOT_ALLOWED; }

        AssignInstructor service = new AssignInstructor();
        return service.assign(SessionInfo.getUserID(), Task.ASSIGN_INSTRUCTOR, sectionId, instructorId);
    }

    // TOGGLE MAINTENANCE MODE
    public boolean toggleMaintenance(boolean status)
    {
        if (!SessionInfo.isLoggedIn()) { return false; }

        if (SessionInfo.getRole() != Role.ADMIN) { return false; }

        ToggleMaintenance service = new ToggleMaintenance();
        return service.toggle(SessionInfo.getUserID(), Task.TOGGLE_MAINTENANCE, status);
    }

    // UPDATE USERS
    public ActionResult updateUsersFromTable(DefaultTableModel model, String mode)
    {
        if (!SessionInfo.isLoggedIn())
            return ActionResult.NOT_ALLOWED;

        ManageUsersService service = new ManageUsersService();

        return service.updateUsers(model, mode);
    }
}
