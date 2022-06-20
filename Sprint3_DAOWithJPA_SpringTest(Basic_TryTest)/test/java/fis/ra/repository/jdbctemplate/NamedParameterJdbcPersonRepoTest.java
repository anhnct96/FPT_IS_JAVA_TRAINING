package com.fresher.repository.jdbctemplate;

import com.fresher.model.Person;
import com.fresher.repository.PersonRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

//@SpringJUnitConfig(classes = {TestDbConfig.class, JdbcConfig.class})
@SpringBootTest
class NamedParameterJdbcPersonRepoTest {
    private Logger logger = LoggerFactory.getLogger(JdbcPersonRepoTest.class);

    static final Long PERSON_ID = 1L;

    @Qualifier("jdbcNamedPersonRepo")
    @Autowired
    PersonRepo jdbcNamedPersonRepo;


    @BeforeEach
    void setUp(){
        assertNotNull(jdbcNamedPersonRepo);
    }

    @Test
    void testFindByIdPositive(){
        jdbcNamedPersonRepo.findById(PERSON_ID).ifPresentOrElse(
                p -> assertEquals("Sherlock", p.getFirstName()),
                Assertions:: fail
        );
    }

    @Test
    public void testUpdate(){
        int result  = jdbcNamedPersonRepo.updatePassword(1L, "newpass");
        assertEquals(1, result);
    }

    @Test
    void testFindByIdNegative(){
        assertThrows( EmptyResultDataAccessException.class, () -> jdbcNamedPersonRepo.findById(99L));
    }

    @Test
    void testCreatePerson(){
        //Long entityId, String username, String firstName, String lastName, String password)
        jdbcNamedPersonRepo.createPerson(3L, "chloe.decker", "Chloe", "Decker", "m0rn1ngstar");

        Optional<Person> personOpt = jdbcNamedPersonRepo.findByUsername("chloe.decker");
        personOpt.ifPresentOrElse(p -> assertNotNull(p.getId()),
                Assertions:: fail);
    }
}
