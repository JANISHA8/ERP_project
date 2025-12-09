package edu.univ.erp.ui.admin;

import edu.univ.erp.domain.Role;

import javax.swing.*;
import java.awt.*;

public class AddUserPanel extends JPanel
{
    public AddUserPanel()
    {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        JLabel heading = new JLabel("Select User Role");
        heading.setFont(new Font("Segoe UI", Font.BOLD, 20));
        JComboBox<Role> roleBox = new JComboBox<>(Role.values());
        roleBox.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        JButton nextBtn = new JButton("Next");
        gbc.insets = new Insets(10,10,10,10);

        gbc.gridx = 0; gbc.gridy = 0;
        add(heading, gbc);
        gbc.gridy = 1;
        add(roleBox, gbc);
        gbc.gridy = 2;
        add(nextBtn, gbc);

        nextBtn.addActionListener(e ->
        {
            Role selected = (Role) roleBox.getSelectedItem();
            if (selected == null)
            {
                JOptionPane.showMessageDialog(this,"Please select a role");
                return;
            }
            switch (selected)
            {
                case ADMIN ->
                    new AddAdminDialog();
                case INSTRUCTOR ->
                    new AddInstructorDialog();
                case STUDENT ->
                    new AddStudentDialog();
            }
        });
    }
}
