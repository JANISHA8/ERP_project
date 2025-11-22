package edu.univ.erp.data;

import edu.univ.erp.domain.Grade;
import java.util.ArrayList;
import java.util.List;

public class GradesData
{
    // In-memory storage for grades
    private List<Grade> grades;

    // Constructor
    public GradesData()
    { grades = new ArrayList<>(); }

    // Add a new grade
    public void addGrade(Grade grade)
    { grades.add(grade); }

    // Get grade by enrollment ID and component
    public Grade getGrade(String enrollmentId, String component)
    {
        for (Grade g : grades)
        {
            if (g.getEnrollment_id().equals(enrollmentId) &&
                g.getComponent().equals(component))
            { return g; }
        }
        return null; // not found
    }

    // Get all grades for a specific enrollment
    public List<Grade> getGradesByEnrollment(String enrollmentId)
    {
        List<Grade> result = new ArrayList<>();
        for (Grade g : grades)
        {
            if (g.getEnrollment_id().equals(enrollmentId))
            { result.add(g); }
        }
        return result;
    }

    // Get all grades
    public List<Grade> getAllGrades()
    { return new ArrayList<>(grades); } // return a copy

    // Update a grade
    public boolean updateGrade(Grade updatedGrade)
    {
        for (int i = 0; i < grades.size(); i++)
        {
            Grade g = grades.get(i);
            if (g.getEnrollment_id().equals(updatedGrade.getEnrollment_id()) &&
                g.getComponent().equals(updatedGrade.getComponent()))
            {
                grades.set(i, updatedGrade);
                return true; // updated successfully
            }
        }
        return false; // not found
    }

    // Delete a grade
    public boolean deleteGrade(String enrollmentId, String component)
    {
        return grades.removeIf(g -> g.getEnrollment_id().equals(enrollmentId) &&
                                    g.getComponent().equals(component));
    }
}
