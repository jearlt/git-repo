package javaproject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountDAO {
	private final String url = "jdbc:sqlite:C:\\Users\\u732991\\Desktop\\JavaProject";

	public void createAccount(Account account) {
		String sql = "INSERT INTO accounts(AccountID, CustomerID, AccountType, Balance) VALUES(?,?,?,?)";

		try (Connection conn = DriverManager.getConnection(url); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, account.getAccountID());
			pstmt.setInt(2, account.getCustomerID());
			pstmt.setString(3, account.getAccountType());
			pstmt.setDouble(4, account.getBalance());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Account getAccount(int accountID) {
		String sql = "SELECT AccountID, CustomerID, AccountType, Balance FROM Accounts WHERE AccountID = ?";

		try (Connection conn = DriverManager.getConnection(url); PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setInt(1, accountID);

			ResultSet rs = pstmt.executeQuery();

			// Only expecting a single result
			if (rs.next()) {
				Account account = new Account();
				account.setAccountID(rs.getInt("AccountID"));
				account.setCustomerID(rs.getInt("CustomerID"));
				account.setAccountType(rs.getString("AccountType"));
				account.setBalance(rs.getDouble("Balance"));
				return account;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null; // Account not found
	}

	public void updateAccount(Account account) {
		String sql = "UPDATE accounts SET CustomerID = ?, AccountType = ?, Balance = ? WHERE AccountID = ?";

		try (Connection conn = DriverManager.getConnection(url); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, account.getCustomerID());
			pstmt.setString(2, account.getAccountType());
			pstmt.setDouble(3, account.getBalance());
			pstmt.setInt(4, account.getAccountID());

			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void deleteAccount(int accountID) {
		String sql = "DELETE FROM accounts WHERE AccountID = ?";

		try (Connection conn = DriverManager.getConnection(url); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, accountID);

			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
