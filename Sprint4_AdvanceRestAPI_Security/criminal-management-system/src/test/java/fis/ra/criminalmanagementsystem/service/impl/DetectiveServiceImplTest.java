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
    DetectiveService DetectiveService;

//    @Test
//    @Transactional(propagation= Propagation.REQUIRED, readOnly=true, noRollbackFor=Exception.class)
//    void getDetectiveSet() {
//        iDetectiveService.getDetectiveSet().stream().forEach(s-> System.out.println(s));
//    }

    @Test
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true, noRollbackFor = Exception.class)
    void getDetectiveById() {
        System.out.println(DetectiveService.findDetectiveById(1L));
    }
}