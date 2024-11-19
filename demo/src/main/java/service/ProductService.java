package service;

import configuration.ConnectDatabase;
import entity.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductService implements IService<Product>{
    private Connection connection;
   private List<Product> productList = new ArrayList<>();
   public ProductService () throws SQLException {
       this.connection= ConnectDatabase.getConnection();
   }
    @Override
    public void add(Product product) {

       String sql = "insert into product (name,price,img) values(?,?,?);";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
//            preparedStatement.setInt(1,product.getId());
            preparedStatement.setString(1,product.getName());
            preparedStatement.setDouble(2,product.getPrice());
            preparedStatement.setString(3,product.getImg());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public List<Product> getAll() {
        List<Product> products = new ArrayList<>();
       String sql = "select *from product;";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet= preparedStatement.executeQuery();
            while (resultSet.next()){
                Product product = new Product(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getDouble("price"),
                        resultSet.getString("img")
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
       String sql = "update product set name =? ,price =? , img =? where id =?";
        try {
            PreparedStatement preparedStatement= connection.prepareStatement(sql);
            preparedStatement.setString(1,product.getName());
            preparedStatement.setDouble(2,product.getPrice());
            preparedStatement.setString(3,product.getImg());
            preparedStatement.setInt(4,id);
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
        String sql = "select * from product where id =?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                return new Product(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getDouble("price"),
                        resultSet.getString("img")
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
    public List<Product> findByString (String name){
       List<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM product WHERE LOWER(name) LIKE LOWER(?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, "%" + name + "%");
            System.out.println("Executing query: " + preparedStatement.toString());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Product product = new Product(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getDouble("price"),
                        resultSet.getString("img")
                );
                products.add(product);
                System.out.println("Found product: " + product.getName());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return products;
    }

}
