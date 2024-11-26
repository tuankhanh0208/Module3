package service;

import entity.Product;
import responsitory.IProductResponsitory;
import responsitory.ProductResponsitory;

import java.util.List;

public class ProductService implements IProductService<Product> {
    private final IProductResponsitory<Product> iProductResponsitory = new ProductResponsitory();

    @Override
    public List<Product> getAll() {
        return this.iProductResponsitory.getAll();
    }

    @Override
    public void add(Product product) {
        this.iProductResponsitory.add(product);
    }

    @Override
    public void delete(int id) {
        this.iProductResponsitory.delete(id);
    }

    @Override
    public Product findById(int id) {
        return this.iProductResponsitory.findById(id);
    }

    @Override
    public void update(int id, Product product) {
        this.iProductResponsitory.update(id,product);
    }

    @Override
    public List<Product> findByString(String keyword) {
        return this.iProductResponsitory.findByString(keyword);
    }
}
