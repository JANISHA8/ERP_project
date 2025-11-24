//Creates connection to our ERP database — courses, students, sections, grades
package edu.univ.erp.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ERPDB
{
    private static final String URL = "jdbc:mysql://localhost:3306/erp_system";
    private static final String USER = "root";
    private static final String PASSWORD = "mysql";

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
