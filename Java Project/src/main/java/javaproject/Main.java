package javaproject;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
    	final String url = "jdbc:sqlite:C:\\Users\\u732991\\Desktop\\JavaProject";
            String sqlCustomers = "SELECT * FROM Customers";

            try (Connection conn = DriverManager.getConnection(url);
                 Statement stmt  = conn.createStatement();
                 ResultSet rsCustomers    = stmt.executeQuery(sqlCustomers);) {

                // loop through the result set
                while (rsCustomers.next()) {
                    System.out.println(rsCustomers.getInt("CustomerID") +  "\t" + 
                                       rsCustomers.getString("FirstName") + "\t" +
                                       rsCustomers.getString("LastName") + "\t" +
                                       rsCustomers.getString("PhoneNumber") + "\t" +
                                       rsCustomers.getString("Email") + "\t" +
                                       rsCustomers.getDouble("Balance"));
                }  
                }
            catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
}
