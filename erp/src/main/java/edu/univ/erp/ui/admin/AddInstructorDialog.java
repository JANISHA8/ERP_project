package edu.univ.erp.ui.admin;

import edu.univ.erp.api.admin.AdminAPI;
import edu.univ.erp.domain.ActionResult;
import edu.univ.erp.domain.Instructor;
import edu.univ.erp.domain.Role;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class AddInstructorDialog extends JDialog
{
    public AddInstructorDialog()
    {
        setTitle("Add Instructor");
        setSize(450, 600);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(12,2,10,10));

        JTextField id = new JTextField();
        JTextField name = new JTextField();
        JTextField email = new JTextField();
        JPasswordField pass = new JPasswordField();
        JTextField gender = new JTextField();
        JTextField contact = new JTextField();
        JTextField dob = new JTextField("yyyy-MM-dd");
        JTextField nationality = new JTextField();
        JTextField department = new JTextField();
        JButton btn = new JButton("Create Instructor");

        add(new JLabel("User ID")); add(id);
        add(new JLabel("Username")); add(name);
        add(new JLabel("Email")); add(email);
        add(new JLabel("Password")); add(pass);
        add(new JLabel("Gender")); add(gender);
        add(new JLabel("Contact No")); add(contact);
        add(new JLabel("DOB (yyyy-MM-dd)")); add(dob);
        add(new JLabel("Nationality")); add(nationality);
        add(new JLabel("Department")); add(department);
        add(new JLabel()); add(btn);

        btn.addActionListener(e ->
        {
            try
            {
                int userID = Integer.parseInt(id.getText());
                String userEmail = email.getText();
                AdminAPI api = new AdminAPI();

                if (api.checkUserExists(userEmail))
                {
                    JOptionPane.showMessageDialog(this, "User with this email already exists!");
                    return;
                }
                Instructor instructor = new Instructor(userID,
                                                        name.getText(),
                                                        userEmail,
                                                        new String(pass.getPassword()),
                                                        "ACTIVE",
                                                        LocalDateTime.now(),
                                                        gender.getText(),
                                                        Long.parseLong(contact.getText()),
                                                        LocalDate.parse(dob.getText()),
                                                        nationality.getText(),
                                                        department.getText(),
                                                        new ArrayList<>(),
                                                        Role.INSTRUCTOR);

                ActionResult result = api.addInstructor(instructor);
                switch (result)
                {
                    case SUCCESS ->
                    {
                        JOptionPane.showMessageDialog(this, "Instructor added successfully");
                        dispose();
                    }
                    case NOT_ALLOWED ->
                        JOptionPane.showMessageDialog(this, "You do not have permission to add instructor.");
                    case DB_ERROR ->
                        JOptionPane.showMessageDialog(this, "Database error occurred while inserting instructor.");
                    default ->
                        JOptionPane.showMessageDialog(this, "Operation failed: " + result);
                }
            }
            catch (NumberFormatException ex) { JOptionPane.showMessageDialog(this, "Incorrect number format: ID and Contact must be numeric"); }
            catch (Exception ex)
            {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Invalid input data or format");
            }
        });
        setModal(true);
        setVisible(true);
    }
}
