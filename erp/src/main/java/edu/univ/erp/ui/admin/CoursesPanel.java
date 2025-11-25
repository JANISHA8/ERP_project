package edu.univ.erp.ui.admin;

import javax.swing.*;
import java.awt.*;

public class CoursesPanel extends JPanel
{
    public CoursesPanel()
    {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        JLabel title = new JLabel("Course Management", JLabel.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 24));
        title.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));
        add(title, BorderLayout.NORTH);

        JTextArea area = new JTextArea();
        area.setText("""
        ✔ Add new courses
        ✔ Assign instructors
        ✔ Update curriculum
        ✔ Manage enrollments
        
        (Next Phase: Connected to Database)
        """);

        area.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        area.setEditable(false);
        area.setBackground(Color.WHITE);

        add(area, BorderLayout.CENTER);
    }
}
