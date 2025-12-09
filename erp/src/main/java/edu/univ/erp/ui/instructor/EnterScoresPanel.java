package edu.univ.erp.ui.instructor;

import edu.univ.erp.api.instructor.InstructorAPI;
import edu.univ.erp.domain.ActionResult;

import javax.swing.*;
import java.awt.*;

public class EnterScoresPanel extends JPanel
{
    private JTextField txtEnrollId, txtCourseCode, txtComponent, txtMarks;

    public EnterScoresPanel()
    {
        setLayout(new GridBagLayout());
        setBackground(Color.WHITE);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10,10,10,10);

        JLabel l0 = new JLabel("Course Code:");
        JLabel l1 = new JLabel("Enrollment ID:");
        JLabel l2 = new JLabel("Component:");
        JLabel l3 = new JLabel("Marks:");

        txtCourseCode = new JTextField(15);
        txtEnrollId   = new JTextField(15);
        txtComponent  = new JTextField(15);
        txtMarks      = new JTextField(15);

        JButton btnSave = new JButton("Save Score");

        gbc.gridx=0; gbc.gridy=0; add(l0,gbc);
        gbc.gridx=1; add(txtCourseCode,gbc);

        gbc.gridx=0; gbc.gridy=1; add(l1,gbc);
        gbc.gridx=1; add(txtEnrollId,gbc);

        gbc.gridx=0; gbc.gridy=2; add(l2,gbc);
        gbc.gridx=1; add(txtComponent,gbc);

        gbc.gridx=0; gbc.gridy=3; add(l3,gbc);
        gbc.gridx=1; add(txtMarks,gbc);

        gbc.gridx=0; gbc.gridy=4; gbc.gridwidth=2;
        add(btnSave,gbc);

        btnSave.addActionListener(e -> saveScore());
    }

    private void saveScore()
    {
        try
        {
            int enrollmentId = Integer.parseInt(txtEnrollId.getText().trim());
            String courseCode = txtCourseCode.getText().trim().toUpperCase();
            String component = txtComponent.getText().trim().toUpperCase();
            double marks = Double.parseDouble(txtMarks.getText().trim());

            InstructorAPI api = new InstructorAPI();

            ActionResult result = api.enterScore(enrollmentId, courseCode, component, marks);

            switch (result)
            {
                case SUCCESS -> JOptionPane.showMessageDialog(this, "Score saved successfully!");
                case MAINTENANCE_MODE -> JOptionPane.showMessageDialog(this, "System is in MAINTENANCE MODE.\nYou cannot modify scores right now.");
                case NOT_ALLOWED -> JOptionPane.showMessageDialog(this, "You are not allowed to enter scores for this course.");
                case NOT_FOUND -> JOptionPane.showMessageDialog(this, "Enrollment not found OR invalid course.");
                case DB_ERROR -> JOptionPane.showMessageDialog(this, "Database error while saving score.");
                default -> JOptionPane.showMessageDialog(this, "Unknown error occurred.");
            }
        }
        catch (NumberFormatException e) { JOptionPane.showMessageDialog(this, "Invalid Enrollment ID or Marks (must be numbers)"); }
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(this, "Unexpected error occurred!");
            e.printStackTrace();
        }
    }
}
