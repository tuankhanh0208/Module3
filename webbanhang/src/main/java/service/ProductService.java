package service;

import configuration.ConnectDatabase;
import entity.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductService implements IProductService<Product> {
    private Connection connection;
    public ProductService() throws SQLException {
        this.connection= ConnectDatabase.getConnection();
    }
    @Override
    public void add(Product product) {

    }

    @Override
    public List<Product> getAll() {
        List<Product> products = new ArrayList<>();
        String sql = "select *from product;";
        try {
            PreparedStatement preparedStatement =connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Product product = new Product(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("description"),
                        resultSet.getString("title"),
                        resultSet.getDouble("price"),
                        resultSet.getString("image")
                );
                products.add(product);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return products;
    }

    @Override
    public void update(int id, Product product) {
        String sql = "update product set name=? ,description=? ,title=? ,price=? ,image=? where id=? ;";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,product.getName());
            preparedStatement.setString(2,product.getDescription());
            preparedStatement.setString(3,product.getTitle());
            preparedStatement.setDouble(4,product.getPrice());
            preparedStatement.setString(5,product.getImage());
            preparedStatement.setInt(6,id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(int id) {
        String sql = "delete from product where id=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Product findById(int id) {
        String sql = "select * from product where id=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                return new Product(
                    resultSet.getInt("id"),
                    resultSet.getString("name"),
                    resultSet.getString("description"),
                    resultSet.getString("title"),
                    resultSet.getDouble("price"),
                    resultSet.getString("image")
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public List<Product> findByString (String name){
        List<Product> products = new ArrayList<>();
        String sql ="select * from product where lower(name) like lower(?)";
        try {
            PreparedStatement preparedStatement =connection.prepareStatement(sql);
            preparedStatement.setString(1,"%" +name +"%");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Product product = new Product(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("description"),
                        resultSet.getString("title"),
                        resultSet.getDouble("price"),
                        resultSet.getString("image")
                );
                products.add(product);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return products;
    }
}
