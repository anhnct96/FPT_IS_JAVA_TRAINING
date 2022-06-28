package vn.fis.training.ordermanagement.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import vn.fis.training.ordermanagement.dto.CreateCustomerDTO;
import vn.fis.training.ordermanagement.dto.CustomerDTO;

public interface CustomerService {
    Page<CustomerDTO> findAll(Pageable pageable);

    CustomerDTO findById(Long customerId);

    CreateCustomerDTO create(CustomerDTO customerDTO);

    CustomerDTO update(Long id, CustomerDTO customerDTO);

    void deleteById(Long id);
}
