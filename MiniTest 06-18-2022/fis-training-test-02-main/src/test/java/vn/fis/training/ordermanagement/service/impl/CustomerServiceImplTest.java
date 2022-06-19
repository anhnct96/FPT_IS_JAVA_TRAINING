package vn.fis.training.ordermanagement.service.impl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import vn.fis.training.ordermanagement.domain.Customer;
import vn.fis.training.ordermanagement.service.CustomerService;

import javax.transaction.Transactional;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CustomerServiceImplTest {

    @Autowired
    CustomerService customerService;

    @Test
    @Transactional
    @Rollback
    void createCustomer() {
        // size = 3 in db
        int expectedSize = 4;
        Customer customer = new Customer(4L, "Tuan Anh", "0822196869", "nam dinh");

        customerService.createCustomer(customer);
        int actualSize = customerService.findAll().size();

        assertEquals(expectedSize, actualSize);
    }

    @Test
    @Transactional
    @Rollback
    void updateCustomer_Exist() {
        // Customer oldCustomer = new Customer (3L, "Nam", "0911928467", "TPHCM");
        Customer newCustomer = new Customer (3L, "Trang", "86324159", "da nang");

        customerService.updateCustomer(newCustomer);
        Customer updatedCustomer = customerService.findById(3L).get();

        assertEquals(newCustomer, updatedCustomer);
    }

    @Test
    @Transactional
    @Rollback
    void updateCustomer_NotExist() {
        // Customer oldCustomer = new Customer (3L, "Nam", "0911928467", "TPHCM");
        Customer newCustomer = new Customer (10L, "Trang", "86324159", "da nang");

        customerService.updateCustomer(newCustomer);
        Customer updatedCustomer = customerService.findById(10L).get();

        assertEquals(newCustomer, updatedCustomer);
    }


    @Test
    void findAll() {
        int expectedSize = 3;

        int actualSize = customerService.findAll().size();

        assertEquals(expectedSize, actualSize);
    }

    @Test
    void findByMobileEquals_Exist() {
        Customer expectedCustomer = new Customer(1L, "Thao", "0125416879", "nam dinh");

        Customer actualCustomer = customerService.findByMobileEquals("0125416879");

        assertEquals(expectedCustomer, actualCustomer);
    }

    @Test
    void findByMobileEquals_NotExist() {
        Customer expectedCustomer = null;

        Customer actualCustomer = customerService.findByMobileEquals("0123456789");

        assertNull(actualCustomer);
    }
}