package service;
import entity.Product;

import java.util.List;
public interface IProductService <T>{
    void add(T t);
    void delete(int id);
    void update(int id,T t);
    List<T> getAll();
    List<T> findByString(String name);
    Product findById(int id);

}
