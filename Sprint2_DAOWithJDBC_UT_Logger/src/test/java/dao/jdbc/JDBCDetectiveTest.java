package dao.jdbc;

import dao.IDetectiveDAO;
import model.Detective;
import org.junit.jupiter.api.Test;

class JDBCDetectiveTest {

    @Test
    void save() {
        IDetectiveDAO detectiveDAO = new JDBCDetective();

        //A2
//        Detective detective = new Detective(
//                1L,1, LocalDateTime.now(), LocalDateTime.now(),"username","firstname","lastname","password",
//                LocalDateTime.now(),"badge", Rank.CHIEF_INSPECTOR,true, EmploymentStatus.ACTIVE
//        );
//        //A3: Assert

    }

    @Test
    void get() {
    }

    @Test
    void getAll() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }
}