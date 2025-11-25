package edu.univ.erp.ui.student;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class StudentResultsPanel extends JPanel
{
    public StudentResultsPanel()
    {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        JLabel title = new JLabel("My Results", JLabel.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 24));
        title.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));

        add(title, BorderLayout.NORTH);

        String[] cols = {"Course", "Marks", "Grade"};
        DefaultTableModel model = new DefaultTableModel(cols, 0);

        JTable table = new JTable(model);
        table.setRowHeight(25);

        model.addRow(new Object[]{"Programming", 88, "A"});
        model.addRow(new Object[]{"Physics", 75, "B+"});
        model.addRow(new Object[]{"Mathematics", 91, "A+"});

        add(new JScrollPane(table), BorderLayout.CENTER);
    }
}
