package vn.fis.training.ordermanagement.repository;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Slf4j
public class OrderRepositoryTest {

    @Autowired
    OrderRepository orderRepository;

    @Test
    void findAll() {
        int expectedSize = 15;

        int actualSize = orderRepository.findAll().size();

        assertEquals(expectedSize, actualSize);
    }
}
