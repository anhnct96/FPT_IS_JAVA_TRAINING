package dao.jdbc.query;

import model.*;
import model.enums.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;


public class DatabaseMapper {
    public static final Logger logger = LoggerFactory.getLogger(DatabaseMapper.class);

    public static Detective getDetective(ResultSet detectiveResultSet){
        try {
            Detective detective = new Detective();

            detective.setId(detectiveResultSet.getLong("id"));
            detective.setVersion(detectiveResultSet.getInt("version"));
            detective.setCreatedAt(detectiveResultSet.getTimestamp("createdAt").toLocalDateTime());
            detective.setModifiedAt(detectiveResultSet.getTimestamp("modifiedAt").toLocalDateTime());

            detective.setUsername(detectiveResultSet.getString("username"));
            detective.setFirstName(detectiveResultSet.getString("firstName"));
            detective.setLastName(detectiveResultSet.getString("lastName"));
            detective.setPassword(detectiveResultSet.getString("pw"));
            detective.setHiringDate(detectiveResultSet.getTimestamp("hiringDate").toLocalDateTime());

            detective.setBadgeNumber(detectiveResultSet.getString("badgeNumber"));
            detective.setRank(Rank.valueOf(detectiveResultSet.getString("rankOfDetective")));
            detective.setArmed(detectiveResultSet.getInt("armed") == 1);
            detective.setStatus(EmploymentStatus.valueOf(detectiveResultSet.getString("stt")));

            return detective;
        } catch (SQLException ex) {
            logger.error(ex.toString());
        }
        return null;
    }

    public static CriminalCase getCriminalCase(ResultSet criminalCaseResultSet){
        try {
            CriminalCase criminalCase = new CriminalCase();

            criminalCase.setId(criminalCaseResultSet.getLong("id"));
            criminalCase.setVersion(criminalCaseResultSet.getInt("version"));
            criminalCase.setCreatedAt(criminalCaseResultSet.getTimestamp("createdAt").toLocalDateTime());
            criminalCase.setModifiedAt(criminalCaseResultSet.getTimestamp("modifiedAt").toLocalDateTime());

            criminalCase.setNumber(criminalCaseResultSet.getString("number"));
            criminalCase.setType(CaseType.valueOf(criminalCaseResultSet.getString("type")));
            criminalCase.setShortDescription(criminalCaseResultSet.getString("shortDescription"));
            criminalCase.setDetailedDescription(criminalCaseResultSet.getString("detailedDescription"));
            criminalCase.setStatus(CaseStatus.valueOf(criminalCaseResultSet.getString("status")));
            criminalCase.setNotes(criminalCaseResultSet.getString("note"));

//            criminalCase.setEvidenceSet((Set<Evidence>) criminalCaseResultSet.getObject("evidenceId"));
//            criminalCase.setLeadInvestigator((Detective) criminalCaseResultSet.getObject("leadInvestigatorId"));
//            criminalCase.setAssigned((Set<Detective>) criminalCaseResultSet.getObject("assignedId"));

            return criminalCase;
        }catch (SQLException ex){
            logger.error(ex.toString());
        }
        return null;
    }

    public static Evidence getEvidence(ResultSet evidenceResultSet){
        try {
            Evidence evidence = new Evidence();

            evidence.setId(evidenceResultSet.getLong("id"));
            evidence.setVersion(evidenceResultSet.getInt("version"));
            evidence.setCreatedAt(evidenceResultSet.getTimestamp("createdAt").toLocalDateTime());
            evidence.setModifiedAt(evidenceResultSet.getTimestamp("modifiedAt").toLocalDateTime());

            evidence.setNumber(evidenceResultSet.getString("number"));
            evidence.setItemName(evidenceResultSet.getString("itemName"));
            evidence.setNotes(evidenceResultSet.getString("note"));
            evidence.setArchived(evidenceResultSet.getInt("archived") == 1);

            return evidence;
        } catch (SQLException ex) {
            logger.error(ex.toString());
        }
        return null;
    }

    public static Storage getStorage(ResultSet storageResultSet){
        try {
            Storage storage = new Storage();

            storage.setId(storageResultSet.getLong("id"));
            storage.setVersion(storageResultSet.getInt("version"));
            storage.setCreatedAt(storageResultSet.getTimestamp("createdAt").toLocalDateTime());
            storage.setModifiedAt(storageResultSet.getTimestamp("modifiedAt").toLocalDateTime());

            storage.setName(storageResultSet.getString("name"));
            storage.setLocation(storageResultSet.getString("location"));

            return storage;
        } catch (SQLException ex) {
            logger.error(ex.toString());
        }
        return null;
    }

    public static TrackEntry getTrackEntry(ResultSet trackEntryResultSet){
        try {
            TrackEntry trackEntry = new TrackEntry();

            trackEntry.setId(trackEntryResultSet.getLong("id"));
            trackEntry.setVersion(trackEntryResultSet.getInt("version"));
            trackEntry.setCreatedAt(trackEntryResultSet.getTimestamp("createdAt").toLocalDateTime());
            trackEntry.setModifiedAt(trackEntryResultSet.getTimestamp("modifiedAt").toLocalDateTime());
            trackEntry.setDate(trackEntryResultSet.getTimestamp("date").toLocalDateTime());
            trackEntry.setAction(TrackAction.valueOf(trackEntryResultSet.getString("action")));
            trackEntry.setReason(trackEntryResultSet.getString("reason"));

            return trackEntry;
        } catch (SQLException ex) {
            logger.error(ex.toString());
        }
        return null;
    }

}
