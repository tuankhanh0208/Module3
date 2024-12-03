package service;
import entity.Categories;
import entity.Product;

import java.util.List;
public interface IProductService<T>{
    void add(T t);
    List<T> getAll();
    void update (int id,T t);
    void delete(int id);
    T findById(int id);
    List<Product> findByString(String name);
    List<Categories> getAllCategories();
    List<Product> getProductByCID(String cid);

    List<Product> getProductByPriceRange(double min, double max);
}
