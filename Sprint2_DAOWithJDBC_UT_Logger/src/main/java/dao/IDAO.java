package dao;

import dao.jdbc.DatabaseUtility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

public interface IDAO<T> {

    final static Logger logger = LoggerFactory.getLogger(IDAO.class);

    // create
    void save (T t);

    // read / retrieve
    List<T> getAll();
    Optional<T> get (long id);


    // update
    boolean update(T t);

    //delete
    boolean delete (T t);

    boolean deleteById(long id);

    void deleteAll();

    int count();

    default void delete(String query) {

    }
}
