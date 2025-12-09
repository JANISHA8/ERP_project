package edu.univ.erp.ui.student;

import edu.univ.erp.api.student.StudentAPI;
import edu.univ.erp.auth.hash.UserAuth;
import edu.univ.erp.auth.session.SessionInfo;
import edu.univ.erp.data.StudentsData;
import edu.univ.erp.domain.ActionResult;
import edu.univ.erp.domain.Student;

import javax.swing.*;
import java.awt.*;

public class StudentProfilePanel extends JPanel
{
    private Student currentStudent;

    private JTextField tfUsername, tfContact;
    private JTextField tfRoll, tfGender, tfDob, tfNationality, tfProgram,
            tfBranch, tfYear, tfSem, tfGradYear, tfEmail, tfLastLogin;

    public StudentProfilePanel()
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

        StudentsData data = new StudentsData();
        currentStudent = data.getStudentByUserId(SessionInfo.getUserID());

        if (currentStudent == null)
        {
            add(new JLabel("Student not found"), BorderLayout.CENTER);
            return;
        }

        JPanel form = new JPanel(new GridLayout(12, 2, 10, 10));
        form.setBorder(BorderFactory.createEmptyBorder(20,40,20,40));

        tfUsername = new JTextField(currentStudent.getUsername());        
        tfContact = new JTextField(String.valueOf(currentStudent.getContact_no()));
        tfRoll = new JTextField(String.valueOf(currentStudent.getRollNO()));
        tfGender = new JTextField(currentStudent.getGender());
        tfDob = new JTextField(String.valueOf(currentStudent.getDob()));
        tfNationality = new JTextField(currentStudent.getNationality());
        tfProgram = new JTextField(currentStudent.getProgram());
        tfBranch = new JTextField(currentStudent.getBranch());
        tfYear = new JTextField(String.valueOf(currentStudent.getCurrent_year()));
        tfSem = new JTextField(String.valueOf(currentStudent.getCurrent_sem()));
        tfGradYear = new JTextField(String.valueOf(currentStudent.getGraduation_year()));
        tfEmail = new JTextField(currentStudent.getEmailID());
        tfLastLogin = new JTextField(String.valueOf(currentStudent.getLastLogin()));

        setReadOnly(tfRoll, tfGender, tfDob, tfNationality, tfProgram, tfBranch, tfYear, tfSem, tfGradYear, tfEmail, tfLastLogin);

        form.add(new JLabel("Username")); 
        form.add(tfUsername);
        form.add(new JLabel("Email"));   
        form.add(tfEmail);
        form.add(new JLabel("Roll No")); 
        form.add(tfRoll);
        form.add(new JLabel("Gender"));  
        form.add(tfGender);
        form.add(new JLabel("Contact")); 
        form.add(tfContact);
        form.add(new JLabel("DOB"));    
        form.add(tfDob);
        form.add(new JLabel("Nationality")); 
        form.add(tfNationality);
        form.add(new JLabel("Program"));  
        form.add(tfProgram);
        form.add(new JLabel("Branch"));   
        form.add(tfBranch);
        form.add(new JLabel("Current Year"));
        form.add(tfYear);
        form.add(new JLabel("Semester"));     
        form.add(tfSem);
        form.add(new JLabel("Graduation Year")); 
        form.add(tfGradYear);

        JButton btnSave = new JButton("Save Changes");
        JButton btnChangePass = new JButton("Change Password");

        JPanel bottom = new JPanel();
        bottom.add(btnSave);
        bottom.add(btnChangePass);

        btnSave.addActionListener(e -> updateProfile());
        btnChangePass.addActionListener(e -> changePassword());

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
            String newUsername = tfUsername.getText().trim();
            long newContact = Long.parseLong(tfContact.getText().trim());

            if (newUsername.isEmpty())
            {
                JOptionPane.showMessageDialog(this,"Username cannot be empty");
                return;
            }

            currentStudent.setUserName(newUsername);
            currentStudent.setContact_no(newContact);

            StudentAPI api = new StudentAPI();
            ActionResult result = api.updateStudentProfile(currentStudent);

            if (result == ActionResult.SUCCESS)
                { JOptionPane.showMessageDialog(this, "Profile updated successfully"); }
            else
                { JOptionPane.showMessageDialog(this, "Profile update failed: " + result); }
        }
        catch (Exception e) { JOptionPane.showMessageDialog(this, "Invalid contact number"); }
    }

    // CHANGE PASSWORD
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
                this,
                fields,
                "Change Password",
                JOptionPane.OK_CANCEL_OPTION
        );

        if (result != JOptionPane.OK_OPTION) { return; }
        String oldPass = new String(pfOld.getPassword());
        String newPass = new String(pfNew.getPassword());
        UserAuth auth = new UserAuth();

        if (!auth.verifyPassword(SessionInfo.getUserID(), oldPass))
        {
            JOptionPane.showMessageDialog(this, "Incorrect current password");
            return;
        }
        if (newPass.length() < 6)
        {
            JOptionPane.showMessageDialog(this, "Password must be at least 6 characters");
            return;
        }
        boolean success = auth.updatePassword(
                SessionInfo.getUserID(),
                UserAuth.hash(newPass));
        if (success)
            JOptionPane.showMessageDialog(this, "Password updated successfully");
        else
            JOptionPane.showMessageDialog(this, "Password update failed");
    }
}
