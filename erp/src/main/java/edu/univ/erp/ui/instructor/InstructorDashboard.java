package edu.univ.erp.ui.instructor;

import edu.univ.erp.ui.auth.LoginFrame;
import edu.univ.erp.auth.hash.UserAuth;
import edu.univ.erp.auth.session.SessionInfo;
import edu.univ.erp.ui.common.BaseDashboard;

import javax.swing.*;
import java.awt.*;

public class InstructorDashboard extends BaseDashboard
{
    private JPanel mainPanel;

    public InstructorDashboard(String username)
    {
        super("INSTRUCTOR DASHBOARD", username);

        JButton btnHome = createButton("Home");
        JButton btnMyCourses = createButton("My Courses");
        JButton btnEnterScores = createButton("Enter Scores");
        JButton btnComputeFinal = createButton("Compute Final");
        JButton btnStats = createButton("Class Stats");
        JButton btnProfile = createButton("Profile");
        JButton btnLogout = createButton("Logout");

        sidebar.add(btnHome);
        sidebar.add(btnMyCourses);
        sidebar.add(btnEnterScores);
        sidebar.add(btnComputeFinal);
        sidebar.add(btnStats);
        sidebar.add(btnProfile);
        sidebar.add(btnLogout);

        mainPanel = new JPanel(new BorderLayout());
        content.add(mainPanel, BorderLayout.CENTER);

        // DEFAULT - GRID HOME
        showHomeGrid();

        btnHome.addActionListener(e -> showHomeGrid());
        btnMyCourses.addActionListener(e -> showWithSidebar(new InstructorCoursesPanel()));
        btnEnterScores.addActionListener(e -> showWithSidebar(new EnterScoresPanel()));
        btnComputeFinal.addActionListener(e -> showWithSidebar(new ComputeFinalPanel()));
        btnStats.addActionListener(e -> showWithSidebar(new ClassStatsPanel()));
        btnProfile.addActionListener(e -> showWithSidebar(new InstructorProfilePanel()));
        btnLogout.addActionListener(e -> {
            dispose();
            new LoginFrame().setVisible(true);
        });
    }

    private void showHomeGrid()
    {
        sidebar.setVisible(false);
        mainPanel.removeAll();
        mainPanel.add(createGridPanel(), BorderLayout.CENTER);
        refresh();
    }

    private void showWithSidebar(JPanel panel)
    {
        sidebar.setVisible(true); // SHOW sidebar
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
        JPanel grid = new JPanel(new GridLayout(3,3,20,20));
        grid.setBorder(BorderFactory.createEmptyBorder(40,40,40,40));
        grid.setBackground(Color.WHITE);
        grid.add(createTile("My Courses", () -> showWithSidebar(new InstructorCoursesPanel())));
        grid.add(createTile("Enter Scores", () -> showWithSidebar(new EnterScoresPanel())));
        grid.add(createTile("Compute Final", () -> showWithSidebar(new ComputeFinalPanel())));
        grid.add(createTile("Class Stats", () -> showWithSidebar(new ClassStatsPanel())));
        grid.add(createTile("Profile", () -> showWithSidebar(new InstructorProfilePanel())));
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
        btn.setPreferredSize(new Dimension(220, 120));
        btn.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btn.setFocusPainted(false);
        btn.setBackground(new Color(52, 152, 219)); // blue
        btn.setForeground(Color.WHITE);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        Color baseColor;
        switch (text)
        {
            case "My Courses":
                baseColor = new Color(26, 188, 156); // Teal
                break;
            case "Enter Scores":
                baseColor = new Color(26, 188, 156); // Teal
                break;
            case "Compute Final":
                baseColor = new Color(26, 188, 156); // Teal
                break;
            case "Class Stats":
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
