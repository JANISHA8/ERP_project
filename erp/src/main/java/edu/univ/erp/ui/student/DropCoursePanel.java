package edu.univ.erp.ui.student;

import edu.univ.erp.api.student.StudentAPI;
import edu.univ.erp.domain.ActionResult;

import javax.swing.*;
import java.awt.*;

public class DropCoursePanel extends JPanel
{
    private JTextField tfCourse;

    public DropCoursePanel()
    {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        JLabel title = new JLabel("DROP COURSE", JLabel.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 22));
        title.setBorder(BorderFactory.createEmptyBorder(20,10,20,10));

        JPanel inputPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10,10,10,10);

        JLabel label = new JLabel("Enter Course Code:");
        label.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        tfCourse = new JTextField(15);

        JButton btnDrop = new JButton("DROP COURSE");
        btnDrop.setBackground(new Color(200,60,60));
        btnDrop.setForeground(Color.WHITE);

        gbc.gridx = 0; gbc.gridy = 0;
        inputPanel.add(label, gbc);

        gbc.gridx = 1;
        inputPanel.add(tfCourse, gbc);

        gbc.gridx = 0; gbc.gridy = 1; gbc.gridwidth = 2;
        inputPanel.add(btnDrop, gbc);

        add(title, BorderLayout.NORTH);
        add(inputPanel, BorderLayout.CENTER);

        btnDrop.addActionListener(e -> dropCourse());
    }

    private void dropCourse()
    {
        String code = tfCourse.getText().trim().toUpperCase();

        if (code.isEmpty())
        {
            JOptionPane.showMessageDialog(this,
                    "Please enter a course code!",
                    "Missing Input",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        StudentAPI api = new StudentAPI();
        ActionResult result = api.dropCourse(code);

        switch (result)
        {
            case SUCCESS ->
                    JOptionPane.showMessageDialog(this,
                            "Course dropped successfully",
                            "Success",
                            JOptionPane.INFORMATION_MESSAGE);

            case MAINTENANCE_MODE ->
                    JOptionPane.showMessageDialog(this,
                            "⚠ SYSTEM IS IN MAINTENANCE MODE\nCourse dropping is currently disabled.",
                            "Maintenance Mode",
                            JOptionPane.WARNING_MESSAGE);

            case NOT_ALLOWED ->
                    JOptionPane.showMessageDialog(this,
                            "You are not allowed to perform this action.",
                            "Access Denied",
                            JOptionPane.ERROR_MESSAGE);

            case NOT_FOUND ->
                    JOptionPane.showMessageDialog(this,
                            "Course not found or you are not enrolled.",
                            "Not Found",
                            JOptionPane.ERROR_MESSAGE);

            case DB_ERROR ->
                    JOptionPane.showMessageDialog(this,
                            "Database error occurred while dropping course.",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);

            default ->
                    JOptionPane.showMessageDialog(this,
                            "Unknown error occurred.",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
        }

        tfCourse.setText("");
    }
}
