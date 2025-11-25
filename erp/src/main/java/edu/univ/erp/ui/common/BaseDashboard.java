package edu.univ.erp.ui.common;

import javax.swing.*;
import java.awt.*;

public abstract class BaseDashboard extends JFrame
{
    protected JPanel sidebar;
    protected JPanel content;
    protected JLabel heading;

    public BaseDashboard(String title, String user)
    {
        setTitle(title);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Header
        JPanel header = new JPanel();
        header.setBackground(new Color(30, 60, 90));
        header.setPreferredSize(new Dimension(getWidth(), 80));

        heading = new JLabel(title);
        JLabel userLabel = new JLabel("Logged in as: " + user);
        userLabel.setForeground(Color.WHITE);
        userLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));

        header.setLayout(new BorderLayout());
        header.add(heading, BorderLayout.WEST);
        header.add(userLabel, BorderLayout.EAST);

        heading.setForeground(Color.WHITE);
        heading.setFont(new Font("Segoe UI", Font.BOLD, 24));

        header.add(heading);
        add(header, BorderLayout.NORTH);

        // Sidebar
        sidebar = new JPanel();
        sidebar.setBackground(new Color(40, 40, 40));
        sidebar.setPreferredSize(new Dimension(220, getHeight()));
        sidebar.setLayout(new GridLayout(10, 1, 10, 10));

        add(sidebar, BorderLayout.WEST);

        // Content
        content = new JPanel();
        content.setBackground(Color.WHITE);
        content.setLayout(new BorderLayout());
        add(content, BorderLayout.CENTER);
    }

    protected JButton createButton(String text)
    {
        JButton btn = new JButton(text);
        btn.setForeground(Color.WHITE);
        btn.setBackground(new Color(45, 45, 45));
        btn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btn.setFocusPainted(false);
        btn.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));
        btn.setHorizontalAlignment(SwingConstants.LEFT);

        btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn.setBackground(new Color(70, 70, 70));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn.setBackground(new Color(45, 45, 45));
            }
        });

        return btn;
    }

    protected void setupLogout(JButton logoutButton)
    {
        logoutButton.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(
                    this,
                    "Do you really want to logout?",
                    "Confirm Logout",
                    JOptionPane.YES_NO_OPTION
            );

            if (confirm == JOptionPane.YES_OPTION) {

                // Clear session
                edu.univ.erp.auth.session.SessionInfo.end();

                // Close dashboard
                dispose();

                // Open Login again
                new edu.univ.erp.ui.auth.LoginFrame().setVisible(true);
            }
        });
    }
}
