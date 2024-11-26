package service;

import connect.ConnectDatabase;
import entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserService implements IUserService<User> {
    private Connection connection;
    private List<User> userList = new ArrayList<>();

    public UserService() throws SQLException {
        this.connection = ConnectDatabase.getConnection();
    }

    @Override
    public void add(User user) {
        String sql = "insert into users (name,email,country) value (?,?,?);";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getCountry());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(int id, User user) {
        String sql = "update users set name =?,email=?,country=? where id =?;";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getCountry());
            preparedStatement.setInt(4, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(int id) {
        String sql = "delete from users where id=?;";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public List<User> getAll() {
        List<User> users = new ArrayList<>();
        String sql = "select *from users";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                User user = new User(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("email"),
                        resultSet.getString("country")
                );
                users.add(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return users;
    }

    @Override
    public User findById(int id) {
        String sql = "select * from users where id=?;";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                return new User(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("email"),
                        resultSet.getString("country")
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public List<User> findByCountry(String country) {
        List<User> users = new ArrayList<>();
        String sql = "select * from users where lower(country) like lower(?);";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, "%" + country + "%");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                User user = new User(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("email"),
                        resultSet.getString("country")
                );
                users.add(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return users;
    }

    @Override
    public List<User> arrangeUser(String arrange) {
        List<User> users = new ArrayList<>();
        String sql = "select * from users order by name " + (arrange != null & arrange.equalsIgnoreCase("desc") ? "desc" : "asc");
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                User user = new User(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("email"),
                        resultSet.getString("country")
                );
                users.add(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return users;
    }

    @Override
    public User getUserById(int id) {
        User user = null;
        String sql = "call getUserById(?);";

        try {
            CallableStatement callableStatement = connection.prepareCall(sql);
            callableStatement.setInt(1, id);
            ResultSet resultSet = callableStatement.executeQuery();
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                String country = resultSet.getString("country");
                user = new User(id, name, email, country);
            }
        } catch (SQLException e) {
            System.err.println("SQL Error: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return user;
    }
//        return user;
//        try (CallableStatement callableStatement = connection.prepareCall(sql)) {
//            callableStatement.setInt(1, id); // Truyền tham số
//            try (ResultSet resultSet = callableStatement.executeQuery()) {
//                if (resultSet.next()) {
//                    String name = resultSet.getString("name");
//                    String email = resultSet.getString("email");
//                    String country = resultSet.getString("country");
//                    user = new User(id, name, email, country); // Tạo đối tượng User
//                }
//            }
//        } catch (SQLException e) {
//            System.err.println("SQL Error: " + e.getMessage());
//            e.printStackTrace();
//            throw new RuntimeException("Error retrieving user by ID", e);
//        }
//        return user;


    @Override
    public void insertUser(User user){
        String sql = "call insertUsers(?,?,?);";
        try {
            CallableStatement callableStatement = connection.prepareCall(sql);
            callableStatement.setString(1,user.getName());
            callableStatement.setString(2,user.getEmail());
            callableStatement.setString(3,user.getCountry());
            System.out.println(callableStatement);
            callableStatement.executeQuery();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
