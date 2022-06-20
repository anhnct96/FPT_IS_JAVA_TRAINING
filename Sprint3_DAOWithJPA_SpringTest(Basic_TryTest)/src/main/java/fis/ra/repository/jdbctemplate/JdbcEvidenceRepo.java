package fis.ra.repository.jdbctemplate;

import fis.ra.model.TrackEnTtry;
import fis.ra.model.Evidence;
import fis.ra.model.Storage;
import fis.ra.repository.EvidenceRepo;
import fis.ra.repository.jdbctemplate.mapper.EvidenceRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public class JdbcEvidenceRepo implements EvidenceRepo {

    @Autowired
    JdbcTemplate jdbcTemplate;

    private RowMapper<Evidence> rowMapper = new EvidenceRowMapper();


    @Override
    public void save(Evidence evidence) {
        String insert = "INSERT INTO evidence (ID, NUMBER," +
                " itemName, notes, archived, VERSION) " +
                "VALUES (?,?,?,?,?,?);";

        jdbcTemplate.update(insert,evidence.getId(),
                evidence.getNumber(),evidence.getItemName()
        ,evidence.getNotes(),evidence.getArchived(),evidence.getVersion());
    }

    @Override
    public void delete(Evidence entity) {

    }

    @Override
    public Evidence update(Evidence entity) {
        return null;
    }

    @Override
    public int deleteById(Long entityId) {
        return 0;
    }

    @Override
    public Optional<Evidence> findById(Long entityId) {
        String sql = "select * from evidence where id = ?";
        return Optional.of(jdbcTemplate.queryForObject(sql,rowMapper,entityId));
    }

    @Override
    public Set<Evidence> findByCriminalCase(TrackEnTtry criminalCase) {
        return null;
    }

    @Override
    public Optional<Evidence> findByNumber(String evidenceNumber) {
        return Optional.empty();
    }

    @Override
    public boolean isInStorage(Storage storage) {
        return false;
    }

    //TODO ...
}
