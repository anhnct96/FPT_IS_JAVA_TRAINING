package vn.fis.training.ordermanagement.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;
import vn.fis.training.ordermanagement.controller.globalexceptionhandler.notfoundexception.CustomerNotFoundException;
import vn.fis.training.ordermanagement.controller.globalexceptionhandler.otherexception.DuplicatedMobileException;
import vn.fis.training.ordermanagement.dto.CreateCustomerDTO;
import vn.fis.training.ordermanagement.dto.CustomerDTO;
import vn.fis.training.ordermanagement.service.CustomerService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CustomerServiceImplTest {

    @Autowired
    CustomerService customerService;

    CustomerDTO customerDTO_Id_1;
    CustomerDTO customerDTO_New;
    CreateCustomerDTO createCustomerDTO_New;
    @BeforeEach
    void init() {
        customerDTO_Id_1 = CustomerDTO.builder()
                .id(1L)
                .name("Tran Phuong Thao")
                .address("18 Le Trong Tan, Ha Noi")
                .mobile("0912906996")
                .orders(List.of(1L,2L,12L))
                .build();

        customerDTO_New = CustomerDTO.builder()
                .id(100L)
                .name("Ngo Si Lien")
                .address("30A Tran Dinh Trong, Ca Mau")
                .mobile("0126987444")
                .orders(null)
                .build();

        createCustomerDTO_New = CreateCustomerDTO.builder()
                .id(11L)
                .name("Ngo Si Lien")
                .address("30A Tran Dinh Trong, Ca Mau")
                .mobile("0126987444")
                .build();
    }

    @Test
    void findAll() {
        int expectedSize = 10;

        int actualSize = customerService.findAll(PageRequest.of(0, 100)).getNumberOfElements();

        assertEquals(expectedSize, actualSize);
    }

    @Test
    void findById_Exist() {
        CustomerDTO expected = customerDTO_Id_1;

        CustomerDTO actual = customerService.findById(1L);

        assertEquals(expected, actual);
    }

    @Test
    void findById_NotExist() {
        CustomerNotFoundException expected = new CustomerNotFoundException(100L);

        Executable actual = new Executable() {
            @Override
            public void execute() throws Throwable {
                customerService.findById(100L);
            }
        };

        assertThrows(expected.getClass(), actual);

    }

    @Test
    @Transactional
    void create() {
        CreateCustomerDTO expected = createCustomerDTO_New;

        CreateCustomerDTO actual = customerService.create(customerDTO_New);

        assertEquals(expected, actual);
    }

    @Test
    @Transactional
    void update_Exist() {
        customerDTO_Id_1.setAddress("202A Phan Chu Trinh, Lao Cai");
        customerDTO_Id_1.setMobile("0163399457");
        customerDTO_Id_1.setName("Nguyen Thu Trang");

        CustomerDTO expected = customerDTO_Id_1;

        CustomerDTO actual = customerService.update(customerDTO_Id_1.getId(), customerDTO_Id_1);

        assertEquals(expected, actual);
    }

    @Test
    @Transactional
    void update_IdNotExist() {
        CustomerNotFoundException expected = new CustomerNotFoundException(customerDTO_New.getId());

        Executable actual = new Executable() {
            @Override
            public void execute() throws Throwable {
                customerService.update(customerDTO_New.getId(), customerDTO_New);
            }
        };

        assertThrows(expected.getClass(), actual);
    }

    @Test
    @Transactional
    void update_MobileDuplicated() {
        String existedMobileInDb = "0913290243";
        customerDTO_Id_1.setMobile(existedMobileInDb);
        DuplicatedMobileException expected = new DuplicatedMobileException(customerDTO_Id_1.getMobile());

        Executable actual = new Executable() {
            @Override
            public void execute() throws Throwable {
                customerService.update(customerDTO_Id_1.getId(), customerDTO_Id_1);
            }
        };

        assertThrows(expected.getClass(), actual);
    }

    @Test
    @Transactional
    void deleteById_Exist() {
        int expected = 9;

        customerService.deleteById(1L);
        int actual = customerService.findAll(PageRequest.of(0, 100)).getNumberOfElements();

        assertEquals(expected, actual);
    }

    @Test
    @Transactional
    void deleteById_NotExist() {
        CustomerNotFoundException expected = new CustomerNotFoundException(customerDTO_New.getId());

        Executable actual = new Executable() {
            @Override
            public void execute() throws Throwable {
                customerService.deleteById(customerDTO_New.getId());
            }
        };

        assertThrows(expected.getClass(), actual);
    }
}