package edu.univ.erp.ui.instructor;

import edu.univ.erp.api.instructor.InstructorAPI;

import javax.swing.*;
import java.awt.*;

public class ClassStatsPanel extends JPanel
{
    private JTextField txtCourse;
    private JTextArea output;

    public ClassStatsPanel()
    {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        JLabel title = new JLabel("Class Statistics", JLabel.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 22));
        title.setBorder(BorderFactory.createEmptyBorder(20,10,20,10));

        JPanel inputPanel = new JPanel(new FlowLayout());
        inputPanel.setBackground(Color.WHITE);

        JLabel lbl = new JLabel("Course Code: ");
        txtCourse = new JTextField(12);

        JButton btn = new JButton("Get Statistics");

        inputPanel.add(lbl);
        inputPanel.add(txtCourse);
        inputPanel.add(btn);

        output = new JTextArea();
        output.setEditable(false);
        output.setFont(new Font("Consolas", Font.PLAIN, 15));
        output.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));

        add(title, BorderLayout.NORTH);
        add(inputPanel, BorderLayout.CENTER);
        add(new JScrollPane(output), BorderLayout.SOUTH);

        btn.addActionListener(e -> getStats());
    }

    private void getStats()
    {
        String course = txtCourse.getText().trim().toUpperCase();

        if (course.isEmpty())
        {
            JOptionPane.showMessageDialog(this, "Enter course code");
            return;
        }

        InstructorAPI api = new InstructorAPI();
        String result = api.getCourseStats(course);

        if (result == null) { output.setText("Error occurred"); }
        else { output.setText(result); }
    }
}
