package akatkar.jdbc.example06;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author alikatkar
 */
public class UpdateRecordsExample {

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
                    String sql = "UPDATE Registration set age=30 WHERE id=101";

                    // Let us check if it returns a true Result Set or not.
                    Boolean ret = statement.execute(sql);
                    System.out.println("Return value is : " + ret.toString());

                    // Let us update age of the record with ID = 103;
                    int rows = statement.executeUpdate(sql);
                    System.out.println("Rows impacted : " + rows);

                    // Let us select all the records and display them.
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
