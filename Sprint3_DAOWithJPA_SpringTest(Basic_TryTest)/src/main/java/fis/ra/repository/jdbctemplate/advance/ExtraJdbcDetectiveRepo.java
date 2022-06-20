package fis.ra.repository.jdbctemplate.advance;

import fis.ra.core.EmploymentStatus;
import fis.ra.core.Rank;
import fis.ra.model.Detective;
import fis.ra.repository.jdbctemplate.JdbcDetectiveRepo;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

@Repository
public class ExtraJdbcDetectiveRepo extends JdbcDetectiveRepo {

    public ExtraJdbcDetectiveRepo(JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate);
    }


    private class DetectiveExtractor implements ResultSetExtractor<Detective> {
        @Override
        public Detective extractData(ResultSet rs) throws SQLException {
            Detective detective = null;
            while (rs.next()) {
                if (detective == null) {
                    detective = new Detective();

                    detective.setId(rs.getLong("detective_id"));
                    detective.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
                    detective.setModifiedAt(rs.getTimestamp("modified_at").toLocalDateTime());
                    detective.setVersion(rs.getInt("version"));

                    detective.setUsername(rs.getString("username"));
                    detective.setFirstName(rs.getString("firstName"));
                    detective.setLastName(rs.getString("lastName"));
                    detective.setPassword(rs.getString("password"));

                    detective.setArmed(rs.getBoolean("armed"));
                    detective.setBadgeNumber(rs.getString("badgeNumber"));
                    detective.setHiringDate(rs.getTimestamp("hiringDate").toLocalDateTime());
                    detective.setRank(Rank.valueOf(rs.getString("detective_rank")));
                    detective.setStatus(EmploymentStatus.valueOf(rs.getString("detective_status")));
                }
            }

            return detective;
        }
    }
}