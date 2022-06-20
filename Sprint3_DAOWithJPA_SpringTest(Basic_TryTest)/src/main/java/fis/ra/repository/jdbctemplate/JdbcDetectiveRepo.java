package fis.ra.repository.jdbctemplate;

import fis.ra.core.Rank;
import fis.ra.model.Detective;
import fis.ra.repository.DetectiveRepo;
import fis.ra.repository.jdbctemplate.mapper.DetectiveRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

//@Transactional
@Repository
public class JdbcDetectiveRepo extends JdbcAbstractRepo<Detective> implements DetectiveRepo {

    private RowMapper<Detective> rowMapper = new DetectiveRowMapper();

    public JdbcDetectiveRepo(JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate);
    }

    @Override
    public Optional<Detective> findById(Long id) {
        String sql = "select detective_id, created_at, modified_at, version, armed, badge_number," +
                "first_name, hiring_date, last_name, passwords, detective_rank, detective_status, username" +
                " from detective where detective_id = ?";
        return Optional.of(jdbcTemplate.queryForObject(sql, rowMapper, id));
    }

    @Override
    public Optional<Detective> findByBadgeNumber(String badgeNumber) {
        String sql = "select detective_id, created_at, modified_at, version, armed, badge_number," +
                "first_name, hiring_date, last_name, passwords, detective_rank, detective_status, username" +
                " from detective where badge_number = ?";
        Detective detective = jdbcTemplate.queryForObject(sql, rowMapper, badgeNumber);
        return detective == null ? Optional.empty() : Optional.of(detective);
    }

    @Override
    public void save(Detective detective) {
        jdbcTemplate.update(
                "insert into detective(detective_id, created_at, modified_at, version, armed, badge_number, " +
                        "first_name, hiring_date, last_name, passwords, detective_rank, detective_status, username) " +
                        "values(?,?,?,?,?,?,?,?,?,?,?,?,?)",
                detective.getId(), detective.getCreatedAt(), detective.getCreatedAt(),detective.getVersion(),
                detective.getArmed(), detective.getBadgeNumber(),
                detective.getFirstName(), detective.getHiringDate(), detective.getLastName(), detective.getPassword(),
                String.valueOf(detective.getRank()), String.valueOf(detective.getStatus()), detective.getUsername()
        );
    }

    @Override
    public Set<Detective> findAll() {
        String sql = "select detective_id, created_at, modified_at, version, armed, badge_number," +
                "first_name, hiring_date, last_name, passwords, detective_rank, detective_status, username" +
                " from detective";
        return new HashSet<>(jdbcTemplate.query(sql, rowMapper));
    }

    @Override
    public Set<Detective> findByRank(Rank rank) {
        String sql = "select detective_id, created_at, modified_at, version, armed, badge_number," +
                "first_name, hiring_date, last_name, passwords, detective_rank, detective_status, username" +
                " from detective where detective_rank= ?";
        return new HashSet<>(jdbcTemplate.query(sql, rowMapper, rank));
    }

    @Override
    public void delete(Detective detective) {
        jdbcTemplate.update("delete from detective where detective_id =? ", detective.getId());
    }

    @Override
    public int deleteById(Long id) {
        return jdbcTemplate.update("delete from detective where detective_id =? ", id);
    }
}
