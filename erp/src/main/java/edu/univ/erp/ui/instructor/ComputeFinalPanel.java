package edu.univ.erp.ui.instructor;

import edu.univ.erp.api.instructor.InstructorAPI;
import edu.univ.erp.domain.ActionResult;

import javax.swing.*;
import java.awt.*;

public class ComputeFinalPanel extends JPanel
{
    private JTextField txtEnroll, txtCourse;

    public ComputeFinalPanel()
    {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10,10,10,10);

        JLabel l1 = new JLabel("Enrollment ID:");
        JLabel l2 = new JLabel("Course Code:");

        txtEnroll = new JTextField(15);
        txtCourse = new JTextField(15);

        JButton btn = new JButton("Compute Final Grade");

        gbc.gridx=0; gbc.gridy=0; add(l1, gbc);
        gbc.gridx=1; add(txtEnroll, gbc);

        gbc.gridx=0; gbc.gridy=1; add(l2, gbc);
        gbc.gridx=1; add(txtCourse, gbc);

        gbc.gridx=0; gbc.gridy=2; gbc.gridwidth=2;
        add(btn, gbc);

        btn.addActionListener(e -> compute());
    }

    private void compute()
    {
        try
        {
            int enrollmentId = Integer.parseInt(txtEnroll.getText().trim());
            String courseCode = txtCourse.getText().trim().toUpperCase();
            InstructorAPI api = new InstructorAPI();
            StringBuilder result = new StringBuilder();
            ActionResult status = api.computeFinal(enrollmentId, courseCode, result);

            switch (status)
            {
                case SUCCESS -> JOptionPane.showMessageDialog(this, "Final Grade Computed\n\n" + result);
                case MAINTENANCE_MODE -> JOptionPane.showMessageDialog(this, "System is in Maintenance Mode.\nYou cannot update grades right now.");
                case NOT_ALLOWED -> JOptionPane.showMessageDialog(this, "You are not allowed to compute grades for this course.");
                case NOT_FOUND -> JOptionPane.showMessageDialog(this, "Student is not enrolled OR no marks found.");
                case DB_ERROR -> JOptionPane.showMessageDialog(this, "Database error while computing grade.");
                default -> JOptionPane.showMessageDialog(this, "Unknown error occurred.");
            }
        }
        catch (NumberFormatException e)
        {
            JOptionPane.showMessageDialog(this, "Invalid Enrollment ID. Must be a number.");
        }
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(this, "Unexpected error occurred.");
            e.printStackTrace();
        }
    }
}
