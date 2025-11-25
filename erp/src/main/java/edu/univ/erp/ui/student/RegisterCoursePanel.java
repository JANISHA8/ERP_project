package edu.univ.erp.ui.student;

import edu.univ.erp.api.student.StudentAPI;

import javax.swing.*;
import java.awt.*;

public class RegisterCoursePanel extends JPanel
{
    private JTextField txtCourseID;
    private JButton btnRegister;
    private JTextArea output;

    public RegisterCoursePanel()
    {
        setLayout(new BorderLayout());
        setBackground(new Color(45, 45, 48));

        JLabel title = new JLabel("REGISTER FOR COURSE");
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Segoe UI", Font.BOLD, 22));
        title.setHorizontalAlignment(JLabel.CENTER);

        JPanel inputPanel = new JPanel(new GridLayout(2,2,10,10));
        inputPanel.setBackground(new Color(45,45,48));

        JLabel lblCourse = new JLabel("Course ID:");
        lblCourse.setForeground(Color.WHITE);

        txtCourseID = new JTextField();

        btnRegister = new JButton("REGISTER");
        btnRegister.setBackground(new Color(33,150,90));
        btnRegister.setForeground(Color.WHITE);

        inputPanel.add(lblCourse);
        inputPanel.add(txtCourseID);
        inputPanel.add(new JLabel());
        inputPanel.add(btnRegister);

        output = new JTextArea();
        output.setEditable(false);

        add(title, BorderLayout.NORTH);
        add(inputPanel, BorderLayout.CENTER);
        add(output, BorderLayout.SOUTH);

        btnRegister.addActionListener(e -> registerCourse());
    }

    private void registerCourse()
    {
        try
        {
            String courseCode = txtCourseID.getText().trim().toUpperCase();

            if (courseCode.isEmpty())
            {
                JOptionPane.showMessageDialog(this, "Please enter a course code");
                return;
            }

            StudentAPI api = new StudentAPI();
            boolean success = api.registerCourse(courseCode);

            if (success)
            {
                JOptionPane.showMessageDialog(this, "Course Registered Successfully!");
            }
            else
            {
                JOptionPane.showMessageDialog(this, "Registration failed. Already enrolled or invalid course.");
            }

        } catch (Exception e)
        {
            JOptionPane.showMessageDialog(this, "Error occurred while registering");
            e.printStackTrace();
        }
    }
}
