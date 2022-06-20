package fis.ra.repository.jdbctemplate.mapper;

import fis.ra.core.CaseStatus;
import fis.ra.core.CaseType;
import fis.ra.core.TrackAction;
import fis.ra.model.Evidence;
import fis.ra.model.TrackEnTtry;
import fis.ra.model.Detective;
import fis.ra.model.TrackEntry;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TrackEntryRowMapper implements RowMapper<TrackEntry> {

    @Override
    public TrackEntry mapRow(ResultSet rs, int rowNum) throws SQLException {
        TrackEntry trackEntry = new TrackEntry();

        trackEntry.setId(rs.getLong("criminal_case_id"));
        trackEntry.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
        trackEntry.setModifiedAt(rs.getTimestamp("modified_at").toLocalDateTime());
        trackEntry.setVersion(rs.getInt("version"));

        trackEntry.setReason(rs.getString("reason"));
        trackEntry.setAction(TrackAction.valueOf(rs.getString("action")));
        trackEntry.setDate(rs.getTimestamp("date").toLocalDateTime());

        long detectiveId =  rs.getLong("detective_id");

        Detective detective = new Detective();
        detective.setId(detectiveId);

        long evidenceId =  rs.getLong("evidence_id");

        Evidence evidence = new Evidence();
        evidence.setId(evidenceId);

        trackEntry.setDetective(detective);
        trackEntry.setEvidence(evidence);
        return trackEntry;
    }
}
