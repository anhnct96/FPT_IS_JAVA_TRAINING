package dao.jdbc.query;

import model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseUpdate {
    public static final Logger logger = LoggerFactory.getLogger(DatabaseUpdate.class);

    public static void updateDetective(Detective detective, PreparedStatement detectivePreparedStatement){
        try {
            detectivePreparedStatement.setInt(1,detective.getVersion());
            detectivePreparedStatement.setDate(2, java.sql.Date.valueOf(detective.getCreatedAt()
                    .toLocalDate()));
            detectivePreparedStatement.setDate(3,java.sql.Date.valueOf(detective.getModifiedAt()
                    .toLocalDate()));

            detectivePreparedStatement.setString(4, detective.getUsername());
            detectivePreparedStatement.setString(5, detective.getFirstName());
            detectivePreparedStatement.setString(6, detective.getLastName());
            detectivePreparedStatement.setString(7,detective.getPassword());
            detectivePreparedStatement.setDate(8,java.sql.Date.valueOf(detective.getHiringDate()
                    .toLocalDate()));
            detectivePreparedStatement.setString(9,detective.getBadgeNumber());
            detectivePreparedStatement.setString(10, detective.getRank().toString());
            if (detective.isArmed() == true) {
                detectivePreparedStatement.setInt(11,1);
            } else {
                detectivePreparedStatement.setInt(11,0);
            }
            detectivePreparedStatement.setString(12,detective.getStatus().toString());
        } catch (SQLException ex) {
            logger.trace(ex.toString());
        }
    }

    public static void updateCriminalCase(CriminalCase criminalCase, PreparedStatement criminalCasePreparedStatement){
        try {
            criminalCasePreparedStatement.setInt(1,criminalCase.getVersion());
            criminalCasePreparedStatement.setDate(2, java.sql.Date.valueOf(criminalCase.getCreatedAt()
                    .toLocalDate()));
            criminalCasePreparedStatement.setDate(3,java.sql.Date.valueOf(criminalCase.getModifiedAt()
                    .toLocalDate()));

            criminalCasePreparedStatement.setString(4, criminalCase.getNumber());
            criminalCasePreparedStatement.setString(5, criminalCase.getType().toString());
            criminalCasePreparedStatement.setString(6, criminalCase.getShortDescription());
            criminalCasePreparedStatement.setString(7,criminalCase.getDetailedDescription());
            criminalCasePreparedStatement.setString(8,criminalCase.getStatus().toString());
            criminalCasePreparedStatement.setString(9,criminalCase.getNotes());
        } catch (SQLException ex) {
            logger.trace(ex.toString());
        }
    };

    public static void updateEvidence(Evidence evidence, PreparedStatement evidencePreparedStatement){
        try {
            evidencePreparedStatement.setInt(1,evidence.getVersion());
            evidencePreparedStatement.setDate(2, java.sql.Date.valueOf(evidence.getCreatedAt()
                    .toLocalDate()));
            evidencePreparedStatement.setDate(3,java.sql.Date.valueOf(evidence.getModifiedAt()
                    .toLocalDate()));

            evidencePreparedStatement.setString(4, evidence.getNumber());
            evidencePreparedStatement.setString(5, evidence.getItemName());
            evidencePreparedStatement.setString(6, evidence.getNotes());
            if (evidence.isArchived() == true) {
                evidencePreparedStatement.setInt(7,1);
            } else {
                evidencePreparedStatement.setInt(7,0);
            }
        } catch (SQLException ex) {
            logger.trace(ex.toString());
        }
    };

    public static void updateStorage(Storage storage, PreparedStatement storagePreparedStatement){
        try {
            storagePreparedStatement.setInt(1,storage.getVersion());
            storagePreparedStatement.setDate(2, java.sql.Date.valueOf(storage.getCreatedAt()
                    .toLocalDate()));
            storagePreparedStatement.setDate(3,java.sql.Date.valueOf(storage.getModifiedAt()
                    .toLocalDate()));

            storagePreparedStatement.setString(4, storage.getName());
            storagePreparedStatement.setString(5, storage.getLocation());
        } catch (SQLException ex) {
            logger.trace(ex.toString());
        }
    };

    public static void updateTrackEntry(TrackEntry trackEntry, PreparedStatement trackEntryPreparedStatement){
        try {
            trackEntryPreparedStatement.setInt(1,trackEntry.getVersion());
            trackEntryPreparedStatement.setDate(2, java.sql.Date.valueOf(trackEntry.getCreatedAt()
                    .toLocalDate()));
            trackEntryPreparedStatement.setDate(3,java.sql.Date.valueOf(trackEntry.getModifiedAt()
                    .toLocalDate()));

            trackEntryPreparedStatement.setDate(4,java.sql.Date.valueOf(trackEntry.getDate()
                    .toLocalDate()));
            trackEntryPreparedStatement.setString(5, trackEntry.getAction().toString());
            trackEntryPreparedStatement.setString(6, trackEntry.getReason());
        } catch (SQLException ex) {
            logger.trace(ex.toString());
        }
    };
}
