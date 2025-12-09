package edu.univ.erp.ui.admin;

import edu.univ.erp.api.admin.AdminAPI;
import edu.univ.erp.auth.session.SessionInfo;
import edu.univ.erp.domain.ActionResult;

import javax.swing.*;
import java.awt.*;

public class AssignInstructorPanel extends JPanel
{
    private JTextField txtSectionId;
    private JTextField txtInstructorId;

    public AssignInstructorPanel()
    {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        JLabel title = new JLabel("ASSIGN INSTRUCTOR TO SECTION", JLabel.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 22));
        title.setBorder(BorderFactory.createEmptyBorder(20,10,20,10));
        add(title, BorderLayout.NORTH);

        JPanel form = new JPanel(new GridLayout(3,2,10,10));
        form.setBorder(BorderFactory.createEmptyBorder(30,80,30,80));
        form.setBackground(Color.WHITE);

        JLabel lblSection    = new JLabel("Section ID:");
        JLabel lblInstructor = new JLabel("Instructor ID:");

        txtSectionId    = new JTextField();
        txtInstructorId = new JTextField();

        JButton btnAssign = new JButton("Assign");

        form.add(lblSection);
        form.add(txtSectionId);

        form.add(lblInstructor);
        form.add(txtInstructorId);

        form.add(new JLabel());
        form.add(btnAssign);

        add(form, BorderLayout.CENTER);
        btnAssign.addActionListener(e -> assignInstructor());
    }

    private void assignInstructor()
    {
        if (!SessionInfo.isLoggedIn())
        {
            JOptionPane.showMessageDialog(this, "You must login first!");
            return;
        }
        if (txtSectionId.getText().isBlank() || txtInstructorId.getText().isBlank())
        {
            JOptionPane.showMessageDialog(this, "Both fields are required!");
            return;
        }

        try
        {
            int sectionID    = Integer.parseInt(txtSectionId.getText().trim());
            int instructorID = Integer.parseInt(txtInstructorId.getText().trim());
            AdminAPI api = new AdminAPI();
            ActionResult result = api.assignInstructor(sectionID, instructorID);

            switch (result)
            {
                case SUCCESS -> JOptionPane.showMessageDialog(this, "Instructor assigned successfully!");
                case NOT_ALLOWED -> JOptionPane.showMessageDialog(this, "You are not allowed to assign instructors.");
                case NOT_FOUND -> JOptionPane.showMessageDialog(this, "Section or Instructor not found.");
                case DB_ERROR -> JOptionPane.showMessageDialog(this, "Database error while assigning instructor.");
                default -> JOptionPane.showMessageDialog(this, "Assignment failed for unknown reason.");
            }
        }
        catch (NumberFormatException ex) { JOptionPane.showMessageDialog(this, "Section ID and Instructor ID must be integers."); }
        catch (Exception ex)
        {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Unexpected error: " + ex.getMessage());
        }
    }
}
