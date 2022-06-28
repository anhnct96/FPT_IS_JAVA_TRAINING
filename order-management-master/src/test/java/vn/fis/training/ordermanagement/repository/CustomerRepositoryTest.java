package vn.fis.training.ordermanagement.repository;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import vn.fis.training.ordermanagement.model.Customer;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Slf4j
public class CustomerRepositoryTest {
    @Autowired
    CustomerRepository customerRepository;

    @Test
    void findAll() {
        int expectedSize = 10;

        int actualSize = customerRepository.findAll().size();

        assertEquals(expectedSize, actualSize);
    }

    @Test
    void findByMobile_Existed() {
        String expectedMobile = "0911926888";

        String actualMobile = customerRepository.findByMobile("0911926888").get().getMobile();

        assertEquals(expectedMobile, actualMobile);
    }

    @Test
    void findByMobile_NonExisted() {
        String expectedMobile = "0123456789";

        Optional<Customer> customer = customerRepository.findByMobile(expectedMobile);

        assertEquals(customer, Optional.empty());
    }
}
