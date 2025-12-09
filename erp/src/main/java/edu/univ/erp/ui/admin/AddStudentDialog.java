package edu.univ.erp.ui.admin;

import edu.univ.erp.api.admin.AdminAPI;
import edu.univ.erp.domain.ActionResult;
import edu.univ.erp.domain.Student;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class AddStudentDialog extends JDialog
{
    public AddStudentDialog()
    {
        setTitle("Add Student");
        setSize(500, 750);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(16,2,10,10));

        JTextField id = new JTextField();
        JTextField name = new JTextField();
        JTextField email = new JTextField();
        JPasswordField pass = new JPasswordField();
        JTextField roll = new JTextField();
        JTextField gender = new JTextField();
        JTextField contact = new JTextField();
        JTextField dob = new JTextField("yyyy-MM-dd");
        JTextField nationality = new JTextField();
        JTextField program = new JTextField();
        JTextField branch = new JTextField();
        JTextField currentYear = new JTextField();
        JTextField currentSem = new JTextField();
        JTextField graduationYear = new JTextField();
        JButton btn = new JButton("Create Student");

        add(new JLabel("User ID")); add(id);
        add(new JLabel("Username")); add(name);
        add(new JLabel("Email")); add(email);
        add(new JLabel("Password")); add(pass);
        add(new JLabel("Roll No")); add(roll);
        add(new JLabel("Gender")); add(gender);
        add(new JLabel("Contact No")); add(contact);
        add(new JLabel("DOB (yyyy-MM-dd)")); add(dob);
        add(new JLabel("Nationality")); add(nationality);
        add(new JLabel("Program")); add(program);
        add(new JLabel("Branch")); add(branch);
        add(new JLabel("Current Year")); add(currentYear);
        add(new JLabel("Current Semester")); add(currentSem);
        add(new JLabel("Graduation Year")); add(graduationYear);
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
                    JOptionPane.showMessageDialog(this, "A user with this email already exists!");
                    return;
                }
                Student student = new Student(userID,
                                            name.getText(),
                                            email.getText(),
                                            new String(pass.getPassword()),
                                            "ACTIVE",
                                            LocalDateTime.now(),
                                            Integer.parseInt(roll.getText()),
                                            gender.getText(),
                                            Long.parseLong(contact.getText()),
                                            LocalDate.parse(dob.getText()),
                                            nationality.getText(),
                                            email.getText(),
                                            program.getText(),
                                            branch.getText(),
                                            Integer.parseInt(currentYear.getText()),
                                            Integer.parseInt(currentSem.getText()),
                                            Integer.parseInt(graduationYear.getText()),
                                            new ArrayList<>());
                ActionResult result = api.addStudent(student);
                switch (result)
                {
                    case SUCCESS ->
                    {
                        JOptionPane.showMessageDialog(this, "Student added successfully");
                        dispose();
                    }
                    case NOT_ALLOWED ->
                        JOptionPane.showMessageDialog(this, "You are not allowed to add students.");
                    case DB_ERROR ->
                        JOptionPane.showMessageDialog(this, "Database error while inserting student.");
                    default ->
                        JOptionPane.showMessageDialog(this, "Operation failed: " + result);
                }
            }
            catch (NumberFormatException ex)
            { JOptionPane.showMessageDialog(this, "Check numeric fields:\nID, Roll No, Contact, Year, Semester, Graduation Year"); }
            catch (Exception ex)
            {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Invalid input format. Please re-check values.");
            }
        });
        setModal(true);
        setVisible(true);
    }
}
