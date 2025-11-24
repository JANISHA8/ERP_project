package edu.univ.erp.ui.student;

import javax.swing.*;
import java.awt.*;

public class StudentDashboard extends JFrame
{
    public StudentDashboard()
    {
        setTitle("Student Dashboard");
        setSize(900, 600);
        setLocationRelativeTo(null);
        add(new JLabel("Welcome Student"), BorderLayout.CENTER);
    }
}
