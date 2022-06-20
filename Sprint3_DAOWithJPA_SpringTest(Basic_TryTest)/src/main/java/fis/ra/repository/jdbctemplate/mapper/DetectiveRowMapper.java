package fis.ra.repository.jdbctemplate.mapper;

import fis.ra.core.EmploymentStatus;
import fis.ra.core.Rank;
import fis.ra.model.Detective;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DetectiveRowMapper implements RowMapper<Detective> {
    @Override
    public Detective mapRow(ResultSet rs, int rowNum) throws SQLException {
        Detective detective = new Detective();
        detective.setId(rs.getLong("detective_id"));
        detective.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
        detective.setModifiedAt(rs.getTimestamp("modified_at").toLocalDateTime());
        detective.setVersion(rs.getInt("version"));

        detective.setUsername(rs.getString("username"));
        detective.setFirstName(rs.getString("firstName"));
        detective.setLastName(rs.getString("lastName"));
        detective.setPassword(rs.getString("passwords"));

        detective.setArmed(rs.getBoolean("armed"));
        detective.setBadgeNumber(rs.getString("badgeNumber"));
        detective.setHiringDate(rs.getTimestamp("hiringDate").toLocalDateTime());
        detective.setRank(Rank.valueOf(rs.getString("detective_rank")));
        detective.setStatus(EmploymentStatus.valueOf(rs.getString("detective_status")));

        return detective;
    }
}
