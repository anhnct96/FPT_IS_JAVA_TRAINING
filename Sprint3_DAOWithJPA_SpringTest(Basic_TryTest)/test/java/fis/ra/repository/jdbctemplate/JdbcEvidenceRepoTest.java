package com.fresher.repository.jdbctemplate;

import com.fresher.model.Evidence;
import com.fresher.repository.EvidenceRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class JdbcEvidenceRepoTest {

    @Autowired
    EvidenceRepo evidenceRepo;

    @Test
    void findById() {
        Optional<Evidence> opt = evidenceRepo.findById(1L);
        System.out.println(opt.get());
    }

    @Test
    void save() {
        Evidence evidence = new Evidence();
        evidence.setId(10L);
        evidence.setNumber("5");
        evidence.setArchived(false);
        evidence.setItemName("Item 1");
        evidence.setNotes("Notes 10");
        evidence.setVersion(1);
        evidenceRepo.save(evidence);

        Optional<Evidence> opt = evidenceRepo.findById(10L);
        System.out.println(opt.get());
    }
}