package service;
import entity.User;

import java.util.List;
public interface IUserService <T>{
    void add (T t);
    void update(int id,T t);
    void delete(int id);
    List<T> getAll();
    T findById(int id);
    List<T> findByCountry(String country);

    List<User> arrangeUser(String arrange);

    User getUserById(int id);

    void insertUser(User user);
}
