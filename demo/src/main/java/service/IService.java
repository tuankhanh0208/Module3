package service;
import java.util.List;
public interface IService <E>{
    void add(E e);
    List<E> getAll();
    void update(int id,E e);
    void delete(int id);
    E findById(int id);
}
