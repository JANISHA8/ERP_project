package edu.univ.erp.ui.student;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class StudentAttendancePanel extends JPanel
{
    public StudentAttendancePanel()
    {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        JLabel title = new JLabel("My Attendance", JLabel.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 24));
        title.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));

        add(title, BorderLayout.NORTH);

        String[] cols = {"Course", "Total Classes", "Attended", "Percentage"};
        DefaultTableModel model = new DefaultTableModel(cols, 0);

        JTable table = new JTable(model);
        table.setRowHeight(25);

        model.addRow(new Object[]{"Programming", 40, 38, "95%"});
        model.addRow(new Object[]{"Physics", 30, 26, "87%"});
        model.addRow(new Object[]{"Calculus", 35, 32, "91%"});

        add(new JScrollPane(table), BorderLayout.CENTER);
    }
}
