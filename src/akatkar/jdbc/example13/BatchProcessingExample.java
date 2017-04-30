/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package akatkar.jdbc.example13;

import akatkar.jdbc.example11.PrepareExample;
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
public class BatchProcessingExample {

    // JDBC driver name and database URL
    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost/Students";

    // Database credentials
    private static final String USER = "root";
    private static final String PASS = "root1234";

    private static void printRows(Statement statement) throws SQLException {
        System.out.println("Displaying available rows...");
        String sql = "SELECT id, first, last, age FROM Registration";
        ResultSet resultSet = statement.executeQuery(sql);

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
        System.out.println();
    }

    public static void main(String[] args) {

        try {

            // STEP 1:
            Class.forName(JDBC_DRIVER);

            // STEP 2:
            System.out.println("Connecting to database ...");
            try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASS)) {
                //STEP 3: Set auto commit as false.
                connection.setAutoCommit(false);

                //STEP 4: Execute a query to create statment with
                // required arguments for RS example.
                System.out.println("Creating statement...");
                try (Statement statement = connection.createStatement()) {

                    // First, let us select all the records and display them.
                    printRows(statement);

                    // Create SQL statement
                    String sql = "INSERT INTO Registration (id, first, last, age) "
                            + "VALUES(200,'Dave', 'Mustain', 55)";
                    // Add above SQL statement in the batch.
                    statement.addBatch(sql);

                    // Create one more SQL statement
                    sql = "INSERT INTO Registration (id, first, last, age) "
                            + "VALUES(201,'Jason', 'Newsted', 54)";
                    // Add above SQL statement in the batch.
                    statement.addBatch(sql);

                    // Create one more SQL statement
                    sql = "UPDATE Registration SET age = 35 WHERE id = 100";
                    // Add above SQL statement in the batch.
                    statement.addBatch(sql);

                    // Create an int[] to hold returned values
                    int[] count = statement.executeBatch();
                    for (int i : count) {
                        System.out.print(i + ", ");
                    }
                    System.out.println("");
                    
                    //Explicitly commit statements to apply changes
                    connection.commit();

                    // Again, let us select all the records and display them.
                    printRows(statement);
                }

            }
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(PrepareExample.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
