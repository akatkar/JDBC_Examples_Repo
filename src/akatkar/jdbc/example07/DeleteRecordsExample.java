/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package akatkar.jdbc.example07;

import akatkar.jdbc.example06.UpdateRecordsExample;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author alikatkar
 */
public class DeleteRecordsExample {

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
                // STEP 3: Create a statement
                System.out.println("Creating a statement ...");
                try (Statement statement = connection.createStatement()) {
                    
                    // STEP 4: Delete record
                    System.out.println("Creating statement...");
                    String sql = "DELETE FROM Registration WHERE id = 103";
                    statement.executeUpdate(sql);

                    // Let's see remaining records.
                    sql = "SELECT id, first, last, age FROM Registration";
                    try (ResultSet resultSet = statement.executeQuery(sql)) {
                        // STEP 5: Extract data from result set
                        while (resultSet.next()) {
                            //Retrieve by column name
                            int id = resultSet.getInt("id");
                            int age = resultSet.getInt("age");
                            String first = resultSet.getString("first");
                            String last = resultSet.getString("last");

                            //Display values
                            System.out.print("ID: " + id);
                            System.out.print(", Age: " + age);
                            System.out.print(", First: " + first);
                            System.out.println(", Last: " + last);
                        }
                    }
                }
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(UpdateRecordsExample.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
