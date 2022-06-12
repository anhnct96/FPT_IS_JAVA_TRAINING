package dao;

import java.util.List;
import java.util.Optional;

public interface IDAO<T> {
    // create
    void save (T t);

    // read / retrieve
    Optional<T> get (long id);
    List<T> getAll();

    // update
    boolean update(T t);

    //delete
    boolean delete (T t);

    boolean deleteById(long id);
}
