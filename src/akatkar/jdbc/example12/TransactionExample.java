/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package akatkar.jdbc.example12;

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
public class TransactionExample {

    // JDBC driver name and database URL
    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost/Students";

    // Database credentials
    private static final String USER = "root";
    private static final String PASS = "root1234";

    private static void printResultSet(ResultSet resultSet) throws SQLException {
        //Ensure we start with first row
        resultSet.beforeFirst();
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
                try (Statement statement = connection.createStatement(
                        ResultSet.TYPE_SCROLL_INSENSITIVE,
                        ResultSet.CONCUR_UPDATABLE)) {
                    
                    //STEP 5: INSERT a row into Registration table
                    System.out.println("Inserting one row....");
                    String SQL = "INSERT INTO Registration "
                            + "VALUES (106, 'Robert', 'Trujillo', 52)";
                    statement.executeUpdate(SQL);

                    //STEP 6: INSERT one more row into Registration table
                    SQL = "INSERT INTO Registration "
                            + "VALUES (107, 'Cliff', 'Burton', 24)";
                    statement.executeUpdate(SQL);

                    //STEP 7: Commit data here.
                    System.out.println("Commiting data here....");
                    connection.commit();

                    //STEP 8: Now list all the available records.
                    String sql = "SELECT id, first, last, age FROM Registration";
                    ResultSet rs = statement.executeQuery(sql);
                    
                    System.out.println("List result set for reference....");
                    printResultSet(rs);

                }

            }
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(PrepareExample.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
