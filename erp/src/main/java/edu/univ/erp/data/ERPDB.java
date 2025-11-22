//Creates connection to our ERP database — courses, students, sections, grades
package edu.univ.erp.data;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ERPDB {

    private static final String URL = "jdbc:mysql://localhost:3306/erpdb";
    private static final String USER = "root";     // change if needed
    private static final String PASS = "root";     // change if needed

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASS);
    }
}