package dao.jdbc;

import dao.ICriminalCaseDAO;
import dao.IDetectiveDAO;
import model.CriminalCase;
import model.Detective;
import model.enums.EmploymentStatus;
import model.enums.Rank;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

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