/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package akatkar.jdbc.example11;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author alikatkar
 */
public class CallableExample {

    // JDBC driver name and database URL
    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost/Students";

    // Database credentials
    private static final String USER = "root";
    private static final String PASS = "root1234";

    public static void main(String[] args) {

        try {

            // STEP 1:
            Class.forName(JDBC_DRIVER);

            // STEP 2:
            System.out.println("Connecting to database ...");
            try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASS)) {
                //STEP 3: Execute a query
                System.out.println("Creating statement...");
                String sql = "{call getStudentName (?, ?)}";
                try (CallableStatement cs = connection.prepareCall(sql)) {

                    //Bind IN parameter first, then bind OUT parameter
                    int studentID = 102;
                    cs.setInt(1, studentID); // This would set ID as 102
                    
                    // Because second parameter is OUT so register it
                    cs.registerOutParameter(2, java.sql.Types.VARCHAR);

                    //Use execute method to run stored procedure.
                    System.out.println("Executing stored procedure...");
                    cs.execute();

                    //Retrieve employee name with getXXX method
                    String name = cs.getString(2);
                    System.out.println("Student name with ID:" + studentID + " is " + name);
                }
            }
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(PrepareExample.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
