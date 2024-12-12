package repository;

import configuration.ConnectDatabase;
import entity.Account;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountRepository implements IAccountRepository<Account> {
    private final Connection connection;
    public AccountRepository() throws SQLException {
        this.connection = ConnectDatabase.getConnection();
    }
    @Override
    public Account login(String user, String pass){
        String sql = "SELECT * FROM account WHERE `user` = ? AND pass = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, user);
            preparedStatement.setString(2, pass);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return new Account(
                            resultSet.getInt("uID"),
                            resultSet.getString("user"),
                            resultSet.getString("pass"),
                            resultSet.getString("role")


                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error while executing login query.", e);
        }

        return null;
    }
    @Override
    public Account checkAccountExist (String user){
        String sql = "SELECT * FROM account WHERE `user` = ? ";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, user);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return new Account(
                            resultSet.getInt("uID"),
                            resultSet.getString("user"),
                            resultSet.getString("pass"),
                            resultSet.getString("role")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error while executing login query.", e);
        }

        return null;
    }
    @Override
    public void signup(String user,String pass){
        String sql = "insert into account (user, pass,role) values (?,?,'user'); ";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,user);
            preparedStatement.setString(2,pass);
//            preparedStatement.setString();
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public boolean resetPassword(String user, String newPass) {
        String sql = "UPDATE account SET pass = ? WHERE user = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, newPass);
            preparedStatement.setString(2, user);
            int rowsUpdate = preparedStatement.executeUpdate();
            System.out.println("Rows updated: " + rowsUpdate);
            return rowsUpdate > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
