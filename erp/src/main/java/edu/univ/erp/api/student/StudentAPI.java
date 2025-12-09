package edu.univ.erp.api.student;

import java.util.List;

import edu.univ.erp.auth.session.SessionInfo;
import edu.univ.erp.data.CourseData;
import edu.univ.erp.data.TimetableData;
import edu.univ.erp.domain.ActionResult;
import edu.univ.erp.domain.Course;
import edu.univ.erp.domain.Role;
import edu.univ.erp.domain.Student;
import edu.univ.erp.domain.Task;
import edu.univ.erp.domain.TimeTable;
import edu.univ.erp.service.RegisterForCourses;
import edu.univ.erp.service.DropCourses;
import edu.univ.erp.service.GetStudent;
import edu.univ.erp.service.UpdateStudentProfile;

public class StudentAPI
{
    // GET STUDENT
    public Student getStudentByEmail(String email)
    {
        if(!SessionInfo.isLoggedIn()) { return null; }
        GetStudent gi = new GetStudent();
        return gi.getStudent(email);
    }

    // REGISTER COURSE
    public ActionResult registerCourse(String courseCode)
    {
        if(!SessionInfo.isLoggedIn()) { return ActionResult.NOT_ALLOWED; }
        RegisterForCourses service = new RegisterForCourses();
        return service.register(Role.STUDENT, Task.REGISTER_FOR_COURSES, courseCode);
    }

    // DROP COURSE
    public ActionResult dropCourse(String courseCode)
    {
        if(!SessionInfo.isLoggedIn()) { return ActionResult.NOT_ALLOWED; }
        DropCourses service = new DropCourses();
        return service.drop(Role.STUDENT, Task.DROP_COURSES, courseCode);
    }

    // MY COURSES
    public List<Course> getMyCourses()
    {
        if (!SessionInfo.isLoggedIn()) { return List.of(); }
        CourseData cd = new CourseData();
        return cd.getCoursesForStudent(SessionInfo.getUserID());
    }

    // UPDATE STUDENT PROFILE
    public ActionResult updateStudentProfile(Student s)
    {
        if(!SessionInfo.isLoggedIn()) { return ActionResult.NOT_ALLOWED; }
        UpdateStudentProfile service = new UpdateStudentProfile();
        return service.update(s);
    }

    public List<TimeTable> getMyTimetable()
    {
        if (!SessionInfo.isLoggedIn()) { return List.of(); }
        TimetableData data = new TimetableData();
        return data.getTimetableForStudent(SessionInfo.getUserID());
    }
}
