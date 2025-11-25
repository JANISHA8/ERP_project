package edu.univ.erp.ui.student;

import edu.univ.erp.ui.auth.LoginFrame;
import edu.univ.erp.ui.common.BaseDashboard;
import edu.univ.erp.ui.common.SettingsPanel;
import edu.univ.erp.auth.session.SessionInfo;
import edu.univ.erp.ui.student.RegisterCoursePanel;

import javax.swing.*;
import java.awt.*;

public class StudentDashboard extends BaseDashboard
{
    public StudentDashboard(String username)
    {
        super("STUDENT DASHBOARD", username);

        JButton btnHome = createButton("Home");
        JButton btnMyCourses = createButton("My Courses");
        JButton btnRegister = createButton("Register Course");
        JButton btnDrop = createButton("Drop Course");
        JButton btnAttendance = createButton("Attendance");
        JButton btnResults = createButton("My Results");
        JButton btnProfile = createButton("Profile");
        JButton btnLogout = createButton("Logout");

        sidebar.add(btnHome);
        sidebar.add(btnMyCourses);
        sidebar.add(btnRegister);
        sidebar.add(btnDrop);
        sidebar.add(btnAttendance);
        sidebar.add(btnResults);
        sidebar.add(btnProfile);
        sidebar.add(btnLogout);

        JPanel mainPanel = new JPanel(new BorderLayout());

        // Default load
        mainPanel.add(new StudentHome(username), BorderLayout.CENTER);
        content.add(mainPanel, BorderLayout.CENTER);

        // Button Actions
        btnHome.addActionListener(e -> {
            mainPanel.removeAll();
            mainPanel.add(new StudentHome(username), BorderLayout.CENTER);
            mainPanel.revalidate();
            mainPanel.repaint();
        });

        btnMyCourses.addActionListener(e -> {
            mainPanel.removeAll();
            mainPanel.add(new StudentCoursesPanel(), BorderLayout.CENTER);
            mainPanel.revalidate();
            mainPanel.repaint();
        });

        btnRegister.addActionListener(e -> {
            mainPanel.removeAll();
            mainPanel.add(new RegisterCoursePanel(), BorderLayout.CENTER);
            mainPanel.revalidate();
            mainPanel.repaint();
        });

        btnDrop.addActionListener(e -> {
            mainPanel.removeAll();
            mainPanel.add(new DropCoursePanel(), BorderLayout.CENTER);
            mainPanel.revalidate();
            mainPanel.repaint();
        });

        btnAttendance.addActionListener(e -> {
            mainPanel.removeAll();
            mainPanel.add(new StudentAttendancePanel(), BorderLayout.CENTER);
            mainPanel.revalidate();
            mainPanel.repaint();
        });

        btnResults.addActionListener(e -> {
            mainPanel.removeAll();
            mainPanel.add(new StudentResultsPanel(), BorderLayout.CENTER);
            mainPanel.revalidate();
            mainPanel.repaint();
        });

        btnProfile.addActionListener(e -> {
            mainPanel.removeAll();
            mainPanel.add(new StudentProfilePanel(username), BorderLayout.CENTER);
            mainPanel.revalidate();
            mainPanel.repaint();
        });

        btnLogout.addActionListener(e -> {
            SessionInfo.end();
            dispose();
            new LoginFrame().setVisible(true);
        });


        if(SessionInfo.isLoggedIn())
        {
            JLabel userLabel = new JLabel("Logged in as: " + SessionInfo.getUsername() + 
                              " (" + SessionInfo.getRole() + ")");
        }
    }
}
