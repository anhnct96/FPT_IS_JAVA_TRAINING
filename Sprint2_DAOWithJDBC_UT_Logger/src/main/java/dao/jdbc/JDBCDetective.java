package dao.jdbc;

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

public class JDBCDetective extends IJDBCDAO implements IDetectiveDAO  {
    private final static Logger logger = LoggerFactory.getLogger(JDBCCriminalCase.class);

    @Override
    public void save(Detective detective) {
        Connection con;
        PreparedStatement stmt;
        try {
            con = DatabaseUtility.getConnection();
            stmt = con.prepareStatement("INSERT INTO Detective VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)");
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
        return getAll().stream()
                .filter(detective -> detective.getId() == id)
                .findFirst();
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
        try {
            Connection con = DatabaseUtility.getConnection();
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
            try {
                Connection con = DatabaseUtility.getConnection();
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

//    public boolean deleteAll() {
//        int canDelete = 0;
//        try {
//            Connection con = DatabaseUtility.getConnection();
//            Statement st = con.createStatement();
//            canDelete = st.executeUpdate("DELETE FROM Detective");
//        } catch(Exception ex) {
//            logger.error(ex.toString());
//        }
//        return canDelete > 0;
//    }

    public static Optional<Detective> findById(long id) {
        Detective detective = null;
        try (Connection con = DatabaseUtility.getConnection()){
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

    private static final String deleteAll = "DELETE FROM Detective";

    public void deleteAll () {
        super.deleteAll(deleteAll);
    }
}
