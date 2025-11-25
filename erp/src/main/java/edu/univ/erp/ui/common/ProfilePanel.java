package edu.univ.erp.ui.common;

import javax.swing.*;
import java.awt.*;

public class ProfilePanel extends JPanel
{
    public ProfilePanel(String username, String role)
    {
        setLayout(new GridBagLayout());
        setBackground(Color.WHITE);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15,15,15,15);

        JLabel title = new JLabel("User Profile");
        title.setFont(new Font("Segoe UI", Font.BOLD, 26));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;

        add(title, gbc);

        gbc.gridwidth = 1;

        gbc.gridy = 1;
        add(new JLabel("Username:"), gbc);

        gbc.gridx = 1;
        add(new JLabel(username), gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        add(new JLabel("Role:"), gbc);

        gbc.gridx = 1;
        add(new JLabel(role), gbc);
    }
}
