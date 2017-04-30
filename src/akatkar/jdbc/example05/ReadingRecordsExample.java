package akatkar.jdbc.example05;

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
public class ReadingRecordsExample {

    // JDBC driver name and database URL
    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost/students";

    // Database credentials
    private static final String USER = "root";
    private static final String PASS = "root1234";

    public static void main(String[] args) {

        try {

            // STEP 1: Register JDBC Driver
            Class.forName(JDBC_DRIVER);

            // STEP 2: Open a Connection
            System.out.println("Connecting to database ...");
            try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASS)) {

                // STEP 3: Create a statement
                System.out.println("Creating a statement ...");
                try (Statement statement = connection.createStatement()) {
                    
                    String sql = "SELECT * from REGISTRATION";                    
                    // STEP 4: Execute a query
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
            Logger.getLogger(ReadingRecordsExample.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
