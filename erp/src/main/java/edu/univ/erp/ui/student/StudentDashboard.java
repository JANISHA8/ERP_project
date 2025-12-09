package edu.univ.erp.ui.student;

import edu.univ.erp.ui.auth.LoginFrame;
import edu.univ.erp.ui.common.BaseDashboard;
import edu.univ.erp.auth.session.SessionInfo;
import edu.univ.erp.auth.hash.UserAuth;

import javax.swing.*;
import java.awt.*;

public class StudentDashboard extends BaseDashboard
{
    private JPanel mainPanel;

    public StudentDashboard(String username)
    {
        super("STUDENT DASHBOARD", username);

        JButton btnHome      = createButton("Home");
        JButton btnTimetable = createButton("Time Table");
        JButton btnMyCourses = createButton("My Courses");
        JButton btnRegister  = createButton("Register Course");
        JButton btnDrop      = createButton("Drop Course");
        JButton btnResults   = createButton("My Results");
        JButton btnProfile   = createButton("Profile");
        JButton btnLogout    = createButton("Logout");

        sidebar.add(btnHome);
        sidebar.add(btnTimetable);
        sidebar.add(btnMyCourses);
        sidebar.add(btnRegister);
        sidebar.add(btnDrop);
        sidebar.add(btnResults);
        sidebar.add(btnProfile);
        sidebar.add(btnLogout);

        mainPanel = new JPanel(new BorderLayout());
        content.add(mainPanel, BorderLayout.CENTER);

        showHomeGrid(username);

        btnHome.addActionListener(e -> showHomeGrid(username));
        btnTimetable.addActionListener(e -> showWithSidebar(new StudentTimetablePanel()));
        btnMyCourses.addActionListener(e -> showWithSidebar(new StudentCoursesPanel()));
        btnRegister.addActionListener(e -> showWithSidebar(new RegisterCoursePanel()));
        btnDrop.addActionListener(e -> showWithSidebar(new DropCoursePanel()));
        btnResults.addActionListener(e -> showWithSidebar(new StudentResultsPanel()));
        btnProfile.addActionListener(e -> showWithSidebar(new StudentProfilePanel()));
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

    private void showHomeGrid(String username)
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
        JPanel grid = new JPanel(new GridLayout(2,4,20,20));
        grid.setBorder(BorderFactory.createEmptyBorder(40,40,40,40));
        grid.setBackground(Color.WHITE);

        grid.add(createTile("Time Table", () ->
                showWithSidebar(new StudentTimetablePanel())));

        grid.add(createTile("My Courses", () ->
                showWithSidebar(new StudentCoursesPanel())));

        grid.add(createTile("Register Course", () ->
                showWithSidebar(new RegisterCoursePanel())));

        grid.add(createTile("Drop Course", () ->
                showWithSidebar(new DropCoursePanel())));

        grid.add(createTile("My Results", () ->
                showWithSidebar(new StudentResultsPanel())));

        grid.add(createTile("Profile", () ->
                showWithSidebar(new StudentProfilePanel())));

        grid.add(createTile("Logout", () ->
        {
            if (SessionInfo.isLoggedIn())
            {
                UserAuth auth = new UserAuth();
                auth.updateStatus(SessionInfo.getUserID(), "OFFLINE");
            }
            SessionInfo.end();
            dispose();
            new LoginFrame().setVisible(true);
        }));
        grid.add(new JPanel());
        return grid;
    }

    private JButton createTile(String text, Runnable action)
    {
        JButton btn = new JButton(text);
        btn.setPreferredSize(new Dimension(200, 100));
        btn.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btn.setFocusPainted(false);
        btn.setBackground(new Color(52, 152, 219));
        btn.setForeground(Color.WHITE);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        Color baseColor;
        switch (text)
        {
            case "Time Table":
                baseColor = new Color(26, 188, 156); // Teal
                break;
            case "My Courses":
                baseColor = new Color(26, 188, 156); // Teal
                break;
            case "Register Course":
                baseColor = new Color(26, 188, 156); // Teal
                break;
            case "Drop Course":
                baseColor = new Color(26, 188, 156); // Teal
                break;
            case "My Results":
                baseColor = new Color(26, 188, 156); // Teal
                break;
            case "Profile":
                baseColor = new Color(26, 188, 156); // Teal
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
