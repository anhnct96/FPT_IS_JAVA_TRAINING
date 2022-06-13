package dao.jdbc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.Statement;

public abstract class IJDBCDAO {
    private final static Logger logger = LoggerFactory.getLogger(IJDBCDAO.class);
    public boolean deleteAll(String query) {
        int canDelete = 0;
        try {
            Connection con = DatabaseUtility.getConnection();
            Statement st = con.createStatement();
            canDelete = st.executeUpdate(query);
        } catch(Exception ex) {
            logger.error(ex.toString());
        }
        return canDelete > 0;
    }
}
