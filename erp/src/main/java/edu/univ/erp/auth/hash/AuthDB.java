//Creates connection to our authentication database
package edu.univ.erp.auth.hash;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class AuthDB {

    private static final String URL = "jdbc:mysql://localhost:3306/authdb";
    private static final String USER = "root";     // change if needed
    private static final String PASS = "root";     // change if needed

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASS);
    }
}
