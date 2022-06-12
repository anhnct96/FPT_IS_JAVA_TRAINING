package dao.jdbc;

import dao.ICriminalCaseDAO;
import dao.jdbc.query.DatabaseMapper;
import model.CriminalCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JDBCCriminalCase implements ICriminalCaseDAO {
    private final static Logger logger = LoggerFactory.getLogger(JDBCCriminalCase.class);

    @Override
    public void save(CriminalCase criminalCase) {

    }

    @Override
    public Optional<CriminalCase> get(long id) {
        return Optional.empty();
    }

    @Override
    public List<CriminalCase> getAll() {
        List<CriminalCase> criminalCases = new ArrayList<>();
        try (Connection con = DatabaseUtility.getConnection();
             PreparedStatement stmt = con.prepareStatement("SELECT * FROM CriminalCase");
             ResultSet rs = stmt.executeQuery ()
        ) {

            while (rs.next()) {
                CriminalCase criminalCase = DatabaseMapper.getCriminalCase(rs);
                if(criminalCase != null) criminalCases.add(criminalCase);
            }
        } catch (Exception ex) {
            logger.error(ex.toString());
        }
        // end of try-with-resources
        return criminalCases;
    }

    @Override
    public boolean update(CriminalCase criminalCase) {
        return false;
    }

    @Override
    public boolean delete(CriminalCase criminalCase) {
        return false;
    }

    @Override
    public boolean deleteById(long id) {
        return false;
    }

    @Override
    public void delete(long id) {

    }
}
