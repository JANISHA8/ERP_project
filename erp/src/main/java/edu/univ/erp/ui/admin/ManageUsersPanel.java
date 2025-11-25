package edu.univ.erp.ui.admin;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class ManageUsersPanel extends JPanel
{
    public ManageUsersPanel()
    {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        JLabel title = new JLabel("Manage Users", JLabel.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 24));
        title.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));
        add(title, BorderLayout.NORTH);

        String[] columns = {"ID", "Username", "Role", "Email", "Status"};

        DefaultTableModel model = new DefaultTableModel(columns, 0);

        JTable userTable = new JTable(model);
        userTable.setFillsViewportHeight(true);
        userTable.setRowHeight(25);
        userTable.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        // Add TEST data (later from DB)
        model.addRow(new Object[]{1, "admin1", "ADMIN", "admin@email.com", "ACTIVE"});
        model.addRow(new Object[]{2, "student1", "STUDENT", "stud@email.com", "ACTIVE"});
        model.addRow(new Object[]{3, "instructor1", "INSTRUCTOR", "inst@email.com", "ACTIVE"});

        JScrollPane scrollPane = new JScrollPane(userTable);
        add(scrollPane, BorderLayout.CENTER);

        JPanel controls = new JPanel();
        JButton add = new JButton("Add User");
        JButton remove = new JButton("Remove User");
        JButton refresh = new JButton("Refresh");

        controls.add(add);
        controls.add(remove);
        controls.add(refresh);

        add(controls, BorderLayout.SOUTH);
    }
}
