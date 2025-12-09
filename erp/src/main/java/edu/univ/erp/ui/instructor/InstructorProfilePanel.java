package edu.univ.erp.ui.instructor;

import edu.univ.erp.api.instructor.InstructorAPI;
import edu.univ.erp.auth.hash.UserAuth;
import edu.univ.erp.auth.session.SessionInfo;
import edu.univ.erp.data.InstructorsData;
import edu.univ.erp.domain.Instructor;
import edu.univ.erp.domain.ActionResult;

import javax.swing.*;
import java.awt.*;

public class InstructorProfilePanel extends JPanel
{
    private Instructor currentInstructor;

    private JTextField tfUsername, tfContact;
    private JTextField tfGender, tfDob, tfNationality, tfDepartment, tfEmail, tfLastLogin;

    public InstructorProfilePanel()
    {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        JLabel title = new JLabel("MY PROFILE", JLabel.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 22));
        title.setBorder(BorderFactory.createEmptyBorder(15,10,15,10));
        add(title, BorderLayout.NORTH);

        if (!SessionInfo.isLoggedIn())
        {
            add(new JLabel("No active session"), BorderLayout.CENTER);
            return;
        }

        InstructorsData data = new InstructorsData();
        currentInstructor = data.getInstructorByUserId(SessionInfo.getUserID());

        if (currentInstructor == null)
        {
            add(new JLabel("Instructor not found"), BorderLayout.CENTER);
            return;
        }

        JPanel form = new JPanel(new GridLayout(8, 2, 10, 10));
        form.setBorder(BorderFactory.createEmptyBorder(20,40,20,40));

        tfUsername = new JTextField(currentInstructor.getUsername());
        tfContact = new JTextField("" + currentInstructor.getContactno());
        tfGender = new JTextField(currentInstructor.getGender());
        tfDob = new JTextField("" + currentInstructor.getDob());
        tfNationality = new JTextField(currentInstructor.getNationality());
        tfDepartment = new JTextField(currentInstructor.getDepartment());
        tfEmail = new JTextField(currentInstructor.getEmailID());
        tfLastLogin = new JTextField("" + currentInstructor.getLastLogin());

        setReadOnly(tfGender, tfDob, tfNationality, tfDepartment, tfEmail, tfLastLogin);

        form.add(new JLabel("Username"));    form.add(tfUsername);
        form.add(new JLabel("Email"));       form.add(tfEmail);
        form.add(new JLabel("Contact No"));  form.add(tfContact);
        form.add(new JLabel("Gender"));      form.add(tfGender);
        form.add(new JLabel("DOB"));         form.add(tfDob);
        form.add(new JLabel("Nationality")); form.add(tfNationality);
        form.add(new JLabel("Department"));  form.add(tfDepartment);
        form.add(new JLabel("Last Login"));  form.add(tfLastLogin);

        JButton btnSave = new JButton("Save Changes");
        JButton btnChangePassword = new JButton("Change Password");

        JPanel bottom = new JPanel();
        bottom.add(btnSave);
        bottom.add(btnChangePassword);

        btnSave.addActionListener(e -> updateProfile());
        btnChangePassword.addActionListener(e -> changePassword());

        add(form, BorderLayout.CENTER);
        add(bottom, BorderLayout.SOUTH);
    }

    private void setReadOnly(JTextField... fields)
    {
        for (JTextField f : fields) { f.setEditable(false); }
    }

    private void updateProfile()
    {
        try
        {
            currentInstructor.setUserName(tfUsername.getText().trim());
            currentInstructor.setContact_no(Long.parseLong(tfContact.getText().trim()));

            InstructorAPI api = new InstructorAPI();
            ActionResult result = api.updateInstructorProfile(currentInstructor);

            if (result == ActionResult.SUCCESS) { JOptionPane.showMessageDialog(this, "Profile updated successfully!"); }
            else { JOptionPane.showMessageDialog(this, "Profile update failed: " + result); }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Invalid input values");
        }
    }

    private void changePassword()
    {
        JPasswordField pfOld = new JPasswordField();
        JPasswordField pfNew = new JPasswordField();

        Object[] fields =
        {
            "Current Password:", pfOld,
            "New Password:", pfNew
        };

        int result = JOptionPane.showConfirmDialog(
                this, fields,
                "Change Password",
                JOptionPane.OK_CANCEL_OPTION
        );

        if (result != JOptionPane.OK_OPTION) { return; }
        String oldPass = new String(pfOld.getPassword());
        String newPass = new String(pfNew.getPassword());

        if (newPass.length() < 6)
        {
            JOptionPane.showMessageDialog(this, "Password must be at least 6 characters long");
            return;
        }

        UserAuth auth = new UserAuth();

        if (!auth.verifyPassword(SessionInfo.getUserID(), oldPass))
        {
            JOptionPane.showMessageDialog(this, "Incorrect current password");
            return;
        }

        boolean success = auth.updatePassword(
                SessionInfo.getUserID(),
                UserAuth.hash(newPass));

        if (success) { JOptionPane.showMessageDialog(this, "Password updated successfully"); }
        else { JOptionPane.showMessageDialog(this, "Password update failed"); }
    }
}
