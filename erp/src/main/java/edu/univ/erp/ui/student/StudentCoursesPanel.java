package edu.univ.erp.ui.student;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

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
        DefaultTableModel model = new DefaultTableModel(cols, 0);

        JTable table = new JTable(model);
        table.setRowHeight(25);

        model.addRow(new Object[]{"CSE101", "Programming", 4, "Dr. Sharma"});
        model.addRow(new Object[]{"PHY102", "Physics", 3, "Dr. Lee"});
        model.addRow(new Object[]{"MAT201", "Calculus", 4, "Dr. Patel"});

        add(new JScrollPane(table), BorderLayout.CENTER);
    }
}
