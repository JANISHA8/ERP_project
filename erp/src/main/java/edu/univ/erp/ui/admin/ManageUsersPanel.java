package edu.univ.erp.ui.admin;

import edu.univ.erp.api.admin.AdminAPI;
import edu.univ.erp.data.AdminsData;
import edu.univ.erp.data.InstructorsData;
import edu.univ.erp.data.StudentsData;
import edu.univ.erp.domain.Admin;
import edu.univ.erp.domain.Instructor;
import edu.univ.erp.domain.Student;
import edu.univ.erp.domain.ActionResult;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ManageUsersPanel extends JPanel
{
    private DefaultTableModel model;
    private JTable table;
    private JButton adminsBtn;
    private JButton instructorsBtn;
    private JButton studentsBtn;
    private JButton saveBtn;
    private JButton refreshBtn;
    private String currentMode = ""; // "ADMIN" / "INSTRUCTOR" / "STUDENT"

    public ManageUsersPanel()
    {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        JLabel title = new JLabel("Manage Users", JLabel.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 24));
        title.setBorder(BorderFactory.createEmptyBorder(20,10,20,10));
        add(title, BorderLayout.NORTH);

        JPanel topPanel = new JPanel(new FlowLayout());
        adminsBtn      = new JButton("ADMINS");
        instructorsBtn = new JButton("INSTRUCTORS");
        studentsBtn    = new JButton("STUDENTS");

        topPanel.add(adminsBtn);
        topPanel.add(instructorsBtn);
        topPanel.add(studentsBtn);
        add(topPanel, BorderLayout.BEFORE_FIRST_LINE);

        model = new DefaultTableModel()
        {
            @Override public boolean isCellEditable(int row, int column)
            { return column != 0; }
        };

        table = new JTable(model);
        table.setRowHeight(25);
        table.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        add(new JScrollPane(table), BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel();
        saveBtn    = new JButton("Save Changes");
        refreshBtn = new JButton("Refresh");

        bottomPanel.add(saveBtn);
        bottomPanel.add(refreshBtn);
        add(bottomPanel, BorderLayout.SOUTH);

        adminsBtn.addActionListener(e -> loadAdmins());
        instructorsBtn.addActionListener(e -> loadInstructors());
        studentsBtn.addActionListener(e -> loadStudents());

        refreshBtn.addActionListener(e ->
        {
            if ("ADMIN".equals(currentMode)) { loadAdmins(); }
            else if ("INSTRUCTOR".equals(currentMode)) { loadInstructors(); }
            else if ("STUDENT".equals(currentMode)) { loadStudents(); }
        });
        saveBtn.addActionListener(e -> saveChanges());
    }

    private void loadAdmins()
    {
        currentMode = "ADMIN";
        String[] cols = {"ID","Username","Email","Status","Department"};
        model.setDataVector(null, cols);

        AdminsData data = new AdminsData();
        List<Admin> list = data.getAllAdmins();
        for (Admin a : list)
        {
            model.addRow(new Object[]
            {
                a.getUserID(),
                a.getUsername(),
                a.getEmailID(),
                a.getStatus(),
                a.getDepartment()
            });
        }
    }

    private void loadInstructors()
    {
        currentMode = "INSTRUCTOR";

        String[] cols = {"ID","Username","Email","Status","Department","Contact"};
        model.setDataVector(null, cols);

        InstructorsData data = new InstructorsData();
        List<Instructor> list = data.getAllInstructors();

        for (Instructor i : list)
        {
            model.addRow(new Object[]
            {
                i.getUserID(),
                i.getUsername(),
                i.getEmailID(),
                i.getStatus(),
                i.getDepartment(),
                i.getContactno()
            });
        }
    }

    private void loadStudents()
    {
        currentMode = "STUDENT";

        String[] cols = {"ID","Username","Email","Status","Program","Branch","Year","Sem"};
        model.setDataVector(null, cols);

        StudentsData data = new StudentsData();
        List<Student> list = data.getAllStudents();

        for (Student s : list)
        {
            model.addRow(new Object[]
            {
                s.getUserID(),
                s.getUsername(),
                s.getEmailID(),
                s.getStatus(),
                s.getProgram(),
                s.getBranch(),
                s.getCurrent_year(),
                s.getCurrent_sem()
            });
        }
    }

    private void saveChanges()
    {
        try
        {
            if (table.isEditing()) { table.getCellEditor().stopCellEditing(); }
            AdminAPI api = new AdminAPI();

            ActionResult result = api.updateUsersFromTable(model, currentMode);
            switch (result)
            {
                case SUCCESS:
                    JOptionPane.showMessageDialog(this, "Changes saved successfully to MySQL!");
                    if ("ADMIN".equals(currentMode)) { loadAdmins(); }
                    else if ("INSTRUCTOR".equals(currentMode)) { loadInstructors(); }
                    else if ("STUDENT".equals(currentMode)) { loadStudents(); }
                    break;

                case MAINTENANCE_MODE:
                    JOptionPane.showMessageDialog(this, "System is in MAINTENANCE MODE.\nOnly admins can modify data." );
                    break;

                case NOT_ALLOWED:
                    JOptionPane.showMessageDialog(this, "You are not allowed to perform this action.");
                    break;

                case NOT_FOUND:
                    JOptionPane.showMessageDialog(this, "Some records not found in database.");
                    break;

                case DB_ERROR:
                    JOptionPane.showMessageDialog(this, "Database error occurred.\nCheck console.");
                    break;

                default:
                    JOptionPane.showMessageDialog(this, "Unknown error occurred.");
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }
}
