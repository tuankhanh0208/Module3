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
    List<Product> findByString(String name, String title);

    List<Categories> getAllCategories();
    List<Product> getProductByCID(String cid);

    List<Product> getProductByPriceRange(double min, double max);
    List<Product> getAllAdmin();
    List<Product> findByTest(String name, double price);
    int addCategory(Categories category);


}
