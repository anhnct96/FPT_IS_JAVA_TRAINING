package dao.inMemoryDao;

import java.util.List;

public interface iInMemoryDao<T> {

    T insertOrUpdate(T t);
    List<T> findAll();
    void deleteById(Long id);
}
