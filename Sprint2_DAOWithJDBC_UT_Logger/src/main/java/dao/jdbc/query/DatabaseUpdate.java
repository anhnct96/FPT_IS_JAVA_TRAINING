package dao.jdbc.query;

import model.Detective;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseUpdate {
    public static final Logger logger = LoggerFactory.getLogger(DatabaseUpdate.class);

    public static void updateDetective(Detective detective, PreparedStatement detectivePreparedStatement){
        try {
            detectivePreparedStatement.setInt(1,detective.getVersion());
            detectivePreparedStatement.setDate(2, java.sql.Date.valueOf(detective.getCreatedAt()
                    .toLocalDate()));
            detectivePreparedStatement.setDate(3,java.sql.Date.valueOf(detective.getModifiedAt()
                    .toLocalDate()));

            detectivePreparedStatement.setString(4, detective.getUsername());
            detectivePreparedStatement.setString(5, detective.getFirstName());
            detectivePreparedStatement.setString(6, detective.getLastName());
            detectivePreparedStatement.setString(7,detective.getPassword());
            detectivePreparedStatement.setDate(8,java.sql.Date.valueOf(detective.getHiringDate()
                    .toLocalDate()));
            detectivePreparedStatement.setString(9,detective.getBadgeNumber());
            detectivePreparedStatement.setString(10, detective.getRank().toString());
            if (detective.isArmed() == true) {
                detectivePreparedStatement.setInt(11,1);
            } else {
                detectivePreparedStatement.setInt(11,0);
            }
            detectivePreparedStatement.setString(12,detective.getStatus().toString());
        } catch (SQLException ex) {
            logger.trace(ex.toString());
        }
    }
}
