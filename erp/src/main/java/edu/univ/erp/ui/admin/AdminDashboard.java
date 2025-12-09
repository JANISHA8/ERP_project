package edu.univ.erp.ui.admin;

import edu.univ.erp.auth.session.SessionInfo;
import edu.univ.erp.auth.hash.UserAuth;
import edu.univ.erp.ui.auth.LoginFrame;
import edu.univ.erp.ui.common.BaseDashboard;

import javax.swing.*;
import java.awt.*;

public class AdminDashboard extends BaseDashboard
{
    private JPanel mainPanel;
    public AdminDashboard(String username)
    {
        super("ADMIN DASHBOARD", username);
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        JButton btnHome = createButton("Home");
        JButton btnUsers = createButton("Manage Users");
        JButton btnCourses = createButton("Courses");
        JButton btnAddUser = createButton("Add User");
        JButton btnCreateCourse = createButton("Create Course");
        JButton btnAssignInstructor = createButton("Assign Instructor");
        JButton btnProfile = createButton("Profile");
        JButton btnSettings = createButton("Settings");
        JButton btnLogout = createButton("Logout");

        sidebar.add(btnHome);
        sidebar.add(btnUsers);
        sidebar.add(btnCourses);
        sidebar.add(btnAddUser);
        sidebar.add(btnCreateCourse);
        sidebar.add(btnAssignInstructor);
        sidebar.add(btnProfile);
        sidebar.add(btnSettings);
        sidebar.add(btnLogout);

        mainPanel = new JPanel(new BorderLayout());
        content.add(mainPanel, BorderLayout.CENTER);

        showHomeGrid();

        btnHome.addActionListener(e -> showHomeGrid());
        btnUsers.addActionListener(e -> showWithSidebar(new ManageUsersPanel()));
        btnCourses.addActionListener(e -> showWithSidebar(new CoursesPanel()));
        btnAddUser.addActionListener(e -> showWithSidebar(new AddUserPanel()));
        btnCreateCourse.addActionListener(e -> showWithSidebar(new CreateCoursePanel()));
        btnAssignInstructor.addActionListener(e -> showWithSidebar(new AssignInstructorPanel()));
        btnProfile.addActionListener(e -> showWithSidebar(new AdminProfilePanel()));
        btnSettings.addActionListener(e -> showWithSidebar(new SettingsPanel()));
        btnLogout.addActionListener(e ->
        {
            if (SessionInfo.isLoggedIn())
            {
                UserAuth auth = new UserAuth();
                auth.updateStatus(SessionInfo.getUserID(), "OFFLINE");
            }
            SessionInfo.end();
            dispose();
            new LoginFrame().setVisible(true);
        });
    }

    private void showHomeGrid()
    {
        sidebar.setVisible(false);
        mainPanel.removeAll();
        mainPanel.add(createGridPanel());
        refresh();
    }

    private void showWithSidebar(JPanel panel)
    {
        sidebar.setVisible(true);
        mainPanel.removeAll();
        mainPanel.add(panel, BorderLayout.CENTER);
        refresh();
    }

    private void refresh()
    {
        mainPanel.revalidate();
        mainPanel.repaint();
    }

    private JPanel createGridPanel()
    {
        JPanel grid = new JPanel(new GridLayout(3,4,20,20));
        grid.setBorder(BorderFactory.createEmptyBorder(40,40,40,40));
        grid.setBackground(Color.WHITE);
        grid.add(createTile("Manage Users", () -> showWithSidebar(new ManageUsersPanel())));
        grid.add(createTile("Courses", () -> showWithSidebar(new CoursesPanel())));
        grid.add(createTile("Add User", () -> showWithSidebar(new AddUserPanel())));
        grid.add(createTile("Create Course", () -> showWithSidebar(new CreateCoursePanel())));
        grid.add(createTile("Assign Instructor", () -> showWithSidebar(new AssignInstructorPanel())));
        grid.add(createTile("Profile", () -> showWithSidebar(new AdminProfilePanel())));
        grid.add(createTile("Settings", () -> showWithSidebar(new SettingsPanel())));
        grid.add(createTile("Logout", () ->
        {
            if (SessionInfo.isLoggedIn())
            {
                UserAuth auth = new UserAuth();
                auth.updateStatus(SessionInfo.getUserID(), "OFFLINE");
            }
            dispose();
            new LoginFrame().setVisible(true);
        }));
        return grid;
    }

    private JButton createTile(String text, Runnable action)
    {
        JButton btn = new JButton(text);
        btn.setPreferredSize(new Dimension(220, 110));
        btn.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btn.setFocusPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setForeground(Color.WHITE);

        Color baseColor;
        switch (text) {
            case "Manage Users":
                baseColor = new Color(26, 188, 156); // Teal
                break;
            case "Courses":
                baseColor = new Color(26, 188, 156); // Teal
                break;
            case "Add User":
                baseColor = new Color(26, 188, 156); // Teal
                break;
            case "Create Course":
                baseColor = new Color(26, 188, 156); // Teal
                break;
            case "Assign Instructor":
                baseColor = new Color(26, 188, 156); // Teal
                break;
            case "Profile":
                baseColor = new Color(26, 188, 156); // Teal
                break;
            case "Settings":
                baseColor = new Color(127, 140, 141); // Slate Gray
                break;
            case "Logout":
                baseColor = new Color(224, 102, 102); // Red
                break;
            default:
                baseColor = new Color(46, 204, 113); // Green fallback
        }
        btn.setBackground(baseColor);

        // Rounded corners
        btn.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(0,0,0,0), 1),
                BorderFactory.createEmptyBorder(15, 15, 15, 15)
        ));

        // Hover effect
        btn.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseEntered(java.awt.event.MouseEvent evt) { btn.setBackground(baseColor.darker()); }
            public void mouseExited(java.awt.event.MouseEvent evt) { btn.setBackground(baseColor); }
        });

        btn.addActionListener(e -> action.run());
        return btn;
    }
}