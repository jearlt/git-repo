package javaproject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TransactionDAO {
    private final String url = "jdbc:sqlite:C:\\Users\\u732991\\Desktop\\JavaProject";

    public void createTransaction(Transaction transaction) {
        String sql = "INSERT INTO transactions(transactionID, accountID, transactionType, amount) VALUES(?,?,?,?)";

        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, transaction.getTransactionID());
            pstmt.setInt(2, transaction.getAccountID());
            pstmt.setString(3, transaction.getTransactionType());
            pstmt.setDouble(4, transaction.getAmount());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public Transaction getTransaction(int transactionID) {
        String sql = "SELECT transactionID, accountID, transactionType, amount FROM transactions WHERE transactionID = ?";

        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, transactionID);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                Transaction transaction = new Transaction();
                transaction.setTransactionID(rs.getInt("transactionID"));
                transaction.setAccountID(rs.getInt("accountID"));
                transaction.setTransactionType(rs.getString("transactionType"));
                transaction.setAmount(rs.getDouble("amount"));
                return transaction;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return null; // Transaction not found
    }

    public void updateTransaction(Transaction transaction) {
        String sql = "UPDATE transactions SET accountID = ?, transactionType = ?, amount = ? WHERE transactionID = ?";

        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, transaction.getAccountID());
            pstmt.setString(2, transaction.getTransactionType());
            pstmt.setDouble(3, transaction.getAmount());
            pstmt.setInt(4, transaction.getTransactionID());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void deleteTransaction(int transactionID) {
        String sql = "DELETE FROM transactions WHERE transactionID = ?";

        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, transactionID);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
