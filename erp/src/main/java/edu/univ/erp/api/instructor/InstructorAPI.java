package edu.univ.erp.api.instructor;

import edu.univ.erp.auth.session.SessionInfo;
import edu.univ.erp.data.InstructorsData;
import edu.univ.erp.data.CourseData;
import edu.univ.erp.data.GradesData;
import edu.univ.erp.domain.ActionResult;
import edu.univ.erp.domain.Instructor;
import edu.univ.erp.domain.Course;
import edu.univ.erp.service.EnterScores;
import edu.univ.erp.service.GetInstructor;
import edu.univ.erp.service.ComputeFinal;
import edu.univ.erp.service.UpdateInstructorProfile;

import java.util.Collections;
import java.util.List;

public class InstructorAPI
{
    // GET INSTRUCTOR
    public Instructor getInstructorByEmail(String email)
    {
        if(!SessionInfo.isLoggedIn()) { return null; }

        GetInstructor gi = new GetInstructor();
        return gi.getInstructor(email);
    }

    // SEE MY SECTIONS
    public List<Course> getMySections()
    {
        if (!SessionInfo.isLoggedIn()) { return Collections.emptyList(); }

        InstructorsData data = new InstructorsData();
        return data.getCoursesForInstructor(SessionInfo.getUserID());
    }

    // ENTER / UPDATE SCORE
    public ActionResult enterScore(int enrollmentId, String courseCode, String component, double marks)
    {
        if (!SessionInfo.isLoggedIn()) { return ActionResult.NOT_ALLOWED; }

        EnterScores service = new EnterScores();
        return service.enterScore(SessionInfo.getUserID(), enrollmentId, courseCode, component, marks);
    }

    // ADD NEW COMPONENT
    public ActionResult addComponentToCourse(String courseCode, String component)
    {
        if (!SessionInfo.isLoggedIn()) { return ActionResult.NOT_ALLOWED; }

        EnterScores service = new EnterScores();
        return service.addComponent(SessionInfo.getUserID(), courseCode, component);
    }

    // COMPUTE FINAL GRADE
    public ActionResult computeFinal(int enrollmentId, String courseCode, StringBuilder resultOut)
    {
        if (!SessionInfo.isLoggedIn()) {return ActionResult.NOT_ALLOWED; }

        ComputeFinal service = new ComputeFinal();
        return service.computeFinal(SessionInfo.getUserID(), enrollmentId, courseCode, resultOut);
    }

    // COURSE STATISTICS
    public String getCourseStats(String courseCode)
    {
        if (!SessionInfo.isLoggedIn()) { return "Session expired"; }

        CourseData cd = new CourseData();
        if (!cd.isCourseOfInstructor(courseCode, SessionInfo.getUserID())) { return "You are not the instructor of this course."; }

        GradesData gd = new GradesData();
        double avg = gd.getCourseAverage(courseCode);
        double high = gd.getHighestScore(courseCode);
        double low = gd.getLowestScore(courseCode);
        int count = gd.getStudentCount(courseCode);

        return  "Course: " + courseCode + "\n\n" +
                "Total Students : " + count + "\n" +
                "Average Score  : " + avg + "\n" +
                "Highest Score  : " + high + "\n" +
                "Lowest Score   : " + low;
    }

    // UPDATE PROFILE
    public ActionResult updateInstructorProfile(Instructor i)
    {
        if (!SessionInfo.isLoggedIn()) { return ActionResult.NOT_ALLOWED; }

        UpdateInstructorProfile service = new UpdateInstructorProfile();
        return service.update(i);
    }
}
