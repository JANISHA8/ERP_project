package edu.univ.erp.ui.admin;

import edu.univ.erp.api.admin.AdminAPI;
import edu.univ.erp.domain.ActionResult;
import edu.univ.erp.domain.Admin;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;

public class AddAdminDialog extends JDialog
{
    public AddAdminDialog()
    {
        setTitle("Add Admin");
        setSize(400, 350);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(6,2,10,10));

        JTextField id = new JTextField();
        JTextField name = new JTextField();
        JTextField email = new JTextField();
        JPasswordField pass = new JPasswordField();
        JTextField dept = new JTextField();
        JButton btn = new JButton("Create Admin");

        add(new JLabel("User ID")); add(id);
        add(new JLabel("Username")); add(name);
        add(new JLabel("Email")); add(email);
        add(new JLabel("Password")); add(pass);
        add(new JLabel("Department")); add(dept);
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
                    JOptionPane.showMessageDialog(this, "User already exists with this email!");
                    return;
                }
                Admin admin = new Admin(userID, name.getText(), userEmail, new String(pass.getPassword()), "ACTIVE", LocalDateTime.now(), dept.getText());
                ActionResult result = api.addAdmin(admin);
                switch (result)
                {
                    case SUCCESS ->
                    {
                        JOptionPane.showMessageDialog(this, "Admin created successfully");
                        dispose();
                    }
                    case NOT_ALLOWED ->
                        JOptionPane.showMessageDialog(this, "You do not have permission to add admin.");
                    case DB_ERROR ->
                        JOptionPane.showMessageDialog(this, "Database error occurred. Try again.");
                    default ->
                        JOptionPane.showMessageDialog(this, "Operation failed: " + result);
                }
            }
            catch (Exception ex)
            {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Invalid input. Check all fields!");
            }
        });
        setModal(true);
        setVisible(true);
    }
}
