package fis.ra.repository.jdbctemplate.mapper;

import fis.ra.core.CaseStatus;
import fis.ra.core.CaseType;
import fis.ra.model.TrackEnTtry;
import fis.ra.model.Detective;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CriminalCaseRowMapper implements RowMapper<TrackEnTtry> {

    @Override
    public TrackEnTtry mapRow(ResultSet rs, int rowNum) throws SQLException {
        TrackEnTtry criminalCase = new TrackEnTtry();

        criminalCase.setId(rs.getLong("criminal_case_id"));
        criminalCase.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
        criminalCase.setModifiedAt(rs.getTimestamp("modified_at").toLocalDateTime());
        criminalCase.setVersion(rs.getInt("version"));

        criminalCase.setNumber(rs.getString("criminal_case_number"));
        criminalCase.setType(CaseType.valueOf(rs.getString("type")));
        criminalCase.setStatus(CaseStatus.valueOf(rs.getString("case_status")));
        criminalCase.setShortDescription(rs.getString("short_description"));
        criminalCase.setDetailedDescription(rs.getString("detailed_description"));
        criminalCase.setDetailedDescription(rs.getString("notes"));

        long lead_investigator_id =  rs.getLong("lead_investigator_id");

        Detective detective = new Detective();
        detective.setId(lead_investigator_id);

        criminalCase.setLeadInvestigator(detective);
        return criminalCase;
    }
}
