package edu.univ.erp.ui.instructor;

import javax.swing.*;
import java.awt.*;

public class AttendancePanel extends JPanel
{
    public AttendancePanel()
    {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        JLabel title = new JLabel("Attendance Management", JLabel.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 24));
        title.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));

        add(title, BorderLayout.NORTH);

        JTextArea info = new JTextArea("""
        You can:
        • Mark daily attendance
        • View attendance history
        • Export attendance report
        
        (Connected to DB in next phase)
        """);

        info.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        info.setEditable(false);
        info.setBackground(Color.WHITE);

        add(info, BorderLayout.CENTER);
    }
}
