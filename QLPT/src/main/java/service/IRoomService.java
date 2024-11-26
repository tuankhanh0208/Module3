package service;

import entity.Room;

import java.util.List;

public interface IRoomService <E> {
    void add(E e);
    List<E> getAll();
    void update(String id,E e);
    void delete(String id);;

    E findById(String id);

    List<Room> findBySearch(String name, String contact, String id);
}
