package DAO;

import Model.Account;
import Util.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public class AccountDAO {
    public List<Account> getAllAccounts() {
        Connection connection = ConnectionUtil.getConnection();
        List<Account> accounts = new ArrayList<>();
        try {
            String sql = "SELECT * FROM Account;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Account account = new Account(rs.getInt("account_id"), rs.getString("username"), rs.getString("password"));
                accounts.add(account);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return accounts;
    }

    public Account getAccountById(int account_id) {
        Connection connection = ConnectionUtil.getConnection();
        try {
            String sql = "SELECT * FROM Account WHERE account_id = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, account_id);
            ResultSet rs = preparedStatement.executeQuery(sql);
            while (rs.next()) {
                Account account = new Account(rs.getInt("account_id"), rs.getString("username"), rs.getString("password"));
                return account;
            } 
        } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
            return null;
    }

    // FUNCTION COMPLETED: REGISTERING AM ACCOUNT
    public Account createAccount(Account account) {
        Connection connection = ConnectionUtil.getConnection();
        try {
            String sql = "INSERT INTO Account(username, password) VALUES(?, ?);";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, account.getUsername());
            preparedStatement.setString(2, account.getPassword());

            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            if (rs.next()) {
                int generated_account_id = (int) rs.getLong(1);
                return new Account(generated_account_id, account.getUsername(), account.getPassword());
            } 
        } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
            return null;
    }

    public void updateAccount(int id, Account account) {
        Connection connection = ConnectionUtil.getConnection();
        try {
            String sql = "UPDATE Account SET username = ?, password = ? WHERE account_id = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

        }
    }



}
