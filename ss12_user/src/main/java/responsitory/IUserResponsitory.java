package responsitory;

import entity.User;

import java.util.List;

public interface IUserResponsitory <E>{
    void add(User user);

    void update(int id, User user);

    void delete(int id);

    List<User> getAll();

    User findById(int id);

    List<User> findByCountry(String country);

    List<User> arrangeUser(String arrange);

    User getUserById(int id);

    void insertUser(User user);
}
