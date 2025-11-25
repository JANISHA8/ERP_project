package edu.univ.erp.ui.student;

import edu.univ.erp.api.student.StudentAPI;

import javax.swing.*;
import java.awt.*;

public class DropCoursePanel extends JPanel
{
    public DropCoursePanel()
    {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        JLabel label = new JLabel("Enter Course ID:");
        JTextField tfCourse = new JTextField(10);

        JButton btnDrop = new JButton("Drop");

        gbc.gridx = 0; gbc.gridy = 0;
        add(label, gbc);

        gbc.gridx = 1;
        add(tfCourse, gbc);

        gbc.gridy = 1; gbc.gridx = 0; gbc.gridwidth = 2;
        add(btnDrop, gbc);

        btnDrop.addActionListener(e -> {
            try
            {
                int id = Integer.parseInt(tfCourse.getText());
                boolean success = new StudentAPI().dropCourse(id);

                JOptionPane.showMessageDialog(this,
                        success ? "Course Dropped!" : "Drop Failed");

            } catch(Exception ex)
            {
                JOptionPane.showMessageDialog(this,"Invalid Course ID");
            }
        });
    }
}
