package fis.ra.criminalmanagementsystem.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DetectiveRepoTest {

    @Autowired
    DetectiveRepo detectiveRepo;
    @Test
    void findByUsername() {
        System.out.println(detectiveRepo.findByUsername("anhnd724"));
    }

    @Test
    void findByBadgeNumber() {
    }
}