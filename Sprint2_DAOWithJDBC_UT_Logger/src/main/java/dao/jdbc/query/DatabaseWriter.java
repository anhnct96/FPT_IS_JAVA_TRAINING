package dao.jdbc.query;

import model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseWriter {
    public static final Logger logger = LoggerFactory.getLogger(DatabaseWriter.class);
    public static void setDetective(Detective detective, PreparedStatement detectivePreparedStatement) {
        try {
            detectivePreparedStatement.setLong(1,detective.getId());
            detectivePreparedStatement.setInt(2,detective.getVersion());
            detectivePreparedStatement.setDate(3, java.sql.Date.valueOf(detective.getCreatedAt()
                    .toLocalDate()));
            detectivePreparedStatement.setDate(4,java.sql.Date.valueOf(detective.getModifiedAt()
                    .toLocalDate()));

            detectivePreparedStatement.setString(5, detective.getUsername());
            detectivePreparedStatement.setString(6, detective.getFirstName());
            detectivePreparedStatement.setString(7, detective.getLastName());
            detectivePreparedStatement.setString(8,detective.getPassword());
            detectivePreparedStatement.setDate(9,java.sql.Date.valueOf(detective.getHiringDate()
                    .toLocalDate()));
            detectivePreparedStatement.setString(10,detective.getBadgeNumber());
            detectivePreparedStatement.setString(11, detective.getRank().toString());
            if (detective.isArmed() == true) {
                detectivePreparedStatement.setInt(12,1);
            } else {
                detectivePreparedStatement.setInt(12,0);
            }
            detectivePreparedStatement.setString(13,detective.getStatus().toString());

        } catch (SQLException ex) {
            logger.trace(ex.toString());
        }
    }

    public static void setCriminalCase(CriminalCase criminalCase, PreparedStatement criminalCasePreparedStatement) {
        try {
            criminalCasePreparedStatement.setLong(1,criminalCase.getId());
            criminalCasePreparedStatement.setInt(2,criminalCase.getVersion());
            criminalCasePreparedStatement.setDate(3, java.sql.Date.valueOf(criminalCase.getCreatedAt()
                    .toLocalDate()));
            criminalCasePreparedStatement.setDate(4,java.sql.Date.valueOf(criminalCase.getModifiedAt()
                    .toLocalDate()));

            criminalCasePreparedStatement.setString(5, criminalCase.getNumber());
            criminalCasePreparedStatement.setString(6, criminalCase.getType().toString());
            criminalCasePreparedStatement.setString(7, criminalCase.getShortDescription());
            criminalCasePreparedStatement.setString(8,criminalCase.getDetailedDescription());
            criminalCasePreparedStatement.setString(9,criminalCase.getStatus().toString());
            criminalCasePreparedStatement.setString(10,criminalCase.getNotes());
        } catch (SQLException ex) {
            logger.trace(ex.toString());
        }
    }

    public static void setEvidence(Evidence evidence, PreparedStatement evidencePreparedStatement) {
        try {
            evidencePreparedStatement.setLong(1,evidence.getId());
            evidencePreparedStatement.setInt(2,evidence.getVersion());
            evidencePreparedStatement.setDate(3, java.sql.Date.valueOf(evidence.getCreatedAt()
                    .toLocalDate()));
            evidencePreparedStatement.setDate(4,java.sql.Date.valueOf(evidence.getModifiedAt()
                    .toLocalDate()));

            evidencePreparedStatement.setString(5, evidence.getNumber());
            evidencePreparedStatement.setString(6, evidence.getItemName());
            evidencePreparedStatement.setString(7, evidence.getNotes());
            if (evidence.isArchived() == true) {
                evidencePreparedStatement.setInt(8,1);
            } else {
                evidencePreparedStatement.setInt(8,0);
            }
        } catch (SQLException ex) {
            logger.trace(ex.toString());
        }
    }

    public static void setStorage(Storage storage, PreparedStatement storagePreparedStatement) {
        try {
            storagePreparedStatement.setLong(1,storage.getId());
            storagePreparedStatement.setInt(2,storage.getVersion());
            storagePreparedStatement.setDate(3, java.sql.Date.valueOf(storage.getCreatedAt()
                    .toLocalDate()));
            storagePreparedStatement.setDate(4,java.sql.Date.valueOf(storage.getModifiedAt()
                    .toLocalDate()));

            storagePreparedStatement.setString(5, storage.getName());
            storagePreparedStatement.setString(6, storage.getLocation());
        } catch (SQLException ex) {
            logger.trace(ex.toString());
        }
    }

    public static void setTrackEntry(TrackEntry trackEntry, PreparedStatement trackEntryPreparedStatement) {
        try {
            trackEntryPreparedStatement.setLong(1,trackEntry.getId());
            trackEntryPreparedStatement.setInt(2,trackEntry.getVersion());
            trackEntryPreparedStatement.setDate(3, java.sql.Date.valueOf(trackEntry.getCreatedAt()
                    .toLocalDate()));
            trackEntryPreparedStatement.setDate(4,java.sql.Date.valueOf(trackEntry.getModifiedAt()
                    .toLocalDate()));

            trackEntryPreparedStatement.setDate(5,java.sql.Date.valueOf(trackEntry.getDate()
                    .toLocalDate()));
            trackEntryPreparedStatement.setString(6, trackEntry.getAction().toString());
            trackEntryPreparedStatement.setString(7, trackEntry.getReason());
        } catch (SQLException ex) {
            logger.trace(ex.toString());
        }
    }
}
