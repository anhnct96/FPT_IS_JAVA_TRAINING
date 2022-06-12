package dao.jdbc;

import dao.ICriminalCaseDAO;
import model.CriminalCase;
import org.junit.jupiter.api.Test;

import java.util.List;

class JDBCCriminalCaseTest {

    @Test
    void save() {
    }

    @Test
    void get() {
    }

    @Test
    void getAll() {
        ICriminalCaseDAO criminalCaseDAO = new JDBCCriminalCase();

        //A2
        List<CriminalCase> criminalCaseList = criminalCaseDAO.getAll();

        //A3: Assert
        System.out.println(criminalCaseList);
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }
}