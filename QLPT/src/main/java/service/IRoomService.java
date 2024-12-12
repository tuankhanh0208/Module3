package service;

import entity.Room;

import java.text.ParseException;
import java.util.List;

public interface IRoomService <E> {
    void add(E e);
    List<E> getAll();
    void update(String id,E e) throws ParseException;
    void delete(String id);;

    E findById(String id);

    List<Room> findBySearch(String name, String contact, String id);
}
