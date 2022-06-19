package dao.jdbc;

import dao.ICriminalCaseDAO;
import dao.IDAO;
import dao.jdbc.query.DatabaseMapper;
import dao.jdbc.query.DatabaseUpdate;
import dao.jdbc.query.DatabaseWriter;
import model.CriminalCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JDBCCriminalCase implements ICriminalCaseDAO {
    private final static Logger logger = LoggerFactory.getLogger(JDBCCriminalCase.class);

    public int count() {
        return getAll().size();
    }

    @Override
    public void save(CriminalCase criminalCase) {
        Connection con;
        PreparedStatement stmt;
        try {
            con = DatabaseUtility.getConnection();
            stmt = con.prepareStatement("INSERT INTO CRIMINALCASE VALUES (?,?,?,?,?,?,?,?,?,?)");
            DatabaseWriter.setCriminalCase(criminalCase, stmt);
            if (stmt.executeUpdate() > 0) {
                logger.info("Added a new criminal case successfully");
            } else {
                logger.info("Fail to add a new criminal case");
            }
        } catch (Exception e) {
            logger.error(e.toString());
        }
    }

    @Override
    public Optional<CriminalCase> get(long id) {
        return getAll().stream()
                .filter(criminalCase -> criminalCase.getId() == id)
                .findFirst();
    }

    @Override
    public List<CriminalCase> getAll() {
        List<CriminalCase> criminalCases = new ArrayList<>();
        try (Connection con = DatabaseUtility.getConnection();
             PreparedStatement stmt = con.prepareStatement("SELECT * FROM CriminalCase");
             ResultSet rs = stmt.executeQuery ()) {

            while (rs.next()) {
                CriminalCase criminalCase = DatabaseMapper.getCriminalCase(rs);

                if(criminalCase != null) {
                    criminalCases.add(criminalCase);
                }
            }
        } catch (Exception ex) {
            logger.error(ex.toString());
        }
        return criminalCases;
    }

    @Override
    public boolean update(CriminalCase newCriminalCase) {
        boolean isUpdated = false;
        if (!findById(newCriminalCase.getId()).isPresent()) {
            logger.info("No criminal case with this id = "+newCriminalCase.getId()+"to update");
        }
        else {
            try {
                Connection con = DatabaseUtility.getConnection();
                PreparedStatement stmt = con.prepareStatement("UPDATE CriminalCase SET version = ?, createdAt = ? ,modifiedAt = ?, number = ?, type = ?, shortDescription = ?, detailedDescription = ? , status = ?, notes = ? WHERE id = ?" );
                DatabaseUpdate.updateCriminalCase(newCriminalCase, stmt);
                stmt.setLong(10, newCriminalCase.getId());
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
    public boolean delete(CriminalCase criminalCase) {
        boolean canDelete = false;
        if (!findById(criminalCase.getId()).isPresent()) {
            logger.info("No criminal case with id = "+criminalCase.getId());
            return false;
        }
        try {
            Connection con = DatabaseUtility.getConnection();
            PreparedStatement stmt = con.prepareStatement("DELETE FROM CriminalCase WHERE id = ?");
            stmt.setLong(1, criminalCase.getId());
            if (stmt.executeUpdate() > 0) {
                logger.info("Successfully deleted criminal case with id = "+ criminalCase.getId());
                canDelete = true;
            }
            else {
                logger.error("Failed to delete criminal case with id = "+ criminalCase.getId());
            }
        } catch (Exception ex) {
            logger.error(ex.toString());
        }
        return canDelete;
    }

    @Override
    public boolean deleteById(long id) {
        boolean canDelete = false;
        if (!findById(id).isPresent()) {
            logger.info("No criminal case with the id = "+id);
        }
        else {
            try {
                Connection con = DatabaseUtility.getConnection();
                PreparedStatement stmt = con.prepareStatement("DELETE FROM CriminalCase WHERE id = ?");
                stmt.setLong(1,id);
                if (stmt.executeUpdate() > 0) {
                    logger.info("Successfully deleted ");
                    canDelete = true;
                }
                else {
                    logger.error("Failed to delete criminal case with id = "+ id);
                }
            } catch (Exception ex) {
                logger.error(ex.toString());
            }
        }
        return canDelete;
    }

    public static Optional<CriminalCase> findById(long id) {
        CriminalCase criminalCase = null;

        try (Connection con = DatabaseUtility.getConnection()){
            PreparedStatement stmt = con.prepareStatement("SELECT * FROM CriminalCase WHERE id = ?");
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                criminalCase = DatabaseMapper.getCriminalCase(rs);
            }
            Optional<CriminalCase> optionalCriminalCase = Optional.of(criminalCase);
            if (optionalCriminalCase.isPresent()) {
                return Optional.of(optionalCriminalCase.get());
            }
        } catch (Exception ex) {
            logger.error(ex.toString());
        }
        return Optional.empty();
    }

    private static final String deleteAll = "DELETE FROM CriminalCase";

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
