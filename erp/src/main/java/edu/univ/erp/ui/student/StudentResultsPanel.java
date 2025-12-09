package edu.univ.erp.ui.student;

import edu.univ.erp.auth.session.SessionInfo;
import edu.univ.erp.data.GradesData;
import edu.univ.erp.domain.Grade;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class StudentResultsPanel extends JPanel
{
    public StudentResultsPanel()
    {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        JLabel title = new JLabel("My Results", JLabel.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 24));
        title.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));
        add(title, BorderLayout.NORTH);

        String[] cols = {"Course Code", "Component", "Score", "Final Grade"};
        DefaultTableModel model = new DefaultTableModel(cols, 0)
        {
            @Override public boolean isCellEditable(int row, int column) { return false; }
        };

        JTable table = new JTable(model);
        table.setRowHeight(25);
        table.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        if(!SessionInfo.isLoggedIn())
        {
            JOptionPane.showMessageDialog(this, "No active session. Please login again.");
            add(new JScrollPane(table), BorderLayout.CENTER);
            return;
        }

        int enrollmentId = SessionInfo.getUserID();
        GradesData gd = new GradesData();
        List<Grade> grades = gd.getGradesByEnrollmentId(enrollmentId);

        if (grades.isEmpty())
        { JOptionPane.showMessageDialog(this, "No grades found for you yet."); }
        else
        {
            for (Grade g : grades)
            {
                model.addRow(new Object[]{
                        g.getCourseCode(),
                        g.getComponent(),
                        g.getScore(),
                        g.getFinalGrade()
                });
            }
        }

        add(new JScrollPane(table), BorderLayout.CENTER);
    }
}
