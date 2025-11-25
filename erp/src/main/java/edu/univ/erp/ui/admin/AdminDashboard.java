package edu.univ.erp.ui.admin;

import edu.univ.erp.ui.auth.LoginFrame;
import edu.univ.erp.ui.common.BaseDashboard;
import edu.univ.erp.ui.common.ReportsPanel;
import edu.univ.erp.ui.common.SettingsPanel;
import edu.univ.erp.auth.session.SessionInfo;

import javax.swing.*;
import java.awt.*;

public class AdminDashboard extends BaseDashboard
{
    public AdminDashboard(String username)
    {
        super("ADMIN DASHBOARD", username);

        JButton btnHome = createButton("Home");
        JButton btnUsers = createButton("Manage Users");
        JButton btnCourses = createButton("Courses");
        JButton btnReports = createButton("Reports");
        JButton btnSettings = createButton("Settings");
        JButton btnLogout = createButton("Logout");

        sidebar.add(btnHome);
        sidebar.add(btnUsers);
        sidebar.add(btnCourses);
        sidebar.add(btnReports);
        sidebar.add(btnSettings);
        sidebar.add(btnLogout);

        JPanel mainPanel = new JPanel(new BorderLayout());
        content.add(mainPanel, BorderLayout.CENTER);

        JLabel welcome = new JLabel("WELCOME " + username, JLabel.CENTER);
        welcome.setFont(new Font("Segoe UI", Font.BOLD, 24));
        mainPanel.add(welcome, BorderLayout.NORTH);

        mainPanel.add(new AdminHome(), BorderLayout.CENTER);

        btnHome.addActionListener(e -> {
            mainPanel.removeAll();
            mainPanel.add(new AdminHome(), BorderLayout.CENTER);
            mainPanel.revalidate();
            mainPanel.repaint();
        });

        btnUsers.addActionListener(e -> {
            mainPanel.removeAll();
            mainPanel.add(new ManageUsersPanel(), BorderLayout.CENTER);
            mainPanel.revalidate();
            mainPanel.repaint();
        });

        btnCourses.addActionListener(e -> {
            mainPanel.removeAll();
            mainPanel.add(new CoursesPanel(), BorderLayout.CENTER);
            mainPanel.revalidate();
            mainPanel.repaint();
        });

        btnReports.addActionListener(e -> {
            mainPanel.removeAll();
            mainPanel.add(new ReportsPanel(), BorderLayout.CENTER);
            mainPanel.revalidate();
            mainPanel.repaint();
        });

        btnSettings.addActionListener(e -> {
            mainPanel.removeAll();
            mainPanel.add(new SettingsPanel(), BorderLayout.CENTER);
            mainPanel.revalidate();
            mainPanel.repaint();
        });

        btnLogout.addActionListener(e -> {
            dispose();
            new LoginFrame().setVisible(true);
        });
    }
}
