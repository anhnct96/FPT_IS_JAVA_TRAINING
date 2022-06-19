package dao.jdbc;

import dao.IDAO;
import dao.IEvidenceDAO;
import dao.jdbc.query.DatabaseMapper;
import dao.jdbc.query.DatabaseUpdate;
import dao.jdbc.query.DatabaseWriter;
import model.Evidence;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JDBCEvidence implements IEvidenceDAO {

    private final static Logger logger = LoggerFactory.getLogger(JDBCEvidence.class);

    public int count() {
        return getAll().size();
    }

    @Override
    public void save(Evidence evidence) {
        Connection con;
        PreparedStatement stmt;
        try {
            con = DatabaseUtility.getConnection();
            stmt = con.prepareStatement("INSERT INTO Evidence VALUES (?,?,?,?,?,?,?,?)");
            DatabaseWriter.setEvidence(evidence, stmt);
            if (stmt.executeUpdate() > 0) {
                logger.info("Added a new evidence successfully");
            } else {
                logger.info("Fail to add a new evidence");
            }
        } catch (Exception e) {
            logger.error(e.toString());
        }
    }

    @Override
    public Optional<Evidence> get(long id) {
        return getAll().stream()
                .filter(evidence -> evidence.getId() == id)
                .findFirst();
    }

    @Override
    public List<Evidence> getAll() {
        List<Evidence> evidences = new ArrayList<>();
        try (Connection con = DatabaseUtility.getConnection();
             PreparedStatement stmt = con.prepareStatement("SELECT * FROM Evidence");
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Evidence evidence = DatabaseMapper.getEvidence(rs);

                if (evidence != null) {
                    evidences.add(evidence);
                }
            }
        } catch (Exception ex) {
            logger.error(ex.toString());
        }
        return evidences;
    }

    @Override
    public boolean update(Evidence newEvidence) {
        boolean isUpdated = false;
        if (findById(newEvidence.getId()).isEmpty()) {
            logger.info("No evidence with this id = "+newEvidence.getId()+"to update");
        }
        else {
            try {
                Connection con = DatabaseUtility.getConnection();
                PreparedStatement stmt = con.prepareStatement("UPDATE Evidence SET version = ?, createdAt = ? ,modifiedAt = ?, number = ?, itemName = ?, notes = ?, archived = ? WHERE id = ?" );
                DatabaseUpdate.updateEvidence(newEvidence, stmt);
                stmt.setLong(8, newEvidence.getId());
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
    public boolean delete(Evidence evidence) {
        boolean canDelete = false;
        if (findById(evidence.getId()).isEmpty()) {
            logger.info("No evidence with id = "+evidence.getId());
            return false;
        }
        try {
            Connection con = DatabaseUtility.getConnection();
            PreparedStatement stmt = con.prepareStatement("DELETE FROM Evidence WHERE id = ?");
            stmt.setLong(1, evidence.getId());
            if (stmt.executeUpdate() > 0) {
                logger.info("Successfully deleted evidence with id = "+ evidence.getId());
                canDelete = true;
            }
            else {
                logger.error("Failed to delete evidence with id = "+ evidence.getId());
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
            logger.info("No evidence with the id = "+id);
        }
        else {
            try {
                Connection con = DatabaseUtility.getConnection();
                PreparedStatement stmt = con.prepareStatement("DELETE FROM Evidence WHERE id = ?");
                stmt.setLong(1,id);
                if (stmt.executeUpdate() > 0) {
                    logger.info("Successfully deleted ");
                    canDelete = true;
                }
                else {
                    logger.error("Failed to delete evidence with id = "+ id);
                }
            } catch (Exception ex) {
                logger.error(ex.toString());
            }
        }
        return canDelete;
    }

    public static Optional<Evidence> findById(long id) {
        Evidence evidence = null;

        try (Connection con = DatabaseUtility.getConnection()){
            PreparedStatement stmt = con.prepareStatement("SELECT * FROM Evidence WHERE id = ?");
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                evidence = DatabaseMapper.getEvidence(rs);
            }
            Optional<Evidence> optionalEvidence = Optional.of(evidence);
            if (optionalEvidence.isPresent()) {
                return optionalEvidence;
            }
        } catch (Exception ex) {
            logger.error(ex.toString());
        }
        return Optional.empty();
    }

    private static final String deleteAll = "DELETE FROM Evidence";

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
