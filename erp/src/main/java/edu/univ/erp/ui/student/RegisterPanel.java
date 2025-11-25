package edu.univ.erp.ui.student;

import edu.univ.erp.api.student.StudentAPI;

import javax.swing.*;
import java.awt.*;

public class RegisterPanel extends JPanel
{
    public RegisterPanel()
    {
        setLayout(new FlowLayout());

        JTextField txtCourse = new JTextField(10);
        JButton btnRegister = new JButton("Register");

        add(new JLabel("Course ID: "));
        add(txtCourse);
        add(btnRegister);

        btnRegister.addActionListener(e ->
        {
            int courseId = Integer.parseInt(txtCourse.getText());

            StudentAPI api = new StudentAPI();
            boolean success = api.registerCourse(courseId);

            if (success)
                JOptionPane.showMessageDialog(this, "Course Registered!");
            else
                JOptionPane.showMessageDialog(this, "Not allowed OR Error!");
        });
    }
}
