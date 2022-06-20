package fis.ra.repository.jdbctemplate.mapper;

import fis.ra.model.TrackEnTtry;
import fis.ra.model.Evidence;
import fis.ra.model.Storage;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EvidenceRowMapper implements RowMapper<Evidence> {
    @Override
    public Evidence mapRow(ResultSet rs, int rowNum) throws SQLException {
        Evidence evidence = new Evidence();
        evidence.setId(rs.getLong("evidence_id"));
        evidence.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
        evidence.setModifiedAt(rs.getTimestamp("modified_at").toLocalDateTime());
        evidence.setVersion(rs.getInt("version"));

        evidence.setNumber(rs.getString("evidence_number"));
        evidence.setItemName(rs.getString("item_name"));
        evidence.setNotes(rs.getString("notes"));
        evidence.setArchived(rs.getBoolean("archived"));

        long storage_id = rs.getLong("storage_id");
        Storage storage = new Storage();
        storage.setId(storage_id);

        long criminal_case_id = rs.getLong("criminal_case_id");
        TrackEnTtry criminalCase = new TrackEnTtry();
        criminalCase.setId(criminal_case_id);

        evidence.setCriminalCase(criminalCase);
        evidence.setStorage(storage);
        return evidence;
    }
}
