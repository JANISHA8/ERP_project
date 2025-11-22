package edu.univ.erp.data;

import edu.univ.erp.domain.Enrollment;
import java.util.ArrayList;
import java.util.List;

public class EnrollmentsData
{
    // In-memory storage for enrollments
    private List<Enrollment> enrollments;

    // Constructor
    public EnrollmentsData()
    { enrollments = new ArrayList<>(); }

    // Add a new enrollment
    public void addEnrollment(Enrollment enrollment)
    { enrollments.add(enrollment); }

    // Get enrollment by student ID and section ID
    public Enrollment getEnrollment(String studentId, String sectionId)
    {
        for (Enrollment e : enrollments)
        {
            if (e.getStudent_id().equals(studentId) && e.getSection_id().equals(sectionId))
            { return e; }
        }
        return null; // not found
    }

    // Get all enrollments for a student
    public List<Enrollment> getEnrollmentsByStudent(String studentId)
    {
        List<Enrollment> result = new ArrayList<>();
        for (Enrollment e : enrollments)
        {
            if (e.getStudent_id().equals(studentId))
            { result.add(e); }
        }
        return result;
    }

    // Get all enrollments for a section
    public List<Enrollment> getEnrollmentsBySection(String sectionId)
    {
        List<Enrollment> result = new ArrayList<>();
        for (Enrollment e : enrollments)
        {
            if (e.getSection_id().equals(sectionId))
            { result.add(e); }
        }
        return result;
    }

    // Get all enrollments
    public List<Enrollment> getAllEnrollments()
    { return new ArrayList<>(enrollments); } // return a copy

    // Update an enrollment
    public boolean updateEnrollment(Enrollment updatedEnrollment)
    {
        for (int i = 0; i < enrollments.size(); i++)
        {
            Enrollment e = enrollments.get(i);
            if (e.getStudent_id().equals(updatedEnrollment.getStudent_id()) &&
                e.getSection_id().equals(updatedEnrollment.getSection_id()))
            {
                enrollments.set(i, updatedEnrollment);
                return true; // updated successfully
            }
        }
        return false; // not found
    }

    // Delete an enrollment
    public boolean deleteEnrollment(String studentId, String sectionId)
    {
        return enrollments.removeIf(e -> e.getStudent_id().equals(studentId) &&
                                         e.getSection_id().equals(sectionId));
    }
}
