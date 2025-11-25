package edu.univ.erp.ui.instructor;

import javax.swing.*;
import java.awt.*;

public class ResultsPanel extends JPanel
{
    public ResultsPanel()
    {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        JLabel title = new JLabel("Results Management", JLabel.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 24));
        title.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));

        add(title, BorderLayout.NORTH);

        JTextArea area = new JTextArea("""
        • Add student marks
        • Update grades
        • Generate result sheets
        
        (To be connected with DB)
        """);

        area.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        area.setEditable(false);
        area.setBackground(Color.WHITE);

        add(area, BorderLayout.CENTER);
    }
}
