package repository;

import configuration.ConnectDatabase;
import entity.Categories;
import entity.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductRepository implements IProductRepository<Product> {
    private final Connection connection;

    public ProductRepository() throws SQLException {
        this.connection = ConnectDatabase.getConnection();
    }

    @Override
    public void add(Product product) {
        String sql = "insert into product (name,description,price,title,image,idCategory) values (?,?,?,?,?,?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, product.getName());
            preparedStatement.setString(2, product.getDescription());
            preparedStatement.setDouble(3, product.getPrice());
            preparedStatement.setString(4, product.getTitle());
            preparedStatement.setString(5, product.getImage());
            preparedStatement.setInt(6, product.getIdCategory().getCid());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Product> getProductByCID(String cid) {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT p.id, p.name, p.description, p.title, p.price, p.image, c.cid AS categoryId, c.name AS categoryName " +
                "FROM product p " +
                "JOIN categories c ON p.idCategory = c.cid " +
                "WHERE p.idCategory = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, cid);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Categories categories = new Categories(
                        resultSet.getInt("categoryId"),
                        resultSet.getString("categoryName")
                );
                Product product = new Product(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("description"),
                        resultSet.getString("title"),
                        resultSet.getDouble("price"),
                        resultSet.getString("image"),
                        categories
                );
//                             resultSet.getInt("idCategory")

                products.add(product);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return products;
    }

    @Override
    public List<Categories> getAllCategories() {
        List<Categories> categories = new ArrayList<>();
        String sql = "select * from categories;";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Categories categories1 = new Categories(
                        resultSet.getInt("cid"),
                        resultSet.getString("name")
                );
                categories.add(categories1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return categories;
    }

    @Override
    public List<Product> getAll() {
        List<Product> products = new ArrayList<>();
        String sql = "select * from product";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Categories categories = new Categories(
                        resultSet.getInt("id"),
                        resultSet.getString("name")
                        );
                Product product = new Product(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("description"),
                        resultSet.getString("title"),
                        resultSet.getDouble("price"),
                        resultSet.getString("image"),
//                        resultSet.getInt("idCategory")
                        categories
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
        String sql = "update product set name=? ,description=? ,title=? ,price=? ,image=? ,idCategory=? where id=? ;";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, product.getName());
            preparedStatement.setString(2, product.getDescription());
            preparedStatement.setString(3, product.getTitle());
            preparedStatement.setDouble(4, product.getPrice());
            preparedStatement.setString(5, product.getImage());
            preparedStatement.setInt(6, product.getIdCategory().getCid());
            preparedStatement.setInt(7, id);
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
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Product findById(int id) {
        String sql = "SELECT p.*, c.cid AS categoryId, c.name AS categoryName " +
                "FROM product p " +
                "JOIN categories c ON p.idCategory = c.cid " +
                "WHERE p.id = ?";;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Categories categories = new Categories(
                        resultSet.getInt("categoryId"),
                        resultSet.getString("categoryName")
                        );
                return new Product(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("description"),
                        resultSet.getString("title"),
                        resultSet.getDouble("price"),
                        resultSet.getString("image"),
//                        resultSet.getInt("idCategory")
                        categories
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public List<Product> findByString(String name, String title) {
        List<Product> products = new ArrayList<>();
        String sql = "select p.*,c.cid as categoryId  from product p join categories c on p.idCategory=c.cid where lower(p.name) like lower(?) or lower(p.title) like lower(?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, "%" + name + "%");
            preparedStatement.setString(2, "%" + title + "%");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Categories categories = new Categories(resultSet.getInt("categoryId"));

                Product product = new Product(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("description"),
                        resultSet.getString("title"),
                        resultSet.getDouble("price"),
                        resultSet.getString("image"),
                        categories
//                        resultSet.getInt("idCategory")
                );
                products.add(product);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return products;
    }
    @Override
    public List<Product> findByTest(String name, double price) {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT p.*, c.cid AS categoryId " +
                "FROM product p " +
                "JOIN categories c ON p.idCategory = c.cid " +
                "WHERE LOWER(p.name) LIKE LOWER(?) OR p.price <= ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, "%" + name + "%");
            preparedStatement.setDouble(2, price);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Categories categories = new Categories(resultSet.getInt("categoryId"));

                Product product = new Product(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("description"),
                        resultSet.getString("title"),
                        resultSet.getDouble("price"),
                        resultSet.getString("image"),
                        categories
//                        resultSet.getInt("idCategory")
                );
                products.add(product);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return products;
    }

    @Override
    public List<Product> getProductByPriceRange(double min, double max) {
        List<Product> products = new ArrayList<>();
        String sql = "select * from product where price between ? and ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setDouble(1, min);
            preparedStatement.setDouble(2, max);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Categories categories = new Categories(resultSet.getInt("id"));
                Product product = new Product(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("description"),
                        resultSet.getString("title"),
                        resultSet.getDouble("price"),
                        resultSet.getString("image"),
                        categories
//                        resultSet.getInt("idCategory")
                );
                products.add(product);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return products;
    }

    @Override
    public List<Product> getAllAdmin() {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT p.*, c.cid, c.name AS categoryName " +
        "FROM product p " +
                "JOIN categories c ON p.idCategory = c.cid";
        ;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Categories categories = new Categories(
                        resultSet.getInt("cid"),
                        resultSet.getString("categoryName")
                );
                Product product = new Product(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("description"),
                        resultSet.getString("title"),
                        resultSet.getDouble("price"),
                        resultSet.getString("image"),
//                        resultSet.getInt("idCategory")
                        categories
                );
                products.add(product);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return products;
    }
    @Override
    public int addCategory(Categories category) {
        String sql = "INSERT INTO categories (name) VALUES (?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, category.getName());

            preparedStatement.executeUpdate();

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                return generatedKeys.getInt(1); // Trả về ID
            }
        } catch (SQLException e) {
            throw new RuntimeException("Lỗi khi thêm danh mục", e);
        }
        return -1;
    }

}
