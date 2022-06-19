package dao.jdbc;

import dao.*;
import model.*;
import model.enums.*;
import model.utility.Utility;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class JDBCDetectiveTest {

    @BeforeEach
    public void init() {
        Detective detective1 = new Detective(
                1L,
                Integer.parseInt(Utility.dummyNumber(2)),
                Utility.unformattedLDTToFormattedLDT(LocalDateTime.now()),
                Utility.unformattedLDTToFormattedLDT(LocalDateTime.now()),
                Utility.dummya2zA2ZString(20),
                Utility.dummya2zA2ZString(20),
                Utility.dummya2zA2ZString(20),
                Utility.dummya2zA2ZString(20),
                Utility.unformattedLDTToFormattedLDT(LocalDateTime.now()),
                Utility.dummya2zA2ZString(20),
                Rank.values()[Utility.randomNumber(0,4)],
                Utility.dummyBoolean(),
                EmploymentStatus.values()[Utility.randomNumber(0,4)]);

        Detective detective2 = new Detective(
                2L,
                Integer.parseInt(Utility.dummyNumber(2)),
                Utility.unformattedLDTToFormattedLDT(LocalDateTime.now()),
                Utility.unformattedLDTToFormattedLDT(LocalDateTime.now()),
                Utility.dummya2zA2ZString(20),
                Utility.dummya2zA2ZString(20),
                Utility.dummya2zA2ZString(20),
                Utility.dummya2zA2ZString(20),
                Utility.unformattedLDTToFormattedLDT(LocalDateTime.now()),
                Utility.dummya2zA2ZString(20),
                Rank.values()[Utility.randomNumber(0,4)],
                Utility.dummyBoolean(),
                EmploymentStatus.values()[Utility.randomNumber(0,4)]);

        Detective detective3 = new Detective(
                3L,
                Integer.parseInt(Utility.dummyNumber(2)),
                Utility.unformattedLDTToFormattedLDT(LocalDateTime.now()),
                Utility.unformattedLDTToFormattedLDT(LocalDateTime.now()),
                Utility.dummya2zA2ZString(20),
                Utility.dummya2zA2ZString(20),
                Utility.dummya2zA2ZString(20),
                Utility.dummya2zA2ZString(20),
                Utility.unformattedLDTToFormattedLDT(LocalDateTime.now()),
                Utility.dummya2zA2ZString(20),
                Rank.values()[Utility.randomNumber(0,4)],
                Utility.dummyBoolean(),
                EmploymentStatus.values()[Utility.randomNumber(0,4)]);

        Detective detective4 = new Detective(
                4L,
                Integer.parseInt(Utility.dummyNumber(2)),
                Utility.unformattedLDTToFormattedLDT(LocalDateTime.now()),
                Utility.unformattedLDTToFormattedLDT(LocalDateTime.now()),
                Utility.dummya2zA2ZString(20),
                Utility.dummya2zA2ZString(20),
                Utility.dummya2zA2ZString(20),
                Utility.dummya2zA2ZString(20),
                Utility.unformattedLDTToFormattedLDT(LocalDateTime.now()),
                Utility.dummya2zA2ZString(20),
                Rank.values()[Utility.randomNumber(0,4)],
                Utility.dummyBoolean(),
                EmploymentStatus.values()[Utility.randomNumber(0,4)]);

        Detective detective5 = new Detective(
                5L,
                Integer.parseInt(Utility.dummyNumber(2)),
                Utility.unformattedLDTToFormattedLDT(LocalDateTime.now()),
                Utility.unformattedLDTToFormattedLDT(LocalDateTime.now()),
                Utility.dummya2zA2ZString(20),
                Utility.dummya2zA2ZString(20),
                Utility.dummya2zA2ZString(20),
                Utility.dummya2zA2ZString(20),
                Utility.unformattedLDTToFormattedLDT(LocalDateTime.now()),
                Utility.dummya2zA2ZString(20),
                Rank.values()[Utility.randomNumber(0,4)],
                Utility.dummyBoolean(),
                EmploymentStatus.values()[Utility.randomNumber(0,4)]);

        CriminalCase criminalcase1 = new CriminalCase(
                1L,
                Integer.parseInt(Utility.dummyNumber(2)),
                Utility.unformattedLDTToFormattedLDT(LocalDateTime.now()),
                Utility.unformattedLDTToFormattedLDT(LocalDateTime.now()),
                String.valueOf(Integer.parseInt(Utility.dummyNumber(2))),
                CaseType.values()[Utility.randomNumber(0,3)],
                Utility.dummya2zA2ZString(20),
                Utility.dummya2zA2ZString(40),
                CaseStatus.values()[Utility.randomNumber(0,3)],
                Utility.dummya2zA2ZString(40),
                detective1
                );

        CriminalCase criminalcase2 = new CriminalCase(
                2L,
                Integer.parseInt(Utility.dummyNumber(2)),
                Utility.unformattedLDTToFormattedLDT(LocalDateTime.now()),
                Utility.unformattedLDTToFormattedLDT(LocalDateTime.now()),
                String.valueOf(Integer.parseInt(Utility.dummyNumber(2))),
                CaseType.values()[Utility.randomNumber(0,3)],
                Utility.dummya2zA2ZString(20),
                Utility.dummya2zA2ZString(40),
                CaseStatus.values()[Utility.randomNumber(0,3)],
                Utility.dummya2zA2ZString(40),
                detective1
        );

        CriminalCase criminalcase3 = new CriminalCase(
                3L,
                Integer.parseInt(Utility.dummyNumber(2)),
                Utility.unformattedLDTToFormattedLDT(LocalDateTime.now()),
                Utility.unformattedLDTToFormattedLDT(LocalDateTime.now()),
                String.valueOf(Integer.parseInt(Utility.dummyNumber(2))),
                CaseType.values()[Utility.randomNumber(0,3)],
                Utility.dummya2zA2ZString(20),
                Utility.dummya2zA2ZString(40),
                CaseStatus.values()[Utility.randomNumber(0,3)],
                Utility.dummya2zA2ZString(40),
                detective3
        );

        CriminalCase criminalcase4 = new CriminalCase(
                4L,
                Integer.parseInt(Utility.dummyNumber(2)),
                Utility.unformattedLDTToFormattedLDT(LocalDateTime.now()),
                Utility.unformattedLDTToFormattedLDT(LocalDateTime.now()),
                String.valueOf(Integer.parseInt(Utility.dummyNumber(2))),
                CaseType.values()[Utility.randomNumber(0,3)],
                Utility.dummya2zA2ZString(20),
                Utility.dummya2zA2ZString(40),
                CaseStatus.values()[Utility.randomNumber(0,3)],
                Utility.dummya2zA2ZString(40),
                detective4
        );

        CriminalCase criminalcase5 = new CriminalCase(
                5L,
                Integer.parseInt(Utility.dummyNumber(2)),
                Utility.unformattedLDTToFormattedLDT(LocalDateTime.now()),
                Utility.unformattedLDTToFormattedLDT(LocalDateTime.now()),
                String.valueOf(Integer.parseInt(Utility.dummyNumber(2))),
                CaseType.values()[Utility.randomNumber(0,3)],
                Utility.dummya2zA2ZString(20),
                Utility.dummya2zA2ZString(40),
                CaseStatus.values()[Utility.randomNumber(0,3)],
                Utility.dummya2zA2ZString(40),
                detective4
        );

        Storage storage1 = new Storage(
                1L,
                Integer.parseInt(Utility.dummyNumber(2)),
                Utility.unformattedLDTToFormattedLDT(LocalDateTime.now()),
                Utility.unformattedLDTToFormattedLDT(LocalDateTime.now()),
                Utility.dummya2zA2ZString(20),
                Utility.dummya2zA2ZString(20)
        );

        Storage storage2 = new Storage(
                2L,
                Integer.parseInt(Utility.dummyNumber(2)),
                Utility.unformattedLDTToFormattedLDT(LocalDateTime.now()),
                Utility.unformattedLDTToFormattedLDT(LocalDateTime.now()),
                Utility.dummya2zA2ZString(20),
                Utility.dummya2zA2ZString(20)
        );

        Storage storage3 = new Storage(
                3L,
                Integer.parseInt(Utility.dummyNumber(2)),
                Utility.unformattedLDTToFormattedLDT(LocalDateTime.now()),
                Utility.unformattedLDTToFormattedLDT(LocalDateTime.now()),
                Utility.dummya2zA2ZString(20),
                Utility.dummya2zA2ZString(20)
        );

        Storage storage4 = new Storage(
                4L,
                Integer.parseInt(Utility.dummyNumber(2)),
                Utility.unformattedLDTToFormattedLDT(LocalDateTime.now()),
                Utility.unformattedLDTToFormattedLDT(LocalDateTime.now()),
                Utility.dummya2zA2ZString(20),
                Utility.dummya2zA2ZString(20)
        );

        Storage storage5 = new Storage(
                5L,
                Integer.parseInt(Utility.dummyNumber(2)),
                Utility.unformattedLDTToFormattedLDT(LocalDateTime.now()),
                Utility.unformattedLDTToFormattedLDT(LocalDateTime.now()),
                Utility.dummya2zA2ZString(20),
                Utility.dummya2zA2ZString(20)
        );

        Evidence evidence1 = new Evidence(
                1L,
                Integer.parseInt(Utility.dummyNumber(2)),
                Utility.unformattedLDTToFormattedLDT(LocalDateTime.now()),
                Utility.unformattedLDTToFormattedLDT(LocalDateTime.now()),
                criminalcase1,
                storage1,
                Utility.dummya2zA2ZString(20),
                Utility.dummya2zA2ZString(20),
                Utility.dummya2zA2ZString(20),
                Utility.dummyBoolean()
        );

        Evidence evidence2 = new Evidence(
                2L,
                Integer.parseInt(Utility.dummyNumber(2)),
                Utility.unformattedLDTToFormattedLDT(LocalDateTime.now()),
                Utility.unformattedLDTToFormattedLDT(LocalDateTime.now()),
                criminalcase2,
                storage2,
                Utility.dummya2zA2ZString(20),
                Utility.dummya2zA2ZString(20),
                Utility.dummya2zA2ZString(20),
                Utility.dummyBoolean()
        );

        Evidence evidence3 = new Evidence(
                3L,
                Integer.parseInt(Utility.dummyNumber(2)),
                Utility.unformattedLDTToFormattedLDT(LocalDateTime.now()),
                Utility.unformattedLDTToFormattedLDT(LocalDateTime.now()),
                criminalcase3,
                storage3,
                Utility.dummya2zA2ZString(20),
                Utility.dummya2zA2ZString(20),
                Utility.dummya2zA2ZString(20),
                Utility.dummyBoolean()
        );

        Evidence evidence4 = new Evidence(
                1L,
                Integer.parseInt(Utility.dummyNumber(2)),
                Utility.unformattedLDTToFormattedLDT(LocalDateTime.now()),
                Utility.unformattedLDTToFormattedLDT(LocalDateTime.now()),
                criminalcase4,
                storage4,
                Utility.dummya2zA2ZString(20),
                Utility.dummya2zA2ZString(20),
                Utility.dummya2zA2ZString(20),
                Utility.dummyBoolean()
        );

        Evidence evidence5 = new Evidence(
                5L,
                Integer.parseInt(Utility.dummyNumber(2)),
                Utility.unformattedLDTToFormattedLDT(LocalDateTime.now()),
                Utility.unformattedLDTToFormattedLDT(LocalDateTime.now()),
                criminalcase5,
                storage5,
                Utility.dummya2zA2ZString(20),
                Utility.dummya2zA2ZString(20),
                Utility.dummya2zA2ZString(20),
                Utility.dummyBoolean()
        );

        TrackEntry trackEntry5 = new TrackEntry(
                5L,
                Integer.parseInt(Utility.dummyNumber(2)),
                Utility.unformattedLDTToFormattedLDT(LocalDateTime.now()),
                Utility.unformattedLDTToFormattedLDT(LocalDateTime.now()),
                Utility.unformattedLDTToFormattedLDT(LocalDateTime.now()),
                evidence5,
                detective5,
                TrackAction.values()[Utility.randomNumber(0,2)],
                Utility.dummya2zA2ZString(20)
                );

        TrackEntry trackEntry1 = new TrackEntry(
                1L,
                Integer.parseInt(Utility.dummyNumber(2)),
                Utility.unformattedLDTToFormattedLDT(LocalDateTime.now()),
                Utility.unformattedLDTToFormattedLDT(LocalDateTime.now()),
                Utility.unformattedLDTToFormattedLDT(LocalDateTime.now()),
                evidence1,
                detective1,
                TrackAction.values()[Utility.randomNumber(0,2)],
                Utility.dummya2zA2ZString(20)
        );

        TrackEntry trackEntry2 = new TrackEntry(
                2L,
                Integer.parseInt(Utility.dummyNumber(2)),
                Utility.unformattedLDTToFormattedLDT(LocalDateTime.now()),
                Utility.unformattedLDTToFormattedLDT(LocalDateTime.now()),
                Utility.unformattedLDTToFormattedLDT(LocalDateTime.now()),
                evidence2,
                detective2,
                TrackAction.values()[Utility.randomNumber(0,2)],
                Utility.dummya2zA2ZString(20)
        );

        TrackEntry trackEntry3 = new TrackEntry(
                3L,
                Integer.parseInt(Utility.dummyNumber(2)),
                Utility.unformattedLDTToFormattedLDT(LocalDateTime.now()),
                Utility.unformattedLDTToFormattedLDT(LocalDateTime.now()),
                Utility.unformattedLDTToFormattedLDT(LocalDateTime.now()),
                evidence3,
                detective3,
                TrackAction.values()[Utility.randomNumber(0,2)],
                Utility.dummya2zA2ZString(20)
        );

        TrackEntry trackEntry4 = new TrackEntry(
                4L,
                Integer.parseInt(Utility.dummyNumber(2)),
                Utility.unformattedLDTToFormattedLDT(LocalDateTime.now()),
                Utility.unformattedLDTToFormattedLDT(LocalDateTime.now()),
                Utility.unformattedLDTToFormattedLDT(LocalDateTime.now()),
                evidence4,
                detective4,
                TrackAction.values()[Utility.randomNumber(0,2)],
                Utility.dummya2zA2ZString(20)
        );

        IDetectiveDAO detectiveDAO = new JDBCDetective();
        ICriminalCaseDAO criminalCaseDAO = new JDBCCriminalCase();
        IEvidenceDAO evidenceDAO = new JDBCEvidence();
        IStorageDAO storageDAO = new JDBCStorage();
        ITrackEntryDAO trackEntryDAO = new JDBCTrackEntry();

        detectiveDAO.save(detective1);
        detectiveDAO.save(detective2);
        detectiveDAO.save(detective3);
        detectiveDAO.save(detective4);
        detectiveDAO.save(detective5);

        criminalCaseDAO.save(criminalcase1);
        criminalCaseDAO.save(criminalcase2);
        criminalCaseDAO.save(criminalcase3);
        criminalCaseDAO.save(criminalcase4);
        criminalCaseDAO.save(criminalcase5);

        evidenceDAO.save(evidence1);
        evidenceDAO.save(evidence2);
        evidenceDAO.save(evidence3);
        evidenceDAO.save(evidence4);
        evidenceDAO.save(evidence5);

        storageDAO.save(storage1);
        storageDAO.save(storage2);
        storageDAO.save(storage3);
        storageDAO.save(storage4);
        storageDAO.save(storage5);

        trackEntryDAO.save(trackEntry1);
        trackEntryDAO.save(trackEntry2);
        trackEntryDAO.save(trackEntry3);
        trackEntryDAO.save(trackEntry4);
        trackEntryDAO.save(trackEntry5);
    }

//    @AfterEach
//    void flushJDBC() {
//        IDetectiveDAO dao = new JDBCDetective();
//        dao.deleteAll();
//    }

    @Test
    void size() {
        IDetectiveDAO dao = new JDBCDetective();

        int size = dao.count();

        assertEquals(5,size);
    }

    @Test
    void save() {

    }

    @Test
    void get() {
        IDetectiveDAO dao = new JDBCDetective();

    }

    @Test
    void getAll() {
        IDetectiveDAO dao = new JDBCDetective();
        dao.getAll().stream().forEach(item -> System.out.println(item));
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }

    @Test
    void deleteById() {
    }

    @Test
    void findById() {
    }

    @Test
    void deleteAll() {
    }

    @Test
    void count() {
        IDetectiveDAO dao = new JDBCDetective();
        int size = dao.count();
        assertEquals(4,size);
    }
}