package fis.ra.repository.jdbctemplate.mapper;

import fis.ra.model.Detective;
import fis.ra.model.Storage;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StorageRowMapper implements RowMapper<Storage> {
    @Override
    public Storage mapRow(ResultSet rs, int rowNum) throws SQLException {
        Storage storage = new Storage();
        storage.setId(rs.getLong("storage_id"));
        storage.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
        storage.setModifiedAt(rs.getTimestamp("modified_at").toLocalDateTime());
        storage.setVersion(rs.getInt("version"));

        storage.setName(rs.getString("name"));
        storage.setLocation(rs.getString("location"));

        return storage;
    }
}
