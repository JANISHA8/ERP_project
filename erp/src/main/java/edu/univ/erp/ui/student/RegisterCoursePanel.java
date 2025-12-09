package edu.univ.erp.ui.student;

import edu.univ.erp.api.student.StudentAPI;
import edu.univ.erp.data.CourseData;
import edu.univ.erp.domain.ActionResult;
import edu.univ.erp.domain.Course;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class RegisterCoursePanel extends JPanel
{
    private JTable table;
    private DefaultTableModel model;

    public RegisterCoursePanel()
    {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        JLabel title = new JLabel("REGISTER FOR COURSE", JLabel.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 22));
        title.setBorder(BorderFactory.createEmptyBorder(15,10,15,10));
        add(title, BorderLayout.NORTH);

        String[] cols =
        {
                "Code", "Title", "Credits",
                "Type", "Max Seats", "Registered", "Instructor"
        };

        model = new DefaultTableModel(cols, 0)
        {
            @Override
            public boolean isCellEditable(int row, int column)
            {
                return false; // STUDENT CANNOT EDIT
            }
        };

        table = new JTable(model);
        table.setRowHeight(28);

        loadCourses();

        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        JButton btnRegister = new JButton("REGISTER SELECTED COURSE");
        btnRegister.setFont(new Font("Segoe UI", Font.BOLD, 14));

        btnRegister.addActionListener(e -> registerSelectedCourse());

        JPanel bottom = new JPanel();
        bottom.add(btnRegister);

        add(bottom, BorderLayout.SOUTH);
    }

    private void loadCourses()
    {
        model.setRowCount(0);

        CourseData data = new CourseData();
        List<Course> courses = data.getAllCourses();

        for (Course c : courses)
        {
            model.addRow(new Object[]{
                    c.getCode(),
                    c.getTitle(),
                    c.getCredits(),
                    c.getType(),
                    c.getMaxStudents(),
                    c.getNumStudents(),
                    c.getInstructorName()
            });
        }
    }

    private void registerSelectedCourse()
    {
        int row = table.getSelectedRow();

        if (row == -1)
        {
            JOptionPane.showMessageDialog(this,
                    "Please select a course first",
                    "No Selection",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        String courseCode = model.getValueAt(row, 0).toString();

        StudentAPI api = new StudentAPI();
        ActionResult result = api.registerCourse(courseCode);

        switch (result)
        {
            case SUCCESS ->
            {
                JOptionPane.showMessageDialog(this,
                        "COURSE REGISTERED SUCCESSFULLY",
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE);
                loadCourses();
            }

            case MAINTENANCE_MODE ->
                    JOptionPane.showMessageDialog(this,
                            "SYSTEM IS IN MAINTENANCE MODE\nCourse registration is currently disabled.",
                            "Maintenance Mode",
                            JOptionPane.WARNING_MESSAGE);

            case NOT_ALLOWED ->
                    JOptionPane.showMessageDialog(this,
                            "You are not allowed to register for courses.",
                            "Access Denied",
                            JOptionPane.ERROR_MESSAGE);

            case NOT_FOUND ->
                    JOptionPane.showMessageDialog(this,
                            "Course not found.",
                            "Not Found",
                            JOptionPane.ERROR_MESSAGE);

            case DB_ERROR ->
                    JOptionPane.showMessageDialog(this,
                            "Database error occurred while registering.",
                            "Database Error",
                            JOptionPane.ERROR_MESSAGE);

            default ->
                    JOptionPane.showMessageDialog(this,
                            "Unknown error occurred.",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
        }
    }
}
