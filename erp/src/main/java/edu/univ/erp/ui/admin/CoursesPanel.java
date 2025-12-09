package edu.univ.erp.ui.admin;

import edu.univ.erp.api.admin.CourseAPI;
import edu.univ.erp.auth.session.SessionInfo;
import edu.univ.erp.domain.ActionResult;
import edu.univ.erp.domain.Course;
import edu.univ.erp.domain.Role;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class CoursesPanel extends JPanel
{
    private final CourseAPI api = new CourseAPI();
    private DefaultTableModel model;
    private JTable table;

    public CoursesPanel()
    {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        JLabel title = new JLabel("Course Management", JLabel.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 24));
        title.setBorder(BorderFactory.createEmptyBorder(20,10,20,10));
        add(title, BorderLayout.NORTH);

        String[] cols = {"Code","Title","Credits","Type","Max","Students","Instructor"};
        model = new DefaultTableModel(cols, 0);
        table = new JTable(model);

        add(new JScrollPane(table), BorderLayout.CENTER);

        JButton editBtn = new JButton("Edit Course");
        JButton deleteBtn = new JButton("Delete Course");
        JButton refreshBtn = new JButton("Refresh");

        JPanel bottom = new JPanel();
        bottom.add(editBtn);
        bottom.add(deleteBtn);
        bottom.add(refreshBtn);

        add(bottom, BorderLayout.SOUTH);
        loadCourses();

        refreshBtn.addActionListener(e -> loadCourses());
        editBtn.addActionListener(e -> openEditDialog());
        deleteBtn.addActionListener(e -> deleteSelectedCourse());
    }

    private void loadCourses()
    {
        model.setRowCount(0);
        List<Course> list = api.fetchAllCourses(Role.ADMIN);

        if (list == null || list.isEmpty())
        {
            JOptionPane.showMessageDialog(this, "No courses available or permission denied.");
            return;
        }

        for (Course c : list)
        {
            model.addRow(new Object[]{c.getCode(),
                                    c.getTitle(),
                                    c.getCredits(),
                                    c.getType(),
                                    c.getMaxStudents(),
                                    c.getNumStudents(),
                                    c.getInstructorName()});
        }
    }
    private void openEditDialog()
    {
        int row = table.getSelectedRow();
        if (row == -1)
        {
            JOptionPane.showMessageDialog(this, "Select a course first");
            return;
        }

        String code = model.getValueAt(row, 0).toString();
        String title = model.getValueAt(row, 1).toString();
        int credits = Integer.parseInt(model.getValueAt(row, 2).toString());
        String type = model.getValueAt(row, 3).toString();
        int max = Integer.parseInt(model.getValueAt(row, 4).toString());
        int students = Integer.parseInt(model.getValueAt(row, 5).toString());
        String instructor = model.getValueAt(row, 6).toString();

        new EditCourseDialog(code, title, credits, type, max, students, instructor, this, api);
    }

    private void deleteSelectedCourse()
    {
        if (!SessionInfo.isLoggedIn())
        {
            JOptionPane.showMessageDialog(this, "You must login first!");
            return;
        }
        int row = table.getSelectedRow();
        if (row == -1)
        {
            JOptionPane.showMessageDialog(this, "Select a course first");
            return;
        }

        String code = model.getValueAt(row, 0).toString();
        int confirm = JOptionPane.showConfirmDialog(this,
                                                    "Are you sure you want to delete course: " + code + "?",
                                                    "Confirm Delete",
                                                    JOptionPane.YES_NO_OPTION);
        if (confirm != JOptionPane.YES_OPTION) { return; }
        ActionResult result = api.deleteCourse(Role.ADMIN, code);

        switch (result)
        {
            case SUCCESS ->
            {
                JOptionPane.showMessageDialog(this, "Course deleted successfully!");
                loadCourses();
            }

            case IN_USE ->
                JOptionPane.showMessageDialog(this,
                "Cannot delete this course.\nStudents are currently enrolled in it.");

            case NOT_ALLOWED ->
                JOptionPane.showMessageDialog(this, "You are not allowed to delete courses.");

            case MAINTENANCE_MODE ->
                JOptionPane.showMessageDialog(this, "System is in maintenance mode.");

            case DB_ERROR ->
                JOptionPane.showMessageDialog(this, "Database error occurred while deleting.");

            default ->
                JOptionPane.showMessageDialog(this, "Delete failed: " + result);
        }
    }
}
