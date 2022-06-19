package dao.jdbc;

import dao.IDAO;
import dao.IDetectiveDAO;
import dao.jdbc.query.DatabaseMapper;
import dao.jdbc.query.DatabaseUpdate;
import dao.jdbc.query.DatabaseWriter;
import model.Detective;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JDBCDetective implements IDetectiveDAO  {
    private final static Logger logger = LoggerFactory.getLogger(JDBCDetective.class);

    public int count() {
        int size = 0;
        try (
                Connection con = DatabaseUtility.getConnection();
                Statement stmt = con.createStatement();
        ) {
            ResultSet rs = stmt.executeQuery("SELECT COUNT(*) AS RECORD_COUNT FROM DETECTIVE");
            rs.next();
            size = rs.getInt("RECORD_COUNT");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return size;
    }
        @Override
    public void save(Detective detective) {
        try (
            Connection con = DatabaseUtility.getConnection();
            PreparedStatement stmt = con.prepareStatement("INSERT INTO Detective VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)");
            ){
            DatabaseWriter.setDetective(detective, stmt);
            if (stmt.executeUpdate() > 0) {
                logger.info("Added a new detective successfully");
            } else {
                logger.info("Fail to add a new detective");
            }
        } catch (Exception e) {
            logger.error(e.toString());
        }
    }

    @Override
    public Optional<Detective> get(long id) {
        ResultSet rs = null;
        Detective detective = null;
        try (Connection con = DatabaseUtility.getConnection();
            PreparedStatement stmt = con.prepareStatement("SELECT * FROM Detective WHERE id = ?");

        ) {
            stmt.setLong(1, id);
            rs = stmt.executeQuery();

            if (rs.next()) {
                detective = DatabaseMapper.getDetective(rs);
            }
            Optional<Detective> optionalDetective = Optional.of(detective);

            if (optionalDetective.isPresent()) {
                return optionalDetective;
            }
        } catch (Exception ex) {
            logger.error(ex.toString());
        } finally {
            try {
                rs.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return Optional.empty();
    }

    @Override
    public List<Detective> getAll() {
        List<Detective> detectives = new ArrayList<>();
        try (Connection con = DatabaseUtility.getConnection();
             PreparedStatement stmt = con.prepareStatement("SELECT * FROM Detective");
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Detective detective = DatabaseMapper.getDetective(rs);

                if (detective != null) {
                    detectives.add(detective);
                }
            }
        } catch (Exception ex) {
            logger.error(ex.toString());
        }
        return detectives;
    }

    @Override
    public boolean update(Detective newDetective) {
        boolean isUpdated = false;
        if (findById(newDetective.getId()).isEmpty()) {
            logger.info("No detective with this id = "+newDetective.getId()+"to update");
        }
        else {
            try {
                Connection con = DatabaseUtility.getConnection();
                PreparedStatement stmt = con.prepareStatement("UPDATE Detective SET version = ?, createdAt = ? ,modifiedAt = ?, username = ?, firstName = ?, lastName = ?, pw = ? , hiringDate = ?, badgeNumber = ?, rankOfDetective = ?, armed = ?, stt = ? WHERE id = ?" );
                DatabaseUpdate.updateDetective(newDetective, stmt);
                stmt.setLong(13, newDetective.getId());
                if (stmt.executeUpdate() > 0) {
                    logger.info("Updated");
                    isUpdated = true;
                }
                else {
                    logger.error("Can not update");
                }
            } catch (Exception e) {
                logger.error(e.toString());
            }

        }
        return isUpdated;
    }

    @Override
    public boolean delete(Detective detective) {
        boolean canDelete = false;
        if (findById(detective.getId()).isEmpty()) {
            logger.info("No detective with id = "+detective.getId());
            return false;
        }
        try (
            Connection con = DatabaseUtility.getConnection()
        ) {
            PreparedStatement stmt = con.prepareStatement("DELETE FROM Detective WHERE id = ?");
            stmt.setLong(1, detective.getId());
            if (stmt.executeUpdate() > 0) {
                logger.info("Successfully deleted detective with id = "+ detective.getId());
                canDelete = true;
            }
            else {
                logger.error("Failed to delete detective with id = "+ detective.getId());
            }
        } catch (Exception ex) {
            logger.error(ex.toString());
        }
        return canDelete;
    }

    @Override
    public boolean deleteById(long id) {
        boolean canDelete = false;
        if (findById(id).isEmpty()) {
            logger.info("No detective with the id = "+id);
        }
        else {
            try (
                Connection con = DatabaseUtility.getConnection();
            ) {
                PreparedStatement stmt = con.prepareStatement("DELETE FROM Detective WHERE id = ?");
                stmt.setLong(1,id);
                if (stmt.executeUpdate() > 0) {
                    logger.info("Successfully deleted ");
                    canDelete = true;
                }
                else {
                    logger.error("Failed to delete detective with id = "+ id);
                }
            } catch (Exception ex) {
                logger.error(ex.toString());
            }
        }
        return canDelete;
    }
    public static Optional<Detective> findById(long id) {
        Detective detective = null;
        try (Connection con = DatabaseUtility.getConnection()) {
            PreparedStatement stmt = con.prepareStatement("SELECT * FROM Detective WHERE id = ?");
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                detective = DatabaseMapper.getDetective(rs);
            }
            Optional<Detective> optionalDetective = Optional.of(detective);

            if (optionalDetective.isPresent()) {
                return optionalDetective;
            }
        } catch (Exception ex) {
            logger.error(ex.toString());
        }
        return Optional.empty();
    }

    private static final String deleteAll = "DELETE FROM Detective ";

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
}
