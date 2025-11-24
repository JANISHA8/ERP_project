package edu.univ.erp.ui.admin;

import javax.swing.*;
import java.awt.*;

public class AdminDashboard extends JFrame
{
    public AdminDashboard()
    {
        setTitle("Admin Dashboard");
        setSize(900, 600);
        setLocationRelativeTo(null);
        add(new JLabel("Welcome Admin"), BorderLayout.CENTER);
    }
}
