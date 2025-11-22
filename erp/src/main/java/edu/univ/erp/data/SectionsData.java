package edu.univ.erp.data;

import edu.univ.erp.domain.Section;
import java.util.ArrayList;
import java.util.List;

public class SectionsData {

    // In-memory storage for sections
    private List<Section> sections;

    // Constructor
    public SectionsData() {
        sections = new ArrayList<>();
    }

    // Add a new section
    public void addSection(Section section) {
        sections.add(section);
    }

    // Get a section by course ID and instructor ID
    public Section getSection(String courseId, String instructorId) {
        for (Section sec : sections) {
            if (sec.getCourse_id().equals(courseId) && sec.getInstructor_id().equals(instructorId)) {
                return sec;
            }
        }
        return null; // not found
    }

    // Get all sections
    public List<Section> getAllSections() {
        return new ArrayList<>(sections); // return a copy
    }

    // Update a section (replace existing one with same course & instructor)
    public boolean updateSection(Section updatedSection) {
        for (int i = 0; i < sections.size(); i++) {
            Section sec = sections.get(i);
            if (sec.getCourse_id().equals(updatedSection.getCourse_id()) &&
                sec.getInstructor_id().equals(updatedSection.getInstructor_id())) {
                sections.set(i, updatedSection);
                return true;
            }
        }
        return false; // not found
    }

    // Delete a section by course ID and instructor ID
    public boolean deleteSection(String courseId, String instructorId) {
        return sections.removeIf(sec -> sec.getCourse_id().equals(courseId) &&
                                        sec.getInstructor_id().equals(instructorId));
    }

    // Get all sections for a particular instructor
    public List<Section> getSectionsByInstructor(String instructorId) {
        List<Section> result = new ArrayList<>();
        for (Section sec : sections) {
            if (sec.getInstructor_id().equals(instructorId)) {
                result.add(sec);
            }
        }
        return result;
    }

    // Get all sections for a particular course
    public List<Section> getSectionsByCourse(String courseId) {
        List<Section> result = new ArrayList<>();
        for (Section sec : sections) {
            if (sec.getCourse_id().equals(courseId)) {
                result.add(sec);
            }
        }
        return result;
    }
}