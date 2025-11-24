package edu.univ.erp.ui.instructor;

import javax.swing.*;
import java.awt.*;

public class InstructorDashboard extends JFrame
{
    public InstructorDashboard()
    {
        setTitle("Instructor Dashboard");
        setSize(900, 600);
        setLocationRelativeTo(null);
        add(new JLabel("Welcome Instructor"), BorderLayout.CENTER);
    }
}
