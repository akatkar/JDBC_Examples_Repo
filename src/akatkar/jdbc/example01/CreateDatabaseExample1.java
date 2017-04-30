/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package akatkar.jdbc.example01;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author alikatkar
 */
public class CreateDatabaseExample1 {
    // JDBC driver name and database URL
    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost";

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
            System.out.println("Connecting to database...");
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
            
            // Step3: execute a statement
            statement = connection.createStatement();
            
            String Sql = "CREATE DATABASE STUDENTS";
            statement.execute(Sql);
            
            System.out.println("Database created succesfully");
                        
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(CreateDatabaseExample1.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println(ex.getMessage());
        } finally {
            System.out.println("closing connection...");
            //Step4: close 
            try{
                if(statement!= null){
                    statement.close();
                }
                if(connection != null){
                    connection.close();
                }
            }catch(SQLException ex){ 
                System.err.println(ex.getMessage());
            }  
        }      
    }   
}
