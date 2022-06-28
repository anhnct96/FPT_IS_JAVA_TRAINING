package vn.fis.training.ordermanagement.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.fis.training.ordermanagement.controller.globalexceptionhandler.notfoundexception.CustomerNotFoundException;
import vn.fis.training.ordermanagement.controller.globalexceptionhandler.otherexception.DuplicatedMobileException;
import vn.fis.training.ordermanagement.dto.CreateCustomerDTO;
import vn.fis.training.ordermanagement.dto.CustomerDTO;
import vn.fis.training.ordermanagement.model.Customer;
import vn.fis.training.ordermanagement.repository.CustomerRepository;
import vn.fis.training.ordermanagement.service.CustomerService;

@Service
@Slf4j
public class CustomerServiceImpl implements CustomerService {

    private CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<CustomerDTO> findAll(Pageable pageable) {
        log.info("Query all Customer. PageNumber:{}, PageSize: {}", pageable.getPageNumber(), pageable.getPageSize());
        Page<Customer> customers = customerRepository.findAll(pageable);
        return customers.map(CustomerDTO.Mapper::fromEntity);
    }

    @Override
    @Transactional(readOnly = true)
    public CustomerDTO findById(Long customerId) {
        log.info("Query Customer By Id = " + customerId);
        return CustomerDTO.Mapper.fromEntity(customerRepository.findById(customerId).orElseThrow(
                () -> {
                    throw new CustomerNotFoundException(customerId);
                }));
    }

    @Override
    @Transactional
    public CreateCustomerDTO create(CustomerDTO customerDTO) {
        log.info("Create new customer " + customerDTO);
        if (customerRepository.findByMobile(customerDTO.getMobile()).isPresent()) {
            throw new DuplicatedMobileException(customerDTO.getMobile());
        }
        Customer customer = CustomerDTO.Mapper.fromDTO(customerDTO);
        customerRepository.save(customer);
        return CreateCustomerDTO.Mapper.fromEntity(customer);
    }

    @Override
    @Transactional
    public CustomerDTO update(Long id, CustomerDTO customerDTO) {
        log.info("Update customer " + customerDTO);
        if (customerRepository.findByMobile(customerDTO.getMobile()).isPresent()) {
            throw new DuplicatedMobileException(customerDTO.getMobile());
        }
        if (customerRepository.findById(id).isEmpty()) {
            throw new CustomerNotFoundException(id);
        }

        Customer customer = new Customer();
        customer.setAddress(customerDTO.getAddress());
        customer.setMobile(customerDTO.getMobile());
        customer.setName(customerDTO.getName());
        customer.setId(id);
        customerRepository.save(customer);
        customerDTO.setId(id);
        return customerDTO;
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        log.info("Delete customer with id = " + id);
        if (!customerRepository.existsById(id)) {
            throw new CustomerNotFoundException(id);
        }
        customerRepository.deleteById(id);
    }
}
