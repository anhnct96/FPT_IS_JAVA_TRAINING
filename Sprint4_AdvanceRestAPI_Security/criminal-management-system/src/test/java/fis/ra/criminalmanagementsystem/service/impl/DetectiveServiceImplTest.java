package fis.ra.criminalmanagementsystem.service.impl;

import fis.ra.criminalmanagementsystem.service.DetectiveService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
class DetectiveServiceImplTest {

    @Autowired
    DetectiveService detectiveService;

//    @Test
//    @Transactional(propagation= Propagation.REQUIRED, readOnly=true, noRollbackFor=Exception.class)
//    void getDetectiveSet() {
//        iDetectiveService.getDetectiveSet().stream().forEach(s-> System.out.println(s));
//    }

    @Test
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true, noRollbackFor = Exception.class)
    void getDetectiveById() {
        System.out.println(detectiveService.findDetectiveById(1L));
    }


    @Test
    void findByUserName() {
        System.out.println(detectiveService.findByUserName("anhnd724"));
    }

    @Test
    void loadUserByUsername() {
        System.out.println(detectiveService.loadUserByUsername("anhnd724").getUsername());
        System.out.println(detectiveService.loadUserByUsername("anhnd724").getPassword());
        System.out.println(detectiveService.loadUserByUsername("anhnd724").getAuthorities());
    }

    @Test
    void loadUserById() {
        System.out.println(detectiveService.loadUserById(1L).getUsername());
        System.out.println(detectiveService.loadUserById(1L).getPassword());
        System.out.println(detectiveService.loadUserById(1L).getAuthorities());
    }
}