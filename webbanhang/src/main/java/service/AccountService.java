package service;

import configuration.ConnectDatabase;
import entity.Account;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountService implements IAccountService<Account>{
    private Connection connection;
    public AccountService() throws SQLException {
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
                            resultSet.getString("pass")


                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error while executing login query.", e);
        }

        return null;
    }
    public Account checkAccountExist (String user){
        String sql = "SELECT * FROM account WHERE `user` = ? ";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, user);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return new Account(
                            resultSet.getInt("uID"),
                            resultSet.getString("user"),
                            resultSet.getString("pass")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error while executing login query.", e);
        }

        return null;
    }
    public void signup(String user,String pass){
        String sql = "insert into account (user, pass) values (?,?); ";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,user);
            preparedStatement.setString(2,pass);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
