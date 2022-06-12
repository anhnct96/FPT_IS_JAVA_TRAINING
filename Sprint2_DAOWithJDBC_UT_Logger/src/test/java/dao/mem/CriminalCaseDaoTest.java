package dao.mem;

import dao.ICriminalCaseDAO;
import model.CriminalCase;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.*;

class CriminalCaseDaoTest {

    public static Logger LOG = LoggerFactory.getLogger(CriminalCaseDaoTest.class);

    @BeforeAll
    public static void initTest(){
        LOG.info("initTest");
    }

    @BeforeEach
    public void initDataEachTest(){
        LOG.info("initDataEachTest");

        MemoryDataSource.CRIMINAL_CASES_LIST.clear();

        CriminalCase criminalCase1 =  new CriminalCase();
        criminalCase1.setId(1);
        CriminalCase criminalCase2 =  new CriminalCase();
        criminalCase2.setId(2);
        CriminalCase criminalCase3 =  new CriminalCase();
        criminalCase3.setId(3);

        MemoryDataSource.CRIMINAL_CASES_LIST.add(criminalCase1);
        MemoryDataSource.CRIMINAL_CASES_LIST.add(criminalCase2);
        MemoryDataSource.CRIMINAL_CASES_LIST.add(criminalCase3);
    }

    @Nested
    class save{
        @Test
        void criminalCaseNotExist() {
            //A1: Arrange
            CriminalCase criminalCase = new CriminalCase();
            criminalCase.setId(4);
            criminalCase.setNumber("0004");
            //A2: Action
            ICriminalCaseDAO iCriminalCaseDAO = new CriminalCaseDAO();
            iCriminalCaseDAO.save(criminalCase);

            //A3: Assert | ...
            assertEquals(4, MemoryDataSource.CRIMINAL_CASES_LIST.size());
        }

        @ParameterizedTest
        @ValueSource(ints = {4, 5, 6})
        void criminalCaseWithId(int id) {
            //A1: Arrange
            CriminalCase criminalCase = new CriminalCase();
            criminalCase.setId(id);
            //A2: Action
            ICriminalCaseDAO iCriminalCaseDAO = new CriminalCaseDAO();
            iCriminalCaseDAO.save(criminalCase);
            //A3: Assert
            assertEquals(4, MemoryDataSource.CRIMINAL_CASES_LIST.size());
        }

        @Test
        void criminalCaseExist() {
            //A1
            CriminalCase criminalCase = new CriminalCase();
            criminalCase.setId(1);
            criminalCase.setNumber("0001");

            //A2
            ICriminalCaseDAO iCriminalCaseDAO = new CriminalCaseDAO();
            iCriminalCaseDAO.save(criminalCase);

            //A3
            assertEquals(3, MemoryDataSource.CRIMINAL_CASES_LIST.size());
        }
    }

    @Test
    void get() {
        LOG.info("test get");
    }

    @Test
    void getAll() {
        LOG.info("test getAll");
    }

    @Test
    void update() {
        LOG.info("test update");
    }

    @Test
    void delete() {
        LOG.info("test delete");
    }

    @AfterEach
    public void clearDataEachTest() {
        LOG.info("clearDataEachTest");
    }

    @AfterAll
    public static void clearTest(){
        LOG.info("clearTest");
    }
}