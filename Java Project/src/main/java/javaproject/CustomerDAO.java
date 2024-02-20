package javaproject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerDAO {

	private final String url = "jdbc:sqlite:C:\\Users\\u732991\\Desktop\\JavaProject";

	public void createCustomer(Customer customer) {
		String sql = "INSERT INTO Customers(FirstName, LastName, PhoneNumber, Email, Balance) VALUES(?,?,?,?,?)";

		try (Connection conn = DriverManager.getConnection(url); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, customer.getFirstName());
			pstmt.setString(2, customer.getLastName());
			pstmt.setString(3, customer.getPhoneNumber());
			pstmt.setString(4, customer.getEmail());
			pstmt.setDouble(5, customer.getBalance());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Customer getCustomer(int customerID) {
		String sql = "SELECT * FROM Customers WHERE CustomerID = ?";

		try (Connection conn = DriverManager.getConnection(url); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, customerID);

			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				Customer customer = new Customer();
				customer.setCustomerID(rs.getInt("CustomerID"));
				customer.setFirstName(rs.getString("FirstName"));
				customer.setLastName(rs.getString("LastName"));
				customer.setPhoneNumber(rs.getString("PhoneNumber"));
				customer.setEmail(rs.getString("Email"));
				customer.setBalance(rs.getDouble("Balance"));

				return customer;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	public void updateCustomer(Customer customer) {
		String sql = "UPDATE Customers SET FirstName = ?, LastName = ?, PhoneNumber = ?, Email = ?, Balance = ? WHERE CustomerID = ?";

		try (Connection conn = DriverManager.getConnection(url); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, customer.getFirstName());
			pstmt.setString(2, customer.getLastName());
			pstmt.setString(3, customer.getPhoneNumber());
			pstmt.setString(4, customer.getEmail());
			pstmt.setDouble(5, customer.getBalance());
			pstmt.setInt(6, customer.getCustomerID());

			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public void deleteCustomer(int customerID) {
		String sql = "DELETE FROM Customers WHERE CustomerID = ?";

		try (Connection conn = DriverManager.getConnection(url); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, customerID);

			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
}
