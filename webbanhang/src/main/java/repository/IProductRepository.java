package repository;

import entity.Categories;
import entity.Product;

import java.util.List;

public interface IProductRepository <T>{
    void add(T t);

    List<Product> getProductByCID(String cid);

    List<Categories> getAllCategories();

    List<T> getAll();
    void update (int id,T t);
    void delete(int id);
    T findById(int id);

    List<Product> findByString(String name);

    List<T> getProductByPriceRange(double min, double max);
}
