package fis.ra.criminalmanagementsystem.repository;

import fis.ra.criminalmanagementsystem.core.CaseStatus;
import fis.ra.criminalmanagementsystem.model.CriminalCase;
import org.hamcrest.CoreMatchers;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;

import static org.assertj.core.internal.bytebuddy.matcher.ElementMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CriminalCaseRepoTest {

    @Autowired
    CriminalCaseRepo criminalCaseRepo;
    CriminalCase cc1, cc2, cc3, cc4, cc5, cc6, cc7;

    @BeforeEach
    void init() {
        cc1 = new CriminalCase();
        cc1.setId(1L);
        cc1.setNumber("110223697");

        cc2 = new CriminalCase();
        cc2.setId(2L);
        cc2.setNumber("163399455");

        cc3 = new CriminalCase();
        cc3.setId(3L);
        cc3.setNumber("187542369");

        cc4 = new CriminalCase();
        cc4.setId(4L);
        cc4.setNumber("102354981");

        cc5 = new CriminalCase();
        cc5.setId(5L);
        cc5.setNumber("603304415");

        cc6 = new CriminalCase();
        cc6.setId(6L);
        cc6.setNumber("984236570");

        cc7 = new CriminalCase();
        cc7.setId(7L);
        cc7.setNumber("954781066");
    }

    @Test
    @Transactional(propagation= Propagation.REQUIRED, readOnly=true, noRollbackFor=Exception.class)
    void findByStatus() {
        // Arrangement
        Collection<CriminalCase> expect = new ArrayList<>();
        expect.add(cc1);
        expect.add(cc7);

        // Action
        Collection<CriminalCase> actual = criminalCaseRepo.findByStatus(CaseStatus.COLD);

        // Assertion
        assertThat(actual, containsInAnyOrder(expect.toArray()));
    }

    @Test
    @Transactional(propagation= Propagation.REQUIRED, readOnly=true, noRollbackFor=Exception.class)
    void findByUsername() {
        // Arrangement
        Collection<CriminalCase> expect = new ArrayList<>();
        expect.add(cc1);
        expect.add(cc2);
        expect.add(cc3);
        expect.add(cc4);
        expect.add(cc5);
        expect.add(cc6);
        expect.add(cc7);

        // Action
        Collection<CriminalCase> actual = criminalCaseRepo.findByAssigned_Username("anhnct1");

        // Assertion
        assertThat(actual, containsInAnyOrder(expect.toArray()));
    }
}