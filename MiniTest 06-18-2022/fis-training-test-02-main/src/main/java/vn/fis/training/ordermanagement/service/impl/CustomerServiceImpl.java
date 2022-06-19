package vn.fis.training.ordermanagement.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.fis.training.ordermanagement.domain.Customer;
import vn.fis.training.ordermanagement.repository.CustomerRepository;
import vn.fis.training.ordermanagement.service.CustomerService;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;


    @Override
    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public Customer updateCustomer(Customer customer) {
        Customer oldCustomer = customerRepository.findById(customer.getId()).get();
        oldCustomer.setAddress(customer.getAddress());
        oldCustomer.setMobile(customer.getMobile());
        oldCustomer.setName(customer.getName());
        return customerRepository.save(oldCustomer);
    }

    @Override
    public void deleteCustomerById(Long customerId) {
        customerRepository.deleteById(customerId);
    }

    @Override
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    @Override
    public Customer findByMobileEquals(String mobileNumber) {
        Optional<Customer> customer = customerRepository.findByMobileEquals(mobileNumber);
        if (customer.isPresent()) {
            return customer.get();
        }
        else {
            return null;
        }
    }

    public Optional<Customer> findById(Long id) {
        return customerRepository.findById(id);
    }
}
