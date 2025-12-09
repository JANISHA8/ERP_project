package edu.univ.erp.ui.common;

import javax.swing.*;

import edu.univ.erp.auth.session.SessionInfo;
import edu.univ.erp.auth.hash.UserAuth;
import edu.univ.erp.data.SettingsData;
import edu.univ.erp.domain.Settings;

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
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setLayout(new BorderLayout());

        addWindowListener(new java.awt.event.WindowAdapter()
        {
            @Override public void windowClosing(java.awt.event.WindowEvent windowEvent)
            {
                if (SessionInfo.isLoggedIn())
                {
                    UserAuth auth = new UserAuth();
                    auth.updateStatus(SessionInfo.getUserID(), "OFFLINE");
                }
                System.exit(0);
            }
        });

        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(new Color(30, 60, 90));
        header.setPreferredSize(new Dimension(getWidth(), 80));

        heading = new JLabel(title);
        JLabel userLabel = new JLabel("Logged in as: " + user);

        heading.setForeground(Color.WHITE);
        heading.setFont(new Font("Segoe UI", Font.BOLD, 24));

        userLabel.setForeground(Color.WHITE);
        userLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));

        header.add(heading, BorderLayout.WEST);
        header.add(userLabel, BorderLayout.EAST);

        add(header, BorderLayout.NORTH);

        sidebar = new JPanel();
        sidebar.setBackground(new Color(40, 40, 40));
        sidebar.setPreferredSize(new Dimension(220, getHeight()));
        sidebar.setLayout(new GridLayout(10, 1, 10, 10));

        add(sidebar, BorderLayout.WEST);

        content = new JPanel();
        content.setBackground(Color.WHITE);
        content.setLayout(new BorderLayout());

        add(content, BorderLayout.CENTER);

        // MAINTENANCE BANNER
        Settings maintenance = SettingsData.getSetting("maintenance_mode");

        if (maintenance != null && maintenance.getValue() != null
            && maintenance.getValue().equalsIgnoreCase("ON"))
        {
            JLabel banner = new JLabel( "MAINTENANCE MODE - READ ONLY", JLabel.CENTER);

            banner.setOpaque(true);
            banner.setBackground(Color.RED);
            banner.setForeground(Color.WHITE);
            banner.setFont(new Font("Segoe UI", Font.BOLD, 16));
            banner.setPreferredSize(new Dimension(getWidth(), 35));

            add(banner, BorderLayout.SOUTH);
        }
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

        btn.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseEntered(java.awt.event.MouseEvent evt)
            { btn.setBackground(new Color(70, 70, 70)); }

            public void mouseExited(java.awt.event.MouseEvent evt)
            { btn.setBackground(new Color(45, 45, 45)); }
        });
        return btn;
    }

    protected void setupLogout(JButton logoutButton)
    {
        logoutButton.addActionListener(e ->
        {
            int confirm = JOptionPane.showConfirmDialog(this,
                                                        "Do you really want to logout?",
                                                        "Confirm Logout",
                                                        JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION)
            {
                SessionInfo.end();
                dispose();
                new edu.univ.erp.ui.auth.LoginFrame().setVisible(true);
            }
        });
    }
}
