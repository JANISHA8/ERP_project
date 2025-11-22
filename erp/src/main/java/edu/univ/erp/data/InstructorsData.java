package edu.univ.erp.data;

import edu.univ.erp.domain.Instructor;
import edu.univ.erp.domain.Course;
import java.util.ArrayList;
import java.util.List;

public class InstructorsData
{
    private List<Instructor> instructors;

    // Constructor
    public InstructorsData()
    { instructors = new ArrayList<>(); }

    // Add a new instructor
    public void addInstructor(Instructor instructor)
    { instructors.add(instructor); }

    // Get instructor by userID
    public Instructor getInstructorById(int userID)
    {
        for (Instructor instr : instructors)
        {
            if (instr.getUserID() == userID)
            { return instr; }
        }
        return null; // not found
    }

    // Get all instructors
    public List<Instructor> getAllInstructors()
    { return new ArrayList<>(instructors); } // return a copy

    // Update an instructor
    public boolean updateInstructor(Instructor updatedInstructor)
    {
        for (int i = 0; i < instructors.size(); i++)
        {
            if (instructors.get(i).getUserID() == updatedInstructor.getUserID())
            {
                instructors.set(i, updatedInstructor);
                return true; // updated successfully
            }
        }
        return false; // not found
    }

    // Delete an instructor
    public boolean deleteInstructor(int userID)
    { return instructors.removeIf(instr -> instr.getUserID() == userID); }

    // Assign a course to an instructor
    public boolean assignCourse(int userID, Course course)
    {
        Instructor instr = getInstructorById(userID);
        if (instr != null)
        {
            List<Course> courses = instr.getCourses();
            if (courses != null)
            { courses.add(course); }
            else
            {
                courses = new ArrayList<>();
                courses.add(course);
                instr.setCourses(courses);
            }
            return true;
        }
        return false;
    }

    // Remove a course from an instructor
    public boolean removeCourse(int userID, Course course)
    {
        Instructor instr = getInstructorById(userID);
        if (instr != null && instr.getCourses() != null)
        { return instr.getCourses().remove(course); }
        return false;
    }
}
