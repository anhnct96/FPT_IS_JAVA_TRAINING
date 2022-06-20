package com.fresher.repository.jdbctemplate;

import com.fresher.model.Detective;
import com.fresher.repository.DetectiveRepo;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.Optional;

//@SpringJUnitConfig(classes = {TestDbConfig.class, JdbcConfig.class})
@SpringBootTest
class JdbcDetectiveRepoTest {
    private Logger logger = LoggerFactory.getLogger(JdbcDetectiveRepoTest.class);

    static final Long DETECTIVE_ID = 1L;

    @Autowired
    @Qualifier("extraJdbcDetectiveRepo")
    DetectiveRepo detectiveRepo;

    @Test
    void testFindByIdWithDetails(){
        Optional<Detective> detective = detectiveRepo.findByIdWithPersonDetails(DETECTIVE_ID);
//        detective.ifPresentOrElse(
//                d -> assertNotNull(d.getPerson()),
//                Assert::fail
//        );
        logger.info("Result: {}", detective);
    }

    @Test
    void findAll() {
        System.out.println(detectiveRepo.findAll());
    }
}