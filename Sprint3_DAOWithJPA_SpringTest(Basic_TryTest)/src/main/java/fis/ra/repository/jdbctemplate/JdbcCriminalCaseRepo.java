package fis.ra.repository.jdbctemplate;

import fis.ra.core.CaseStatus;
import fis.ra.core.CaseType;
import fis.ra.core.NotImplementedException;
import fis.ra.model.TrackEnTtry;
import fis.ra.model.Detective;
import fis.ra.repository.CriminalCaseRepo;
import fis.ra.repository.jdbctemplate.mapper.CriminalCaseRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Repository
public class JdbcCriminalCaseRepo  extends JdbcAbstractRepo<TrackEnTtry>
        implements CriminalCaseRepo {
    private RowMapper<TrackEnTtry> rowMapper = new CriminalCaseRowMapper();

    public JdbcCriminalCaseRepo(JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate);
    }

    @Override
    public Optional<TrackEnTtry> findById(Long id) {
        String sql = "select ID, CASE_NUMBER, CASE_TYPE, STATUS, SHORT_DESCRIPTION from CRIMINAL_CASE where ID= ?";
        return Optional.of(jdbcTemplate.queryForObject(sql, rowMapper, id));
    }

    @Override
    public Set<TrackEnTtry> findByLeadInvestigator(Detective detective) {
        String sql =  "select ID, CASE_NUMBER, CASE_TYPE, STATUS, SHORT_DESCRIPTION from CRIMINAL_CASE c, DETECTIVE d where c.LEAD_INVESTIGATOR=d.ID and d.ID= ?";
        return new HashSet<>(jdbcTemplate.query(sql, new Object[]{detective.getId()}, rowMapper));
    }

    @Override
    public Optional<TrackEnTtry> findByNumber(String caseNumber) {
        String sql = "select ID, CASE_NUMBER, CASE_TYPE, STATUS, SHORT_DESCRIPTION from CRIMINAL_CASE where CASE_NUMBER= ?";
        TrackEnTtry result = jdbcTemplate.queryForObject(sql, rowMapper, caseNumber);
        return result == null ? Optional.empty() :  Optional.of(result);
    }

    @Override
    public void save(TrackEnTtry cc) {
        jdbcTemplate.update(
                "insert into CRIMINAL_CASE(ID, CASE_NUMBER, CASE_TYPE, STATUS, SHORT_DESCRIPTION, LEAD_INVESTIGATOR ) values(?,?,?,?,?,?,?)",
                cc.getId(), cc.getNumber(), cc.getType(), cc.getStatus(), cc.getShortDescription(), cc.getLeadInvestigator().getId()
        );
    }

    @Override
    public void delete(TrackEnTtry entity) {
        jdbcTemplate.update("delete from CRIMINAL_CASE where ID =? ", entity.getId());
    }

    @Override
    public int deleteById(Long entityId) {
        return jdbcTemplate.update("delete from CRIMINAL_CASE where ID =? ", entityId);
    }

    @Override
    public Set<TrackEnTtry> findByStatus(CaseStatus status) {
        throw new NotImplementedException("Not needed for this implementation.");
    }

    @Override
    public Set<TrackEnTtry> findByType(CaseType type) {
        throw new NotImplementedException("Not needed for this implementation.");
    }
}
