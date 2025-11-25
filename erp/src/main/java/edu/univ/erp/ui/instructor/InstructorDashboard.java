package edu.univ.erp.ui.instructor;

import edu.univ.erp.ui.auth.LoginFrame;
import edu.univ.erp.ui.common.BaseDashboard;
import edu.univ.erp.ui.common.ReportsPanel;
import edu.univ.erp.ui.common.SettingsPanel;
import edu.univ.erp.auth.session.SessionInfo;

import javax.swing.*;
import java.awt.*;

public class InstructorDashboard extends BaseDashboard
{
    public InstructorDashboard(String username)
    {
        super("INSTRUCTOR DASHBOARD", username);

        JButton btnHome = createButton("Home");
        JButton btnMyCourses = createButton("My Courses");
        JButton btnAttendance = createButton("Attendance");
        JButton btnResults = createButton("Results");
        JButton btnSettings = createButton("Settings");
        JButton btnLogout = createButton("Logout");

        sidebar.add(btnHome);
        sidebar.add(btnMyCourses);
        sidebar.add(btnAttendance);
        sidebar.add(btnResults);
        sidebar.add(btnSettings);
        sidebar.add(btnLogout);

        JPanel mainPanel = new JPanel(new BorderLayout());

        // Default Page
        mainPanel.add(new InstructorHome(username), BorderLayout.CENTER);
        content.add(mainPanel, BorderLayout.CENTER);

        // -------- Button Actions ------------

        btnHome.addActionListener(e -> {
            mainPanel.removeAll();
            mainPanel.add(new InstructorHome(username), BorderLayout.CENTER);
            mainPanel.revalidate();
            mainPanel.repaint();
        });

        btnMyCourses.addActionListener(e -> {
            mainPanel.removeAll();
            mainPanel.add(new InstructorCoursesPanel(), BorderLayout.CENTER);
            mainPanel.revalidate();
            mainPanel.repaint();
        });

        btnAttendance.addActionListener(e -> {
            mainPanel.removeAll();
            mainPanel.add(new AttendancePanel(), BorderLayout.CENTER);
            mainPanel.revalidate();
            mainPanel.repaint();
        });

        btnResults.addActionListener(e -> {
            mainPanel.removeAll();
            mainPanel.add(new ResultsPanel(), BorderLayout.CENTER);
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
