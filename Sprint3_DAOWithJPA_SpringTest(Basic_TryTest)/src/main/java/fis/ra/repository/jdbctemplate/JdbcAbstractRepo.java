package fis.ra.repository.jdbctemplate;

import fis.ra.core.NotImplementedException;
import fis.ra.model.AbstractEntity;
import fis.ra.repository.AbstractRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Optional;

public class JdbcAbstractRepo<T extends AbstractEntity> implements AbstractRepo<T> {

    @Autowired
    protected JdbcTemplate jdbcTemplate;

    public JdbcAbstractRepo(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void save(T entity) {
        throw new NotImplementedException("Not needed for this implementation.");
        //NOOP
    }

    @Override
    public T update(T entity) {
        //NOOP
        throw new NotImplementedException("Not needed for this implementation.");
        //return null;
    }


    @Override
    public void delete(T entity) {
        //NOOP
        throw new NotImplementedException("Not needed for this implementation.");
    }

    @Override
    public int deleteById(Long entityId) {
        //NOOP
        throw new NotImplementedException("Not needed for this implementation.");
        //return 0;
    }

    @Override
    public Optional<T> findById(Long entityId) {
        //NOOP
        throw new NotImplementedException("Not needed for this implementation.");
        //return null;
    }
}
