package edu.univ.erp.ui.student;

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.util.List;
import javax.swing.table.DefaultTableModel;

import edu.univ.erp.api.student.StudentAPI;
import edu.univ.erp.domain.Course;

public class StudentCoursesPanel extends JPanel
{
    public StudentCoursesPanel()
    {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        JLabel title = new JLabel("My Courses", JLabel.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 24));
        title.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));

        add(title, BorderLayout.NORTH);

        String[] cols = {"Course Code", "Course Name", "Credits", "Instructor"};
        DefaultTableModel model = new DefaultTableModel(cols, 0)
        {
            @Override
            public boolean isCellEditable(int row, int column)
            {
                return false; // STUDENT CANNOT EDIT ANYTHING
            }
        };

        JTable table = new JTable(model);
        table.setRowHeight(25);

        table.addMouseListener(new java.awt.event.MouseAdapter()
        {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e)
            {
                if (e.getClickCount() == 2) // double click
                {
                    JOptionPane.showMessageDialog(
                        StudentCoursesPanel.this,
                        "You are not allowed to modify course information.",
                        "Access Denied",
                        JOptionPane.WARNING_MESSAGE
                    );
                }
            }
        });

        StudentAPI api = new StudentAPI();
        List<Course> courses = api.getMyCourses();

        for (Course c : courses)
        {
            model.addRow(new Object[]{
                    c.getCode(),
                    c.getTitle(),
                    c.getCredits(),
                    c.getInstructorName()
            });
        }
        add(new JScrollPane(table), BorderLayout.CENTER);
    }
}
