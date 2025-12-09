package edu.univ.erp.ui.instructor;

import edu.univ.erp.api.instructor.InstructorAPI;
import edu.univ.erp.auth.session.SessionInfo;
import edu.univ.erp.domain.Course;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class InstructorCoursesPanel extends JPanel
{
    public InstructorCoursesPanel()
    {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        JLabel title = new JLabel("My Courses", JLabel.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 24));
        title.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));
        add(title, BorderLayout.NORTH);

        String[] columns = {"Course Code", "Course Name", "Credits", "Type", "Students"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);
        JTable table = new JTable(model);
        table.setRowHeight(25);

        InstructorAPI api = new InstructorAPI();
        List<Course> courses = api.getMySections();

        if (courses == null || courses.isEmpty()) { System.out.println("No courses found for this instructor: " + SessionInfo.getUserID()); }
        else
        {
            for (Course c : courses)
            {
                model.addRow(new Object[]{
                        c.getCode(),
                        c.getTitle(),
                        c.getCredits(),
                        c.getType(),
                        c.getNumStudents()
                });
            }
        }
        add(new JScrollPane(table), BorderLayout.CENTER);
    }
}
