package edu.univ.erp.data;

import edu.univ.erp.auth.hash.AuthDB;

import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class ManageUsersData
{
    public boolean updateUsers(DefaultTableModel model, String currentMode)
    {
        boolean ok = true;

        try (Connection authConn = AuthDB.getConnection();
             Connection erpConn  = ERPDB.getConnection())
        {
            for (int i = 0; i < model.getRowCount(); i++)
            {
                int userId      = Integer.parseInt(model.getValueAt(i, 0).toString());
                String username = model.getValueAt(i, 1).toString();
                String email    = model.getValueAt(i, 2).toString();

                // UPDATE in auth_db.users_auth
                String userSql = "UPDATE auth_db.users_auth SET username=?, email_id=? WHERE user_id=?";

                try (PreparedStatement ps = authConn.prepareStatement(userSql))
                {
                    ps.setString(1, username);
                    ps.setString(2, email);
                    ps.setInt(3, userId);

                    int affected = ps.executeUpdate();
                    System.out.println("users_auth rows updated = " + affected);

                    if (affected == 0) ok = false;
                }

                // ADMIN
                if ("ADMIN".equalsIgnoreCase(currentMode))
                {
                    String dept = model.getValueAt(i,4).toString();

                    String sql = "UPDATE erp_db.admins SET department=? WHERE user_id=?";

                    try (PreparedStatement ps = erpConn.prepareStatement(sql))
                    {
                        ps.setString(1, dept);
                        ps.setInt(2, userId);

                        int affected = ps.executeUpdate();
                        System.out.println("admins rows updated = " + affected);

                        if (affected == 0) ok = false;
                    }
                }

                // INSTRUCTOR
                else if ("INSTRUCTOR".equalsIgnoreCase(currentMode))
                {
                    String dept = model.getValueAt(i,4).toString();
                    long contact = Long.parseLong(model.getValueAt(i,5).toString());

                    String sql = "UPDATE erp_db.instructors SET department=?, contact_no=? WHERE user_id=?";

                    try (PreparedStatement ps = erpConn.prepareStatement(sql))
                    {
                        ps.setString(1, dept);
                        ps.setLong(2, contact);
                        ps.setInt(3, userId);

                        int affected = ps.executeUpdate();
                        System.out.println("instructors rows updated = " + affected);

                        if (affected == 0) ok = false;
                    }
                }

                // STUDENT
                else if ("STUDENT".equalsIgnoreCase(currentMode))
                {
                    String program = model.getValueAt(i,4).toString();
                    String branch  = model.getValueAt(i,5).toString();
                    int year        = Integer.parseInt(model.getValueAt(i,6).toString());
                    int sem         = Integer.parseInt(model.getValueAt(i,7).toString());

                    String sql = "UPDATE erp_db.students SET program=?, branch=?, current_year=?, current_sem=? WHERE user_id=?";

                    try (PreparedStatement ps = erpConn.prepareStatement(sql))
                    {
                        ps.setString(1, program);
                        ps.setString(2, branch);
                        ps.setInt(3, year);
                        ps.setInt(4, sem);
                        ps.setInt(5, userId);

                        if (ps.executeUpdate() == 0) ok = false;
                    }
                }
            }
            return ok;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }
    }
}
