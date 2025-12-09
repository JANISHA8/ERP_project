package edu.univ.erp.ui.student;

import edu.univ.erp.api.student.StudentAPI;
import edu.univ.erp.domain.TimeTable;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class StudentTimetablePanel extends JPanel
{
    public StudentTimetablePanel()
    {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        JLabel title = new JLabel("MY TIMETABLE", JLabel.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 22));
        title.setBorder(BorderFactory.createEmptyBorder(15,10,15,10));
        add(title, BorderLayout.NORTH);

        String[] cols = {"Course", "Day", "Start", "End", "Room"};
        DefaultTableModel model = new DefaultTableModel(cols,0);

        JTable table = new JTable(model);
        table.setRowHeight(25);

        StudentAPI api = new StudentAPI();
        List<TimeTable> list = api.getMyTimetable();

        for (TimeTable t : list)
        {
            model.addRow(new Object[]{
                    t.getCourseCode(),
                    t.getDay(),
                    t.getStartTime(),
                    t.getEndTime(),
                    t.getRoom()
            });
        }

        if (list.isEmpty())
        {
            model.addRow(new Object[]{"-", "No classes scheduled", "-", "-", "-"});
        }

        add(new JScrollPane(table), BorderLayout.CENTER);
    }
}
