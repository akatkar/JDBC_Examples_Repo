/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package akatkar.jdbc.example02;

import akatkar.jdbc.example01.CreateDatabaseExample1;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author alikatkar
 */
public class DropTableExample {
    // JDBC driver name and database URL
    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost/students";

    //  Database credentials
    private static final String USER = "root";
    private static final String PASS = "root1234";

    public static void main(String[] args) {

        Connection connection = null;
        Statement statement = null;

        try {
            // Step 1: Register JDBC Driver
            Class.forName(JDBC_DRIVER);

            // Step2: Open a connection
            System.out.println("Connecting to students database...");
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Connected database successfully...");

            // Step3: execute a statement
            statement = connection.createStatement();

            String sql = "DROP TABLE REGISTRATION ";

            statement.execute(sql);

            System.out.println("Registration table deleted in students database succesfully");

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(CreateDatabaseExample1.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println(ex.getMessage());
        } finally {
            System.out.println("closing connection...");
            //Step4: close 
            try {
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException ex) {
                System.err.println(ex.getMessage());
            }
        }
    }
}
