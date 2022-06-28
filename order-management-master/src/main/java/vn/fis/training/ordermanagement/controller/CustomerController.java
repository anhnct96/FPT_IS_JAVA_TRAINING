package vn.fis.training.ordermanagement.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.fis.training.ordermanagement.dto.CreateCustomerDTO;
import vn.fis.training.ordermanagement.dto.CustomerDTO;
import vn.fis.training.ordermanagement.service.CustomerService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/customer")
@Slf4j
public class CustomerController {

    private CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/{pageNumber}/{pageSize}")
    public Page<CustomerDTO> findAll(@PathVariable(name = "pageNumber") Integer pageNumber, @PathVariable(name = "pageSize") Integer pageSize) {
        log.info("Request All Customer. PageNumber: {}, PageSize: {}", pageNumber, pageSize);
        return customerService.findAll(PageRequest.of(pageNumber, pageSize));
    }

    @GetMapping("/{id}")
    public CustomerDTO findById(@PathVariable(name = "id") Long id) {
        log.info("Request Customer with id = " + id);
        return customerService.findById(id);
    }

    @PutMapping("/create/{pageNumber}/{pageSize}")
    public ResponseEntity<Page<CustomerDTO>> create(@RequestBody @Valid CustomerDTO customerDTO, @PathVariable Integer pageNumber, @PathVariable(name = "pageSize")Integer pageSize) {
        log.info("Assign a customer " + customerDTO);
        customerService.create(customerDTO);
        return ResponseEntity.ok(customerService.findAll(PageRequest.of(pageNumber, pageSize)));
    }

    @PostMapping("/{id}")
    public CustomerDTO update(@PathVariable(name = "id") Long id, @RequestBody CustomerDTO customerDTO) {
        log.info("Update a customer " + customerService.findById(id) + " to " + customerDTO);
        return customerService.update(id, customerDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable(name = "id") Long id) {
        log.info("Delete a customer with id = " + id);
        customerService.deleteById(id);
    }
}
