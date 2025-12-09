package edu.univ.erp.ui.admin;

import edu.univ.erp.api.admin.CourseAPI;
import edu.univ.erp.domain.ActionResult;
import edu.univ.erp.domain.Course;
import edu.univ.erp.domain.Role;

import javax.swing.*;
import java.awt.*;

public class EditCourseDialog extends JDialog
{
    public EditCourseDialog(String code, String title,
                            int credits, String type,
                            int max, int students,
                            String instructor, JPanel parent,
                            CourseAPI api)
    {
        setTitle("Edit Course - " + code);
        setSize(400,320);
        setLocationRelativeTo(parent);
        setLayout(new GridLayout(7,2,10,10));

        JTextField titleF = new JTextField(title);
        JTextField creditsF = new JTextField(String.valueOf(credits));
        JTextField typeF = new JTextField(type);
        JTextField maxF = new JTextField(String.valueOf(max));
        JTextField studF = new JTextField(String.valueOf(students));
        JTextField instructorF = new JTextField(instructor);
        JButton save = new JButton("Save");

        add(new JLabel("Title:"));   add(titleF);
        add(new JLabel("Credits:")); add(creditsF);
        add(new JLabel("Type:"));    add(typeF);
        add(new JLabel("Max Students:")); add(maxF);
        add(new JLabel("Current Students:")); add(studF);
        add(new JLabel("Instructor")); add(instructorF);
        add(new JLabel());
        add(save);

        save.addActionListener(e ->
        {
            try
            {
                Course updated = new Course(code,
                                            titleF.getText().trim(),
                                            Integer.parseInt(creditsF.getText().trim()),
                                            typeF.getText().trim(),
                                            Integer.parseInt(maxF.getText().trim()),
                                            Integer.parseInt(studF.getText().trim()),
                                            instructorF.getText().trim());

                ActionResult result = api.updateCourse(Role.ADMIN, updated);
                switch (result)
                {
                    case SUCCESS ->
                    {
                        JOptionPane.showMessageDialog(this, "Course Updated Successfully!");
                        dispose();
                    }
                    case MAINTENANCE_MODE -> JOptionPane.showMessageDialog(this, "System is in maintenance mode.\nOnly admin operations are allowed.");
                    case NOT_ALLOWED -> JOptionPane.showMessageDialog(this, "You are not allowed to update courses.");
                    case DB_ERROR -> JOptionPane.showMessageDialog(this, "Database error occurred while updating course.");
                    default -> JOptionPane.showMessageDialog(this, "Course update failed for unknown reason.");
                }
            }
            catch (NumberFormatException ex) { JOptionPane.showMessageDialog(this, "Credits / Max / Students must be valid numbers"); }
            catch (Exception ex)
            {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Unexpected error: " + ex.getMessage());
            }
        });
        setVisible(true);
    }
}
