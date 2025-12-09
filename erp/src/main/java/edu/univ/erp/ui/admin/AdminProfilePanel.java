package edu.univ.erp.ui.admin;

import edu.univ.erp.api.admin.AdminAPI;
import edu.univ.erp.auth.session.SessionInfo;
import edu.univ.erp.data.AdminsData;
import edu.univ.erp.domain.ActionResult;
import edu.univ.erp.domain.Admin;

import javax.swing.*;
import java.awt.*;

public class AdminProfilePanel extends JPanel
{
    private Admin currentAdmin;

    private JTextField tfUsername, tfEmail, tfRole, tfStatus, tfLastLogin, tfDepartment;

    public AdminProfilePanel()
    {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        JLabel title = new JLabel("MY PROFILE", JLabel.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 24));
        title.setBorder(BorderFactory.createEmptyBorder(20,10,20,10));
        add(title, BorderLayout.NORTH);

        if(!SessionInfo.isLoggedIn())
        {
            add(new JLabel("No active session"), BorderLayout.CENTER);
            return;
        }

        AdminsData ad = new AdminsData();
        currentAdmin = ad.getAdminByUserId(SessionInfo.getUserID());

        if(currentAdmin == null)
        {
            add(new JLabel("Admin not found"), BorderLayout.CENTER);
            return;
        }

        JPanel form = new JPanel(new GridLayout(7,2,10,10));
        form.setBorder(BorderFactory.createEmptyBorder(20,40,20,40));

        tfUsername = new JTextField(currentAdmin.getUsername());
        tfEmail = new JTextField(currentAdmin.getEmailID());
        tfRole = new JTextField(currentAdmin.getRole().toString());
        tfStatus = new JTextField(currentAdmin.getStatus());
        tfLastLogin = new JTextField(currentAdmin.getLastLogin() == null ?
                                    "Never logged in" :
                                    currentAdmin.getLastLogin().toString());
        tfDepartment = new JTextField(currentAdmin.getDepartment());

        tfRole.setEditable(false);
        tfStatus.setEditable(false);
        tfLastLogin.setEditable(false);

        JButton btnChangePassword = new JButton("Change Password");
        JButton btnSave = new JButton("Save Changes");

        btnSave.setFont(new Font("Segoe UI", Font.BOLD, 16));

        btnSave.addActionListener(e -> updateProfile());
        btnChangePassword.addActionListener(e ->
                new ChangePasswordDialog(
                (JFrame) SwingUtilities.getWindowAncestor(this)));

        form.add(new JLabel("Username"));
        form.add(tfUsername);
        form.add(new JLabel("Email"));      
        form.add(tfEmail);
        form.add(new JLabel("Role"));      
        form.add(tfRole);
        form.add(new JLabel("Status"));     
        form.add(tfStatus);
        form.add(new JLabel("Last Login")); 
        form.add(tfLastLogin);
        form.add(new JLabel("Department"));
        form.add(tfDepartment);
        form.add(new JLabel());            
        form.add(btnChangePassword);

        add(form, BorderLayout.CENTER);
        add(btnSave, BorderLayout.SOUTH);
    }

    private void updateProfile()
    {
        try
        {
            Admin updated = new Admin(
                    currentAdmin.getUserID(),
                    tfUsername.getText(),
                    tfEmail.getText(),
                    currentAdmin.getPasswordHash(),
                    currentAdmin.getStatus(),
                    currentAdmin.getLastLogin(),
                    tfDepartment.getText()
            );

            AdminAPI api = new AdminAPI();
            ActionResult result = api.updateAdminProfile(updated);

            switch (result)
            {
                case SUCCESS ->
                    JOptionPane.showMessageDialog(this,
                        "Profile updated successfully.");

                case NOT_ALLOWED ->
                    JOptionPane.showMessageDialog(this,
                        "Permission denied.");

                case DB_ERROR ->
                    JOptionPane.showMessageDialog(this,
                        "Database error.");

                default ->
                    JOptionPane.showMessageDialog(this,
                        "Update failed: " + result);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Invalid input values");
        }
    }
}
