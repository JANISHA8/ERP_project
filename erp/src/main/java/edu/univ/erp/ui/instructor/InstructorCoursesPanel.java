package edu.univ.erp.ui.instructor;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

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

        String[] columns = {"Course ID", "Course Name", "Semester", "Students"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);

        JTable table = new JTable(model);
        table.setRowHeight(25);

        model.addRow(new Object[]{"CSE101", "Programming", "1", 40});
        model.addRow(new Object[]{"CSE205", "DSA", "3", 35});
        model.addRow(new Object[]{"CSE310", "OS", "5", 45});

        add(new JScrollPane(table), BorderLayout.CENTER);
    }
}
