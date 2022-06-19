package dao.jdbc;

import dao.IDAO;
import dao.IStorageDAO;
import dao.jdbc.query.DatabaseMapper;
import dao.jdbc.query.DatabaseWriter;
import model.Evidence;
import model.Storage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JDBCStorage implements IStorageDAO {
    private final static Logger logger = LoggerFactory.getLogger(JDBCStorage.class);

    public static Optional<Storage> findById(long id) {
        Storage storage = null;

        try (Connection con = DatabaseUtility.getConnection()){
            PreparedStatement stmt = con.prepareStatement("SELECT * FROM Storage WHERE id = ?");
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                storage = DatabaseMapper.getStorage(rs);
            }
            Optional<Storage> optionalStorage = Optional.of(storage);
            if (optionalStorage.isPresent()) {
                return Optional.of(optionalStorage.get());
            }
        } catch (Exception ex) {
            logger.error(ex.toString());
        }
        return Optional.empty();
    }

    @Override
    public void save(Storage storage) {
        Connection con;
        PreparedStatement stmt;
        try {
            con = DatabaseUtility.getConnection();
            stmt = con.prepareStatement("INSERT INTO Storage VALUES (?,?,?,?,?,?)");
            DatabaseWriter.setStorage(storage, stmt);
            if (stmt.executeUpdate() > 0) {
                logger.info("Added a new storage successfully");
            } else {
                logger.info("Fail to add a new storage");
            }
        } catch (Exception e) {
            logger.error(e.toString());
        }
    }

    @Override
    public Optional<Storage> get(long id) {
        return getAll().stream()
                .filter(storage -> storage.getId() == id)
                .findFirst();
    }

    @Override
    public List<Storage> getAll() {
        List<Storage> storages = new ArrayList<>();
        try (Connection con = DatabaseUtility.getConnection();
             PreparedStatement stmt = con.prepareStatement("SELECT * FROM Storage");
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Storage storage = DatabaseMapper.getStorage(rs);

                if (storage != null) {
                    storages.add(storage);
                }
            }
        } catch (Exception ex) {
            logger.error(ex.toString());
        }
        return storages;
    }

    @Override
    public boolean update(Storage storage) {
        return false;
    }

    @Override
    public boolean delete(Storage storage) {
        return false;
    }

    @Override
    public boolean deleteById(long id) {
        return false;
    }

    private static final String deleteAll = "DELETE FROM Storage";

    public void deleteAll() {
        try (
                Connection con = DatabaseUtility.getConnection();
                Statement st = con.createStatement();
        ) {
            st.executeUpdate(deleteAll);
        } catch (SQLException ex) {
            logger.error(ex.toString());
        } catch (Exception ex) {
            logger.error(ex.toString());
        }
    }

    public int count() {
        return getAll().size();
    }

}
