package service;

import configuration.ConnectDatabase;
import entity.Account;
import entity.Categories;
import entity.Product;
import repository.IProductRepository;
import repository.ProductRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductService implements IProductService<Product> {

private final IProductRepository<Product> iProductRepository = new ProductRepository();

    public ProductService() throws SQLException {
    }

    @Override
    public void add(Product product) {
        this.iProductRepository.add(product);
    }

    @Override
    public List<Product> getAll() {
        return this.iProductRepository.getAll();
    }

    @Override
    public void update(int id, Product product) {
        this.iProductRepository.update(id,product);
    }

    @Override
    public void delete(int id) {
        this.iProductRepository.delete(id);
    }

    @Override
    public Product findById(int id) {
        return this.iProductRepository.findById(id);
    }

    @Override
    public List<Product> findByString(String name) {
        return this.iProductRepository.findByString(name);
    }

    @Override
    public List<Categories> getAllCategories() {
        return this.iProductRepository.getAllCategories();
    }

    @Override
    public List<Product> getProductByCID(String cid) {
        return this.iProductRepository.getProductByCID(cid);
    }

    @Override
    public List<Product> getProductByPriceRange(double min, double max) {
        return this.iProductRepository.getProductByPriceRange(min,max);
    }
}
