package edu.univ.erp.ui.student;

import javax.swing.*;
import java.awt.*;

public class StudentProfilePanel extends JPanel
{
    public StudentProfilePanel(String username)
    {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        JLabel title = new JLabel("My Profile", JLabel.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 24));
        title.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));

        JTextArea info = new JTextArea(
                "Username: " + username +
                "\nRole: STUDENT" +
                "\nDepartment: CSE" +
                "\nStatus: Active"
        );

        info.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        info.setEditable(false);
        info.setBackground(Color.WHITE);

        add(title, BorderLayout.NORTH);
        add(info, BorderLayout.CENTER);
    }
}
