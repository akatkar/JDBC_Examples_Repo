/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package akatkar.jdbc.example08;

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
public class WhereClauseExample {

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

                    // STEP 4: Extract records without any condition.
                    System.out.println("Fetching records without condition...");
                    String sql = "SELECT id, first, last, age FROM Registration";
                    try (ResultSet resultSet = statement.executeQuery(sql)) {

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
                    System.out.println("");

                    // STEP 5: Extract records having ID equal or greater than 101
                    System.out.println("Fetching records with condition...");
                    sql = "SELECT id, first, last, age FROM Registration"
                            + " WHERE id >= 101 ";

                    try (ResultSet resultSet = statement.executeQuery(sql)) {
                        
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
