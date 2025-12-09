package edu.univ.erp.ui.admin;

import edu.univ.erp.auth.hash.UserAuth;
import edu.univ.erp.auth.session.SessionInfo;

import javax.swing.*;
import java.awt.*;

public class ChangePasswordDialog extends JDialog
{
    public ChangePasswordDialog(JFrame parent)
    {
        setTitle("Change Password");
        setSize(360, 220);
        setLayout(new GridLayout(3, 2, 10, 10));
        setLocationRelativeTo(parent);

        JPasswordField current = new JPasswordField();
        JPasswordField newPass = new JPasswordField();

        JButton save = new JButton("Save");

        add(new JLabel("Current Password:"));
        add(current);

        add(new JLabel("New Password:"));
        add(newPass);

        add(new JLabel(""));
        add(save);

        save.addActionListener(e ->
        {
            String curr = new String(current.getPassword());
            String newP = new String(newPass.getPassword());

            UserAuth auth = new UserAuth();

            // check if locked
            if (auth.isUserBlocked(SessionInfo.getUserID()))
            {
                JOptionPane.showMessageDialog(this,
                    "Too many wrong attempts.\nTry again after 1 minute");
                return;
            }

            if (!auth.verifyPassword(SessionInfo.getUserID(), curr))
            {
                auth.recordFailedAttempt(SessionInfo.getUserID());
                JOptionPane.showMessageDialog(this, "Current password is wrong!");
                return;
            }
            // correct password
            auth.resetAttempts(SessionInfo.getUserID());

            if (newP.length() < 4)
            {
                JOptionPane.showMessageDialog(this, "Password too short");
                return;
            }

            boolean ok = auth.updatePassword(
                    SessionInfo.getUserID(),
                    UserAuth.hash(newP)
            );

            if (ok)
            {
                JOptionPane.showMessageDialog(this, "Password updated!");
                dispose();
            }
            else { JOptionPane.showMessageDialog(this, "Update failed"); }
        });

        setVisible(true);
    }
}
