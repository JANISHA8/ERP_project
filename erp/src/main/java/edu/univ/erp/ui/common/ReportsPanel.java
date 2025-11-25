package edu.univ.erp.ui.common;

import javax.swing.*;
import java.awt.*;

public class ReportsPanel extends JPanel
{
    public ReportsPanel()
    {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        JLabel title = new JLabel("System Reports", JLabel.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 24));
        add(title, BorderLayout.NORTH);

        JTextArea text = new JTextArea("""
        - User Report
        - Attendance Report
        - Performance Report
        - Financial Report
        """);

        text.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        text.setEditable(false);
        text.setBackground(Color.WHITE);

        add(text, BorderLayout.CENTER);
    }
}
