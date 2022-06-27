package fis.ra.criminalmanagementsystem.repository;

import fis.ra.criminalmanagementsystem.model.Evidence;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class EvidenceRepoTest {

    @Autowired
    EvidenceRepo evidenceRepo;

    Evidence e1, e2;

    @BeforeEach
    void init() {
        e1 = new Evidence();
        e1.setId(1L);
        e1.setNumber("110");

        e2 = new Evidence();
        e2.setId(8L);
        e2.setNumber("117");
    }

    @Test
    @Transactional(propagation= Propagation.REQUIRED, readOnly=true, noRollbackFor=Exception.class)
    void findByCriminalCase_Number() {
        // Arrangement
        Collection<Evidence> expect = new ArrayList<>();
        expect.add(e1);
        expect.add(e2);

        // Action
        Collection<Evidence> actual = evidenceRepo.findByCriminalCase_Number("110223697");

        // Assertion
        assertThat(actual, containsInAnyOrder(expect.toArray()));
    }
}