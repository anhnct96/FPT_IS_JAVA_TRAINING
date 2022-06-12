package service;

import dao.jdbc.JDBCDetective;
import model.Detective;
import model.enums.EmploymentStatus;
import model.enums.Rank;

import java.time.LocalDateTime;


public class Main {
    public static void main(String[] args) {
        JDBCDetective detectiveDAO = new JDBCDetective();

        Detective detective1 = new Detective(
                1L,1, LocalDateTime.now(), LocalDateTime.now(),
                "username1","firstname","lastname","password", LocalDateTime.now(),
                "badge1", Rank.CHIEF_INSPECTOR,true, EmploymentStatus.ACTIVE
        );

        Detective detective2 = new Detective(
                2L,1, LocalDateTime.now(), LocalDateTime.now(),
                "username2","firstname","lastname","password", LocalDateTime.now(),
                "badge2", Rank.CHIEF_INSPECTOR,true, EmploymentStatus.ACTIVE
        );

        Detective detective3 = new Detective(
                3L,1, LocalDateTime.now(), LocalDateTime.now(),
                "username3","firstname","lastname","password", LocalDateTime.now(),
                "badge3", Rank.CHIEF_INSPECTOR,true, EmploymentStatus.ACTIVE
        );

        detectiveDAO.save(detective1);
        detectiveDAO.save(detective2);
        detectiveDAO.save(detective3);

        Detective detective4 = new Detective(
                3L,12, LocalDateTime.now(), LocalDateTime.now(),
                "username3","firstname","lastname","password", LocalDateTime.now(),
                "badge3", Rank.CHIEF_INSPECTOR,true, EmploymentStatus.ACTIVE
        );

        //detectiveDAO.update(detective4);
        System.out.println(detectiveDAO.update(detective4));
    }
}
