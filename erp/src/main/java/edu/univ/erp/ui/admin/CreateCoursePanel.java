package edu.univ.erp.ui.admin;

import edu.univ.erp.api.admin.AdminAPI;
import edu.univ.erp.data.InstructorsData;
import edu.univ.erp.domain.ActionResult;
import edu.univ.erp.domain.Course;
import edu.univ.erp.domain.Instructor;

import javax.swing.*;
import java.awt.*;

public class CreateCoursePanel extends JPanel
{
    public CreateCoursePanel()
    {
        setLayout(new GridLayout(7,2,10,10));

        JTextField code = new JTextField();
        JTextField title = new JTextField();
        JTextField credits = new JTextField();
        JTextField maxStudents = new JTextField();
        JTextField numStudents = new JTextField();
        JComboBox<String> type = new JComboBox<>(new String[]{"credit","audit"});
        JTextField instructorId = new JTextField();
        JButton btn = new JButton("Create Course");

        add(new JLabel("Code")); add(code);
        add(new JLabel("Title")); add(title);
        add(new JLabel("Credits")); add(credits);
        add(new JLabel("Max No. Of Students That Can Enroll")); add(maxStudents);
        add(new JLabel("No. of Students Enrolled")); add(numStudents);
        add(new JLabel("Type")); add(type);
        add(new JLabel("Instructor User ID")); add(instructorId);
        add(new JLabel()); add(btn);

        btn.addActionListener(e ->
        {
            try
            {
                if (code.getText().isBlank() ||
                    title.getText().isBlank() ||
                    instructorId.getText().isBlank())
                {
                    JOptionPane.showMessageDialog(this, "All fields are required!");
                    return;
                }

                int insId = Integer.parseInt(instructorId.getText());
                InstructorsData instructorsData = new InstructorsData();
                Instructor instructor = instructorsData.getInstructorByUserId(insId);
                if (instructor == null)
                {
                    JOptionPane.showMessageDialog(this, "No Instructor found with ID: " + insId);
                    return;
                }

                Course c = new Course(code.getText(),
                                    title.getText(),
                                    Integer.parseInt(credits.getText()),
                                    (String) type.getSelectedItem(),
                                    Integer.parseInt(maxStudents.getText()),
                                    Integer.parseInt(numStudents.getText()),
                                    instructor.getUsername());

                AdminAPI api = new AdminAPI();
                ActionResult result = api.createCourse(c);
                switch (result)
                {
                    case SUCCESS -> JOptionPane.showMessageDialog(this, "Course Created Successfully");
                    case MAINTENANCE_MODE ->JOptionPane.showMessageDialog(this, "System in maintenance mode. Only admin operations are allowed.");
                    case NOT_ALLOWED -> JOptionPane.showMessageDialog(this, "You are not allowed to create courses.");
                    case DB_ERROR -> JOptionPane.showMessageDialog(this, "Database error while creating course.");
                    default -> JOptionPane.showMessageDialog(this, "Course creation failed for unknown reason.");
                }
            }
            catch (NumberFormatException ex) { JOptionPane.showMessageDialog(this, "Please enter valid numbers for ID / Credits / Students"); }
            catch (Exception ex)
            {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Unexpected error: " + ex.getMessage());
            }
        });
    }
}
