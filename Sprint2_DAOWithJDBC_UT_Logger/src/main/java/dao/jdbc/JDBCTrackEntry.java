package dao.jdbc;

import dao.ITrackEntryDAO;
import dao.jdbc.query.DatabaseMapper;
import dao.jdbc.query.DatabaseUpdate;
import dao.jdbc.query.DatabaseWriter;

import model.TrackEntry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JDBCTrackEntry extends IJDBCDAO implements ITrackEntryDAO {

    private final static Logger logger = LoggerFactory.getLogger(JDBCTrackEntry.class);

    @Override
    public void save(TrackEntry trackEntry) {
        Connection con;
        PreparedStatement stmt;
        try {
            con = DatabaseUtility.getConnection();
            stmt = con.prepareStatement("INSERT INTO TrackEntry VALUES (?,?,?,?,?,?,?)");
            DatabaseWriter.setTrackEntry(trackEntry, stmt);
            if (stmt.executeUpdate() > 0) {
                logger.info("Added a new track entry successfully");
            } else {
                logger.info("Fail to add a new track entry");
            }
        } catch (Exception e) {
            logger.error(e.toString());
        }
    }

    @Override
    public Optional<TrackEntry> get(long id) {
        return getAll().stream()
                .filter(trackEntry -> trackEntry.getId() == id)
                .findFirst();
    }

    @Override
    public List<TrackEntry> getAll() {
        List<TrackEntry> trackEntries = new ArrayList<>();
        try (Connection con = DatabaseUtility.getConnection();
             PreparedStatement stmt = con.prepareStatement("SELECT * FROM TrackEntry");
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                TrackEntry trackEntry = DatabaseMapper.getTrackEntry(rs);

                if (trackEntry != null) {
                    trackEntries.add(trackEntry);
                }
            }
        } catch (Exception ex) {
            logger.error(ex.toString());
        }
        return trackEntries;
    }

    @Override
    public boolean update(TrackEntry newTrackEntry) {
        boolean isUpdated = false;
        if (findById(newTrackEntry.getId()).isEmpty()) {
            logger.info("No track entry with this id = "+newTrackEntry.getId()+"to update");
        }
        else {
            try {
                Connection con = DatabaseUtility.getConnection();
                PreparedStatement stmt = con.prepareStatement("UPDATE Detective SET version = ?, createdAt = ? ,modifiedAt = ?, date = ?, action = ?, reason = ? WHERE id = ?" );
                DatabaseUpdate.updateTrackEntry(newTrackEntry, stmt);
                stmt.setLong(13, newTrackEntry.getId());
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
    public boolean delete(TrackEntry trackEntry) {
        boolean canDelete = false;
        if (findById(trackEntry.getId()).isEmpty()) {
            logger.info("No track entry with id = "+trackEntry.getId());
            return false;
        }
        try {
            Connection con = DatabaseUtility.getConnection();
            PreparedStatement stmt = con.prepareStatement("DELETE FROM TrackEntry WHERE id = ?");
            stmt.setLong(1, trackEntry.getId());
            if (stmt.executeUpdate() > 0) {
                logger.info("Successfully deleted track entry with id = "+ trackEntry.getId());
                canDelete = true;
            }
            else {
                logger.error("Failed to delete track entry with id = "+ trackEntry.getId());
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
            logger.info("No track entry with the id = "+id);
        }
        else {
            try {
                Connection con = DatabaseUtility.getConnection();
                PreparedStatement stmt = con.prepareStatement("DELETE FROM TrackEntry WHERE id = ?");
                stmt.setLong(1,id);
                if (stmt.executeUpdate() > 0) {
                    logger.info("Successfully deleted ");
                    canDelete = true;
                }
                else {
                    logger.error("Failed to delete track entry with id = "+ id);
                }
            } catch (Exception ex) {
                logger.error(ex.toString());
            }
        }
        return canDelete;
    }

    private static final String deleteAll = "DELETE FROM TrackEntry";

    public void deleteAll () {
        super.deleteAll(deleteAll);
    }

    public static Optional<TrackEntry> findById(long id) {
        TrackEntry trackEntry = null;
        try (Connection con = DatabaseUtility.getConnection()){
            PreparedStatement stmt = con.prepareStatement("SELECT * FROM TrackEntry WHERE id = ?");
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                trackEntry = DatabaseMapper.getTrackEntry(rs);
            }
            Optional<TrackEntry> optionalTrackEntry = Optional.of(trackEntry);

            if (optionalTrackEntry.isPresent()) {
                return optionalTrackEntry;
            }
        } catch (Exception ex) {
            logger.error(ex.toString());
        }
        return Optional.empty();
    }
}
