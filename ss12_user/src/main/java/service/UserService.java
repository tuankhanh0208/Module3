package service;

import connect.ConnectDatabase;
import entity.User;
import responsitory.IUserResponsitory;
import responsitory.UserResponsitory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserService implements IUserService<User> {
    private final IUserResponsitory<User> iUserResponsitory = new UserResponsitory();

    public UserService() throws SQLException {
    }

    @Override
    public void add(User user) {
        this.iUserResponsitory.add(user);
    }

    @Override
    public void update(int id, User user) {
        this.iUserResponsitory.update(id,user);
    }

    @Override
    public void delete(int id) {
        this.iUserResponsitory.delete(id);
    }

    @Override
    public List<User> getAll() {
        return this.iUserResponsitory.getAll();
    }

    @Override
    public User findById(int id) {
        return this.iUserResponsitory.findById(id);
    }

    @Override
    public List<User> findByCountry(String country) {
        return this.iUserResponsitory.findByCountry(country);
    }

    @Override
    public List<User> arrangeUser(String arrange) {
        return this.iUserResponsitory.arrangeUser(arrange);
    }

    @Override
    public User getUserById(int id) {
        return this.iUserResponsitory.getUserById(id);
    }

    @Override
    public void insertUser(User user) {
        this.iUserResponsitory.insertUser(user);
    }
}
